package mca.filesmanagement.files.domain.files;

class PhaseTechnical extends Phase {

	public static final String NAME = "ANALISIS_TECNICO";
	
	public PhaseTechnical() {
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
