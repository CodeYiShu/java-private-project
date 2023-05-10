package com.codeshu;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.codeshu.entity.AttributeEntity;
import com.codeshu.entity.BenTiEntity;
import com.codeshu.repository.BenTiRepository;
import com.codeshu.request.BenTiRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jClient;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
class YunShuoTest {

	@Autowired
	private BenTiRepository benTiRepository;

	@Resource
	private Neo4jClient neo4jClient;

	/**
	 * 直接用这个方法来插入和更新即可
	 */
	@Test
	public void testBatchUpdateAndInsert() {
		//当节点的ID为NULL时则表示新增节点，当节点的ID不为NULL时表示更新；当ID不为NULL但是库中没有此节点，也会自动创建
		List<BenTiRequest> requestList = getBenTiEntityHasId();

		//传入的这些本体节点
		List<Long> benTiIds = requestList.stream().map(BenTiRequest::getId).filter(Objects::nonNull).collect(Collectors.toList());
		//删除这些本体节点的属性节点
		benTiRepository.deleteAttribute(benTiIds);
		//删除这些本体节点的所有射出关系
		benTiRepository.deleteRelationShip(benTiIds);

		//为本体节点和属性节点生成 id 字段值
		generateIdToNode(requestList);
		//再去保存这些本体节点和她的属性节点
		List<BenTiEntity> benTiEntityList = BeanUtil.copyToList(requestList, BenTiEntity.class);
		benTiRepository.saveAll(benTiEntityList);

		//本体节点之间的关系
		List<String> cypherList = getCreateRelationShipCypher(requestList);
		for (String cypher : cypherList) {
			neo4jClient.query(cypher).run();
		}
	}

	/**
	 * 生成本体节点和本体节点之间的关系 cypher 语句
	 *
	 * @param requestList 本体节点集合
	 * @return cypher语句
	 */
	public List<String> getCreateRelationShipCypher(List<BenTiRequest> requestList) {
		//建立关系的固定语法
		final String str = "MATCH (a:BenTi), (b:BenTi) WHERE a.id = %s AND b.id = %s MERGE (a)-[:%s]->(b);";
		List<String> cypherList = new ArrayList<>();

		for (BenTiRequest request : requestList) {
			//获取当前本体节点的所有弧头本体节点
			List<BenTiRequest> endBenTiRequestList = request.getEndBenTiList();
			for (BenTiRequest endBenTiRequest : endBenTiRequestList) {
				//根据节点名称，将 id 字段值设置进弧头本体节点的 id 字段中
				Long id = requestList.stream().filter(entity -> entity.getName().equals(endBenTiRequest.getName()))
						.map(BenTiRequest::getId).filter(Objects::nonNull).findFirst().orElse(null);
				endBenTiRequest.setId(id);

				//参数填充入cypher中
				String format = String.format(str, request.getId(), endBenTiRequest.getId(), endBenTiRequest.getRelationshipType());
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
			if (Objects.isNull(request.getId())) {
				request.setId(snowflake.nextId());
			}
			List<AttributeEntity> attributeEntityList = request.getAttributeList();
			//为所有属性节点添加上 id
			for (AttributeEntity attributeEntity : attributeEntityList) {
				attributeEntity.setId(snowflake.nextId());
			}
		}
	}

	/**
	 * 模拟入参
	 *
	 * @return 参数
	 */
	public List<BenTiRequest> getBenTiEntityHasId() {
		BenTiRequest request0 = new BenTiRequest();
		request0.setName("危险点");
		//当前本体属性指向当前本体
		AttributeEntity attribute0 = new AttributeEntity(null, "危险点属性", "String", true, false, 0, 100, 0, "备注");
		request0.getAttributeList().add(attribute0);
		//当前本体指向其他本体
		BenTiRequest endBenTi0 = new BenTiRequest();
		endBenTi0.setRelationshipType("危险点");
		endBenTi0.setName("接入式电子");
		request0.getEndBenTiList().add(endBenTi0);

		BenTiRequest request1 = new BenTiRequest();
		request1.setId(1656212893378641920L);
		request1.setName("修改施工工具");
		//当前本体属性指向当前本体
		AttributeEntity attribute1 = new AttributeEntity(null, "修改属性", "String", true, false, 0, 100, 0, "备注");
		request1.getAttributeList().add(attribute1);

		BenTiRequest request2 = new BenTiRequest();
		request2.setId(1656212893378641923L);
		request2.setName("接入式电子");
		//当前本体属性指向当前本体
		AttributeEntity attribute3 = new AttributeEntity(null, "接入式电子属性1", "String", true, false, 0, 100, 0, "备注");
		AttributeEntity attribute4 = new AttributeEntity(null, "接入式电子属性2", "Integer", false, true, 0, 100, 0, "备注");
		request2.getAttributeList().add(attribute3);
		request2.getAttributeList().add(attribute4);
		//当前本体指向其他本体
		BenTiRequest endBenTi1 = new BenTiRequest();
		endBenTi1.setRelationshipType("施工工具");
		endBenTi1.setName("修改施工工具");
		request2.getEndBenTiList().add(endBenTi1);

		BenTiRequest request3 = new BenTiRequest();
		request3.setId(1656212893378641926L);
		request3.setName("电子计量装置");
		AttributeEntity attribute5 = new AttributeEntity(null, "电子计量装置属性1", "Integer", false, true, 0, 100, 0, "备注");
		request3.getAttributeList().add(attribute5);

		return Arrays.asList(request0,request1, request2, request3);
	}

	@Test
	public void testQuery() {
		List<BenTiEntity> benTiEntityList = benTiRepository.findAll();
		for (BenTiEntity benTiEntity : benTiEntityList) {
			System.out.println(benTiEntity);
		}
	}
}
