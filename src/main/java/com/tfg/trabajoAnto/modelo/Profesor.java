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
public class Profesor extends Usuario{


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "profesor_aula",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "aula_id")
            )
    private Set<Aula> aula= new HashSet<>();

	
	public Profesor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<Aula> getAula() {
		return aula;
	}

	public void setAula(Set<Aula> aula) {
		this.aula = aula;
	}
	
	@Override
	public String Rol() {
		return "PROFESORES";
	}
}