package mca.filesmanagement.files.domain.files;

/**
 * Clase del dominio correspondiente a la fase de inicial.
 *
 * @author agat
 */
class PhaseInitial extends Phase {

	public static final String NAME = "INICIAL";

	/***/
	PhaseInitial() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getType() {
		return NAME;
	}
}
