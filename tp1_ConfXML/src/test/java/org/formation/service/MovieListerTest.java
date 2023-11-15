package org.formation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.formation.config.AppConfig;
import org.formation.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MovieListerTest {


	@Test
	public void testDirectedBy() {
		ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");

		performTest(context);
		
	}

	//@Test
//	public void testMovies() {
//
//		ApplicationContext context = new AnnotationConfigApplicationContext((AppConfig.class));
//
//		performTest(context);
//
//	}



	@Test
	public void testDirectedByWithFile() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("file");
		context.register(AppConfig.class);
		context.refresh();

		performTest(context);

	}

	@Test
	public void testDirectedByWithJdbc() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("jdbc");
		context.register(AppConfig.class);
		context.refresh();
		performTest(context);

		//Failed to obtain JDBC Connection;


	}

	public void performTest(ApplicationContext context){

		for (String beanName : context.getBeanDefinitionNames()){
			System.out.println(beanName);
		}
		MovieLister movieLister =
				(MovieLister) context.getBean("movieLister");


		List<Movie> hitchcock = movieLister.moviesDirectedBy("Hitchcock");
		List<Movie> HITCHCOCK = movieLister.moviesDirectedBy("HITCHCOCK");
		assertEquals(2, hitchcock.size());
		assertEquals(2, HITCHCOCK.size());
		List<Movie> empty = movieLister.moviesDirectedBy("");
		assertNotNull(empty);
		assertEquals(0, empty.size());
	}
}
