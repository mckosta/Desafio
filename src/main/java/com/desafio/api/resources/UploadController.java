package com.desafio.api.resources;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.desafio.api.models.Cidades;
import com.desafio.api.service.UploadService;


@RestController
@RequestMapping("/api/csv")
public class UploadController {

	@Autowired
	private UploadService uploadService;

	@Autowired
	UploadController(UploadService uploadService) {
		this.uploadService = uploadService;
	}
	
	//Ler o arquivo CSV das cidades para a base de dados
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public List<Cidades> uploadFile(@RequestPart(value = "file") MultipartFile multiPartFile) throws IOException {
		return uploadService.uploadFile(multiPartFile);
	}
	
	@RequestMapping
	public List<Cidades> getCidades(){
		return uploadService.getCidades();
	}
}
