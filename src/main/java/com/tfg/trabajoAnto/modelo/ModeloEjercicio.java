package com.tfg.trabajoAnto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modelo_ejercicio")
public abstract class ModeloEjercicio {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@Column(name = "funcion_logica")
	protected String funcion_logica;
	
	@Column(name = "imagen")
	protected String imagen;
	
	@Column(name = "a")
	private int a;
	
	@Column(name = "b")
	private int b;
	
	@Column(name = "c")
	private int c;
	
	public ModeloEjercicio() {
		super();
		this.imagen=null;
	}
	
	public ModeloEjercicio(ModeloEjercicio m) {
		super();
		this.id=m.getId();
		this.a=m.getA();
		this.b=m.getB();
		this.c=m.getC();
		this.funcion_logica=m.getFuncion_logica();
		this.imagen=m.getImagen();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getFuncion_logica() {
		return funcion_logica;
	}

	public void setFuncion_logica(String funcion_logica) {
		this.funcion_logica = funcion_logica;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
}
