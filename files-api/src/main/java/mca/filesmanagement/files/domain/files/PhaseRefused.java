package mca.filesmanagement.files.domain.files;

/**
 * Clase del dominio correspondiente a la fase de rechazo.
 *
 * @author agat
 */
class PhaseRefused extends Phase {

	public static final String NAME = "RECHAZADO";

	/***/
	PhaseRefused() {
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
