package mca.filesmanagement.files.commons;

import org.junit.jupiter.api.Test;

import net.sf.beanrunner.BeanRunner;


public class DtoTest {

	/** Getters y setters. */
	@Test
    public void testGettersAndSetters() throws Exception {
		BeanRunner beanRunner = new BeanRunner();
		//beanRunner.addTestValue(PhaseCodeEnum.class, PhaseCodeEnum.ANALISIS_TECNICO);
		beanRunner.addTestValue(DocumentDto.class, new DocumentDto());
		beanRunner.testBean(new DocumentDto());

		beanRunner = new BeanRunner();
		beanRunner.addTestValue(FileDetailDto.class, new FileDetailDto());
		beanRunner.testBean(new FileDetailDto());

		beanRunner = new BeanRunner();
		beanRunner.addTestValue(FileNewDto.class, new FileNewDto());
		beanRunner.testBean(new FileNewDto());

		beanRunner = new BeanRunner();
		beanRunner.addTestValue(FileUpdatedDto.class, new FileUpdatedDto());
		beanRunner.testBean(new FileUpdatedDto());

		beanRunner = new BeanRunner();
		beanRunner.addTestValue(InitialOptionDto.class, new InitialOptionDto());
		beanRunner.testBean(new InitialOptionDto());
	}
}
