package mca.filesmanagement.files.infrastructure.adapter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.FileDummieFactory;
import mca.filesmanagement.files.commons.InitOption;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.infrastructure.model.InitialOptionEntity;
import mca.filesmanagement.files.infrastructure.repository.JpaDocumentRepository;
import mca.filesmanagement.files.infrastructure.repository.JpaFilesRepository;
import mca.filesmanagement.files.infrastructure.repository.JpaInitialOptionRepository;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("File Repositories Test")
public class FilesRepositoryAdapterTest {

	@InjectMocks
	private FilesRepositoryAdapter filesRepositoryAdapter;
	
	@Mock
	private ModelMapper modelMapper;

	@Mock
	private JpaFilesRepository jpaFilesRepository;

	@Mock
	private JpaDocumentRepository jpaDocumentRepository;

	@Mock
	private JpaInitialOptionRepository jpaInitialOptionRepository;

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

		when(this.jpaInitialOptionRepository.findByCode(anyString())).thenReturn(FileDummieFactory.createInitialOptionEntity(id));
		when(this.jpaDocumentRepository.save(any())).thenReturn(FileDummieFactory.createDocumentEntity(id));
		when(this.jpaFilesRepository.save(any())).thenReturn(FileDummieFactory.createFileEntity(id));

		this.filesRepositoryAdapter.createFile(String.format(FileDummieFactory.USER_FORMAT, id), FileDummieFactory.createFileAggregate(id));

		verify(this.jpaInitialOptionRepository, times(1)).findByCode(anyString());
		verify(this.jpaDocumentRepository, times(1)).save(any());
		verify(this.jpaFilesRepository, times(1)).save(any());
	}
	
	/** Test file changes. */
	@Test
	@DisplayName("Test file changes")
	public void givenAnExistingFileWhenUpdatedThenFileChanges() {
		long id = 1;
		
		when(this.jpaInitialOptionRepository.findByCode(anyString())).thenReturn(FileDummieFactory.createInitialOptionEntity(id));
		when(this.jpaFilesRepository.findByCode(anyString())).thenReturn(FileDummieFactory.createFileEntity(id));
		when(this.jpaFilesRepository.save(any())).thenReturn(FileDummieFactory.createFileEntity(id));
		
		this.filesRepositoryAdapter.saveFile(FileDummieFactory.createFileAggregate(id));
		
		verify(this.jpaInitialOptionRepository, times(1)).findByCode(anyString());
		verify(this.jpaFilesRepository, times(1)).findByCode(anyString());
		verify(this.jpaFilesRepository, times(1)).save(any());
	}
	
	/** Test find by process. */
	@Test
	@DisplayName("Test find by process")
	public void givenAnExistingFileWhenFindByProcessThenFileReturn() {
		long id = 1;
		
		when(this.jpaFilesRepository.findByProces(anyString())).thenReturn(FileDummieFactory.createFileEntity(id));
		
		FileDetailDto dto = FileDummieFactory.createFileDetail(id);
		FileDetailDto dtoReturn = this.filesRepositoryAdapter.findByProces(dto.getProcesCode());
		
		assertTrue(dto.getCode().equals(dtoReturn.getCode()));
		assertTrue(dto.getDescription().equals(dtoReturn.getDescription()));
	}
	
	/** Test find by code. */
	@Test
	@DisplayName("Test find by code")
	public void givenAnExistingFileWhenFindByCodeThenFileReturn() {
		long id = 1;
		
		when(this.jpaFilesRepository.findByCode(anyString())).thenReturn(FileDummieFactory.createFileEntity(id));
		
		FileDetailDto dto = FileDummieFactory.createFileDetail(id);
		FileDetailDto dtoReturn = this.filesRepositoryAdapter.findByCode(dto.getProcesCode());
		
		assertTrue(dto.getCode().equals(dtoReturn.getCode()));
		assertTrue(dto.getDescription().equals(dtoReturn.getDescription()));
	}
	
	/** Test delete by code. */
	@Test
	@DisplayName("Test delete by code")
	public void givenAnExistingFileWhenDeleteByCodeThenFileDeleted() {
		long id = 1;
		
		doNothing().when(this.jpaFilesRepository).deleteByCode(anyString());
		
		this.filesRepositoryAdapter.deleteByCode(FileDummieFactory.generateCode(id));
		
		verify(this.jpaFilesRepository, times(1)).deleteByCode(anyString());
	}
	
	/** Test find initial option by code. */
	@Test
	@DisplayName("Test find initial option by code")
	public void givenExistingInitialOptionWhenFindThenReturn() {
		long id = 1;
		
		when(this.jpaInitialOptionRepository.findByCode(anyString())).thenReturn(FileDummieFactory.createInitialOptionEntity(id));
		when(this.modelMapper.map(any(), any())).then(new Answer<InitialOptionDto>() {
			@Override
			public InitialOptionDto answer(InvocationOnMock invocation) throws Throwable {
				InitialOptionEntity entity = invocation.getArgument(0);
				
				InitialOptionDto dto = new InitialOptionDto();
				dto.setCode(entity.getCode());
				dto.setDescription(entity.getDescription());
				dto.setId(entity.getId());
				return dto;
			}
		});
		
		InitialOptionDto dto = this.filesRepositoryAdapter.findInitialOption(InitOption.PETICION_DTO);
		
		assertTrue(dto.getId().longValue() == id);
		assertTrue(dto.getDescription().equals(String.format(FileDummieFactory.DESCRIPCION_FORMAT, id)));
		verify(this.jpaInitialOptionRepository, times(1)).findByCode(anyString());
	}
	
	/** Test find initial option by id. */
	@Test
	@DisplayName("Test find initial option by id")
	public void givenExistingInitialOptionWhenFindByIdThenReturn() {
		long id = 1;
		
		when(this.jpaInitialOptionRepository.findById(anyLong())).thenReturn(Optional.of(FileDummieFactory.createInitialOptionEntity(id)));
		when(this.modelMapper.map(any(), any())).then(new Answer<InitialOptionDto>() {
			@Override
			public InitialOptionDto answer(InvocationOnMock invocation) throws Throwable {
				Optional<InitialOptionEntity> entityOp = invocation.getArgument(0);
				InitialOptionEntity entity = entityOp.get();
				InitialOptionDto dto = new InitialOptionDto();
				dto.setCode(entity.getCode());
				dto.setDescription(entity.getDescription());
				dto.setId(entity.getId());
				return dto;
			}
		});
		
		InitialOptionDto dto = this.filesRepositoryAdapter.findInitialOption(id);
		
		assertTrue(dto.getId().longValue() == id);
		assertTrue(dto.getDescription().equals(String.format(FileDummieFactory.DESCRIPCION_FORMAT, id)));
		verify(this.jpaInitialOptionRepository, times(1)).findById(anyLong());
	}
	
	
	/** Test find all initial. */
	@Test
	@DisplayName("Test find all initial")
	public void givenExistingInitialOptionsWhenFindAllThenReturnAll() {
		long id = 1;
		
		when(this.jpaInitialOptionRepository.findAll()).thenReturn(Arrays.asList(FileDummieFactory.createInitialOptionEntity(id)));
		when(this.modelMapper.map(any(), any())).then(new Answer<InitialOptionDto>() {
			@Override
			public InitialOptionDto answer(InvocationOnMock invocation) throws Throwable {
				InitialOptionEntity entity = invocation.getArgument(0);
				
				InitialOptionDto dto = new InitialOptionDto();
				dto.setCode(entity.getCode());
				dto.setDescription(entity.getDescription());
				dto.setId(entity.getId());
				return dto;
			}
		});
		
		List<InitialOptionDto> dtos = this.filesRepositoryAdapter.findAllInitialOption();
		
		assertFalse(dtos.isEmpty());
		assertTrue(dtos.get(0).getId().longValue() == id);
		assertTrue(dtos.get(0).getDescription().equals(String.format(FileDummieFactory.DESCRIPCION_FORMAT, id)));
	}
}
