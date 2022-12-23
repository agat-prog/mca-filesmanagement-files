package mca.filesmanagement.files.domain;

import mca.filesmanagement.files.domain.files.FactoryFileAggregate;

public class FactoryProvider {

	private FactoryProvider() {
		super();
	}
	
	public static IFactoryFileAggregate getFactoryFileAggregate() {
		return new FactoryFileAggregate();
	}
}
