package mca.filesmanagement.files.domain.files;

import java.util.UUID;

import mca.filesmanagement.files.domain.IFactoryFileAggregate;

/**
 * Implementación principal de la factoría de agregados de expedientes.
 *
 * @author agat
 */
public class FactoryFileAggregate implements IFactoryFileAggregate {

	/***/
	public FactoryFileAggregate() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileAggregate create() {
		String uuid = UUID.randomUUID().toString();
		FileAggregate fileAggregate = new FileAggregate(uuid);
		return fileAggregate;
	}
}
