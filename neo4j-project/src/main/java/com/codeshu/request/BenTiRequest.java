package com.codeshu.request;

import com.codeshu.entity.AttributeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/9 15:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenTiRequest {

	/**
	 * 本体节点id（更新时需携带）
	 */
	private Long benTiId;

	/**
	 * 本体名称
	 */
	private String benTiName;

	/**
	 * 旧模型id
	 */
	private Long oldModelId;

	/**
	 * 模型名称
	 */
	private String modelName;


	/**
	 * 多个属性作为弧尾，指向当前本体
	 */
	private List<AttributeEntity> attributeList = new ArrayList<>();

	/**
	 * 当前节点作为弧尾，指向其他本体节点
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
