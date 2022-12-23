package mca.filesmanagement.files.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mca.filesmanagement.files.infrastructure.model.InitialOptionEntity;

@Repository
public interface JpaInitialOptionRepository extends JpaRepository<InitialOptionEntity, Long> {
	InitialOptionEntity findByCode(String code);
}
