package com.codeshu.yunshuo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.codeshu.entity.AttributeEntity;
import com.codeshu.entity.BenTiEntity;
import com.codeshu.entity.Model;
import com.codeshu.repository.BenTiRepository;
import com.codeshu.repository.ModelRepository;
import com.codeshu.request.BenTiRequest;
import com.codeshu.response.GetAllResponse;
import com.codeshu.response.QueryBenTiRelationShipResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jClient;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 不考虑更新的情况，因为每一次编辑都是生成新的版本，不会动旧数据
 */
@SpringBootTest
class YunShuoNoUpdateTest {

	@Autowired
	private BenTiRepository benTiRepository;

	@Resource
	private Neo4jClient neo4jClient;

	@Autowired
	private ModelRepository modelRepository;

	@Test
	public void testBatchInsert() {
		List<BenTiRequest> requestList = CommonTest.getBenTiEntity();

		//为所有本体节点及其内部的属性节点添加上自定义 id 字段值
		for (BenTiRequest request : requestList) {
			request.setBenTiId(CommonTest.generateId());
			List<AttributeEntity> attributeEntityList = request.getAttributeList();
			for (AttributeEntity attributeEntity : attributeEntityList) {
				attributeEntity.setId(CommonTest.generateId());
			}
		}

		//保存这些本体节点和她的属性节点
		List<BenTiEntity> benTiEntityList = new ArrayList<>();
		for (BenTiRequest request : requestList) {
			BenTiEntity benTiEntity = new BenTiEntity();
			benTiEntity.setId(request.getBenTiId());
			benTiEntity.setName(request.getBenTiName());
			//让属性节点指向本体节点
			benTiEntity.setAttributeList(request.getAttributeList());
			benTiEntityList.add(benTiEntity);
		}
		benTiRepository.saveAll(benTiEntityList);

		//构建本体节点和本体节点之间的关系
		createBenTiRelation(requestList);

		//构建模型和本体节点之间的关系
		createModelRelationShip(benTiEntityList, requestList.get(0));
	}

	/**
	 * 构建本体节点和本体节点之间的关系
	 *
	 * @param requestList 本体节点集合
	 */
	public void createBenTiRelation(List<BenTiRequest> requestList) {
		//建立关系的固定语法
		final String str = "MATCH (a:BenTi), (b:BenTi) WHERE a.id = %s AND b.id = %s MERGE (a)-[:%s {description:'%s',remark:'%s'}]->(b);";

		for (BenTiRequest request : requestList) {
			//获取当前本体节点的所有弧头本体节点
			List<BenTiRequest.BenTiRelationship> endBenTiRequestList = request.getEndBenTiList();

			for (BenTiRequest.BenTiRelationship endBenTiRequest : endBenTiRequestList) {
				//根据节点名称，将 id 字段值设置进弧头本体节点的 id 字段中
				Long id = requestList.stream().filter(entity -> entity.getBenTiName().equals(endBenTiRequest.getEndBenTiName()))
						.map(BenTiRequest::getBenTiId).filter(Objects::nonNull).findFirst().orElse(null);
				endBenTiRequest.setEndBenTiId(id);

				//参数填充入cypher中
				String formatCypher = String.format(str, request.getBenTiId(), endBenTiRequest.getEndBenTiId(), endBenTiRequest.getRelationshipType(), endBenTiRequest.getRelationShipDescription(), endBenTiRequest.getRelationShipRemark());
				//执行cypher
				neo4jClient.query(formatCypher).run();
			}
		}
	}

	/**
	 * 构建模型和本体节点之间的关系
	 *
	 * @param benTiEntityList 本体节点
	 * @param request         请求参数
	 */
	public void createModelRelationShip(List<BenTiEntity> benTiEntityList, BenTiRequest request) {
		Model model = new Model();
		model.setId(CommonTest.generateId());
		model.setName(request.getModelName());
		model.setRemark(request.getModelRemark());
		//模型节点指向当前所有的本体节点
		model.setBenTiEntityList(benTiEntityList);
		model.setProjectId(request.getProjectId());
		model.setCreateDate(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

		//项目下所有模型中，最新模型的版本
		String lastVersion = modelRepository.selectLastVersionByProjectId(request.getProjectId());
		if (StrUtil.isBlank(lastVersion)) {
			//如果没有则说明此模型是项目的第一个模型
			model.setVersion("1.0");
		} else {
			//版本号加1
			String newVersion = String.format("%.1f", Double.parseDouble(lastVersion) + 1);
			model.setVersion(newVersion);
		}
		modelRepository.save(model);
	}

	/**
	 * 查询
	 */
	@Test
	public void testQuery() {
		List<GetAllResponse> getAllResponseList = new ArrayList<>();

		//查询模型下的所有本体（包含其属性）
		Optional<Model> modelOptional = modelRepository.findById(1656597025371742208L);
		Model model = modelOptional.orElse(null);

		if (Objects.nonNull(model)) {
			List<BenTiEntity> benTiEntityList = model.getBenTiEntityList();
			//查询本体节点之间的关系
			List<Long> benTiIds = benTiEntityList.stream().map(BenTiEntity::getId).filter(Objects::nonNull).collect(Collectors.toList());
			List<QueryBenTiRelationShipResponse> relationShipResponseList = benTiRepository.selectBenTiRelationShip(benTiIds);
			//根据弧尾节点（起始节点）分组
			Map<Long, List<QueryBenTiRelationShipResponse>> responseMap = relationShipResponseList.stream().collect(Collectors.groupingBy(QueryBenTiRelationShipResponse::getStartId));

			for (BenTiEntity benTiEntity : benTiEntityList) {
				//一个本体节点一个response
				GetAllResponse getAllResponse = new GetAllResponse();

				//当前本体节点的基本信息和属性节点信息
				getAllResponse.setBenTiId(benTiEntity.getId());
				getAllResponse.setBenTiName(benTiEntity.getName());
				getAllResponse.setAttributeList(benTiEntity.getAttributeList());

				//当前本体节点的多个弧头节点的信息
				List<QueryBenTiRelationShipResponse> relationShipResponses = responseMap.get(benTiEntity.getId());
				if (CollUtil.isNotEmpty(relationShipResponses)) {
					for (QueryBenTiRelationShipResponse relationShipResponse : relationShipResponses) {
						GetAllResponse.BenTiRelationship relationship = new GetAllResponse.BenTiRelationship();
						BenTiEntity endBenTiEntity = benTiEntityList.stream()
								.filter(entity -> entity.getId().equals(relationShipResponse.getEndId())).findFirst().orElse(null);
						if (Objects.nonNull(endBenTiEntity)) {
							relationship.setEndBenTiId(relationShipResponse.getEndId());
							relationship.setEndBenTiName(endBenTiEntity.getName());
							relationship.setRelationshipType(relationShipResponse.getRelationshipType());
							relationship.setRelationShipDescription(relationShipResponse.getRelationShipDescription());
							relationship.setRelationShipRemark(relationShipResponse.getRelationShipRemark());
							getAllResponse.getEndBenTiList().add(relationship);
						}
					}
				}
				getAllResponseList.add(getAllResponse);
			}

			System.out.println(JSONUtil.parseArray(getAllResponseList));
		}
	}
}
