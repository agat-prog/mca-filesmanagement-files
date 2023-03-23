package mca.filesmanagement.files.port.out;

import mca.filesmanagement.files.commons.FileNewDto;

/**
 * Contrato la generación de SAGAs.
 *
 * @author agat
 */
public interface FilesSagas {

	/**
	 * Genera una SAGA en la creación de un expediente.
	 * @param fileNewDto DTO con la información necesaria para la creación de un expediente.
	 * @return Identificador del expediente creado.
	 */
	Long generateFileCommand(FileNewDto fileNewDto);
}
