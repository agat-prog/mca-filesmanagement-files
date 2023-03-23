package mca.filesmanagement.files.infrastructure.model;

import org.junit.jupiter.api.Test;

import net.sf.beanrunner.BeanRunner;

public class ModelTest {

	/** Getters y setters. */
	@Test
    public void testGettersAndSetters() throws Exception {
		BeanRunner beanRunner = new BeanRunner();
		beanRunner.addTestValue(FileEntity.class, new FileEntity());
		beanRunner.testBean(new FileEntity());
		
		beanRunner = new BeanRunner();
		beanRunner.addTestValue(InitialOptionEntity.class, new InitialOptionEntity());
		beanRunner.testBean(new InitialOptionEntity());
		
		beanRunner = new BeanRunner();
		beanRunner.addTestValue(DocumentEntity.class, new DocumentEntity());
		beanRunner.testBean(new DocumentEntity());
		/*
		beanRunner = new BeanRunner();
		beanRunner.addTestValue(PhaseInstanceEntity.class, new PhaseInstanceEntity());
		beanRunner.testBean(new PhaseInstanceEntity());

		PhaseInstanceEntity entity = new PhaseInstanceEntity();
		PhaseEntity phaseEntity = new PhaseEntity();
		phaseEntity.setDescription("Descripcion");
		assertEquals(entity.getPhaseCode(), PhaseCodeEnum.NULL);
		assertNull(entity.getPhaseDescription());

		entity.setPhase(phaseEntity);
		assertNotNull(entity.getPhaseCode());
		assertNotNull(entity.getPhaseDescription());

		beanRunner = new BeanRunner();
		beanRunner.addTestValue(ProcesEntity.class, new ProcesEntity());
		beanRunner.testBean(new ProcesEntity());
		*/
	}
}
