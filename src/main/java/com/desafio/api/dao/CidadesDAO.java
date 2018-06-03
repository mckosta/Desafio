package com.desafio.api.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.desafio.api.models.Cidades;
import com.desafio.api.repository.CidadesRepository;

@Repository
public class CidadesDAO {
	
	@Autowired
	private CidadesRepository cidadesRepository;

	public void batchStore(List<Cidades> cidadesList) {
		cidadesRepository.saveAll(cidadesList);
	}

	public List<Cidades> getCidades() {
		return cidadesRepository.findAll();
	}

}
