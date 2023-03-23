package mca.filesmanagement.files.service;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.common.MessageImpl;
import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.FileDummieFactory;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.port.in.IFilesUseCase;
import mca.filesmanagement.files.port.out.FilesSagas;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Services tests")
public class FilesServiceTest {

	@InjectMocks
	private FilesService filesService;

	@Mock
	private IFilesUseCase filesUseCase;

	@Mock
	private FilesSagas filesSagas;

	/***/
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/** Test return ok when create a new file. */
	@Test
	@DisplayName("Test return ok when create a new file.")
	public void givenNoExistingFieWhenCreateThenReturnOk() {
		long id = 1;

		when(this.filesSagas.generateFileCommand(any())).thenReturn(id);

		Long response = this.filesService.createFile(FileDummieFactory.createFileNew(id));
		assertNotNull(response);
		assertTrue(id == response.longValue());
	}

	/** Test return ok when update a existing file. */
	@Test
	@DisplayName("Test return ok when update a existing file.")
	public void givenExistingFieWhenUpdateThenReturnOk() {
		long id = 1;

		doNothing().when(this.filesUseCase).update(any());

		this.filesService.update(FileDummieFactory.createFileUpdate(id));
		verify(this.filesUseCase, times(1)).update(any());
	}

	/** Test available initial options list. */
	@Test
	@DisplayName("Test available initial options list.")
	public void givenRequestAvailableInitOptionsWhenRequestThenReturnList() {
		long id = 1;

		when(this.filesUseCase.findAllInitialOption()).thenReturn(Arrays.asList(FileDummieFactory.createInitialOption(id)));

		List<InitialOptionDto> listaResponse = this.filesService.findAllInitialOption();

		// Results verify
		assertNotNull(listaResponse);
		verify(this.filesUseCase, times(1)).findAllInitialOption();

		assertNotNull(listaResponse);
		assertFalse(listaResponse.isEmpty());
		assertTrue(listaResponse.size() == 1);
		assertEquals(String.format(FileDummieFactory.DESCRIPCION_FORMAT, id),
				listaResponse.get(0).getDescription());
	}

	/** Test find a existing file by code. */
	@Test
	@DisplayName("Test find a existing fle by code")
	public void givenAExistingFileWhenFindThenReturnFileDto() {
		long id = 1;

		when(this.filesUseCase.getByCode(any())).thenReturn(FileDummieFactory.createFileDetail(id));

		FileDetailDto dto = this.filesService.getByCode(String.format(FileDummieFactory.CODE_FORMAT, id));

		verify(this.filesUseCase, times(1)).getByCode(anyString());
		assertNotNull(dto);
		assertEquals(FileDummieFactory.generateCode(id), dto.getCode());
	}

	/** Test create domain event handler. */
	@Test
	@DisplayName("Test create domain event handler.")
	public void givenRequestEventHandlerWhenGetThenReturnDomainEventHandler() {
		DomainEventHandlers domainEventHandlers = this.filesService.processDomainEventHandlers();
		assertNotNull(domainEventHandlers);
		
		Message message = new MessageImpl();
		message.setPayload("");
		message.setHeader("event-aggregate-type", "0120");
		domainEventHandlers.findTargetMethod(message);
		
	}
}
