package com.desafio.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.desafio.api.dao.CidadesDAO;
import com.desafio.api.models.Cidades;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class UploadService {
	
	@Autowired
	private CidadesDAO CidadesDAO;

	public List<Cidades> uploadFile(MultipartFile multipartFile) throws IOException {

		File file = convertMultiPartToFile(multipartFile);

		List<Cidades> mandatoryMissedList = new ArrayList<Cidades>();

		try (Reader reader = new FileReader(file);) {
			//@SuppressWarnings("unchecked")
			CsvToBean<Cidades> csvToBean = new CsvToBeanBuilder<Cidades>(reader).withType(Cidades.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<Cidades> cidadesList = csvToBean.parse();

			Iterator<Cidades> cidadesListClone = cidadesList.iterator();

			while (cidadesListClone.hasNext()) {

				//Cidades cidades = cidadesListClone.next();
				
				cidadesListClone.next();
			}

			CidadesDAO.batchStore(cidadesList);
		}
		return mandatoryMissedList;
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	public List<Cidades> getCidades() {
		return CidadesDAO.getCidades();
	}

}
