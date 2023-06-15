package com.tfg.trabajoAnto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfg.trabajoAnto.modelo.Aula;

public interface RepoAula extends JpaRepository<Aula, Integer>{

	@Query(value="SELECT * FROM aula", nativeQuery = true)
	List<Aula> getAllAulas();
	
	@Query(value="SELECT * FROM aula WHERE nombre = ?1", nativeQuery = true)
	Aula getAulaByNombre(String nombre);
	
	@Query(value="SELECT * FROM aula WHERE id = ?1", nativeQuery = true)
	Aula getAulaById(int id);
	
	@Query(value="SELECT aula.* FROM aula JOIN profesor_aula ON true WHERE profesor_aula.aula_id = aula.id AND profesor_aula.profesor_id = ?1", nativeQuery = true)
	List<Aula> getAulasByProfesorId(int id);
	
	@Query(value="SELECT * FROM aula WHERE id NOT IN (SELECT profesor_aula.aula_id FROM profesor_aula WHERE profesor_aula.profesor_id = ?1)", nativeQuery = true)
	List<Aula> getNuevasAulasProfesorId(int id);
}
