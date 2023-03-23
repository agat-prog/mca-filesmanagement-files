package mca.filesmanagement.files.domain.files;

/**
 * Factoría de fases.
 *
 * @author agat
 */
public class FactoryPhase {

	/***/
	public FactoryPhase() {
		super();
	}

	/**
	 * Crea una fase en base a su tipo.
	 * @param type Tipo o identificador de fase.
	 * @return Phase.
	 */
	public Phase create(String type) {
		if (PhaseInitial.NAME.equals(type)) {
			return new PhaseInitial();
		} else if (PhaseFinished.NAME.equals(type)) {
			return new PhaseFinished();
		} else if (PhaseRefused.NAME.equals(type)) {
			return new PhaseRefused();
		} else if (PhaseTechnical.NAME.equals(type)) {
			return new PhaseTechnical();
		} else if (PhaseValidated.NAME.equals(type)) {
			return new PhaseValidated();
		} else {
			return Phase.NULL_INSTANCE;
		}
	}
}
