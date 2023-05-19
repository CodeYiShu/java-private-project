package com.codeshu.update;

import com.codeshu.update.entity.MyNodeEntity;
import com.codeshu.update.entity.OtherNodeEntity;
import com.codeshu.update.repository.MyNodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author CodeShu
 * @date 2023/5/19 10:45
 */
@SpringBootTest
public class Neo4jUpdateTest {
	@Autowired
	private MyNodeRepository myNodeRepository;

	@Test
	public void testInsert() {
		MyNodeEntity myNode = new MyNodeEntity();
		myNode.setId(1L);
		myNode.setName("我的节点");
		myNode.setAge(12);
		OtherNodeEntity o1 = new OtherNodeEntity(111L, "其他节点1");
		OtherNodeEntity o2 = new OtherNodeEntity(222L, "其他节点2");
		myNode.getOtherNodeList().add(o1);
		myNode.getOtherNodeList().add(o2);
		myNodeRepository.save(myNode);
	}

	@Test
	public void testUpdate1() {
		//更新基本属性的属性值

		MyNodeEntity myNode = new MyNodeEntity();
		myNode.setId(1L);
		myNode.setName("我的节点666");
		myNode.setAge(13);
		myNodeRepository.save(myNode);
	}

	@Test
	public void testUpdate2() {
		//更新基本属性为null

		MyNodeEntity myNode = new MyNodeEntity();
		myNode.setId(1L);
		//显式指定为null
		myNode.setName(null);
		//不指定默认为null（包装对象）
		//myNode.setAge(null);
		myNodeRepository.save(myNode);
	}

	@Test
	public void testUpdate3() {
		//更新关系

		MyNodeEntity myNode = new MyNodeEntity();
		myNode.setId(1L);
		myNode.setName("我的节点");
		myNode.setAge(12);
		//删除旧关系
		myNodeRepository.deleteRelationShipAndOtherNode(myNode.getId());
		//增加新关系
		OtherNodeEntity o1 = new OtherNodeEntity(333L, "其他节点3");
		OtherNodeEntity o2 = new OtherNodeEntity(444L, "其他节点4");
		myNode.getOtherNodeList().add(o1);
		myNode.getOtherNodeList().add(o2);
		;
		myNodeRepository.save(myNode);
	}

}
