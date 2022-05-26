package com.dwa.proyecto.servicios;

import java.security.MessageDigest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwa.proyecto.modelo.Usuario;

@Service
public class ServicioUsuario {
	
	@Autowired
	private RepoUsuario service;
	
	private String Mensaje;
	
	public String getMensaje()
	{
		return Mensaje;
	}
	
	public void setMensaje(String mensaje) 
	{
		Mensaje= mensaje;
	}
	
	public boolean validar(String correo, String clave, HttpSession sesion)
	{
		Usuario usr= service.validar(correo,encriptar(clave));
		if(usr !=null)
		{
			sesion.setAttribute("iduser", usr.getId());
			Mensaje = usr.getNombrecompleto()+ " - " + usr.getCorreo();
			return true;
		}
		Mensaje= "Datos de acceso incorrecto";
		return false;
	}
	
	private byte[] encriptar(String clave)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(clave.getBytes("utf8"));
			return digest.digest();
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public boolean agregar(Usuario u, String clave, HttpSession sesion)
	{
		try
		{
			u.setClaveacceso(encriptar(clave));
			//INSERTAR EN LA BD
			service.save(u);
			sesion.setAttribute("iduser", u.getId());
			this.Mensaje= "Datos del usuario almacenados con exito";
			return true;
		}
		catch(Exception e)
		{
			this.Mensaje= "Error al registrar al usuario";
			return false;
		}
	}
	
	public Usuario buscar(int id) {
		try {
			Usuario usuario = service.findById(id).get();
			return usuario == null ? new Usuario() : usuario;
			
		}catch(Exception e) {
			this.Mensaje = "No se pudo realizar la busqueda del usuario";
			return null;
		}
	}

}
