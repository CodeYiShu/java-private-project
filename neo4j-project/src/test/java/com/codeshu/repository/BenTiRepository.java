package com.codeshu.repository;

import com.codeshu.entity.BenTiEntity;
import com.codeshu.response.QueryBenTiRelationShipResponse;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/5 14:43
 */
@Repository
public interface BenTiRepository extends Neo4jRepository<BenTiEntity, Long> {

	/**
	 * 批量删除本体节点的所有属性节点
	 *
	 * @param ids 本体节点 id 集合
	 */
	@Query("MATCH (a:Attribute)-[r]->(b:BenTi) WHERE b.id IN $0 DELETE a,r")
	void deleteAttribute(List<Long> ids);

	/**
	 * 批量删除本体节点的所有指出关系
	 *
	 * @param ids 本体节点 id 集合
	 */
	@Query("MATCH (a:BenTi)-[r]->(b:BenTi) WHERE a.id IN $0 DELETE r")
	void deleteRelationShip(List<Long> ids);

	/**
	 * 查询本体节点之间的关系
	 *
	 * @return 关系信息
	 */
	@Query("MATCH (a:BenTi)-[r]->(b:BenTi) RETURN a,a.id as startId,type(r) as relationshipType," +
			"properties(r).relationShipDescription as description,properties(r).remark as relationShipRemark,b.id as endId")
	List<QueryBenTiRelationShipResponse> selectBenTiRelationShip();
}
