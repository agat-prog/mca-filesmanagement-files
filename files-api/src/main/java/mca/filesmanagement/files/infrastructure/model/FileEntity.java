package mca.filesmanagement.files.infrastructure.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FILE")
public class FileEntity {

	@Id
	@Column (name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "ID_INIT_OPTION", nullable = false)
	private long initOption;
	
	@Column (name = "USER", nullable = false, updatable = false)
	private String user;
	
	@Column (name = "ACTIVE")
	private boolean active;
	
	@Column (name = "CODE", nullable = false, updatable = false)
	private String code;
	
	@Column (name = "DESCRIPTION")
	private String description;

	@Column (name = "PHASE_ACTUAL", nullable = false)
	private String phaseActual;

	@Column (name = "PROCES_CODE", nullable = false, updatable = false)
	private String proces;	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable (
			name = "FILE_DOCUMENT"
			, joinColumns = {@JoinColumn(name="FILE_ID")}
			, inverseJoinColumns = {@JoinColumn(name="DOCUMENT_ID")}
	)
	private List<DocumentEntity> documents = new ArrayList<>(0);
	
	public FileEntity() {
		super();
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
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the phaseActual
	 */
	public String getPhaseActual() {
		return phaseActual;
	}

	/**
	 * @param phaseActual the phaseActual to set
	 */
	public void setPhaseActual(String phaseActual) {
		this.phaseActual = phaseActual;
	}

	/**
	 * @return the proces
	 */
	public String getProces() {
		return proces;
	}

	/**
	 * @param proces the proces to set
	 */
	public void setProces(String proces) {
		this.proces = proces;
	}

	/**
	 * @return the initOption
	 */
	public long getInitOption() {
		return initOption;
	}

	/**
	 * @param initOption the initOption to set
	 */
	public void setInitOption(long initOption) {
		this.initOption = initOption;
	}

	/**
	 * @return the documents
	 */
	public List<DocumentEntity> getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(List<DocumentEntity> documents) {
		this.documents = documents;
	}
}
