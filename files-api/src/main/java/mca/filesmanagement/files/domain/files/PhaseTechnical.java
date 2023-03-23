package mca.filesmanagement.files.domain.files;

/**
 * Clase del dominio correspondiente a la fase de análisis técnico.
 *
 * @author agat
 */
class PhaseTechnical extends Phase {

	public static final String NAME = "ANALISIS_TECNICO";

	/***/
	PhaseTechnical() {
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
