package mca.filesmanagement.files.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.files.infrastructure.model.FileEntity;

/**
 * Repositorio de JPA para el acceso a la información de expdientes.
 *
 * @author agat
 */
@Repository
public interface JpaFilesRepository extends JpaRepository<FileEntity, Long> {
	/**
	 * Elimina un expediente a partir de su código externo único.
	 * @param code Código externo único.
	 */
	void deleteByCode(String code);

	/**
	 * Devuelve el expediente a partir de su código externo.
	 * @param code Código externo único.
	 * @return Entidad de JPA del expediente encontrado.
	 */
	FileEntity findByCode(String code);

	/**
	 * Devuelve el expediente a partir del código externo del proceso asociado.
	 * @param procesCode Código externo único del proceso BPM.
	 * @return Entidad de JPA del expediente encontrado.
	 */
	FileEntity findByProces(String procesCode);
}
