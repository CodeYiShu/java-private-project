package com.codeshu.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/4 17:37
 */
@Node(labels = "Movie")
@Data
public class MovieEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Property("title")
	private final String title;

	@Property("tagline")
	private final String description;

	public MovieEntity(String title, String description) {
		this.title = title;
		this.description = description;
	}


	// 定义一个关系（参演）
	@Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
	private List<PersonEntity> actors = new ArrayList<>();

	// 定义一个关系（导演）
	@Relationship(type = "DIRECTED", direction = Relationship.Direction.INCOMING)
	private PersonEntity director;

}
