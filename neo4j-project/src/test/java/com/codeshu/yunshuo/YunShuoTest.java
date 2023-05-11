package com.codeshu.yunshuo;

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

@SpringBootTest
class YunShuoTest {

	@Autowired
	private BenTiRepository benTiRepository;

	@Resource
	private Neo4jClient neo4jClient;

	@Autowired
	private ModelRepository modelRepository;

	/**
	 * 直接用这个方法来插入和更新即可
	 */
	@Test
	public void testBatchUpdateAndInsert() {
		//当节点的ID为NULL时则表示新增节点，当节点的ID不为NULL时表示更新；当ID不为NULL但是库中没有此节点，也会自动创建
		List<BenTiRequest> requestList = CommonTest.getBenTiEntity();

		//传入的这些本体节点
		List<Long> benTiIds = requestList.stream().map(BenTiRequest::getBenTiId).filter(Objects::nonNull).collect(Collectors.toList());
		//删除这些本体节点的属性节点
		benTiRepository.deleteAttribute(benTiIds);
		//删除这些本体节点的所有射出关系
		benTiRepository.deleteRelationShip(benTiIds);

		//为本体节点和属性节点生成 id 字段值
		generateIdToNode(requestList);

		//再去保存这些本体节点和她的属性节点
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
	 * 为节点生成自定义 id 字段值
	 *
	 * @param requestList 本体节点集合（包含其属性节点）
	 */
	public void generateIdToNode(List<BenTiRequest> requestList) {
		for (BenTiRequest request : requestList) {
			//为没有 id 的本体节点添加上 id
			if (Objects.isNull(request.getBenTiId())) {
				request.setBenTiId(CommonTest.generateId());
			}
			List<AttributeEntity> attributeEntityList = request.getAttributeList();
			//为所有属性节点添加上 id
			for (AttributeEntity attributeEntity : attributeEntityList) {
				attributeEntity.setId(CommonTest.generateId());
			}
		}
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
			List<Long> benTiIds = benTiEntityList.stream().map(BenTiEntity::getId).filter(Objects::nonNull).collect(Collectors.toList());

			//查询本体节点之间的关系
			List<QueryBenTiRelationShipResponse> relationShipResponseList = benTiRepository.selectBenTiRelationShip(benTiIds);
			//根据弧尾节点（起始节点）分组
			Map<Long, List<QueryBenTiRelationShipResponse>> responseMap = relationShipResponseList.stream().collect(Collectors.groupingBy(QueryBenTiRelationShipResponse::getStartId));

			for (Map.Entry<Long, List<QueryBenTiRelationShipResponse>> entry : responseMap.entrySet()) {
				Long startId = entry.getKey();
				List<QueryBenTiRelationShipResponse> relationShipResponses = entry.getValue();

				GetAllResponse getAllResponse = new GetAllResponse();
				getAllResponse.setBenTiId(startId);
				//弧尾节点的信息
				BenTiEntity startBenTiEntity = benTiEntityList.stream().filter(entity -> entity.getId().equals(startId)).findFirst().orElse(null);
				assert startBenTiEntity != null;
				getAllResponse.setBenTiName(startBenTiEntity.getName());
				getAllResponse.setAttributeList(startBenTiEntity.getAttributeList());

				for (QueryBenTiRelationShipResponse relationShipResponse : relationShipResponses) {
					GetAllResponse.BenTiRelationship relationship = new GetAllResponse.BenTiRelationship();
					//弧头节点的信息
					BenTiEntity endBenTiEntity = benTiEntityList.stream().filter(entity -> entity.getId().equals(relationShipResponse.getEndId()))
							.findFirst().orElse(null);
					if (Objects.nonNull(endBenTiEntity)) {
						relationship.setEndBenTiId(relationShipResponse.getEndId());
						relationship.setEndBenTiName(endBenTiEntity.getName());
						relationship.setRelationshipType(relationShipResponse.getRelationshipType());
						relationship.setRelationShipDescription(relationShipResponse.getRelationShipDescription());
						relationship.setRelationShipRemark(relationShipResponse.getRelationShipRemark());
						getAllResponse.getEndBenTiList().add(relationship);
					}
				}
				getAllResponseList.add(getAllResponse);
			}

			System.out.println(JSONUtil.parseArray(getAllResponseList));
		}
	}
}
