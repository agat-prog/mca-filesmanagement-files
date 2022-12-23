package mca.filesmanagement.files.domain.files;

class PhaseNull extends Phase {

	public PhaseNull() {
		super();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	
	@Override
	protected String getType() {
		return null;
	}
}
