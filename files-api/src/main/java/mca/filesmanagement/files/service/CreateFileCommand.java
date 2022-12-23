package mca.filesmanagement.files.service;

import static docs.api.messaging.commands.DocsChannels.CHANNEL_DOCS_SERVICE;
import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bpm.api.messaging.commands.BpmChannels;
import bpm.api.messaging.commands.CreateBpmCommand;
import bpm.api.messaging.commands.DeleteBpmCommand;
import bpm.api.messaging.replies.BpmCreatedEvent;
import docs.api.messaging.commands.CreateDocCommand;
import docs.api.messaging.commands.DeleteDocCommand;
import docs.api.messaging.commands.DocsChannels;
import docs.api.messaging.replies.DocCreatedEvent;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import mca.filesmanagement.files.domain.usescases.CreateFileParams;
import mca.filesmanagement.files.port.in.IFilesUseCase;

public class CreateFileCommand implements SimpleSaga<CreateFileSagaData> {

	private static Logger LOGGER = LoggerFactory.getLogger(CreateFileCommand.class);
	
	private IFilesUseCase filesUseCase;
	
	public CreateFileCommand(IFilesUseCase filesUseCase) {
		super();
		
		this.filesUseCase = filesUseCase;
	}

	@Override
	public SagaDefinition<CreateFileSagaData> getSagaDefinition() {
		return this.sagaDefinition;
	}
	
	private SagaDefinition<CreateFileSagaData> sagaDefinition = 
			step()
				.invokeLocal(this::create)
				.withCompensation(this::reject)
			.step()
				.invokeParticipant(this::createBpmProcess)
				.onReply(BpmCreatedEvent.class, this::onBpmCreatedProcess)
				.withCompensation(this::deleteBpmProcess)
			.step()
				.invokeParticipant(this::createDocProcess)
				.onReply(DocCreatedEvent.class, this::onDocCreatedProcess)
				.withCompensation(this::deleteDocument)
			.step()
				.invokeLocal(this::approve)
			.build();
	 
	private CommandWithDestination createDocProcess(CreateFileSagaData data) {
		LOGGER.info("createDocProcess --> Enviando datos a DOCS");
		
		CreateDocCommand command = new CreateDocCommand();
		command.setName(data.getArchiveName());
		command.setContentBase64(data.getArchiveContentBase64());
		command.setUser(data.getUserName());
		return send(command).to(CHANNEL_DOCS_SERVICE).build();
	}
	
	private void onDocCreatedProcess(CreateFileSagaData data, DocCreatedEvent event) {
		LOGGER.info("onDocCreatedProcess UUID --> " + event.getUuid().toString());
	
		data.setDocUuid(event.getUuid().toString());
	}
	
	private CommandWithDestination deleteDocument(CreateFileSagaData data) {
		LOGGER.info("deleteDocument --> Enviando eliminación datos a DOCS");
		return send(new DeleteDocCommand(UUID.fromString(data.getDocUuid()))).to(DocsChannels.CHANNEL_DOCS_SERVICE).build();
	}
	
	private void create(CreateFileSagaData data) {
		LOGGER.info("create --> Creando expediente:");
	}

	private CommandWithDestination createBpmProcess(CreateFileSagaData data) {
		LOGGER.info("createBpmProcess --> Enviando datos a BPM");
		CreateBpmCommand command = new CreateBpmCommand();
		command.setUser(data.getUserName());
		return send(command).to(BpmChannels.CHANNEL_BPM_SERVICE).build();
	}
	
	private void reject(CreateFileSagaData data) {
		LOGGER.info("reject --> Eliminando expediente -->" + data.getCode());
		
		this.filesUseCase.deleteByCode(data.getCode());
	}
	
	private void onBpmCreatedProcess(CreateFileSagaData data, BpmCreatedEvent event) {
		LOGGER.info("onBpmCreatedProcess UUID --> " + event.getUuid().toString());
	
		data.setBpmUuid(event.getUuid().toString());
		data.setPhaseCode(event.getPhase());
	}
	
	private CommandWithDestination deleteBpmProcess(CreateFileSagaData data) {
		LOGGER.info("deleteBpmProcess --> Enviando eliminación datos a BPM");
		return send(new DeleteBpmCommand(UUID.fromString(data.getBpmUuid()))).to(BpmChannels.CHANNEL_BPM_SERVICE).build();
	}

	private void approve(CreateFileSagaData data) {
		CreateFileParams params = new CreateFileParams(data.getInitOption(), data.getCode(), data.getDescription(), data.getPhaseCode(), data.getBpmUuid(), data.getDocUuid());
		this.filesUseCase.createFile(data.getUserName(), params);
		
		LOGGER.info("approve --> APROBADO !!!!!--> BPM uuid:" + data.getBpmUuid() + "; DOC uuid:" + data.getDocUuid());
	}
}
