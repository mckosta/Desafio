package com.desafio.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.desafio.api.models.Cidades;

@Repository
public interface CidadesRepository extends JpaRepository<Cidades, Integer> {

	public Cidades findById(int ibge_id);
	
	public List<Cidades> findBycapitalOrderByName(String capital);
		
	@Query(value = "select s1.uf, s1.tot tot \r\n"
			+ " from (select c.uf uf, count(c.ibge_id) tot FROM cidades c group by c.uf) s1,\r\n"
			+ "      (select max(i1.tot) tot from (select c.uf uf, count(c.ibge_id) tot FROM cidades c group by c.uf) i1) s2,\r\n"
			+ "      (select min(i2.tot) tot from (select c.uf uf, count(c.ibge_id) tot FROM cidades c group by c.uf) i2) s3\r\n"
			+ " where s1.tot = s2.tot\r\n" + "    or s1.tot = s3.tot\r\n" + "order by s1.uf", nativeQuery = true)
	List<?> QtdeCidUfMinMax();

	@Query(value = "select count(distinct(c.name)) from cidades c where c.uf = :uf", nativeQuery = true)
	Long QtdeCidUf(@Param("uf") String uf);

	@Query(value = "select distinct(c.name) from cidades c where c.uf = ?1", nativeQuery = true)
	List<String> NomeCidUF(String uf);

	@Query(value = "select count(*) from cidades", nativeQuery = true)
	int TotReg();

	@Query(value = "select \"ibge_id\",\r\n" + 
			"       count(distinct(c.ibge_id)),\r\n" + 
			"       \"alternative_names\",\r\n" + 
			"       count(distinct(c.alternative_names)),\r\n" + 
			"       \"capital\",\r\n" + 
			"       count(distinct(c.capital)),\r\n" + 
			"       \"lat\",\r\n" + 
			"       count(distinct(c.lat)),\r\n" + 
			"       \"lon\",\r\n" + 
			"       count(distinct(c.lon)),\r\n" + 
			"       \"mesoregion\",\r\n" + 
			"       count(distinct(c.mesoregion)),\r\n" + 
			"       \"microregion\",\r\n" + 
			"       count(distinct(c.microregion)),\r\n" + 
			"       \"name\",\r\n" + 
			"       count(distinct(c.name)),\r\n" + 
			"       \"no_accents\",\r\n" + 
			"       count(distinct(c.no_accents)),\r\n" + 
			"       \"uf\",\r\n" + 
			"       count(distinct(c.uf))\r\n" + 
			" from cidades c", nativeQuery = true)
	List<?> TotRegCol();

	@Query(value = "select count((uf) from cidades", nativeQuery=true)
	int TotRegColUf();
	
	

}