package com.codeshu.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author CodeShu
 * @date 2023/5/11 10:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long startId;

	private String type;

	private String description;

	private String remark;

	private Long endId;
}
