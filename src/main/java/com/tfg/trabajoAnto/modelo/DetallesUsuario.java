package com.tfg.trabajoAnto.modelo;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DetallesUsuario implements UserDetails{
	
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Usuario user;
    
    public DetallesUsuario(Usuario user) {
        this.user = user;
    }
 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.Rol()));
         
        return authorities;
    }

    
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getContra();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getNombre();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isEnabled();
	}
	
	public Usuario getUser() {
		return this.user;
	}

}
