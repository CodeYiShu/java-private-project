package com.codeshu;

import com.codeshu.entity.MovieEntity;
import com.codeshu.entity.PersonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.repository.query.QueryFragmentsAndParameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class Neo4jTemplateTest {
	@Autowired
	private Neo4jTemplate neo4jTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate() {
		//创建类型为Movie的节点实体
		MovieEntity movie = new MovieEntity("你的名字", "影片讲述了男女高中生在梦中相遇，并寻找彼此的故事。");

		//创建类型为Person的节点实体
		PersonEntity person1 = new PersonEntity(1998, "上白石萌音");
		PersonEntity person2 = new PersonEntity(1993, "神木隆之介");
		PersonEntity person3 = new PersonEntity(1973, "新海诚");

		//将person1和person2节点通过参演关系，关联到movie节点
		//将person3节点通过导演关系，关联到movie节点
		movie.getActors().add(person1);
		movie.getActors().add(person2);
		movie.setDirector(person3);

		//存入图数据库持久化
		neo4jTemplate.save(movie);
	}

	@Test
	public void testQuery1() {
		Optional<MovieEntity> optional = neo4jTemplate.findById(11, MovieEntity.class);
		MovieEntity movieEntity = optional.orElse(null);
		System.out.println(movieEntity);
	}

	@Test
	public void testQuery2() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "新海诚");
		//Optional<PersonEntity> optional = neo4jTemplate.findOne("MATCH (n:Person {name: $name}) RETURN n", map, PersonEntity.class);
		Optional<PersonEntity> optional = neo4jTemplate.findOne("MATCH (n:Person) WHERE n.name = $name RETURN n", map, PersonEntity.class);
		PersonEntity person = optional.orElse(null);
		System.out.println(person);
	}

	@Test
	public void testQuery3() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", 2);
		QueryFragmentsAndParameters parameters = new QueryFragmentsAndParameters(
				"MATCH (person:Person) -[ relation:ACTED_IN]-> (movie:Movie) \n" +
						"WHERE relation.id = $id RETURN person", map);
		List<PersonEntity> personEntityList = neo4jTemplate.toExecutableQuery(PersonEntity.class, parameters).getResults();
		System.out.println(personEntityList);
	}

}
