package com.tfg.trabajoAnto.modelo;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario{

	public Administrador() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String Rol() {
		return "ADMINISTRADOR";
	}
}