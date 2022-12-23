package mca.filesmanagement.files.port.out;

import files.api.messaging.events.FileDomainEvent;
import mca.filesmanagement.files.domain.files.FileAggregate;

public interface IPublicationService {
	void notify(FileAggregate aggregate, FileDomainEvent event);
}
