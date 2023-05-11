package com.codeshu.repository;

import com.codeshu.entity.Model;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CodeShu
 * @date 2023/5/5 14:43
 */
@Repository
public interface ModelRepository extends Neo4jRepository<Model, Long> {

	/**
	 * 根据项目ids获取模型的最新版本
	 *
	 * @param projectIds 项目id
	 * @return 最新模型版本
	 */
	@Query("MATCH (n:Model) WHERE n.projectId = $0 RETURN n.version ORDER BY n.createDate desc LIMIT 1")
	String selectLastVersionByProjectId(Long projectIds);
}
