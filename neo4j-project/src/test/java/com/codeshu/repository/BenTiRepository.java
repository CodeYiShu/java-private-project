package com.codeshu.repository;

import com.codeshu.entity.BenTiEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CodeShu
 * @date 2023/5/5 14:43
 */
@Repository
public interface BenTiRepository extends Neo4jRepository<BenTiEntity, Long> {

}
