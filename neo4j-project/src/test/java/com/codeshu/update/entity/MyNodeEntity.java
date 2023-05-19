package com.codeshu.update.entity;

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
 * @date 2023/5/19 10:40
 */
@Node(labels = "MyNode")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyNodeEntity {
	@Id
	private Long id;

	@Property("name")
	private String name;

	@Property("age")
	private Integer age;

	@Relationship(type = "MANAGE", direction = Relationship.Direction.OUTGOING)
	private List<OtherNodeEntity> otherNodeList = new ArrayList<>();
}
