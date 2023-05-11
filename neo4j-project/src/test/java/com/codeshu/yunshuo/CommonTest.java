package com.codeshu.yunshuo;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.codeshu.entity.AttributeEntity;
import com.codeshu.request.BenTiRequest;

import java.util.Arrays;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/11 17:02
 */
public class CommonTest {
	/**
	 * 模拟入参
	 *
	 * @return 参数
	 */
	public static List<BenTiRequest> getBenTiEntity() {

		BenTiRequest request1 = new BenTiRequest();
		//request1.setId(1656131971312533504L);
		request1.setBenTiName("施工工具");
		//当前本体属性指向当前本体
		AttributeEntity attribute1 = new AttributeEntity(null, "施工工具属性1", "String", true, false, 0, 100, 0, "备注");
		AttributeEntity attribute2 = new AttributeEntity(null, "施工工具属性2", "Integer", false, true, 0, 100, 0, "备注");
		request1.getAttributeList().add(attribute1);
		request1.getAttributeList().add(attribute2);

		BenTiRequest request2 = new BenTiRequest();
		//request2.setId(1656131971312533507L);
		request2.setBenTiName("接入式电子");
		//当前本体属性指向当前本体
		AttributeEntity attribute3 = new AttributeEntity(null, "接入式电子属性1", "String", true, false, 0, 100, 0, "备注");
		AttributeEntity attribute4 = new AttributeEntity(null, "接入式电子属性2", "Integer", false, true, 0, 100, 0, "备注");
		request2.getAttributeList().add(attribute3);
		request2.getAttributeList().add(attribute4);
		//当前本体指向其他本体
		BenTiRequest.BenTiRelationship endBenTi1 = new BenTiRequest.BenTiRelationship();
		endBenTi1.setRelationshipType("施工工具");
		endBenTi1.setRelationShipDescription("关系描述");
		endBenTi1.setRelationShipRemark("关系备注");
		endBenTi1.setEndBenTiName("施工工具");
		request2.getEndBenTiList().add(endBenTi1);

		BenTiRequest request3 = new BenTiRequest();
		//request3.setId(1656131971312533510L);
		request3.setBenTiName("电子计量装置");
		//当前本体指向其他本体
		BenTiRequest.BenTiRelationship endBenTi2 = new BenTiRequest.BenTiRelationship();
		endBenTi2.setRelationshipType("分类");
		endBenTi2.setEndBenTiName("接入式电子");
		endBenTi2.setRelationShipDescription("关系描述");
		endBenTi2.setRelationShipRemark("关系备注");
		request3.getEndBenTiList().add(endBenTi2);

		request1.setModelName("模型名称1");
		request1.setModelRemark("模型备注1");
		request1.setProjectId(111111111111111112L);

		return Arrays.asList(request1, request2, request3);
	}


	/**
	 * 生成雪花算法的Id
	 *
	 * @return Id
	 */
	public static Long generateId() {
		Snowflake snowflake = IdUtil.getSnowflake();
		return snowflake.nextId();
	}
}
