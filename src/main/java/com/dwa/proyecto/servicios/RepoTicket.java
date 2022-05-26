package com.dwa.proyecto.servicios;

import org.springframework.data.repository.CrudRepository;

import com.dwa.proyecto.modelo.Ticket;

public interface RepoTicket extends CrudRepository<Ticket, Integer> {

}
