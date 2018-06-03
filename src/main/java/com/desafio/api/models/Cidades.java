package com.desafio.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.hateoas.ResourceSupport;

import com.opencsv.bean.CsvBindByName;

@Entity
public class Cidades extends ResourceSupport {
	@Id
	@CsvBindByName
	private int ibge_id;
	
	@CsvBindByName
	private String uf;
	
	@CsvBindByName
	private String name;
	
	@CsvBindByName
	private String capital;
	
	@CsvBindByName
	private String lon;
	
	@CsvBindByName
	private String lat;
	
	@CsvBindByName
	private String no_accents;
	
	@CsvBindByName
	private String alternative_names;
	
	@CsvBindByName
	private String microregion;
	
	@CsvBindByName
	private String mesoregion;

	public int getIbge_id() {
		return ibge_id;
	}

	public void setIbge_id(int ibge_id) {
		this.ibge_id = ibge_id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getNo_accents() {
		return no_accents;
	}

	public void setNo_accents(String no_accents) {
		this.no_accents = no_accents;
	}

	public String getAlternative_names() {
		return alternative_names;
	}

	public void setAlternative_names(String alternative_names) {
		this.alternative_names = alternative_names;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}	

}
