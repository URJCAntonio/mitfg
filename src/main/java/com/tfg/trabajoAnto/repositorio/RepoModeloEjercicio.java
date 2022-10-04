package com.tfg.trabajoAnto.repositorio;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfg.trabajoAnto.modelo.ModeloEjercicio;


public interface RepoModeloEjercicio extends JpaRepository<ModeloEjercicio, Integer>{

	@Query(value="SELECT * FROM modelo_ejercicio", nativeQuery = true)
	List<ModeloEjercicio> getAllEjercicios();
	
	@Query(value="SELECT * FROM modelo_ejercicio WHERE dtype = 'Simple'", nativeQuery = true)
	List<ModeloEjercicio> getAllSimples();
	
	@Query(value="SELECT * FROM modelo_ejercicio WHERE dtype = 'Medio'", nativeQuery = true)
	List<ModeloEjercicio> getAllMedios();
	
	@Query(value="SELECT * FROM modelo_ejercicio WHERE dtype = 'Complejo'", nativeQuery = true)
	List<ModeloEjercicio> getAllComplejos();
	
	@Query(value="SELECT * FROM modelo_ejercicio WHERE id = ?1", nativeQuery = true)
	ModeloEjercicio getEjercicioById(int i);
	
	@Query(value="SELECT CASE WHEN dtype = 'Simple' THEN true ELSE false END AS bool FROM modelo_ejercicio WHERE id = ?1", nativeQuery = true)
	int isSimpleById(int i);
	
	@Query(value="SELECT CASE WHEN dtype = 'Medio' THEN true ELSE false END AS bool FROM modelo_ejercicio WHERE id = ?1", nativeQuery = true)
	int isMedioById(int i);
	
	@Query(value="SELECT CASE WHEN dtype = 'Complejo' THEN true ELSE false END AS bool FROM modelo_ejercicio WHERE id = ?1", nativeQuery = true)
	int isComplejoById(int i);
	
}
	
