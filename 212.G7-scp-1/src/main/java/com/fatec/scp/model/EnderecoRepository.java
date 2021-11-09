package com.fatec.scp.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

	public Cliente findByCpf(@Param("cpf") String cpf);
}