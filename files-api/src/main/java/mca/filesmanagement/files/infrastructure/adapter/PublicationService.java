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

/**
 * Servicio de publicación de eventos para el agregado de expedientes.
 *
 * @author agat
 */
@Service
public class PublicationService
		extends
			AbstractAggregateDomainEventPublisher<FileAggregate, FileDomainEvent>
		implements
			IPublicationService {

	private static Logger LOG = LoggerFactory
			.getLogger(PublicationService.class);

	/**
	 * Crea una instancia de un servicio de publicación inyectando el Publisher de eventuate.
	 * @param eventPublisher DomainEventPublisher.
	 */
	public PublicationService(DomainEventPublisher eventPublisher) {
		super(eventPublisher, FileAggregate.class, FileAggregate::getId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notify(FileAggregate aggregate, FileDomainEvent event) {
		LOG.info("NotificationService.notify: " + aggregate.getId());
		this.publish(aggregate, Arrays.asList(event));
	}
}
