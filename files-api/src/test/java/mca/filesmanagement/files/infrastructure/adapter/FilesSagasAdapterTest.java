package mca.filesmanagement.files.infrastructure.adapter;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import mca.filesmanagement.files.commons.FileDummieFactory;
import mca.filesmanagement.files.service.CreateFileCommand;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("File Repositories Test")
public class FilesSagasAdapterTest {
	
	@InjectMocks
	private FilesSagasAdapter filesSagasAdapter;
	
	@Mock
	private SagaInstanceFactory sagaInstanceFactory;

	@Mock
	private CreateFileCommand createFileCommand;

	/** Inicialización de mocks. */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	/** Test generate file command. */
	@Test
	@DisplayName("Test generate file command")
	public void givenNewFileWhenGenerateFileThenIdReturn() {
		long id = 1;
		
		//doNothing().when(this.sagaInstanceFactory).create(any(), any());
		when(this.sagaInstanceFactory.create(any(), any())).thenReturn(null);
		
		Long idReturn = this.filesSagasAdapter.generateFileCommand(FileDummieFactory.createFileNew(id));
	
		assertNotNull(idReturn);
	}
}
