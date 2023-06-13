package com.tfg.trabajoAnto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ejercicio")
public class Ejercicio {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private ModeloEjercicio modeloEjercicio;
	
	@ManyToOne
	private ModeloExamen modeloExamen;
	
	@ManyToOne
	private Alumno alumno;
	
	private String tipo;
	
	private char a;
	
	private char b;
	
	private char c;
	
	private char[] s;
	
	private boolean[] correcto;
	
	private String auxiliar;
	
	public Ejercicio(String tipo) {
		super();
		this.c=2;
		this.tipo = tipo;
		
		switch (tipo) {
		case "simple":
			this.s = new char[1];
			this.correcto = new boolean[1];
			break;
		case "simpleImg":
			this.s = new char[1];
			this.correcto = new boolean[1];
			break;
		case "simpleTV":
			this.s = new char[4];
			this.correcto = new boolean[4];
			break;
		case "medio":
			this.s = new char[1];
			this.correcto = new boolean[1];
			break;
		case "medioImg":
			this.s = new char[1];
			this.correcto = new boolean[1];
			break;
		case "medioTV":
			this.s = new char[8];
			this.correcto = new boolean[8];
			break;
		case "complejo":
			this.s = new char[6];
			this.correcto = new boolean[6];
			break;
		default:
			break;
		}
	}
	
	public Ejercicio() {
		super();
		//DEFAULT CONSTRUCTOR
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ModeloEjercicio getModeloEjercicio() {
		return modeloEjercicio;
	}


	public void setModeloEjercicio(ModeloEjercicio modeloEjercicio) {
		this.modeloEjercicio = modeloEjercicio;
	}


	public Alumno getAlumno() {
		return alumno;
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	public ModeloExamen getModeloExamen() {
		return modeloExamen;
	}

	public void setModeloExamen(ModeloExamen modeloExamen) {
		this.modeloExamen = modeloExamen;
	}

	public char getA() {
		return a;
	}

	public void setA(char a) {
		this.a = a;
	}

	public char getB() {
		return b;
	}

	public void setB(char b) {
		this.b = b;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}
	
	public char[] getS() {
		return s;
	}

	public void setS(char[] s) {
		this.s = s;
	}

	public char getS(int posicion) {
		return s[posicion];
	}

	public void setS(char s) {
		this.s[0] = s;
	}
	
	public void setS(char s, int posicion) {
		this.s[posicion] = s;
	}
/*
	public char getS(char l) {
		switch (l) {
			case 'D':	return s[0];
			case 'E':	return s[1];
			case 'F':	return s[2];
			case 'G':	return s[3];
			case 'H':	return s[4];
			case 'S':	return s[5];
			default:	return '2';
		}
	}
*/
/*
	public void setS(char s, char l) {
		switch (l) {
			case 'D':	this.s[0] = s;	break;
			case 'E':	this.s[1] = s;	break;
			case 'F':	this.s[2] = s;	break;
			case 'G':	this.s[3] = s;	break;
			case 'H':	this.s[4] = s;	break;
			case 'S':	this.s[5] = s;	break;
			default:	break;
		}
	}
*/
	
	public boolean[] getCorrecto() {
		return correcto;
	}
	public void correcto() {
		this.correcto[0] = true;
	}
	public void correcto(int i) {
		this.correcto[i] = true;
	}
	public void incorrecto() {
		this.correcto[0] = false;
	}
	public void incorrecto(int i) {
		this.correcto[i] = false;
	}
	public void setCorrecto(boolean correcto, int posicion) {
		this.correcto[posicion] = correcto;
	}
	public void setCorrecto(boolean[] correcto) {
		this.correcto = correcto;
	}
	
	
	public String getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(String auxiliar) {
		this.auxiliar = auxiliar;
	}
	
}
