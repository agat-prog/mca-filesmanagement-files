package mca.filesmanagement.files.port.out;

import java.util.List;

import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.InitOption;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.domain.files.FileAggregate;

public interface IFilesRepository {
	void createFile(String userName, FileAggregate fileAggregate);
	void saveFile(FileAggregate fileAggregate);
	void deleteByCode(String code);
	InitialOptionDto findInitialOption(InitOption initOption);
	InitialOptionDto findInitialOption(long id);
	List<InitialOptionDto> findAllInitialOption();
	FileDetailDto findByProces(String procesCode);
	FileDetailDto findByCode(String code);
}
