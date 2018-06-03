package com.desafio.api.resources;

import com.desafio.api.models.Cidades;
import com.desafio.api.repository.CidadesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cidades")
public class CidadesResources {

	@Autowired
	private CidadesRepository cr;

	// Retornar somente as cidades que são capitais ordenadas por nome
	@GetMapping(value = "capitais", produces = "application/json")
	public @ResponseBody List<Cidades> cidadescap() {
		String capital = "true";
		List<Cidades> listaCidades = cr.findBycapitalOrderByName(capital);
		return listaCidades;
	}

	// Retornar o nome do estado com a maior e menor quantidade de cidades e
	// a quantidade de cidades;
	@GetMapping(value = "qtde_cid_uf_min_max", produces = "application/json")
	public @ResponseBody List<?> qtduf() {
		List<?> qtduf = cr.QtdeCidUfMinMax();
		return qtduf;
	}

	// Retornar a quantidade de cidades por estado
	@GetMapping(value = "qtde_cid_uf/{uf}", produces = "application/json")
	public @ResponseBody Long cidadesuf(@PathVariable(value = "uf") String uf) {
		Long cidadesuf = cr.QtdeCidUf(uf);
		return cidadesuf;
	}

	// Obter os dados da cidade informando o id do IBGE
	@GetMapping(value = "id_ibge/{id}", produces = "application/json")
	public @ResponseBody Cidades cidades(@PathVariable(value = "id") int ibge_id) {
		Cidades cidades = cr.findById(ibge_id);
		return cidades;
	}

	// Retornar o nome das cidades baseado em um estado selecionado
	@GetMapping(value = "nome_cid_uf/{uf}", produces = "application/json")
	public @ResponseBody List<String> nomecidadesuf(@PathVariable(value = "uf") String uf) {
		List<String> nomecidadesuf = cr.NomeCidUF(uf);
		return nomecidadesuf;
	}

	// Permitir adicionar uma nova Cidade
	@PostMapping
	public Cidades cadastraCidade(@RequestBody Cidades cidades) {
		return cr.save(cidades);
	}

	// Permitir deletar uma cidade
	@DeleteMapping
	public Cidades deletaCidade(@RequestBody Cidades cidades) {
		cr.delete(cidades);
		return cidades;
	}

	// NOTOK> Permitir selecionar uma coluna (do CSV) e através dela entrar com uma
	// string para filtrar. retornar assim todos os objetos que contenham tal string

	// Retornar a quantidade de registro baseado em uma coluna. Não deve
	// contar itens iguais	
	@GetMapping(value = "tot_reg_col", produces = "application/json")
	public @ResponseBody List<?> TotRegCol() {
		List<?> totregcol = cr.TotRegCol();
		return totregcol;
	}	

	// Retornar a quantidade de registros total
	@GetMapping(value = "tot_reg", produces = "application/json")
	public @ResponseBody int totreg() {
		int totreg = cr.TotReg();
		return totreg;
	}

	// NOTOK> Dentre todas as cidades, obter as duas cidades mais distantes uma da outra	
	// com base na localização (distância em KM em linha reta)

	// Todas Cidades
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Cidades> listaCidades() {
		Iterable<Cidades> listaCidades = cr.findAll();
		return listaCidades;
	}

}
