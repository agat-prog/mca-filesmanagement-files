package mca.filesmanagement.files.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mca.filesmanagement.files.commons.FileDummieFactory;
import mca.filesmanagement.files.commons.FileNewDto;
import net.sf.beanrunner.BeanRunner;

public class CreateFileSagaDataTest {

	/** Getters y setters. */
	@Test
    public void testGettersAndSetters() throws Exception {
		long id = 1;
		BeanRunner beanRunner = new BeanRunner();
		beanRunner.addTestValue(CreateFileSagaData.class, new CreateFileSagaData());
		beanRunner.testBean(new CreateFileSagaData());
		
		FileNewDto fileNewDto = FileDummieFactory.createFileNew(id);
		CreateFileSagaData createFileSagaData = new CreateFileSagaData(fileNewDto);
		
		assertTrue(fileNewDto.getUserName().equals(createFileSagaData.getUserName()));
		assertTrue(fileNewDto.getCode().equals(createFileSagaData.getCode()));
		assertTrue(fileNewDto.getDescription().equals(createFileSagaData.getDescription()));
		assertTrue(fileNewDto.getArchiveName().equals(createFileSagaData.getArchiveName()));
		assertTrue(fileNewDto.getArchiveContentBase64().equals(createFileSagaData.getArchiveContentBase64()));
		assertTrue(fileNewDto.getInitOption() == createFileSagaData.getInitOption());
	}
}
