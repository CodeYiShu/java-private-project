package com.codeshu.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author CodeShu
 * @date 2022/11/12 0:10
 */
public class TreeUtils {
	public static void main(String[] args) {
		//ArrayList<TreeNode> treeList = getTreeList();
		//System.out.println(treeList);
//		List<TreeNode> allTopNode = getAllTopNode(treeList);
//		System.out.println(allTopNode);
//		List<TreeNode> allNotTopNode = getAllNotTopNode(treeList);
//		System.out.println(allNotTopNode);
		//指定节点
//		TreeNode parentNode = new TreeNode();
//		parentNode.setId(1L);
//		parentNode.setParentId(0L);
//		parentNode.setIsSonNode(false);
//		parentNode.setNodeName("祖先节点");
		//所有非祖先节点
//		List<TreeNode> allNotTopNode = getAllNotTopNode(getTreeList());
//		getSubNode(parentNode,allNotTopNode);
//		System.out.println(parentNode);
//		List<String> allLevelName = getAllLevelName();
//		System.out.println(allLevelName);
		//获取所有节点
		ArrayList<TreeNode> treeList = getTreeList();
		//得到指定父节点
		TreeNode treeNode = getById(3L);
		//获取所有子节点
		getSubNode(treeNode,getAllNotTopNode(treeList));
		//获取子节点的ID集合
		List<Long> ids = new ArrayList<>();
		getSonTreeNodeIds(treeNode.getSonNode(),ids);
		System.out.println(ids);

	}

	public static List<String> getAllLevelName() {
		String allLevelName = "";
		List<String> allLevelNameList = new ArrayList<>();

		//获取所有节点
		List<TreeNode> treeNodeList = getTreeList();
		//将每个节点与其父节点进行拼接成xxx-xxx-xxx
		for (TreeNode treeNode : treeNodeList) {
			//获取父分类ID
			Long parentId = treeNode.getParentId();
			//拼接节点名称
			StringBuilder stringBuilder = new StringBuilder();
			//当parentId不为0，则进行拼接
			while (parentId != 0) {
				TreeNode parent = getById(parentId);
				stringBuilder.insert(0, parent.getNodeName() + "-");
				parentId = parent.getParentId();
			}
			stringBuilder.append(treeNode.getNodeName());
			allLevelName = stringBuilder.toString();
			allLevelNameList.add(allLevelName);
		}
		return allLevelNameList;
	}

	/**
	 * 获取指定节点的所有子节点
	 * @param parentNode 父节点
	 * @param allNotTopNode  所有非祖先节点（非祖先节点都有父节点）
	 */
	public static void getSubNode(TreeNode parentNode,List<TreeNode> allNotTopNode){
		//过滤出所有parentNode的子节点
		Stream<TreeNode> treeNodeStream = allNotTopNode.stream().filter(new Predicate<TreeNode>() {
			@Override
			public boolean test(TreeNode treeNode) {
				return treeNode.getParentId().equals(parentNode.getId()); //非祖先节点的parentId，等于指定节点的id，那么就是指定节点的子节点
			}
		});
		//得到过滤出的parentNode的子节点集合
		List<TreeNode> sonNodeList = treeNodeStream.collect(Collectors.toList());

		//递归处理
		if (!sonNodeList.isEmpty() || !(sonNodeList == null)){ //如果parentNode具备子节点，则继续获取其子节点的子节点
			parentNode.setSonNode(sonNodeList); //所有子节点先存入parentNode
			sonNodeList.forEach(new Consumer<TreeNode>() { //递归所有子节点
				@Override
				public void accept(TreeNode treeNode) {
					getSubNode(treeNode,allNotTopNode); //递归调用本方法，让子节点作为parentNode，用具有父节点的所有非祖先节点allNotTopNode来筛选
				}
			});
		}else { //叶子节点的SonNode直接给一个空集合，停止递归
			parentNode.setSonNode(new ArrayList<>());
		}
	}

	/**
	 * 获取所有顶层节点
	 * @param treeList 所有的节点数据
	 * @return 顶层节点
	 */
	public static List<TreeNode> getAllTopNode(List<TreeNode> treeList){
		//过滤出顶层节点
		Stream<TreeNode> treeNodeStream = treeList.stream().filter(new Predicate<TreeNode>() {
			@Override
			public boolean test(TreeNode treeNode) {
				return treeNode.getIsSonNode() != true; //顶层节点判断后返回true，即留下
			}
		});
		List<TreeNode> topNodeList = treeNodeStream.collect(Collectors.toList());
		return topNodeList;
	}

	/**
	 * 获取所有非祖先节点
	 * @param treeList 所有的节点数据
	 * @return 顶层节点
	 */
	public static List<TreeNode> getAllNotTopNode(List<TreeNode> treeList){
		//过滤出顶层节点
		Stream<TreeNode> treeNodeStream = treeList.stream().filter(new Predicate<TreeNode>() {
			@Override
			public boolean test(TreeNode treeNode) {
				return treeNode.getIsSonNode() == true; //非顶层节点判断后返回true，即留下
			}
		});
		List<TreeNode> notTopNodeList = treeNodeStream.collect(Collectors.toList());
		return notTopNodeList;
	}

	public static ArrayList<TreeNode> getTreeList(){
		ArrayList<TreeNode> treeNodeArrayList = new ArrayList<>();
		TreeNode treeNode1 = new TreeNode();
		treeNode1.setId(1L);
		treeNode1.setParentId(0L);
		treeNode1.setIsSonNode(false);
		treeNode1.setNodeName("祖先节点");

		TreeNode treeNode2 = new TreeNode();
		treeNode2.setId(2L);
		treeNode2.setParentId(1L);
		treeNode2.setIsSonNode(true);
		treeNode2.setNodeName("爸爸节点");

		TreeNode treeNode3 = new TreeNode();
		treeNode3.setId(3L);
		treeNode3.setParentId(1L);
		treeNode3.setIsSonNode(true);
		treeNode3.setNodeName("叔叔节点");

		TreeNode treeNode4 = new TreeNode();
		treeNode4.setId(4L);
		treeNode4.setParentId(2L);
		treeNode4.setIsSonNode(true);
		treeNode4.setNodeName("最小节点");

		treeNodeArrayList.add(treeNode1);
		treeNodeArrayList.add(treeNode2);
		treeNodeArrayList.add(treeNode3);
		treeNodeArrayList.add(treeNode4);
		return treeNodeArrayList;
	}

	public static TreeNode getById(Long id){
		ArrayList<TreeNode> treeList = getTreeList();
		for (TreeNode treeNode : treeList) {
			if (treeNode.getId().equals(id)){
				return treeNode;
			}
		}
		return null;
	}

	/**
	 * 提取所有子分类的ID
	 */
	public static void getSonTreeNodeIds(List<TreeNode> sonNodeList,List<Long> ids){
		Stream<Long> longStream = sonNodeList.stream().map(new Function<TreeNode, Long>() {
			@Override
			public Long apply(TreeNode treeNode) {
				return treeNode.getId();
			}
		});
		List<Long> list = longStream.collect(Collectors.toList());
		ids.addAll(list);

		if (!sonNodeList.isEmpty() || !(sonNodeList == null)) {
			sonNodeList.forEach(sonNode -> getSonTreeNodeIds(sonNode.getSonNode(), ids));
		} else {
			return;
		}
	}
}
