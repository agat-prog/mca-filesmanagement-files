package mca.filesmanagement.files.domain.files;

class PhaseRefused extends Phase {

	public static final String NAME = "RECHAZADO";
	
	public PhaseRefused() {
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
