package mca.filesmanagement.files.domain.files;

class PhaseValidated extends Phase {
	
	public static final String NAME = "VALIDADO";

	public PhaseValidated() {
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
