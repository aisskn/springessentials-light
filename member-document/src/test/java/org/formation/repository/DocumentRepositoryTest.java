package org.formation.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.formation.model.Document;
import org.formation.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DocumentRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	
	@Test
	void testFindByOwner() {
		Member id1 = new Member();
		id1.setId(1l);
		
		 List<Document> documents = documentRepository.findByOwner(id1);
		
		 documents.stream().forEach(System.out::println);	
		 
		 assertEquals(3,documents.size());
	}
	
	@Test
	void testFindByOwnerName() {

		
		 List<Document> documents = documentRepository.findByOwnerName("THIBAU");
		
		 documents.stream().forEach(System.out::println);
		 
		 assertEquals(6,documents.size());
		 	
	}
}
