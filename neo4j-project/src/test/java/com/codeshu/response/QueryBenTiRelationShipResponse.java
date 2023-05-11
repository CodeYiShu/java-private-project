package com.codeshu.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 本体节点关系的查询映射
 *
 * @author CodeShu
 * @date 2023/5/11 10:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryBenTiRelationShipResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 弧尾（起始节点）
	 */
	private Long startId;

	/**
	 * 关系类型
	 */
	private String relationshipType;

	/**
	 * 关系描述
	 */
	private String relationShipDescription;

	/**
	 * 关系备注
	 */
	private String relationShipRemark;

	/**
	 * 弧头（末尾节点）
	 */
	private Long endId;
}
