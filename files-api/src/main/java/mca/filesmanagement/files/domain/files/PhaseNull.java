package mca.filesmanagement.files.domain.files;

/**
 * Representación de la fase nula.
 *
 * @author agat
 */
class PhaseNull extends Phase {

	/***/
	PhaseNull() {
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
		return null;
	}
}
