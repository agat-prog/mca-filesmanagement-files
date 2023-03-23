package mca.filesmanagement.files.domain.files;

/**
 * Clase del dominio que representa un proceso del BPM asociado al expediente.
 *
 * @author agat
 */
class Process {

	private String code;
	private Phase phase;

	/***/
	Process() {
		super();

		this.phase = Phase.NULL_INSTANCE;
	}

	/**
	 * Indica si el proceso ha finalizado.
	 * @return true si está finalizado o false en caso contrario.
	 */
	protected boolean isFinished() {
		return this.phase.isFinished();
	}

	/**
	 * Establece la fase actual del expediente.
	 * @param phaseType Tipo de la fase.
	 */
	protected void setPhaseActual(String phaseType) {
		FactoryPhase factoryPhase = new FactoryPhase();
		this.phase = factoryPhase.create(phaseType);
	}

	/**
	 * Devuelve la fase actual del expediente.
	 * @return Tipo de la fase.
	 */
	public String getPhaseActualType() {
		return this.phase.getType();
	}

	/**
	 * @param code the code to set
	 */
	protected void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param phase the phase to set
	 */
	protected void setPhase(Phase phase) {
		this.phase = phase;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
