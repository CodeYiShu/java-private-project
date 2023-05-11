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
 * @date 2023/5/11 14:21
 */
@Node(labels = "Model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model {
	@Id
	private Long id;

	@Property("name")
	private String name;

	@Property("version")
	private String version;

	/**
	 * 模型节点指向多个本体节点
	 */
	@Relationship(type = "MANAGE", direction = Relationship.Direction.OUTGOING)
	private List<BenTiEntity> benTiEntityList = new ArrayList<>();
}
