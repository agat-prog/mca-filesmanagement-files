package mca.filesmanagement.files.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;

@Configuration
public class ServiceConfiguration {

	@Bean
	public DomainEventDispatcher fileDomainEventDispatcher(FilesService fileService, DomainEventDispatcherFactory domainEventDispatcherFactory) {
		return domainEventDispatcherFactory.make("fileDomainEventDispatcher", fileService.processDomainEventHandlers());
	}
}
