package mca.filesmanagement.files.port.out;

import java.util.List;

import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.InitOption;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.domain.files.FileAggregate;

/**
 * Contrato para el puerto de salida de acceso a datos externos.
 *
 * @author agat
 */
public interface IFilesRepository {

	/**
	 * Crea un expediente a partir de un agregado.
	 * @param userName Identificador del expediente.
	 * @param fileAggregate Agregado del expediente.
	 */
	void createFile(String userName, FileAggregate fileAggregate);

	/**
	 * Guarda un agregado en persistencia.
	 * @param fileAggregate Agregado del expediente.
	 */
	void saveFile(FileAggregate fileAggregate);

	/**
	 * Elimina un expediente a partir de su código identificativo.
	 * @param code Código único identificativo.
	 */
	void deleteByCode(String code);

	/**
	 * Devuelve un DTO de una forma de inicio a partir de su objeto del dominio.
	 *
	 * @param initOption Forma de inicio.
	 * @return DTO.
	 */
	InitialOptionDto findInitialOption(InitOption initOption);

	/**
	 * Devuelve una forma de inicio a partir de su ID.
	 * @param id Identificador PK de base de datos.
	 * @return DTO.
	 */
	InitialOptionDto findInitialOption(long id);

	/**
	 * Devuelve todas las formas de inicio.
	 * @return Lista.
	 */
	List<InitialOptionDto> findAllInitialOption();

	/**
	 * Devuelve el detalle de un expediente a partir de su identificador de proceso BPM.
	 * @param procesCode Código externo único del proceso BPM asociado.
	 * @return DTO del expediente.
	 */
	FileDetailDto findByProces(String procesCode);

	/**
	 * Devuelve el detalle de un expediente a partir de su código externo.
	 * @param code Código externo único.
	 * @return DTO con el detalle de un expediente.
	 */
	FileDetailDto findByCode(String code);
}
