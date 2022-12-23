package mca.filesmanagement.files.domain.usescases;

public record CreateFileParams(Long initialoption, String code, String description, String phaseType, String processCode, String documentCode) {}
