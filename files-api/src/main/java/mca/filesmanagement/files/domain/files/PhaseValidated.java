package mca.filesmanagement.files.domain.files;

/**
 * Clase del dominio correspondiente a la fase de validación.
 *
 * @author agat
 */
class PhaseValidated extends Phase {

	public static final String NAME = "VALIDADO";

	/***/
	PhaseValidated() {
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
