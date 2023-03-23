package mca.filesmanagement.files.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.files.infrastructure.model.InitialOptionEntity;

/**
 * Repositorio de JPA para el acceso a la información de las formas de inicio.
 *
 * @author agat
 */
@Repository
public interface JpaInitialOptionRepository extends JpaRepository<InitialOptionEntity, Long> {

	/**
	 * Devuelve la entidad de la forma de inicio a partir de su código.
	 * @param code Código único de la forma de inicio.
	 * @return Entidad de JPA de la forma de inicio.
	 */
	InitialOptionEntity findByCode(String code);
}
