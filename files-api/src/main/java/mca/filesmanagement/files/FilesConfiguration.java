package mca.filesmanagement.files;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import mca.filesmanagement.files.port.in.IFilesUseCase;
import mca.filesmanagement.files.service.CreateFileCommand;
import mca.filesmanagement.files.service.ServiceConfiguration;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
@Import({OptimisticLockingDecoratorConfiguration.class, ServiceConfiguration.class})
public class FilesConfiguration {

	/**
	 * Bean correspondiente al comando de creación de expedientes.
	 * @param filesUseCase Servicio con la implementación de los casos de uso de expedientes.
	 * @return CreateFileCommand
	 */
	@Bean
	public CreateFileCommand createFileCommand(IFilesUseCase filesUseCase) {
		return new CreateFileCommand(filesUseCase);
	}

	/**
	 * Mapper para la transformación de DTO.
	 * @return ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
