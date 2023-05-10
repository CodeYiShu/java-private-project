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
	private Long id;

	/**
	 * 本体名称
	 */
	private String name;

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

	/**
	 * 多个属性作为弧尾，指向当前本体
	 */
	private List<AttributeEntity> attributeList = new ArrayList<>();

	/**
	 * 当前节点作为弧尾，指向其他本体节点
	 */
	private List<BenTiRequest> endBenTiList = new ArrayList<>();
}
