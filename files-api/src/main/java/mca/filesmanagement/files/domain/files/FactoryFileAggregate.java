package mca.filesmanagement.files.domain.files;

import java.util.UUID;

import mca.filesmanagement.files.domain.IFactoryFileAggregate;

public class FactoryFileAggregate implements IFactoryFileAggregate {

	public FactoryFileAggregate() {
		super();
	}
	
	@Override
	public FileAggregate create() {
		String uuid = UUID.randomUUID().toString();
		FileAggregate fileAggregate = new FileAggregate(uuid);
		return fileAggregate;
	}
}
