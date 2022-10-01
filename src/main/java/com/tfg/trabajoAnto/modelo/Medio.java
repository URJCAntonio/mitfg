package com.tfg.trabajoAnto.modelo;

import javax.persistence.Entity;

@Entity
public class Medio extends ModeloEjercicio {

	public Medio() {
		super();
	}
	public Medio(ModeloEjercicio m) {
		super(m);
	}
}