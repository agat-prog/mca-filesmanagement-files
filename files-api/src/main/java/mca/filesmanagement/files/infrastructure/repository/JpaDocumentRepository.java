package mca.filesmanagement.files.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.files.infrastructure.model.DocumentEntity;

@Repository
public interface JpaDocumentRepository extends JpaRepository<DocumentEntity, Long> {
	
	DocumentEntity findByCode(String code);
}
