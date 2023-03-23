package mca.filesmanagement.files.infrastructure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad de JPA de un documento.
 *
 * @author agat
 */
@Entity
@Table(name = "DOCUMENT")
public class DocumentEntity {

	@Id
	@Column (name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (name = "CODE", nullable = false, updatable = false, unique = true)
	private String code;

	/***/
	public DocumentEntity() {
		super();
	}

	/**
	 * Crea una instancia con el código inicializado.
	 * @param code Código único del documento.
	 */
	public DocumentEntity(String code) {
		super();

		this.code = code;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
