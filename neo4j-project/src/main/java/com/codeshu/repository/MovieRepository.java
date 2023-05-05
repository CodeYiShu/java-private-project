package com.codeshu.repository;

import com.codeshu.entity.MovieEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CodeShu
 * @date 2023/5/5 14:43
 */
@Repository
public interface MovieRepository extends Neo4jRepository<MovieEntity, Long> {
	/**
	 * 根据 title 属性查询 Movie 节点
	 *
	 * @param title title属性值
	 * @return Movie节点
	 */
	MovieEntity findMovieEntityByTitle(String title);
}
