package com.codeshu.update.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author CodeShu
 * @date 2023/5/19 10:40
 */
@Node(labels = "OtherNode")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherNodeEntity {
	@Id
	private Long id;

	@Property("name")
	private String name;
}
