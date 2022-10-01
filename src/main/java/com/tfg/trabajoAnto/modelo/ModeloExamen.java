package com.tfg.trabajoAnto.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="modeloExamen")
public class ModeloExamen {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
	private String nombre;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
            name = "modelo_noimg",
            joinColumns = @JoinColumn(name = "examen_id"),
            inverseJoinColumns = @JoinColumn(name = "ejercicio_id")
            )
	private Set<ModeloEjercicio> noImg= new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
            name = "modelo_siimg",
            joinColumns = @JoinColumn(name = "examen_id"),
            inverseJoinColumns = @JoinColumn(name = "ejercicio_id")
            )
	private Set<ModeloEjercicio> siImg= new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
            name = "modelo_tablaverdad",
            joinColumns = @JoinColumn(name = "examen_id"),
            inverseJoinColumns = @JoinColumn(name = "ejercicio_id")
            )
	private Set<ModeloEjercicio> tablaVerdad= new HashSet<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<ModeloEjercicio> getNoImg() {
		return noImg;
	}

	public void setNoImg(Set<ModeloEjercicio> noImg) {
		this.noImg = noImg;
	}

	public Set<ModeloEjercicio> getSiImg() {
		return siImg;
	}

	public void setSiImg(Set<ModeloEjercicio> siImg) {
		this.siImg = siImg;
	}

	public Set<ModeloEjercicio> getTablaVerdad() {
		return tablaVerdad;
	}

	public void setTablaVerdad(Set<ModeloEjercicio> tablaVerdad) {
		this.tablaVerdad = tablaVerdad;
	}
}