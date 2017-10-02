package br.com.valmarjunior.dao;

import br.com.valmarjunior.model.Aluno;
import br.com.valmarjunior.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {
	
	List<Aluno> findbyStatus(@Param("status") Status status);
}
