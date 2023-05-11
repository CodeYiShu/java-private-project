package com.codeshu.repository;

import com.codeshu.entity.Model;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author CodeShu
 * @date 2023/5/5 14:43
 */
@Repository
public interface ModelRepository extends Neo4jRepository<Model, Long> {

	/**
	 * 根据 id 获取模型的版本
	 *
	 * @param id id字段
	 * @return 模型版本
	 */
	@Query("MATCH (n:Model) WHERE n.id = $0 RETURN n.version")
	String selectVersionById(Long id);
}
