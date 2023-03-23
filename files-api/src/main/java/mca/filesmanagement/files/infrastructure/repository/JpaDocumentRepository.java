package mca.filesmanagement.files.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.files.infrastructure.model.DocumentEntity;

/**
 * Repositorio de JPA para el acceso a la información de documentos.
 *
 * @author agat
 */
@Repository
public interface JpaDocumentRepository extends JpaRepository<DocumentEntity, Long> {

	/**
	 * Devuelve el documento a partir de su código externo único.
	 * @param code Código externo único.
	 * @return Entidad de JPA del documento.
	 */
	DocumentEntity findByCode(String code);
}
