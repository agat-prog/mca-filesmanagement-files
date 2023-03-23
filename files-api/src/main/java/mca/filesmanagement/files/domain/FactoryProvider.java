package mca.filesmanagement.files.domain;

import mca.filesmanagement.files.domain.files.FactoryFileAggregate;

/**
 * Proveedor de factorías de agregados de expedientes.
 *
 * @author agat
 */
public final class FactoryProvider {

	/***/
	private FactoryProvider() {
		super();
	}

	/**
	 * Devuelve una factoría de un agregado de expediente.
	 * @return IFactoryFileAggregate
	 */
	public static IFactoryFileAggregate getFactoryFileAggregate() {
		return new FactoryFileAggregate();
	}
}
