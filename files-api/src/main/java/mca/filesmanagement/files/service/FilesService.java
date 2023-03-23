package mca.filesmanagement.files.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bpm.api.messaging.events.ProcessUpdatedEvent;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.FileNewDto;
import mca.filesmanagement.files.commons.FileUpdatedDto;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.port.in.IFilesUseCase;
import mca.filesmanagement.files.port.out.FilesSagas;

/**
 * Servicio o fachada de acceso al CORE.
 *
 * @author agat
 */
@Service
public class FilesService {

	@Autowired
	private FilesSagas filesSagas;

	@Autowired
	private IFilesUseCase filesUseCase;

	/***/
	public FilesService() {
		super();
	}

	/**
	 * Crea un Expediente nuevo.
	 * @param fileNewDto Datos del expediente a crear.
	 * @return Identificador de base de datos PK.
	 */
	public Long createFile(FileNewDto fileNewDto) {
		return this.filesSagas.generateFileCommand(fileNewDto);
	}

	/**
	 * Devuelve el detalle de un expediente a partir de su identificador.
	 * @param fileCode Código único del expediente.
	 * @return DTO con los datos del expediente.
	 */
	public FileDetailDto getByCode(String fileCode) {
		return this.filesUseCase.getByCode(fileCode);
	}

	/**
	 * Manejador de eventos de dominio.
	 * @return DomainEventHandlers
	 */
	protected DomainEventHandlers processDomainEventHandlers() {
	    return DomainEventHandlersBuilder
	            .forAggregateType("mca.filesmanagement.bpm.domain.process.ProcessAggregate")
	            .onEvent(ProcessUpdatedEvent.class, this::handleProcesUpdated)
	            .build();
	}

	private void handleProcesUpdated(DomainEventEnvelope<ProcessUpdatedEvent> event) {
		this.filesUseCase.changePhase(event.getEvent().getCode(), event.getEvent().getPhaseCode());
	}

	/**
	 * Actualiza un expediente.
	 * @param fileUpdated DTO con los datos del expediente a actualizar.
	 */
	public void update(FileUpdatedDto fileUpdated) {
		this.filesUseCase.update(fileUpdated);
	}

	/**
	 * Devuelve la lista de opciones de inicio.
	 * @return Lista.
	 */
	public List<InitialOptionDto> findAllInitialOption(){
		return this.filesUseCase.findAllInitialOption();
	}
}
