package mca.filesmanagement.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@EnableResourceServer
@SpringBootApplication
@Import({FilesConfiguration.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FilesApp {

	@Value("${mca.filesmanagement.files.oauth2.clientid}")
	private String oauthClientId;

	@Value("${mca.filesmanagement.files.oauth2.clientsecret}")
	private String oauthSecret;

	@Value("${mca.filesmanagement.files.oauth2.checktokenendpointurl}")
	private String checkTokenEndPoint;

	/**
	 * Entrada principal de la aplicación.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FilesApp.class, args);
	}

	/**
	 * Bean correspondiente al servicio remoto de obtención de token en OAuth2.
	 * @return ResourceServerTokenServices.
	 */
	@Bean
	public ResourceServerTokenServices tokenService() {
		RemoteTokenServices tokenService = new RemoteTokenServices();
		tokenService.setClientId(this.oauthClientId);
		tokenService.setClientSecret(this.oauthSecret);
		tokenService.setCheckTokenEndpointUrl(this.checkTokenEndPoint);
		return tokenService;
	}
}
