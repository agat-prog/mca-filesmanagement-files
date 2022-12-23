package mca.filesmanagement.files.infrastructure.adapter;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import files.api.messaging.events.FileDomainEvent;
import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import mca.filesmanagement.files.domain.files.FileAggregate;
import mca.filesmanagement.files.port.out.IPublicationService;

@Service
public class PublicationService extends AbstractAggregateDomainEventPublisher<FileAggregate, FileDomainEvent> implements IPublicationService {

	private static Logger LOG = LoggerFactory.getLogger(PublicationService.class);
	
	public PublicationService(DomainEventPublisher eventPublisher) {
		super(eventPublisher, FileAggregate.class, FileAggregate::getId);
	}
	
	@Override
	public void notify(FileAggregate aggregate, FileDomainEvent event) {
		LOG.info("NotificationService.notify: "+ aggregate.getId());
		this.publish(aggregate, Arrays.asList(event));
	}
}
