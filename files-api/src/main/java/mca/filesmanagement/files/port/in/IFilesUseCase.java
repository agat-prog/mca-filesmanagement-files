package mca.filesmanagement.files.port.in;

import java.util.List;

import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.FileUpdatedDto;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.domain.usescases.CreateFileParams;

/**
 * Contrato para los casos de uso de los expedientes.
 *
 * @author agat
 */
public interface IFilesUseCase {

	/**
	 * Crea un expediente a partir de sus datos obligatorios.
	 * @param userName Identificador del usuario.
	 * @param params Parámetros de creación.
	 */
	void createFile(String userName, CreateFileParams params);

	/**
	 * Elimina un expediente a partir de su código único.
	 * @param code Código único del expediente a eliminar.
	 */
	void deleteByCode(String code);

	/**
	 * Cambia la fase de un expediente determinado.
	 * @param processCode Código externo único del proceso.
	 * @param phaseType Fase a la que tramitar.
	 */
	void changePhase(String processCode, String phaseType);

	/**
	 * Devuelve el detalle de un expediente a partir de su código.
	 * @param fileCode Código del expediente.
	 * @return DTO con el detalle del expediente.
	 */
	FileDetailDto getByCode(String fileCode);

	/**
	 * Actualiza un expediente.
	 * @param fileUpdated DTO con los datos de actualización del expediente.
	 */
	void update(FileUpdatedDto fileUpdated);

	/**
	 * Devuelve la lista de formas de inicio.
	 * @return Lista
	 */
	List<InitialOptionDto> findAllInitialOption();
}
