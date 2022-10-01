package com.tfg.trabajoAnto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.trabajoAnto.modelo.Alumno;
import com.tfg.trabajoAnto.modelo.Aula;
import com.tfg.trabajoAnto.modelo.Profesor;
import com.tfg.trabajoAnto.modelo.Usuario;
import com.tfg.trabajoAnto.repositorio.RepoAula;
import com.tfg.trabajoAnto.repositorio.RepoUsuario;

@Service
public class ServicioAula {

	@Autowired
	private RepoUsuario repousu;
	
	@Autowired
	private RepoAula repoaula;
	
	
	public void addAlumno(Aula aula, String nombre) {
		Alumno alumno = (Alumno) repousu.getUserByUsername(nombre);
		aula.getAlumnos().add(alumno);
	}
	
	public void addAlumno(Aula aula, Alumno alumno) {
		aula.getAlumnos().add(alumno);
	}
	
	public void save(Aula aula) {
		repoaula.save(aula);
	}
	
}
