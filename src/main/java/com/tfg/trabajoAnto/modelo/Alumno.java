package com.tfg.trabajoAnto.modelo;

import java.util.HashSet;
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
	private int[] intentos = new int[43];
	private int[] aciertos = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
		for(int i=0;i<43;i++) {
			intentos[i]=0;
			aciertos[i]=0;
		}
	}

	public Set<ModeloExamen> getExamenes() {
		return examenes;
	}

	public void setExamenes(Set<ModeloExamen> examenes) {
		this.examenes = examenes;
	}
	
	public int[] getIntentos() {
		return intentos;
	}

	public void setIntentos(int[] intentos) {
		this.intentos = intentos;
	}

	public int[] getAciertos() {
		return aciertos;
	}

	public void setAciertos(int[] aciertos) {
		this.aciertos = aciertos;
	}

	@Override
	public String Rol() {
		return "ALUMNOS";
	}
}
 