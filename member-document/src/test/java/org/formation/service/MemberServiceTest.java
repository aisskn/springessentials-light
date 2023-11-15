package org.formation.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.formation.model.Document;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(properties = { "spring.jpa.properties.hibernate.hbm2ddl.import_files=" }) //properties présent à cause du import.sql
public class MemberServiceTest {

	@Autowired
	MemberService documentService;

	@Autowired
	ApplicationContext context;
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DocumentRepository documentRepository;

	@BeforeEach
	void displayBean(){
		for (String BeanNames: context.getBeanDefinitionNames()){
			System.out.println(BeanNames);
		}
	}
	
	@Test
	void testImport() {
		long initialMemberCount = memberRepository.count();
		long initialDocumentCount = documentRepository.count();
		
		Document doc = new Document();
		doc.setContentType("dummy");
		doc.setName("Dummy.doc");
		doc.setUploadedDate(new Date());
		
		documentService.importDocument(doc);
		
		assertAll("Adding One Member 2 docs",
				() -> assertEquals(initialMemberCount, memberRepository.count()),
				() -> assertEquals(initialDocumentCount + initialMemberCount, documentRepository.count()));

	}
}
