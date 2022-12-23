package mca.filesmanagement.files.commons;

public class FileNewDto {
	
	private String userName;
	private String code;
	private String description;
	private String archiveName;
	private String archiveContentBase64;
	private long initOption;
	
	public FileNewDto() {
		super();
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
	 * @return the archiveName
	 */
	public String getArchiveName() {
		return archiveName;
	}

	/**
	 * @param archiveName the archiveName to set
	 */
	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	/**
	 * @return the archiveContentBase64
	 */
	public String getArchiveContentBase64() {
		return archiveContentBase64;
	}

	/**
	 * @param archiveContentBase64 the archiveContentBase64 to set
	 */
	public void setArchiveContentBase64(String archiveContentBase64) {
		this.archiveContentBase64 = archiveContentBase64;
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
}
