package mca.filesmanagement.files.port.out;

import mca.filesmanagement.files.commons.FileNewDto;

public interface FilesSagas {
	Long generateFileCommand(FileNewDto fileNewDto);
}
