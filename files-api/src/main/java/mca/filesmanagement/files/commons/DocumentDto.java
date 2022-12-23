package mca.filesmanagement.files.commons;

public class DocumentDto {
	
	private Long id;
	private String code;
	private String name;
	private String contentBase64;
	private String originPath;

	public DocumentDto() {
		super();
	}
	
	public DocumentDto(String code) {
		super();
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the contentBase64
	 */
	public String getContentBase64() {
		return contentBase64;
	}

	/**
	 * @param contentBase64 the contentBase64 to set
	 */
	public void setContentBase64(String contentBase64) {
		this.contentBase64 = contentBase64;
	}

	/**
	 * @return the originPath
	 */
	public String getOriginPath() {
		return originPath;
	}

	/**
	 * @param originPath the originPath to set
	 */
	public void setOriginPath(String originPath) {
		this.originPath = originPath;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param uuid the code to set
	 */
	public void setCode(String code) {
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
}
