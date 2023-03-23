package mca.filesmanagement.files.domain.files;

/**
 * Clase del dominio correspondiente a la fase de finalización.
 *
 * @author agat
 */
public class PhaseFinished extends Phase {

	public static final String NAME = "FINALIZADO";

	/***/
	public PhaseFinished() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getType() {
		return NAME;
	}
}
