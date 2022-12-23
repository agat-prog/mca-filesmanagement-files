package mca.filesmanagement.files.domain.files;

abstract class Phase {

	protected static final PhaseNull NULL_INSTANCE = new PhaseNull();
	
	public Phase() {
		super();
	}
	
	protected abstract boolean isFinished();
	protected abstract String getType();

}
