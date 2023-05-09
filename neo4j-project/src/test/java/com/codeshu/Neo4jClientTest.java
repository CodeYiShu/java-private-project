package com.codeshu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jClient;

/**
 * @author CodeShu
 * @date 2023/5/9 17:23
 */
@SpringBootTest
public class Neo4jClientTest {
	@Autowired
	private Neo4jClient neo4jClient;

	@Test
	public void testCreateRelationShip() {
		String str = "MATCH (a:BenTi), (b:BenTi) WHERE id(a) = %s AND id(b) = %s MERGE (a)-[:%s]->(b)";
		String xxx = String.format(str, 7L, 26L, "xxx");
		System.out.println(xxx);
		neo4jClient.query(xxx).run();
	}
}
