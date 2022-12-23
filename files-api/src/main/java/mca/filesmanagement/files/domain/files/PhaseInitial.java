package mca.filesmanagement.files.domain.files;

class PhaseInitial extends Phase {
	
	public static final String NAME = "INICIAL";

	public PhaseInitial() {
		super();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected String getType() {
		return NAME;
	}
}
