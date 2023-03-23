package mca.filesmanagement.files.port.out;

import files.api.messaging.events.FileDomainEvent;
import mca.filesmanagement.files.domain.files.FileAggregate;

/**
 * Contrato para la publicación de eventos.
 *
 * @author agat
 */
public interface IPublicationService {

	/**
	 * Genera un evento en la cola de eventos correspondiente.
	 * @param aggregate Agregado del expediente con todos su datos.
	 * @param event Evento generado.
	 */
	void notify(FileAggregate aggregate, FileDomainEvent event);
}
