package mca.filesmanagement.files.commons;

import org.springframework.util.Base64Utils;

import mca.filesmanagement.files.domain.files.FactoryFileAggregate;
import mca.filesmanagement.files.domain.files.FileAggregate;
import mca.filesmanagement.files.infrastructure.model.DocumentEntity;
import mca.filesmanagement.files.infrastructure.model.FileEntity;
import mca.filesmanagement.files.infrastructure.model.InitialOptionEntity;

public class FileDummieFactory {

	public static final String CODE_FORMAT = "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX";
	public static final String DESCRIPCION_FORMAT = "descripcion_%s";
	public static final String USER_FORMAT = "user_%s";
	public static final String NAME_FORMAT = "name_%s";
	public static final String CONTENIDO_FORMAT = "contenido_%s";

	/***/
	public FileDummieFactory() {
		super();
	}
	
	public static InitialOptionDto createInitialOption(long id) {
		InitialOptionDto dto = new InitialOptionDto();
		dto.setCode(InitOption.SEDE.name());
		dto.setDescription(String.format(DESCRIPCION_FORMAT, id));
		dto.setId(id);
		return dto;
	}
	
	public static InitialOptionEntity createInitialOptionEntity(long id) {
		InitialOptionEntity entity = new InitialOptionEntity();
		entity.setCode(InitOption.SEDE.name());
		entity.setDescription(String.format(DESCRIPCION_FORMAT, id));
		entity.setId(id);
		return entity;
	}
	
	public static FileDetailDto createFileDetail(long id) {
		FileDetailDto dto = new FileDetailDto();
		dto.setCode(generateCode(id));
		dto.setDescription(String.format(DESCRIPCION_FORMAT, id));
		dto.setFinished(false);
		dto.setId(id);
		dto.setInitOption(id);
		dto.setPhaseCode(generateCode(id));
		dto.setProcesCode(generateCode(id));
		dto.setUserName(String.format(USER_FORMAT, id));
		return dto;
	}
	
	public static FileEntity createFileEntity(long id) {
		FileEntity dto = new FileEntity();
		dto.setCode(generateCode(id));
		dto.setDescription(String.format(DESCRIPCION_FORMAT, id));
		dto.setId(id);
		dto.setInitOption(id);
		dto.setActive(true);
		dto.setProces(generateCode(id));
		dto.setUser(String.format(USER_FORMAT, id));
		return dto;
	}
	
	public static FileUpdatedDto createFileUpdate(long id) {
		FileUpdatedDto dto = new FileUpdatedDto();
		dto.setCode(generateCode(id));
		dto.setDescription(String.format(DESCRIPCION_FORMAT, id));
		dto.setInitialOption(id);
		return dto;
	}
	
	public static FileNewDto createFileNew(long id) {
		FileNewDto dto = new FileNewDto();
		dto.setArchiveName(String.format(NAME_FORMAT, id));
		dto.setDescription(String.format(DESCRIPCION_FORMAT, id));
		dto.setInitOption(id);
		dto.setUserName(String.format(USER_FORMAT, id));
		dto.setCode(generateCode(id));
		dto.setArchiveContentBase64(Base64Utils.encodeToString(String.format(CONTENIDO_FORMAT, id).getBytes()));
		return dto;
	}
	
	public static DocumentEntity createDocumentEntity(long id) {
		DocumentEntity entity = new DocumentEntity();
		entity.setCode(generateCode(id));
		entity.setId(id);
		return entity;
	}
	
	public static FileAggregate createFileAggregate(long id) {
		FileAggregate fileAggregate = new FactoryFileAggregate().create();
		fileAggregate.setId(FileDummieFactory.generateCode(id));
		fileAggregate.setPublisher(null);
		fileAggregate.setFilesRepository(null);
		fileAggregate.addDocument(generateCode(id));
		return fileAggregate;
	}

	/**
	 * Genera un UUID relleno de un mismo número.
	 * @param id ID.
	 * @return String
	 */
	public static String generateCode(long id) {
		return CODE_FORMAT.replaceAll("X", String.valueOf(id));
	}
}
