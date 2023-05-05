package com.codeshu;

import com.codeshu.entity.MovieEntity;
import com.codeshu.entity.PersonEntity;
import com.codeshu.repository.MovieRepository;
import com.codeshu.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Neo4jRepositoryTest {
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private PersonRepository personRepository;

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

		movieRepository.save(movie);
	}

	@Test
	public void testQuery1() {
		PersonEntity person = personRepository.findPersonEntityByName("上白石萌音");
		System.out.println(person);
		MovieEntity movie = movieRepository.findMovieEntityByTitle("你的名字");
		System.out.println(movie);
	}

	@Test
	public void testQuery2() {
		PersonEntity person = personRepository.findPersonEntityById(5L);
		System.out.println(person);
	}
}
