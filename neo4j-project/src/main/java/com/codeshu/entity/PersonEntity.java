package com.codeshu.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author CodeShu
 * @date 2023/5/4 17:32
 */
@Node("Person")
@Data
public class PersonEntity {
	@Id
	@GeneratedValue
	private Long id;

	@Property("name")
	private String name;

	@Property("born")
	private Integer born;

	public PersonEntity(Integer born, String name) {
		this.name = name;
		this.born = born;
	}
}
