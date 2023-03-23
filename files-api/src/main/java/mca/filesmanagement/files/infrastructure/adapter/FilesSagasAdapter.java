package mca.filesmanagement.files.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import mca.filesmanagement.files.commons.FileNewDto;
import mca.filesmanagement.files.port.out.FilesSagas;
import mca.filesmanagement.files.service.CreateFileCommand;
import mca.filesmanagement.files.service.CreateFileSagaData;

/**
 * Adaptador necesario para la generación de SAGAs.
 *
 * @author agat
 */
@Service
public class FilesSagasAdapter implements FilesSagas {

	@Autowired
	private SagaInstanceFactory sagaInstanceFactory;

	@Autowired
	private CreateFileCommand createFileCommand;

	/***/
	public FilesSagasAdapter() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Long generateFileCommand(FileNewDto fileNewDto) {
		CreateFileSagaData fileSagaData = new CreateFileSagaData(fileNewDto);
		sagaInstanceFactory.create(createFileCommand, fileSagaData);

		return 1L;
	}
}
