package com.codeshu;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.codeshu.entity.AttributeEntity;
import com.codeshu.entity.BenTiEntity;
import com.codeshu.repository.AttributeRepository;
import com.codeshu.repository.BenTiRepository;
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
	private AttributeRepository attributeRepository;

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
		List<BenTiEntity> benTiEntityList = getBenTiEntityHasId();

		//查询出库中所有这些本体节点
		List<Long> ids = benTiEntityList.stream().map(BenTiEntity::getId).filter(Objects::nonNull).collect(Collectors.toList());
		List<BenTiEntity> DbBenTiEntityList = benTiRepository.findAllById(ids);
		//获取这些本体节点的所有属性节点
		List<List<AttributeEntity>> attributeEntityLists = DbBenTiEntityList.stream().map(BenTiEntity::getAttributeEntityList)
				.filter(Objects::nonNull).collect(Collectors.toList());
		List<Long> allAttributeIds = new ArrayList<>();
		for (List<AttributeEntity> attributeEntityList : attributeEntityLists) {
			List<Long> attributeIds = attributeEntityList.stream().map(AttributeEntity::getId).filter(Objects::nonNull).collect(Collectors.toList());
			allAttributeIds.addAll(attributeIds);
		}
		//删除这些本体节点的属性节点
		attributeRepository.deleteAllById(allAttributeIds);

		//为本体节点和属性节点生成 id 字段值
		generateIdToNode(benTiEntityList);
		//再去保存这些本体节点和她的属性节点
		benTiRepository.saveAll(benTiEntityList);

		//本体节点之间的关系
		List<String> cypherList = getCreateRelationShipCypher(benTiEntityList);
		for (String cypher : cypherList) {
			neo4jClient.query(cypher).run();
		}
	}

	/**
	 * 生成本体节点和本体节点之间的关系 cypher 语句
	 *
	 * @param benTiEntityList 本体节点集合
	 * @return cypher语句
	 */
	public List<String> getCreateRelationShipCypher(List<BenTiEntity> benTiEntityList) {
		//建立关系的固定语法
		final String str = "MATCH (a:BenTi), (b:BenTi) WHERE a.id = %s AND b.id = %s MERGE (a)-[:%s]->(b);";
		List<String> cypherList = new ArrayList<>();

		for (BenTiEntity benTiEntity : benTiEntityList) {
			//获取当前本体节点的所有弧头本体节点
			List<BenTiEntity> endBenTiEntityList = benTiEntity.getEndBenTiEntityList();
			for (BenTiEntity endBenTiEntity : endBenTiEntityList) {
				//根据节点名称，将 id 字段值设置进弧头本体节点的 id 字段中
				Long id = benTiEntityList.stream().filter(entity -> entity.getName().equals(endBenTiEntity.getName()))
						.map(BenTiEntity::getId).filter(Objects::nonNull).findFirst().orElse(null);
				endBenTiEntity.setId(id);

				//参数填充入cypher中
				String format = String.format(str, benTiEntity.getId(), endBenTiEntity.getId(), endBenTiEntity.getRelationshipType());
				cypherList.add(format);
			}
		}
		return cypherList;
	}

	/**
	 * 生成自定义 id 字段值
	 *
	 * @param benTiEntityList 本体节点集合（包含其属性节点）
	 */
	public void generateIdToNode(List<BenTiEntity> benTiEntityList) {
		Snowflake snowflake = IdUtil.getSnowflake();

		for (BenTiEntity benTiEntity : benTiEntityList) {
			//为没有 id 的本体节点添加上 id
			if (Objects.isNull(benTiEntity.getId())) {
				benTiEntity.setId(snowflake.nextId());
			}
			List<AttributeEntity> attributeEntityList = benTiEntity.getAttributeEntityList();
			//为所有属性节点添加上 id
			for (AttributeEntity attributeEntity : attributeEntityList) {
				attributeEntity.setId(snowflake.nextId());
			}
		}
	}

	public List<BenTiEntity> getBenTiEntityHasId() {
		BenTiEntity entity1 = new BenTiEntity();
		//entity1.setId(1656131971312533504L);
		entity1.setName("施工工具");
		//当前本体属性指向当前本体
		AttributeEntity attributeEntity1 = new AttributeEntity(null, "施工工具属性1", "String", true, false, 0, 100, 0, "备注");
		AttributeEntity attributeEntity2 = new AttributeEntity(null, "施工工具属性2", "Integer", false, true, 0, 100, 0, "备注");
		entity1.getAttributeEntityList().add(attributeEntity1);
		entity1.getAttributeEntityList().add(attributeEntity2);

		BenTiEntity entity2 = new BenTiEntity();
		//entity2.setId(1656131971312533507L);
		entity2.setName("接入式电子");
		//当前本体属性指向当前本体
		AttributeEntity attributeEntity3 = new AttributeEntity(null, "接入式电子属性1", "String", true, false, 0, 100, 0, "备注");
		AttributeEntity attributeEntity4 = new AttributeEntity(null, "接入式电子属性2", "Integer", false, true, 0, 100, 0, "备注");
		entity2.getAttributeEntityList().add(attributeEntity3);
		entity2.getAttributeEntityList().add(attributeEntity4);
		//当前本体指向其他本体
		BenTiEntity endBenTi1 = new BenTiEntity();
		endBenTi1.setRelationshipType("施工工具");
		endBenTi1.setName("施工工具");
		entity2.getEndBenTiEntityList().add(endBenTi1);

		BenTiEntity entity3 = new BenTiEntity();
		//entity3.setId(1656131971312533510L);
		entity3.setName("电子计量装置");
		//当前本体指向其他本体
		BenTiEntity endBenTi2 = new BenTiEntity();
		endBenTi2.setRelationshipType("分类");
		endBenTi2.setName("接入式电子");
		entity3.getEndBenTiEntityList().add(endBenTi2);

		return Arrays.asList(entity1, entity2, entity3);
	}

	@Test
	public void testQuery(){
		List<BenTiEntity> benTiEntityList = benTiRepository.findAll();
		System.out.println(JSONUtil.parseArray(benTiEntityList));
	}
}
