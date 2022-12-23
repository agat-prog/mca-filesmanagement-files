package mca.filesmanagement.files.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class FilesService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(FilesService.class);
	
	@Autowired
	private FilesSagas filesSagas;
	
	@Autowired
	private IFilesUseCase filesUseCase;
	
	public FilesService() {
		super();
	}
	
	public Long createFile(FileNewDto fileNewDto) {
		return this.filesSagas.generateFileCommand(fileNewDto);
	}
	
	public FileDetailDto getByCode(String fileCode) {
		return this.filesUseCase.getByCode(fileCode);
	}
	
	protected DomainEventHandlers processDomainEventHandlers() {
	    return DomainEventHandlersBuilder
	            .forAggregateType("mca.filesmanagement.bpm.domain.process.ProcessAggregate")
	            .onEvent(ProcessUpdatedEvent.class, this::handleProcesUpdated)
	            .build();
	}

	private void handleProcesUpdated(DomainEventEnvelope<ProcessUpdatedEvent> event) {
		ProcessUpdatedEvent phaseEvent = event.getEvent();
		
		LOGGER.info("FilesService.handleProcesUpdated:" + phaseEvent);
		this.filesUseCase.changePhase(phaseEvent.getCode(), phaseEvent.getPhaseCode());
	}

	public void update(FileUpdatedDto fileUpdated) {
		this.filesUseCase.update(fileUpdated);
	}
	
	public List<InitialOptionDto> findAllInitialOption(){
		return this.filesUseCase.findAllInitialOption();
	}
}
