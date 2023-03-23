package mca.filesmanagement.files.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.InitOption;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.domain.files.Document;
import mca.filesmanagement.files.domain.files.FileAggregate;
import mca.filesmanagement.files.infrastructure.model.DocumentEntity;
import mca.filesmanagement.files.infrastructure.model.FileEntity;
import mca.filesmanagement.files.infrastructure.repository.JpaDocumentRepository;
import mca.filesmanagement.files.infrastructure.repository.JpaFilesRepository;
import mca.filesmanagement.files.infrastructure.repository.JpaInitialOptionRepository;
import mca.filesmanagement.files.port.out.IFilesRepository;

/**
 * Adaptador del repositorio externo de acceso a base de datos.
 *
 * @author agat
 */
@Service
public class FilesRepositoryAdapter implements IFilesRepository {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JpaFilesRepository jpaFilesRepository;

	@Autowired
	private JpaDocumentRepository jpaDocumentRepository;

	@Autowired
	private JpaInitialOptionRepository jpaInitialOptionRepository;

	/***/
	public FilesRepositoryAdapter() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void createFile(String userName, FileAggregate fileAggregate) {
		List<DocumentEntity> listDocuments = new ArrayList<>(1);
		listDocuments.add(jpaDocumentRepository.save(new DocumentEntity(fileAggregate.getFirstDocument().orElseThrow().getCode())));

		FileEntity fileEntity = new FileEntity();
		fileEntity.setActive(fileAggregate.isActive());
		fileEntity.setCode(fileAggregate.getId());
		fileEntity.setDescription(fileAggregate.getDescription());
		fileEntity.setPhaseActual(fileAggregate.getPhaseCode());
		fileEntity.setProces(fileAggregate.getProcessCode());
		fileEntity.setUser(userName);
		fileEntity.setInitOption(jpaInitialOptionRepository.findByCode(fileAggregate.getInitOption().name()).getId());
		fileEntity.getDocuments().addAll(listDocuments);
		this.jpaFilesRepository.save(fileEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void saveFile(FileAggregate fileAggregate) {
		List<DocumentEntity> listDocuments = new ArrayList<>(1);

		fileAggregate.getAllDocument().forEach(d -> listDocuments.add(this.getOrCreate(d)));

		FileEntity fileEntity = this.jpaFilesRepository.findByCode(fileAggregate.getId());
		fileEntity.setActive(fileAggregate.isActive());
		fileEntity.setDescription(fileAggregate.getDescription());
		fileEntity.setInitOption(jpaInitialOptionRepository.findByCode(fileAggregate.getInitOption().name()).getId());
		fileEntity.setPhaseActual(fileAggregate.getPhaseCode());
		fileEntity.getDocuments().addAll(listDocuments);

		this.jpaFilesRepository.save(fileEntity);
	}

	/**
	 * Devuelve o crea si no existe, un documento a partir de su código.
	 * @param document Documento a crear.
	 * @return Entidad del documento.
	 */
	private DocumentEntity getOrCreate(Document document) {
		DocumentEntity entity = this.jpaDocumentRepository.findByCode(document.getCode());
		if (Objects.isNull(entity)) {
			entity = new DocumentEntity();
			entity.setCode(document.getCode());
			this.jpaDocumentRepository.save(entity);
		}
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileDetailDto findByProces(String procesCode) {
		FileEntity fileEntity = this.jpaFilesRepository.findByProces(procesCode);
		return this.toDto(fileEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileDetailDto findByCode(String code) {
		FileEntity fileEntity = this.jpaFilesRepository.findByCode(code);
		return this.toDto(fileEntity);
	}

	/**
	 * Realiza el mapeo desde una entidad de expediente a su versión DTO.
	 * @param entity Entidad JPA del expediente.
	 * @return DTO con el detalle del expediente.
	 */
	private FileDetailDto toDto(FileEntity entity) {
		if (Objects.nonNull(entity)) {
			FileDetailDto dto = new FileDetailDto();
			dto.setCode(entity.getCode());
			dto.setDescription(entity.getDescription());
			dto.setId(entity.getId());
			dto.setInitOption(entity.getInitOption());
			dto.setPhaseCode(entity.getPhaseActual());
			dto.setProcesCode(entity.getProces());
			dto.setUserName(entity.getUser());
			entity.getDocuments().stream()
								.map(this::toDto)
								.toList()
								.forEach(d -> dto.addDocument(d));
			return dto;
		}

		return null;
	}

	/**
	 * Devuelve el código de un documento.
	 * @param entity Entidad de JPA del documento.
	 * @return Código único del documento.
	 */
	private String toDto(DocumentEntity entity) {
		return entity.getCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteByCode(String code) {
		this.jpaFilesRepository.deleteByCode(code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public InitialOptionDto findInitialOption(InitOption initOption) {
		return this.modelMapper.map(jpaInitialOptionRepository.findByCode(initOption.name()), InitialOptionDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public InitialOptionDto findInitialOption(long id) {
		return this.modelMapper.map(jpaInitialOptionRepository.findById(id), InitialOptionDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<InitialOptionDto> findAllInitialOption() {
		return this.jpaInitialOptionRepository.findAll().stream().map(opt -> this.modelMapper.map(opt, InitialOptionDto.class)).toList();
	}
}
