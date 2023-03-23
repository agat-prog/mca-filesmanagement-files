package mca.filesmanagement.files.domain.files;

/**
 * Clase padre del dominio para las fases.
 *
 * @author agat
 */
abstract class Phase {

	protected static final PhaseNull NULL_INSTANCE = new PhaseNull();

	/***/
	Phase() {
		super();
	}

	/**
	 * Si la fase está finalizada.
	 * @return Devuelve true si la fase está finalizada o false en caso contrario.
	 */
	protected abstract boolean isFinished();

	/**
	 * Devuelve el tipo de la fase.
	 * @return Identificador del tipo de la fase.
	 */
	protected abstract String getType();

}
