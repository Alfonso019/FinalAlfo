package com.dwa.proyecto.servicios;

import java.util.List;
import java.util.Optional;

import com.dwa.proyecto.modelo.Ticket;

public interface IticketService {
	public List<Ticket>listar();
	public Optional<Ticket>listarId(int id);
	public int save(Ticket p);

}
