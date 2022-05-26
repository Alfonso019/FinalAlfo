package com.dwa.proyecto.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwa.proyecto.modelo.Usuario;
import com.dwa.proyecto.servicios.ServicioUsuario;

@Controller
public class UsuarioController {
	@Autowired
	ServicioUsuario servicioUsuario;
	
	@GetMapping(path= {"/usuario/validar", "/"})
	public String validar()
	{
		return "usuario/validar";
	}
	
	@PostMapping("/usuario/validar")
	public String agregar(@RequestParam("correo")String correo, @RequestParam("clave") String clave,@RequestParam("rango") String rango,Model modelo, HttpSession sesion)
	{
		boolean res = servicioUsuario.validar(correo, clave, sesion);
		if(rango.equals("Administrador"))
		{
			if(res)
			{
				return "redirect:/ticket/listaradmin";
			}
		}
		else
		{
			if(rango.equals("Alumno"))
			{
				if(res)
				{
					return "redirect:/ticket/listar";
				}
			}
			else
			{
				if(res)
				{
					return "redirect:/ticket/listar";
				}
			}
		}
		modelo.addAttribute("error", servicioUsuario.getMensaje());
		modelo.addAttribute("correo", correo);
		modelo.addAttribute("clave", clave);
		return "/usuario/validar";
	}
	
	@GetMapping("/usuario/agregar")
	public String agregar(Model model)
	{
		model.addAttribute("usuario", new Usuario());
		return "usuario/agregar";
	}
	
	@PostMapping("/usuario/agregar")
	public String agregar(Model modelo, @ModelAttribute Usuario user,
			@RequestParam("clave") String clave, HttpSession sesion)
	{
		boolean res= servicioUsuario.agregar(user, clave, sesion);
		if(res)
		{
			return "redirect:/ticket/listar";
		}
		modelo.addAttribute("error", servicioUsuario.getMensaje());
		return "usuario/agregar";
	}
	
	

}
