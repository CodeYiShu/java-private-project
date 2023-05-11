package com.codeshu.response;

import com.codeshu.entity.AttributeEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/11 11:45
 */
@Data
public class GetAllResponse {
	/**
	 * 本体节点id
	 */
	private Long benTiId;

	/**
	 * 本体节点名称
	 */
	private String benTiName;

	/**
	 * 本体节点的所有属性节点
	 */
	private List<AttributeEntity> attributeList = new ArrayList<>();

	/**
	 * 本体节点的所有弧头节点（所射出的本体节点）
	 */
	private List<BenTiRelationship> endBenTiList = new ArrayList<>();

	/**
	 * 本体节点之间的关系定义
	 */
	@Data
	public static class BenTiRelationship {
		/**
		 * 本体节点id
		 */
		private Long endBenTiId;

		/**
		 * 本体节点名字
		 */
		private String endBenTiName;

		/**
		 * 本体节点之间的关系类型
		 */
		private String relationshipType;

		/**
		 * 本体节点之间的关系描述
		 */
		private String relationShipDescription;

		/**
		 * 本体节点之间的关系备注
		 */
		private String relationShipRemark;
	}
}
