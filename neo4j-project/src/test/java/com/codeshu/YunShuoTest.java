package com.codeshu;

import com.codeshu.entity.AttributeEntity;
import com.codeshu.entity.BenTiEntity;
import com.codeshu.repository.AttributeRepository;
import com.codeshu.repository.BenTiRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

	@Test
	public void testBatchInsert() {
		List<BenTiEntity> benTiEntityList = getBenTiEntity();
		benTiRepository.saveAll(benTiEntityList);
	}

	public List<BenTiEntity> getBenTiEntity() {
		BenTiEntity entity1 = new BenTiEntity();
		entity1.setName("电子计量装置");
		AttributeEntity attributeEntity1 = new AttributeEntity(null, "术语介绍1", "String", true, false, 0, 100, 0, "备注");
		AttributeEntity attributeEntity2 = new AttributeEntity(null, "设计序号2", "Integer", false, true, 0, 100, 0, "备注");
		entity1.getAttributeEntityList().add(attributeEntity1);
		entity1.getAttributeEntityList().add(attributeEntity2);

		BenTiEntity entity2 = new BenTiEntity();
		entity2.setName("安全工具");
		AttributeEntity attributeEntity3 = new AttributeEntity(null, "术语介绍3", "String", true, false, 0, 100, 0, "备注");
		AttributeEntity attributeEntity4 = new AttributeEntity(null, "设计序号4", "Integer", false, true, 0, 100, 0, "备注");
		entity2.getAttributeEntityList().add(attributeEntity3);
		entity2.getAttributeEntityList().add(attributeEntity4);

		return Arrays.asList(entity1, entity2);
	}

	/**
	 * 直接用这个方法来插入和更新即可
	 */
	@Test
	public void testBatchUpdate() {
		List<BenTiEntity> benTiEntityList = getBenTiEntityHasId();

		//查询出库中所有这些本体
		List<Long> ids = benTiEntityList.stream().map(BenTiEntity::getId).filter(Objects::nonNull).collect(Collectors.toList());
		List<BenTiEntity> DbBenTiEntityList = benTiRepository.findAllById(ids);
		//获取这些本体的所有属性
		List<List<AttributeEntity>> attributeEntityLists = DbBenTiEntityList.stream().map(BenTiEntity::getAttributeEntityList).filter(Objects::nonNull).collect(Collectors.toList());
		List<Long> allAttributeIds = new ArrayList<>();
		for (List<AttributeEntity> attributeEntityList : attributeEntityLists) {
			List<Long> attributeIds = attributeEntityList.stream().map(AttributeEntity::getId).filter(Objects::nonNull).collect(Collectors.toList());
			allAttributeIds.addAll(attributeIds);
		}
		//删除这些本体的属性
		attributeRepository.deleteAllById(allAttributeIds);

		//再去保存这些本体的属性
		benTiRepository.saveAll(benTiEntityList);

	}

	public List<BenTiEntity> getBenTiEntityHasId() {
		//指定ID进行更新
		BenTiEntity entity1 = new BenTiEntity();
		entity1.setId(4L);
		entity1.setName("电子计量装置xx");
		AttributeEntity attributeEntity1 = new AttributeEntity(null, "术语介绍1", "String", true, false, 0, 100, 0, "备注");
		AttributeEntity attributeEntity2 = new AttributeEntity(null, "设计序号2", "Integer", false, true, 0, 100, 0, "备注");
		entity1.getAttributeEntityList().add(attributeEntity1);
		entity1.getAttributeEntityList().add(attributeEntity2);

		BenTiEntity entity2 = new BenTiEntity();
		entity2.setId(16L);
		entity2.setName("安全工具xx");
		AttributeEntity attributeEntity3 = new AttributeEntity(null, "术语介绍3", "String", true, false, 0, 100, 0, "备注");
		AttributeEntity attributeEntity4 = new AttributeEntity(null, "设计序号4", "Integer", false, true, 0, 100, 0, "备注");
		entity2.getAttributeEntityList().add(attributeEntity3);
		entity2.getAttributeEntityList().add(attributeEntity4);

		return Arrays.asList(entity1, entity2);
	}

	@Test
	public void testCreateRelationShip() {

	}
}
