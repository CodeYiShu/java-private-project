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
public class GetResponse {
	private Long benTiId;

	private String benTiName;

	private List<AttributeEntity> attributeList = new ArrayList<>();

	private List<BenTiRelationship> endBenTiList = new ArrayList<>();

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
