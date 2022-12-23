package mca.filesmanagement.files.domain.files;

class Process {

	private String code;
	private Phase phase;
	
	public Process() {
		super();
		
		this.phase = Phase.NULL_INSTANCE;
	}
	
	protected boolean isFinished() {
		return this.phase.isFinished();
	}
	
	protected void setPhaseActual(String phaseType) {
		FactoryPhase factoryPhase = new FactoryPhase();
		this.phase = factoryPhase.create(phaseType);
	}
	
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
