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

/**
 * Agregado de expedientes.
 *
 * @author agat
 */
public class FileAggregate {

	private String id;
	private InitOption initOption;
	private String description;
	private Process process;
	private List<Document> documents = new ArrayList<>(0);
	private IPublicationService publisher;
	private IFilesRepository filesRepository;

	/**
	 * Crea una instancia con su ID inicializado.
	 *
	 * @param id Identificador único del agregado.
	 */
	public FileAggregate(String id) {
		super();

		Assert.notNull(id, "El id no puede ser null");

		this.id = id;
		this.initOption = InitOption.PETICION_DTO;
		this.process = new Process();
	}

	/**
	 * Método para indicar la finalización de la creación.
	 */
	public void afterCreated() {
		this.publishFileUpdatedEvent();
	}

	/**
	 * Realiza el cambio de fase en el expediente.
	 * @param phaseType Código identificativo de la fase.
	 */
	public void changePhase(String phaseType) {
		this.setPhaseActual(phaseType);
		this.publishFileUpdatedEvent();
	}

	/**
	 * Publica el evento de actualización del expediente a la cola de eventos.
	 */
	private void publishFileUpdatedEvent() {
		FileUpdatedEvent event = new FileUpdatedEvent();
		event.setCode(this.getId());
		event.setActive(this.isActive());
		event.setDescription(this.getDescription());
		event.setInitOption(this.filesRepository.findInitialOption(this.initOption).getDescription());
		event.setProcessCode(this.getProcessCode());

		this.publisher.notify(this, event);
	}

	/**
	 * Añade un documento a la lista de documentos adjuntos.
	 * @param code Identificador único del documento a anexar.
	 */
	public void addDocument(String code) {
		this.documents.add(new Document(code));
	}

	/**
	 * Devuelve el primer documento de los adjuntos en el expediente.
	 *
	 * @return Optional con el documento.
	 */
	public Optional<Document> getFirstDocument() {
		Document document = null;
		if (!documents.isEmpty()) {
			document = this.documents.get(0);
		}
		return Optional.ofNullable(document);
	}

	/**
	 * Devuelve la lista completa de documentos adjuntos.
	 * @return Lista.
	 */
	public List<Document> getAllDocument(){
		return this.documents;
	}

	/**
	 * Establece la fase actual del expediente.
	 * @param phaseType
	 */
	public void setPhaseActual(String phaseType)  {
		Assert.isTrue(StringUtils.hasLength(phaseType), "El tipo de la fase no puede estar vacío o null");

		this.process.setPhaseActual(phaseType);
	}

	/**
	 * Establece el código de proceso de un expediente.
	 *
	 * @param processCode
	 */
	public void setProcessCode(String processCode) {
		Assert.isTrue(StringUtils.hasLength(processCode), "El code del proceso no puede estar vacío o null");

		this.process.setCode(processCode);
	}

	/**
	 * Establece el identificador del expediente.
	 * @param id Identificador único del expediente.
	 */
	public void setId(String id) {
		Assert.isTrue(StringUtils.hasLength(id), "El id no puede estar vacío o null");

		this.id = id;
	}

	/**
	 * Actualiza información de un expediente.
	 * @param description La descripción.
	 * @param initOption Forma de inicio.
	 */
	public void update(String description, InitOption initOption) {
		this.setDescription(description);
		this.setInitOption(initOption);
		this.publishFileUpdatedEvent();
	}

	/**
	 * Establece el dato de descripción.
	 * @param description La descripción.
	 */
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

	/**
	 * Devuelve el código de fase en el que se encuentra el expediente.
	 * @return Código identificativo de la fase.
	 */
	public String getPhaseCode() {
		return this.process.getPhaseActualType();
	}

	/**
	 * Devuelve el código de proceso.
	 * @return Código externo único del proceso asociado al expediente.
	 */
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
