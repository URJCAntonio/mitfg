package com.tfg.trabajoAnto.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

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