package com.tfg.trabajoAnto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.trabajoAnto.modelo.Alumno;
import com.tfg.trabajoAnto.modelo.Aula;
import com.tfg.trabajoAnto.modelo.Profesor;
import com.tfg.trabajoAnto.modelo.Usuario;


public interface RepoUsuario extends CrudRepository<Usuario, Integer>{

	@Query(value="SELECT * FROM usuario WHERE 1", nativeQuery = true)
	List<Usuario> getAllUsers();
	
	@Query(value="SELECT * FROM usuario WHERE dtype = \"Alumno\"", nativeQuery = true)
	List<Alumno> getAllAlumnos();
	
	@Query(value="SELECT * FROM usuario WHERE dtype = \"Profesor\"", nativeQuery = true)
	List<Profesor> getAllProfesores();
	
	@Query(value="SELECT * FROM usuario WHERE id = ?1", nativeQuery = true)
    Usuario getUserById(int id);
	
	@Query(value="SELECT * FROM usuario WHERE nombre = ?1", nativeQuery = true)
    Usuario getUserByUsername(String usuario);
	
	@Query(value="SELECT usuario.* FROM aula_alumnos JOIN usuario WHERE aula_alumnos.alumno_id = usuario.id AND aula_alumnos.aula_id = ?1", nativeQuery = true)
	List<Usuario> getUsuariosbyAulaId(int id);
	
	@Query(value="SELECT usuario.id, usuario.contra, usuario.enabled, usuario.nombre FROM aula_alumnos JOIN usuario WHERE aula_alumnos.alumno_id = usuario.id AND aula_alumnos.aula_id = ?1", nativeQuery = true)
	List<Alumno> getAlumnosbyAulaId(int id);
	
	@Query(value="SELECT * FROM usuario WHERE id IN ?1", nativeQuery = true)
	List<Alumno> getAlumnosbyNombres(String[] nombres);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE usuario SET enabled = !enabled WHERE nombre = ?1", nativeQuery = true)
	void banUsuarioByNombre(String nombres);
	
	
/*
	@Query(value="SELECT * FROM usuario JOIN users_roles on usuario.id = users_roles.user_id JOIN roles ON users_roles.role_id = roles.id_role WHERE users_roles.role_id = '1'", nativeQuery = true)
    List<Usuario> getAllUsers();
	
	@Query(value="SELECT u.* FROM `usuario` AS u JOIN `users_roles` AS r1 ON u.id = r1.user_id JOIN `users_roles` AS r2 ON u.id = r2.user_id JOIN `users_roles` AS r3 ON u.id = r3.user_id WHERE r1.role_id= 1 AND r2.role_id = ?1 AND r3.role_id = ?2;", nativeQuery = true)
    List<Usuario> getUserbyCursoGrupo(int curso, int grupo);
	
	@Query(value="SELECT u.* FROM `usuario` AS u JOIN `users_roles` AS r1 ON u.id = r1.user_id JOIN `users_roles` AS r2 ON u.id = r2.user_id JOIN `users_roles` AS r3 ON u.id = r3.user_id WHERE r1.role_id= (SELECT id_role FROM `roles` WHERE nombre = \"ALUMNOS\" ) AND r2.role_id = (SELECT id_role FROM `roles` WHERE nombre = ?1 ) AND r3.role_id = (SELECT id_role FROM `roles` WHERE nombre = ?2 );", nativeQuery = true)
    List<Usuario> getUserbyCursoGrupo(String curso, String grupo);
*/
}
