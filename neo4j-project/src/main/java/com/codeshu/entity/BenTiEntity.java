package com.codeshu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/9 15:14
 */
@Node(labels = "BenTi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenTiEntity {
	/**
	 * 自定义id
	 */
	@Id
	private Long id;

	/**
	 * 本体名称
	 */
	@Property("name")
	private String name;


	/**
	 * 多个属性作为弧尾，指向当前本体
	 */
	@Relationship(type = "ATTRIBUTE_IN", direction = Relationship.Direction.INCOMING)
	private List<AttributeEntity> attributeList = new ArrayList<>();
}
