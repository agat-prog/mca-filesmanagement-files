package mca.filesmanagement.files.domain.files;

/**
 * Clase del dominio para los documentos.
 *
 * @author agat
 */
public class Document {

	private String code;

	/**
	 * Constructor con el código de documento único.
	 * @param code Código único
	 */
	public Document(String code) {
		super();

		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
