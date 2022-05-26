package com.dwa.proyecto.servicios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dwa.proyecto.modelo.Usuario;

public interface RepoUsuario extends CrudRepository<Usuario, Integer> {
	@Query(value= "SELECT * FROM usuario WHERE correo = ?1 AND claveacceso = ?2", nativeQuery= true)
	Usuario validar(String correo, byte[] claveacceso);

}
