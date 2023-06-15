package com.tfg.trabajoAnto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfg.trabajoAnto.modelo.Ejercicio;

public interface RepoEjercicio extends JpaRepository<Ejercicio, Integer>{

	//Optional<Examen> findByNombre(String nombre);
	@Query(value="SELECT modelo_ejercicio_id FROM ejercicio GROUP BY modelo_ejercicio_id", nativeQuery = true)
    List<Integer> getAllModeloId();

	@Query(value="SELECT * FROM ejercicio WHERE modelo_examen_id = ?1", nativeQuery = true)
    List<Ejercicio> getEjerciciosByExamenId(int i);
	
	@Query(value="SELECT * FROM ejercicio WHERE modelo_examen_id = ?2 AND alumno_id = ?1", nativeQuery = true)
    List<Ejercicio> getEjerciciosDeAlumnoByExamenId(int alumno, int i);
	
	@Query(value="SELECT * FROM ejercicio WHERE modelo_examen_id = ?2 AND alumno_id = ?1 AND tipo = ?3", nativeQuery = true)
    List<Ejercicio> getEjerciciosDeAlumnoByExamenId(int alumno, int i, String tipo);
	
	@Query(value="SELECT modelo_ejercicio_id FROM ejercicio WHERE alumno_id = ?1 GROUP BY modelo_ejercicio_id", nativeQuery = true)
    List<Integer> getIdEjercicios(int id);
	
	@Query(value="SELECT modelo_ejercicio_id FROM ejercicio WHERE alumno_id IN ?1 AND tipo IN ('simple','simpleImg','simpleTV') GROUP BY modelo_ejercicio_id", nativeQuery = true)
    List<Integer> getIdEjerciciosSimples(int[] id);
	
	@Query(value="SELECT modelo_ejercicio_id FROM ejercicio WHERE alumno_id IN ?1 AND tipo IN ('medio','medioImg','medioTV') GROUP BY modelo_ejercicio_id", nativeQuery = true)
    List<Integer> getIdEjerciciosMedios(int[] id);
	
	@Query(value="SELECT modelo_ejercicio_id FROM ejercicio WHERE alumno_id IN ?1 AND tipo = 'complejo' GROUP BY modelo_ejercicio_id", nativeQuery = true)
    List<Integer> getIdEjerciciosComplejos(int[] id);
	
	@Query(value="SELECT modelo_ejercicio_id FROM ejercicio WHERE tipo IN ('simple','simpleImg','simpleTV') GROUP BY modelo_ejercicio_id", nativeQuery = true)
    List<Integer> getIdEjerciciosSimples();
	
	@Query(value="SELECT modelo_ejercicio_id FROM ejercicio WHERE tipo IN ('medio','medioImg','medioTV') GROUP BY modelo_ejercicio_id", nativeQuery = true)
    List<Integer> getIdEjerciciosMedios();
	
	@Query(value="SELECT modelo_ejercicio_id FROM ejercicio WHERE tipo = 'complejo' GROUP BY modelo_ejercicio_id", nativeQuery = true)
    List<Integer> getIdEjerciciosComplejos();
	
	@Query(value="SELECT * FROM ejercicio WHERE alumno_id = ?1 AND modelo_ejercicio_id = ?2", nativeQuery = true)
	List<Ejercicio> getCorrectoIncorrecto(int id, int ejercicio);
	
	@Query(value="SELECT * FROM ejercicio WHERE alumno_id IN ?1 AND modelo_ejercicio_id = ?2", nativeQuery = true)
	List<Ejercicio> getCorrectoIncorrecto(int id[], int ejercicio);
	
	@Query(value="SELECT * FROM ejercicio WHERE modelo_ejercicio_id = ?1", nativeQuery = true)
	List<Ejercicio> getCorrectoIncorrecto(int ejercicio);
    
    @Query(value="SELECT * FROM ejercicio WHERE modelo_ejercicio_id = 1", nativeQuery = true)
    List<Ejercicio> getCorrectoAux();
}
	
