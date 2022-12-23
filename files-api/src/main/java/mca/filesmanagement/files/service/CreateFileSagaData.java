package mca.filesmanagement.files.service;

import mca.filesmanagement.files.commons.FileNewDto;

public class CreateFileSagaData {

	private String bpmUuid;
	private String phaseCode;
	private String docUuid;
	private FileNewDto fileNewDto;
	
	public CreateFileSagaData() {
		super();
		
		this.fileNewDto = new FileNewDto();
	}
	
	public CreateFileSagaData(FileNewDto fileNewDto) {
		super();
		
		this.fileNewDto = fileNewDto;
	}

	/**
	 * @return the bpmUuid
	 */
	public String getBpmUuid() {
		return bpmUuid;
	}

	/**
	 * @param bpmUuid the bpmUuid to set
	 */
	public void setBpmUuid(String bpmUuid) {
		this.bpmUuid = bpmUuid;
	}

	/**
	 * @return
	 * @see mca.filesmanagement.files.commons.FileDetailDto#getPhaseCode()
	 */
	public String getPhaseCode() {
		return this.phaseCode;
	}

	/**
	 * @param phaseCode
	 * @see mca.filesmanagement.files.commons.FileDetailDto#setPhaseCode(java.lang.String)
	 */
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	/**
	 * @return
	 * @see mca.filesmanagement.files.commons.FileNewDto#getUserName()
	 */
	public String getUserName() {
		return fileNewDto.getUserName();
	}

	/**
	 * @return
	 * @see mca.filesmanagement.files.commons.FileNewDto#getCode()
	 */
	public String getCode() {
		return fileNewDto.getCode();
	}

	/**
	 * @return
	 * @see mca.filesmanagement.files.commons.FileNewDto#getDescription()
	 */
	public String getDescription() {
		return fileNewDto.getDescription();
	}

	/**
	 * @return
	 * @see mca.filesmanagement.files.commons.FileNewDto#getArchiveName()
	 */
	public String getArchiveName() {
		return fileNewDto.getArchiveName();
	}

	/**
	 * @return
	 * @see mca.filesmanagement.files.commons.FileNewDto#getArchiveContentBase64()
	 */
	public String getArchiveContentBase64() {
		return fileNewDto.getArchiveContentBase64();
	}

	/**
	 * @return the docUuid
	 */
	public String getDocUuid() {
		return docUuid;
	}

	/**
	 * @param docUuid the docUuid to set
	 */
	public void setDocUuid(String docUuid) {
		this.docUuid = docUuid;
	}

	/**
	 * @return the fileNewDto
	 */
	public FileNewDto getFileNewDto() {
		return fileNewDto;
	}

	/**
	 * @param fileNewDto the fileNewDto to set
	 */
	public void setFileNewDto(FileNewDto fileNewDto) {
		this.fileNewDto = fileNewDto;
	}

	/**
	 * @return
	 * @see mca.filesmanagement.files.commons.FileNewDto#getInitOption()
	 */
	public long getInitOption() {
		return fileNewDto.getInitOption();
	}
}
