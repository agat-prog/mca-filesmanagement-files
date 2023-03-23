package mca.filesmanagement.files.domain.usescases;

/**
 * DTO con los parámetros necesarios para la creación de un expediente.
 *
 * @param initialoption Forma de inicio del expediente.
 * @param code Código externo único del expediente.
 * @param description Descripción del expediente.
 * @param phaseType Fase inicial.
 * @param processCode Código externo único del proceso BPM asociado.
 * @param documentCode Código externo único del documento adjunto.
 *
 * @author agat
 */
public record CreateFileParams(Long initialoption,
		String code,
		String description,
		String phaseType,
		String processCode,
		String documentCode) {
}
