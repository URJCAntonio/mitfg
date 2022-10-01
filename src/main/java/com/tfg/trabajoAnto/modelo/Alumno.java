package com.tfg.trabajoAnto.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Alumno extends Usuario{

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
            name = "alumno_examen",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "examen_id")
            )
	private Set<ModeloExamen> examenes= new HashSet<>();
	
	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<ModeloExamen> getExamenes() {
		return examenes;
	}

	public void setExamenes(Set<ModeloExamen> examenes) {
		this.examenes = examenes;
	}
	
	@Override
	public String Rol() {
		return "ALUMNOS";
	}
}
 