package com.dwa.proyecto.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dwa.proyecto.modelo.Ticket;
import com.dwa.proyecto.modelo.Usuario;
import com.dwa.proyecto.servicios.IticketService;
import com.dwa.proyecto.servicios.ServicioTicket;
import com.dwa.proyecto.servicios.ServicioUsuario;


@Controller
public class TicketController {
	@Autowired
	ServicioUsuario servicioUsuario;
	@Autowired
	ServicioTicket servicioTicket;
	
	@GetMapping("/ticket/agregar")
	public String agregar(Model modelo) {
		modelo.addAttribute("ticket", new Ticket());
		return "ticket/agregar";
	}
	
	
	
	@PostMapping("/ticket/agregar")
	public String agregar(Model modelo, @ModelAttribute Ticket ticket, HttpSession sesion) {
		int iduser = Integer.parseInt(sesion.getAttribute("iduser").toString());
		Usuario autor = servicioUsuario.buscar(iduser);
		if(autor == null || autor.getId() == 0) 
			modelo.addAttribute("error", servicioUsuario.getMensaje());
		else {
			ticket.setUsuario(autor);
			if(!servicioTicket.agregar(ticket));
				modelo.addAttribute("error", servicioTicket.getMensaje());
		}
		return "ticket/agregar";
	}
	
	

@Autowired
	private IticketService service;
	@GetMapping("/ticket/listar")
	public String listar(Model model) {
		List<Ticket>tickets=service.listar();
		model.addAttribute("ticket", tickets);
		return "ticket/listar";
	}
	
	@GetMapping("/ticket/listaradmin")
	public String listaradmin(Model model) {
		List<Ticket>tickets=service.listar();
		model.addAttribute("ticket", tickets);
		return "ticket/listaradmin";
	}

}
