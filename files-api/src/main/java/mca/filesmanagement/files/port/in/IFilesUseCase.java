package mca.filesmanagement.files.port.in;

import java.util.List;

import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.FileUpdatedDto;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.domain.usescases.CreateFileParams;

public interface IFilesUseCase {
	void createFile(String userName, CreateFileParams params);
	void deleteByCode(String code);
	void changePhase(String processCode, String phaseType);
	FileDetailDto getByCode(String fileCode);
	void update(FileUpdatedDto fileUpdated);
	List<InitialOptionDto> findAllInitialOption();
}
