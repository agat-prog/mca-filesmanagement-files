package mca.filesmanagement.files.controller;

import java.util.Objects;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Controlador padre del resto de controller.
 *
 * @author agat
 */
public abstract class AbstractController {

	protected static final String HAS_ADMIN = "hasAuthority('ADMIN')";

	/**
	 * Devuelve el username asociado al contexto de sesión.
	 * @return Username.
	 */
	protected final String getUserName() {
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (Objects.nonNull(authentication)
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		return currentUserName;
	}
}
