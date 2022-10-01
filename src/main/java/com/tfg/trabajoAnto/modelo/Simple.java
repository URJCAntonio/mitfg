package com.tfg.trabajoAnto.modelo;

import javax.persistence.Entity;

@Entity
public class Simple extends ModeloEjercicio {

	public Simple() {
		super();
	}
	public Simple(ModeloEjercicio m) {
		super(m);
	}
}
