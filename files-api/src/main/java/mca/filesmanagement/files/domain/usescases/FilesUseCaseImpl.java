package mca.filesmanagement.files.domain.usescases;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.FileUpdatedDto;
import mca.filesmanagement.files.commons.InitOption;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.domain.FactoryProvider;
import mca.filesmanagement.files.domain.files.FileAggregate;
import mca.filesmanagement.files.port.in.IFilesUseCase;
import mca.filesmanagement.files.port.out.IFilesRepository;
import mca.filesmanagement.files.port.out.IPublicationService;

/**
 * Implementación del servicio que contiene los casos de uso para expedientes.
 *
 * @author agat
 */
@Service
public class FilesUseCaseImpl implements IFilesUseCase {

	@Autowired
	private IFilesRepository filesRepository;

	@Autowired
	private IPublicationService notificationService;

	/***/
	public FilesUseCaseImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createFile(String userName, CreateFileParams params) {
		FileAggregate fileAggregate = FactoryProvider.getFactoryFileAggregate().create();
		fileAggregate.setId(params.code());
		fileAggregate.setDescription(params.description());
		fileAggregate.setPhaseActual(params.phaseType());
		fileAggregate.setProcessCode(params.processCode());
		fileAggregate.addDocument(params.documentCode());
		InitialOptionDto initialDto = this.filesRepository.findInitialOption(params.initialoption());
		fileAggregate.setInitOption(InitOption.valueOf(initialDto.getCode()));
		this.filesRepository.createFile(userName, fileAggregate);

		fileAggregate.setFilesRepository(this.filesRepository);
		fileAggregate.setPublisher(this.notificationService);
		fileAggregate.afterCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changePhase(String processCode, String phaseType) {
		FileDetailDto fileDto = this.filesRepository.findByProces(processCode);
		/*
		 * El proceso podría todavía no existir al llegar primero la actualización
		 * antes de terminar la saga.
		 */
		if (Objects.nonNull(fileDto)) {
			FileAggregate fileAggregate = this.load(fileDto);
			fileAggregate.changePhase(phaseType);
			this.filesRepository.saveFile(fileAggregate);
		}
	}

	/**
	 * Genera un agregado de expediente a partir de un DTO.
	 * @param dto DTO con toda la información de un expediente.
	 * @return Agregado de expediente.
	 */
	private FileAggregate load(FileDetailDto dto) {
		FileAggregate fileAggregate = FactoryProvider.getFactoryFileAggregate().create();
		fileAggregate.setDescription(dto.getDescription());
		fileAggregate.setId(dto.getCode());

		InitialOptionDto initialDto = this.filesRepository.findInitialOption(dto.getInitOption());
		fileAggregate.setInitOption(InitOption.valueOf(initialDto.getCode()));
		fileAggregate.setPhaseActual(dto.getPhaseCode());
		fileAggregate.setProcessCode(dto.getProcesCode());
		fileAggregate.setPublisher(this.notificationService);
		fileAggregate.setFilesRepository(this.filesRepository);
		return fileAggregate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteByCode(String code) {
		this.filesRepository.deleteByCode(code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileDetailDto getByCode(String fileCode) {
		FileDetailDto fileDto = this.filesRepository.findByCode(fileCode);
		FileAggregate fileAggregate = this.load(fileDto);
		fileDto.setFinished(!fileAggregate.isActive());
		return fileDto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(FileUpdatedDto fileUpdated) {
		FileDetailDto fileDto = this.filesRepository.findByCode(fileUpdated.getCode());
		FileAggregate fileAggregate = this.load(fileDto);
		InitialOptionDto initialDto = this.filesRepository.findInitialOption(fileUpdated.getInitialOption());
		fileAggregate.update(fileUpdated.getDescription(), InitOption.valueOf(initialDto.getCode()));
		this.filesRepository.saveFile(fileAggregate);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<InitialOptionDto> findAllInitialOption(){
		return this.filesRepository.findAllInitialOption();
	}
}
