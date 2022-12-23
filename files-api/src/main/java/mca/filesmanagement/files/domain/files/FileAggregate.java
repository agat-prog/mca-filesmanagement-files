package mca.filesmanagement.files.domain.files;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import files.api.messaging.events.FileUpdatedEvent;
import mca.filesmanagement.files.commons.InitOption;
import mca.filesmanagement.files.port.out.IFilesRepository;
import mca.filesmanagement.files.port.out.IPublicationService;

public class FileAggregate {

	private String id;
	private InitOption initOption;
	private String description;
	private Process process;
	private List<Document> documents = new ArrayList<>(0);
	private IPublicationService publisher;
	private IFilesRepository filesRepository;
	
	public FileAggregate(String id) {
		super();
		
		Assert.notNull(id, "El id no puede ser null");
		
		this.id = id;
		this.initOption = InitOption.PETICION_DTO;
		this.process = new Process();
	}
	
	public void afterCreated() {
		this.publishFileUpdatedEvent();
	}
	
	public void changePhase(String phaseType) {
		this.setPhaseActual(phaseType);
		this.publishFileUpdatedEvent();
	}
	
	private void publishFileUpdatedEvent() {
		FileUpdatedEvent event = new FileUpdatedEvent();
		event.setCode(this.getId());
		event.setActive(this.isActive());
		event.setDescription(this.getDescription());
		event.setInitOption(this.filesRepository.findInitialOption(this.initOption).getDescription());
		event.setProcessCode(this.getProcessCode());
		
		this.publisher.notify(this, event);
	}
	
	public void addDocument(String code) {
		this.documents.add(new Document(code));
	}
	
	public Optional<Document> getFirstDocument() {
		Document document = null;
		if (!documents.isEmpty()) {
			document = this.documents.get(0);
		}
		return Optional.ofNullable(document);
	}
	
	public List<Document> getAllDocument(){
		return this.documents;
	}
	
	public void setPhaseActual(String phaseType)  {
		Assert.isTrue(StringUtils.hasLength(phaseType), "El tipo de la fase no puede estar vacío o null");
		
		this.process.setPhaseActual(phaseType);
	}
	
	public void setProcessCode(String processCode) {
		Assert.isTrue(StringUtils.hasLength(processCode), "El code del proceso no puede estar vacío o null");
		
		this.process.setCode(processCode);
	}
	
	public void setId(String id) {
		Assert.isTrue(StringUtils.hasLength(id), "El id no puede estar vacío o null");
		
		this.id = id;
	}
	
	public void update(String description, InitOption initOption) {
		this.setDescription(description);
		this.setInitOption(initOption);
		this.publishFileUpdatedEvent();
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param initOption the initOption to set
	 */
	public void setInitOption(InitOption initOption) {
		this.initOption = initOption;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the initOption
	 */
	public InitOption getInitOption() {
		return initOption;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return !this.process.isFinished();
	}

	public String getPhaseCode() {
		return this.process.getPhaseActualType();
	}

	public String getProcessCode() {
		return this.process.getCode();
	}
	
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(IPublicationService publisher) {
		this.publisher = publisher;
	}

	/**
	 * @param filesRepository the filesRepository to set
	 */
	public void setFilesRepository(IFilesRepository filesRepository) {
		this.filesRepository = filesRepository;
	}
}
