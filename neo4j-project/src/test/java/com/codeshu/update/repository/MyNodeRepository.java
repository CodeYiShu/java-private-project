package com.codeshu.update.repository;

import com.codeshu.update.entity.MyNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author CodeShu
 * @date 2023/5/19 10:40
 */
@Repository
public interface MyNodeRepository extends Neo4jRepository<MyNodeEntity, Long> {
	@Query("MATCH (a:MyNode)-[r]->(b:OtherNode) WHERE a.id = $0 DELETE r,b")
	void deleteRelationShipAndOtherNode(Long id);
}
