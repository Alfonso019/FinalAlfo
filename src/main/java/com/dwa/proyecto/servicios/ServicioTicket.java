package com.dwa.proyecto.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwa.proyecto.modelo.Ticket;



@Service
public class ServicioTicket implements IticketService{
	
	@Autowired
	private RepoTicket repoTicket;
	
	private String Mensaje;

	public String getMensaje() {
		return Mensaje;
	}

	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	
	public boolean agregar(Ticket ticket) {
		try {
			repoTicket.save(ticket);
			this.Mensaje = "Ticket registrado con exito.";
			return true;
		}catch(Exception e) {
			this.Mensaje = "Error al registrar el Ticket";
			return false;
		}
	}


	@Override
	public List<Ticket> listar() {
	
		return (List<Ticket>)repoTicket.findAll();
	}

	@Override
	public Optional<Ticket> listarId(int id) {
		
		return repoTicket.findById(id);
	}

	@Override
	public int save(Ticket p) {
		int res=0;
		Ticket ticket=repoTicket.save(p);
		if(!ticket.equals(null)) {
			res=1;
		}
		return res;
	}

}
