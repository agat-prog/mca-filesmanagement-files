package mca.filesmanagement.files.domain.usescases;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.FileDummieFactory;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.port.out.IFilesRepository;
import mca.filesmanagement.files.port.out.IPublicationService;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("File Use Case Test")
public class FilesUseCaseImplTest {

	@InjectMocks
	private FilesUseCaseImpl filesUseCaseImpl;

	@Mock
	private IFilesRepository filesRepository;

	@Mock
	private IPublicationService publisher;

	/** Inicialización de mocks. */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/** Test file creation. */
	@Test
	@DisplayName("Test file creation")
	public void givenAnUserWhenCreateFileThenFileIsCreated() {
		long id = 1;

		when(this.filesRepository.findInitialOption(any())).thenReturn(FileDummieFactory.createInitialOption(id));
		when(this.filesRepository.findInitialOption(anyLong())).thenReturn(FileDummieFactory.createInitialOption(id));
		doNothing().when(this.filesRepository).createFile(any(), any());
		doNothing().when(this.publisher).notify(any(), any());

		String phaseType = "INICIAL";
		String processCode = FileDummieFactory.generateCode(id);
		String description = String.format(FileDummieFactory.DESCRIPCION_FORMAT, id);
		String documentCode = FileDummieFactory.generateCode(id);
		String code = FileDummieFactory.generateCode(id);
		CreateFileParams params = new CreateFileParams(id, code, description, phaseType, processCode, documentCode);
		this.filesUseCaseImpl.createFile(String.format(FileDummieFactory.USER_FORMAT, id), params);

		verify(this.filesRepository, times(1)).findInitialOption(any());
		verify(this.publisher, times(1)).notify(any(), any());
	}

	/** Test phase changes. */
	@Test
	@DisplayName("Test phase change")
	public void givenAnExistingFileWhenPhaseChangeThenFileModified() {
		long id = 1;

		when(this.filesRepository.findByProces(anyString())).thenReturn(FileDummieFactory.createFileDetail(id));
		doNothing().when(this.filesRepository).saveFile(any());
		when(this.filesRepository.findInitialOption(any())).thenReturn(FileDummieFactory.createInitialOption(id));
		when(this.filesRepository.findInitialOption(anyLong())).thenReturn(FileDummieFactory.createInitialOption(id));

		String processCode = FileDummieFactory.generateCode(id);
		String phaseType = "INICIAL";
		this.filesUseCaseImpl.changePhase(processCode, phaseType);

		verify(this.filesRepository, times(1)).findByProces(anyString());
		verify(this.filesRepository, times(1)).saveFile(any());
		verify(this.filesRepository, times(1)).findInitialOption(any());
		verify(this.filesRepository, times(1)).findInitialOption(anyLong());
	}

	/** Test delete file. */
	@Test
	@DisplayName("Test delete file")
	public void givenAnExistingFileWhenDeleteThenDeleteFile() {
		long id = 1;

		doNothing().when(this.filesRepository).deleteByCode(anyString());

		this.filesUseCaseImpl.deleteByCode(FileDummieFactory.generateCode(id));

		verify(this.filesRepository, times(1)).deleteByCode(anyString());
	}
	
	/** Test find file by code. */
	@Test
	@DisplayName("Test find file by code")
	public void givenAnExistingFileWhenFindByCodeThenFileReturned() {
		long id = 1;
		
		when(this.filesRepository.findInitialOption(anyLong())).thenReturn(FileDummieFactory.createInitialOption(id));
		when(this.filesRepository.findByCode(anyString())).thenReturn(FileDummieFactory.createFileDetail(id));
		
		FileDetailDto dto = this.filesUseCaseImpl.getByCode(FileDummieFactory.generateCode(id));
		
		assertFalse(dto.isFinished());
		verify(this.filesRepository, times(1)).findByCode(anyString());
		verify(this.filesRepository, times(1)).findInitialOption(anyLong());
	}
	
	/** Test file changes. */
	@Test
	@DisplayName("Test file changes")
	public void givenAnExistingFileWhenUpdatedThenFileChanges() {
		long id = 1;
		
		when(this.filesRepository.findInitialOption(anyLong())).thenReturn(FileDummieFactory.createInitialOption(id));
		when(this.filesRepository.findInitialOption(any())).thenReturn(FileDummieFactory.createInitialOption(id));
		when(this.filesRepository.findByCode(anyString())).thenReturn(FileDummieFactory.createFileDetail(id));
		doNothing().when(this.filesRepository).saveFile(any());
		
		this.filesUseCaseImpl.update(FileDummieFactory.createFileUpdate(id));
		verify(this.filesRepository, times(1)).findByCode(anyString());
		verify(this.filesRepository, times(1)).findInitialOption(any());
		verify(this.filesRepository, times(2)).findInitialOption(anyLong());
	}
	
	/** Test find all initial options. */
	@Test
	@DisplayName("Test find all initial options")
	public void givenAnExistingInitialOptionsWhenfindAllThenListReturn() {
		long id = 1;
		
		when(this.filesRepository.findAllInitialOption()).thenReturn(Arrays.asList(FileDummieFactory.createInitialOption(id)));

		List<InitialOptionDto> options = this.filesUseCaseImpl.findAllInitialOption();
		
		assertFalse(options.isEmpty());
		verify(this.filesRepository, times(1)).findAllInitialOption();
	}

}
