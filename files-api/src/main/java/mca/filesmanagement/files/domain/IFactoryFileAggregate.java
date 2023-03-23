package mca.filesmanagement.files.domain;

import mca.filesmanagement.files.domain.files.FileAggregate;

/**
 * Contrato para cualquier factoría de agregados de expedientes.
 *
 * @author agat
 */
public interface IFactoryFileAggregate {

	/**
	 * Crea una agregado de expediente.
	 * @return FileAggregate.
	 */
	FileAggregate create();
}
