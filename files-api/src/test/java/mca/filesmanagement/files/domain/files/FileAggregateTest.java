package mca.filesmanagement.files.domain.files;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.files.commons.FileDummieFactory;
import mca.filesmanagement.files.commons.InitOption;
import mca.filesmanagement.files.port.out.IFilesRepository;
import mca.filesmanagement.files.port.out.IPublicationService;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("File Aggregate Test")
public class FileAggregateTest {
	
	private FileAggregate fileAggregate;
	
	@Mock
	private IPublicationService publisher;
	
	@Mock
	private IFilesRepository filesRepository;

	/** Inicialización de mocks. */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.fileAggregate = this.init();
	}
	
	/** Test the file has been created ok. */
	@Test
	@DisplayName("Test the file has been created ok")
	public void givenNoFileWhenNewFileThenFileCreatedOk() {
		long id = 1;

		when(this.filesRepository.findInitialOption(any())).thenReturn(FileDummieFactory.createInitialOption(id));
		doNothing().when(this.publisher).notify(any(), any());

		String code = FileDummieFactory.generateCode(id);
		this.fileAggregate.setProcessCode(code);
		this.fileAggregate.afterCreated();

		assertTrue(InitOption.PETICION_DTO.equals(this.fileAggregate.getInitOption()));
		assertTrue(this.fileAggregate.isActive());
		assertTrue(code.equals(this.fileAggregate.getProcessCode()));
		assertNull(fileAggregate.getPhaseCode());

		verify(this.filesRepository, times(1)).findInitialOption(any());
		verify(this.publisher, times(1)).notify(any(), any());
	}

	/** Test next to a valid phase. */
	@Test
	@DisplayName("Test next to a valid phase")
	public void givenANewFileWhenUpdatePhaseThenNewPhaseCreated() {
		long id = 1;

		assertNull(this.fileAggregate.getPhaseCode());

		this.fileAggregate.setDescription(String.format(FileDummieFactory.DESCRIPCION_FORMAT, id));
		this.fileAggregate.setPhaseActual(PhaseInitial.NAME);
		assertTrue(PhaseInitial.NAME.equals(this.fileAggregate.getPhaseCode()));

		assertTrue(this.fileAggregate.isActive());
		assertTrue(this.fileAggregate.getDescription().equalsIgnoreCase(String.format(FileDummieFactory.DESCRIPCION_FORMAT, id)));
	}

	/** Test change phase. */
	@Test
	@DisplayName("Test change phase")
	public void givenANewFileWhenChangePhaseThenNewPhaseCreated() {
		long id = 1;

		when(this.filesRepository.findInitialOption(any())).thenReturn(FileDummieFactory.createInitialOption(id));
		doNothing().when(this.publisher).notify(any(), any());

		this.fileAggregate.changePhase(PhaseInitial.NAME);

		assertTrue(PhaseInitial.NAME.equals(this.fileAggregate.getPhaseCode()));
		assertTrue(this.fileAggregate.isActive());

		verify(this.filesRepository, times(1)).findInitialOption(any());
		verify(this.publisher, times(1)).notify(any(), any());
	}
	
	/** Test file is not active. */
	@Test
	@DisplayName("Test file is not active")
	public void givenAFileWhenFinishedPhaseThenFileIsNotActive() {
		long id = 1;

		when(this.filesRepository.findInitialOption(any())).thenReturn(FileDummieFactory.createInitialOption(id));
		doNothing().when(this.publisher).notify(any(), any());

		this.fileAggregate.changePhase(new PhaseRefused().getType());
		assertTrue(this.fileAggregate.isActive());
		
		this.fileAggregate.changePhase(new PhaseTechnical().getType());
		assertTrue(this.fileAggregate.isActive());
		
		this.fileAggregate.changePhase(new PhaseValidated().getType());
		assertTrue(this.fileAggregate.isActive());
		
		this.fileAggregate.changePhase(new PhaseFinished().getType());
		
		assertTrue(PhaseFinished.NAME.equals(this.fileAggregate.getPhaseCode()));
		assertFalse(this.fileAggregate.isActive());

		verify(this.filesRepository, times(4)).findInitialOption(any());
		verify(this.publisher, times(4)).notify(any(), any());
	}

	/** Test add document. */
	@Test
	@DisplayName("Test add document")
	public void givenANewFileWhenAddDocumentThenFirstDocumentAvailable() {
		long id = 1;

		assertTrue(this.fileAggregate.getFirstDocument().isEmpty());
		assertTrue(this.fileAggregate.getAllDocument().isEmpty());
		
		this.fileAggregate.addDocument(FileDummieFactory.generateCode(id));

		assertTrue(this.fileAggregate.isActive());
		assertTrue(this.fileAggregate.getFirstDocument().isPresent());
		assertTrue(FileDummieFactory.generateCode(id).equals(fileAggregate.getFirstDocument().get().getCode()));
		assertFalse(this.fileAggregate.getAllDocument().isEmpty());
	}
	
	/** Test update file. */
	@Test
	@DisplayName("Test update file")
	public void givenAExistingFileWhenUpdatedThenHasChangedAndPublishEvent() {
		long id = 1;
		
		when(this.filesRepository.findInitialOption(any())).thenReturn(FileDummieFactory.createInitialOption(id));
		doNothing().when(this.publisher).notify(any(), any());
		
		this.fileAggregate.update(String.format(FileDummieFactory.DESCRIPCION_FORMAT, id), InitOption.SEDE);
		
		assertTrue(InitOption.SEDE.equals(this.fileAggregate.getInitOption()));
		assertTrue(this.fileAggregate.getDescription().equalsIgnoreCase(String.format(FileDummieFactory.DESCRIPCION_FORMAT, id)));
		assertTrue(this.fileAggregate.isActive());
		verify(this.filesRepository, times(1)).findInitialOption(any());
		verify(this.publisher, times(1)).notify(any(), any());
	}

	private FileAggregate init() {
		FileAggregate fileAggregate = new FactoryFileAggregate().create();
		fileAggregate.setId(FileDummieFactory.generateCode(1l));
		fileAggregate.setPublisher(publisher);
		fileAggregate.setFilesRepository(filesRepository);
		return fileAggregate;
	}
}
