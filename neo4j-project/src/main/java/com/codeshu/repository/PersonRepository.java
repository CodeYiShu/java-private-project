package com.codeshu.repository;

import com.codeshu.entity.PersonEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author CodeShu
 * @date 2023/5/5 14:37
 */
@Repository
public interface PersonRepository extends Neo4jRepository<PersonEntity, Long> {
	/**
	 * 根据 name 属性查询 Person 节点
	 *
	 * @param name name属性值
	 * @return Perosn节点
	 */
	PersonEntity findPersonEntityByName(String name);

	/**
	 * 根据 id 查询 person节点
	 *
	 * @param id 主键 id 属性值
	 * @return Person节点
	 */
	@Query("MATCH (n:Person) WHERE id(n) = $0 RETURN n")
	PersonEntity findPersonEntityById(Long id);
}
