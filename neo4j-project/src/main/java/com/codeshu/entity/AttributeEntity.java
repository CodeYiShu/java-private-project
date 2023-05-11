package com.codeshu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author CodeShu
 * @date 2023/5/4 17:37
 */
@Node(labels = "Attribute")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeEntity {

	@Id
	private Long id;

	@Property("name")
	private String name;

	@Property("dataType")
	private String dataType;

	@Property("isMust")
	private Boolean isMust;

	@Property("isVisible")
	private Boolean isVisible;

	@Property("choose")
	private Integer choose;

	@Property("max")
	private Integer max;

	@Property("min")
	private Integer min;

	@Property("remark")
	private String remark;
}
