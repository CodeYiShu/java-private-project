package com.codeshu.tree;

import lombok.Data;

import java.util.List;

/**
 * @author CodeShu
 * @date 2022/11/25 17:54
 */
@Data
public class TreeNode{
	//节点自己的ID
	private Long id;
	//节点数据
	private String nodeName;
	//父节点的ID
	private Long parentId;
	//子节点为true，顶层节点为false
	private Boolean isSonNode;
	//子节点对象
	private List<TreeNode> sonNode;
}
