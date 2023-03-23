package mca.filesmanagement.files.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.FileDummieFactory;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.service.FilesService;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Controller tests")
public class FilesControllerTest {

	@InjectMocks
	private FilesController filesController;

	@Mock
	private FilesService filesService;

	/** Inicialización de Mockito. */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/** Test available initial options list. */
	@Test
	@DisplayName("Test available initial options list.")
	public void givenRequestAvailableInitOptionsWhenRequestThenReturnList() {
		long id = 1;

		when(this.filesService.findAllInitialOption()).thenReturn(Arrays.asList(FileDummieFactory.createInitialOption(id)));

		ResponseEntity<List<InitialOptionDto>> response = this.filesController.findAllInitialOption();

		// Results verify
		assertNotNull(response);
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		verify(this.filesService, times(1)).findAllInitialOption();

		List<InitialOptionDto> listaResponse = response.getBody();
		assertNotNull(listaResponse);
		assertFalse(listaResponse.isEmpty());
		assertTrue(listaResponse.size() == 1);
		assertEquals(String.format(FileDummieFactory.DESCRIPCION_FORMAT, id),
				listaResponse.get(0).getDescription());
	}

	/** Test return a existing file. */
	@Test
	@DisplayName("Test return a existing file.")
	public void givenExistingFieWhenFindThenReturnFile() {
		long id = 1;
		when(this.filesService.getByCode(anyString()))
				.thenReturn(FileDummieFactory.createFileDetail(id));

		ResponseEntity<FileDetailDto> response = this.filesController
				.getFileByCode(String.format(FileDummieFactory.CODE_FORMAT, id));

		// Results verify
		assertNotNull(response);
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		verify(this.filesService, times(1)).getByCode(anyString());

		FileDetailDto dto = response.getBody();
		assertNotNull(dto);
		assertNotNull(dto.getId());
		assertEquals(FileDummieFactory.generateCode(id), dto.getCode());
	}

	/** Test return ok when update a existing file. */
	@Test
	@DisplayName("Test return ok when update a existing file.")
	public void givenExistingFieWhenUpdateThenReturnOk() {
		long id = 1;

		doNothing().when(this.filesService).update(any());

		ResponseEntity<Void> response = this.filesController.update(FileDummieFactory.createFileUpdate(id));

		// Results verify
		assertNotNull(response);
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
	}

	/** Test return ok when create a new file. */
	@Test
	@DisplayName("Test return ok when create a new file.")
	public void givenNoExistingFieWhenCreateThenReturnOk() {
		long id = 1;

		when(this.filesService.createFile(any())).thenReturn(id);

		MultipartFile file = new MockMultipartFile("fileName", "contenido".getBytes()) ;
		ResponseEntity<Void> response = this.filesController.create(file, FileDummieFactory.generateCode(id), id);

		// Results verify
		assertNotNull(response);
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
	}

	/** Test return echo. */
	@Test
	@DisplayName("Test return echo.")
	public void givenATexthenEchoThenReturnText() {
		String texto = "Hola mundo";
		ResponseEntity<String> response = this.filesController.echo(texto);

		assertTrue(texto.equals(response.getBody()));
	}
}
