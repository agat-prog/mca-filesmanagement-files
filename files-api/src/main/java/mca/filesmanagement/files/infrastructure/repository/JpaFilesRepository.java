package mca.filesmanagement.files.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.files.infrastructure.model.FileEntity;

@Repository
public interface JpaFilesRepository extends JpaRepository<FileEntity, Long> {
	void deleteByCode(String code);
	FileEntity findByCode(String code);
	FileEntity findByProces(String procesCode);
}
