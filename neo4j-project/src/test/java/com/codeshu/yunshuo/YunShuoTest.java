package com.codeshu.yunshuo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
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
import com.codeshu.yunshuo.CommonTest;
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
			benTiEntity.setAttributeList(request.getAttributeList());
			benTiEntityList.add(benTiEntity);
		}
		benTiRepository.saveAll(benTiEntityList);

		//本体节点之间的关系
		List<String> cypherList = getCreateRelationShipCypher(requestList);
		for (String cypher : cypherList) {
			neo4jClient.query(cypher).run();
		}

		//让模型节点关联到所有本体节点
		createModelRelationShip(benTiEntityList, requestList.get(0));
	}

	/**
	 * 生成本体节点和本体节点之间的关系 cypher 语句
	 *
	 * @param requestList 本体节点集合
	 * @return cypher语句
	 */
	public List<String> getCreateRelationShipCypher(List<BenTiRequest> requestList) {
		//建立关系的固定语法
		final String str = "MATCH (a:BenTi), (b:BenTi) WHERE a.id = %s AND b.id = %s MERGE (a)-[:%s {description:'%s',remark:'%s'}]->(b);";
		List<String> cypherList = new ArrayList<>();

		for (BenTiRequest request : requestList) {
			//获取当前本体节点的所有弧头本体节点
			List<BenTiRequest.BenTiRelationship> endBenTiRequestList = request.getEndBenTiList();
			for (BenTiRequest.BenTiRelationship endBenTiRequest : endBenTiRequestList) {
				//根据节点名称，将 id 字段值设置进弧头本体节点的 id 字段中
				Long id = requestList.stream().filter(entity -> entity.getBenTiName().equals(endBenTiRequest.getEndBenTiName()))
						.map(BenTiRequest::getBenTiId).filter(Objects::nonNull).findFirst().orElse(null);
				endBenTiRequest.setEndBenTiId(id);

				//参数填充入cypher中
				String format = String.format(str, request.getBenTiId(), endBenTiRequest.getEndBenTiId(), endBenTiRequest.getRelationshipType(), endBenTiRequest.getRelationShipDescription(), endBenTiRequest.getRelationShipRemark());
				cypherList.add(format);
			}
		}
		return cypherList;
	}

	/**
	 * 生成自定义 id 字段值
	 *
	 * @param requestList 本体节点集合（包含其属性节点）
	 */
	public void generateIdToNode(List<BenTiRequest> requestList) {
		Snowflake snowflake = IdUtil.getSnowflake();

		for (BenTiRequest request : requestList) {
			//为没有 id 的本体节点添加上 id
			if (Objects.isNull(request.getBenTiId())) {
				request.setBenTiId(snowflake.nextId());
			}
			List<AttributeEntity> attributeEntityList = request.getAttributeList();
			//为所有属性节点添加上 id
			for (AttributeEntity attributeEntity : attributeEntityList) {
				attributeEntity.setId(snowflake.nextId());
			}
		}
	}

	public void createModelRelationShip(List<BenTiEntity> benTiEntityList, BenTiRequest request) {
		Model model = new Model();
		Snowflake snowflake = IdUtil.getSnowflake();
		model.setId(snowflake.nextId());
		model.setName(request.getModelName());
		model.setRemark(request.getModelRemark());
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
		Optional<Model> modelOptional = modelRepository.findById(1656578388736954368L);
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
				BenTiEntity startBenTiEntity = benTiEntityList.stream().filter(entity -> entity.getId().equals(startId))
						.findFirst().orElse(null);
				assert startBenTiEntity != null;
				getAllResponse.setBenTiName(startBenTiEntity.getName());
				getAllResponse.setAttributeList(startBenTiEntity.getAttributeList());

				for (QueryBenTiRelationShipResponse relationShipResponse : relationShipResponses) {
					GetAllResponse.BenTiRelationship relationship = new GetAllResponse.BenTiRelationship();
					//弧头节点的信息
					BenTiEntity endEntity = benTiEntityList.stream().filter(entity -> entity.getId().equals(relationShipResponse.getEndId()))
							.findFirst().orElse(null);

					relationship.setEndBenTiId(relationShipResponse.getEndId());
					relationship.setEndBenTiName(Objects.nonNull(endEntity) ? endEntity.getName() : null);
					relationship.setRelationshipType(relationShipResponse.getRelationshipType());
					relationship.setRelationShipDescription(relationShipResponse.getRelationShipDescription());
					relationship.setRelationShipRemark(relationShipResponse.getRelationShipRemark());
					getAllResponse.getEndBenTiList().add(relationship);
				}
				getAllResponseList.add(getAllResponse);
			}

			System.out.println(JSONUtil.parseArray(getAllResponseList));
		}
	}
}
