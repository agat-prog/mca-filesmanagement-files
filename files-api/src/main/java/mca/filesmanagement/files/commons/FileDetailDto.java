package mca.filesmanagement.files.commons;

import java.util.ArrayList;
import java.util.List;

public class FileDetailDto{
	
	private Long id;
	private String userName;
	private String code;
	private String description;
	private String procesCode;
	private String phaseCode;
	private long initOption;
	private boolean finished;
	private List<String> documents = new ArrayList<>(0);

	public FileDetailDto() {
		super();
	}

	/**
	 * @return the procesCode
	 */
	public String getProcesCode() {
		return procesCode;
	}

	/**
	 * @param procesCode the procesCode to set
	 */
	public void setProcesCode(String procesCode) {
		this.procesCode = procesCode;
	}

	/**
	 * @return the phaseCode
	 */
	public String getPhaseCode() {
		return phaseCode;
	}

	/**
	 * @param phaseCode the phaseCode to set
	 */
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the documents
	 */
	public List<String> getDocuments() {
		return documents;
	}
	
	public void addDocument(String document) {
		this.documents.add(document);
	}

	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @param finished the finished to set
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
