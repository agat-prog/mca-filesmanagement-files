package mca.filesmanagement.files;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//@Configuration
public class ResourceServerEndpointConfig extends ResourceServerConfigurerAdapter {

	public ResourceServerEndpointConfig() {
		super();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//http.authorizeRequests().antMatchers("/api/files/echo").access("hasAuthority('ADMIN')");
		//.anyRequest();
		//http.authorizeRequests().antMatchers("/api/files/echo").access("isAuthenticated()");
	}
}
