package mca.filesmanagement.files.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import mca.filesmanagement.files.commons.FileNewDto;
import mca.filesmanagement.files.port.out.FilesSagas;
import mca.filesmanagement.files.service.CreateFileCommand;
import mca.filesmanagement.files.service.CreateFileSagaData;

@Service
public class FilesSagasAdapter implements FilesSagas {
	
	@Autowired
	private SagaInstanceFactory sagaInstanceFactory;
	
	@Autowired
	private CreateFileCommand createFileCommand;
	  
	public FilesSagasAdapter() {
		super();
	}

	@Override
	@Transactional
	public Long generateFileCommand(FileNewDto fileNewDto) {
		CreateFileSagaData fileSagaData = new CreateFileSagaData(fileNewDto);
		sagaInstanceFactory.create(createFileCommand, fileSagaData);
		
		return 1l;
	}
}
