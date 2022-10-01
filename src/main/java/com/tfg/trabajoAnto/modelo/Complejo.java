package com.tfg.trabajoAnto.modelo;

import javax.persistence.Entity;

@Entity
public class Complejo extends ModeloEjercicio {

	private String funcion_logicaD;
	private String funcion_logicaE;
	private String funcion_logicaF;
	private String funcion_logicaG;
	private String funcion_logicaH;
	
	public Complejo() {
		super();
	}
	
	public String[] getFunciones_logicas() {
		String[] funciones_logicas = {funcion_logicaD,funcion_logicaE,funcion_logicaF,funcion_logicaG,funcion_logicaH,funcion_logica};
		return funciones_logicas;
	}

	public void setFunciones_logicas(String D, String E, String F, String G, String H, String S) {
		this.funcion_logicaD = D;
		this.funcion_logicaE = E;
		this.funcion_logicaF = F;
		this.funcion_logicaG = G;
		this.funcion_logicaH = H;
		this.funcion_logica = S;
	}
	public String getFuncion_logica(int i) {
		switch (i) {
		case 0:
			return funcion_logicaD;
		case 1:
			return funcion_logicaE;
		case 2:
			return funcion_logicaF;
		case 3:
			return funcion_logicaG;
		case 4:
			return funcion_logicaH;
		case 5:
			return funcion_logica;
		default:
			return null;
		}
		
	}
}