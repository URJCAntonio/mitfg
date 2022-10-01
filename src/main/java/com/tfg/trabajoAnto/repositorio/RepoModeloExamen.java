package com.tfg.trabajoAnto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.trabajoAnto.modelo.ModeloExamen;

public interface RepoModeloExamen extends JpaRepository<ModeloExamen, Integer>{

	//Optional<Examen> findByNombre(String nombre);

	@Query(value="SELECT * FROM modelo_examen WHERE id = ?1", nativeQuery = true)
    ModeloExamen getExamenById(int i);
	
	@Query(value="SELECT * FROM modelo_examen WHERE nombre = ?1", nativeQuery = true)
    ModeloExamen getExamenByNombre(String nombre);
	
	@Query(value="SELECT examen_id FROM alumno_examen WHERE alumno_id = ?1", nativeQuery = true)
	List<Integer> getExamenesByUserId(int i);
	
	@Query(value="SELECT \"hola\" FROM alumno_examen WHERE alumno_id = ?1 AND examen_id = ?2", nativeQuery = true)
	String checkUserExam(int alumno, int examen);
	
}
	
