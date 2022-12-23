package mca.filesmanagement.files.domain.files;

public class PhaseFinished extends Phase {
	
	public static final String NAME = "FINALIZADO";

	public PhaseFinished() {
		super();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected String getType() {
		return NAME;
	}
}
