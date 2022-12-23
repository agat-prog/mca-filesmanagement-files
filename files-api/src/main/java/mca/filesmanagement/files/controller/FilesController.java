package mca.filesmanagement.files.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mca.filesmanagement.files.commons.FileDetailDto;
import mca.filesmanagement.files.commons.FileNewDto;
import mca.filesmanagement.files.commons.FileUpdatedDto;
import mca.filesmanagement.files.commons.InitialOptionDto;
import mca.filesmanagement.files.service.FilesService;

@RestController
@RequestMapping("/api/files")
public class FilesController extends AbstractController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(FilesController.class);
	
	@Autowired
	private FilesService filesService;
	
	public FilesController() {
		super();
	}
	
	@PreAuthorize(HAS_ADMIN)
	@GetMapping(path = "/echo")
	public ResponseEntity<String> echo(@RequestParam (required = true , value = "texto") String texto){

		System.out.println("UserName:" +this.getUserName());
		
		return ResponseEntity.ok(texto);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestParam (required = true , value = "file") MultipartFile file,
									@RequestParam (required = true , value = "code") String code,
									@RequestParam (required = true , value = "initialoption") Long initialoption) {
		LOGGER.info(String.format("Creación de expediente con archivo subido: [%s]", file.getOriginalFilename()));
		
		try {
			FileNewDto fileNewDto = new FileNewDto();
			fileNewDto.setArchiveContentBase64(Base64Utils.encodeToString(file.getBytes()));
			fileNewDto.setCode(code);
			fileNewDto.setInitOption(initialoption);
			fileNewDto.setArchiveName(file.getOriginalFilename());
			fileNewDto.setUserName(this.getUserName());
			this.filesService.createFile(fileNewDto);
		} 
		catch (IOException e) {
			LOGGER.error("Error al crear el expediente", e);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody FileUpdatedDto fileUpdated){
		LOGGER.info(String.format("update --> %s", fileUpdated));
		this.filesService.update(fileUpdated);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/{fileCode}")
	public ResponseEntity<FileDetailDto> getFileByCode(@PathVariable(name = "fileCode", required = true) String fileCode){
		FileDetailDto fileDetailDto = this.filesService.getByCode(fileCode);
		return ResponseEntity.ok(fileDetailDto);
	}
	
	@GetMapping(path = "/initialoptions/all")
	public ResponseEntity<List<InitialOptionDto>> findAllInitialOption(){
		return ResponseEntity.ok(this.filesService.findAllInitialOption());
	}
}
