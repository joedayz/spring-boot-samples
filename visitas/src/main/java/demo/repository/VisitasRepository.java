package demo.repository;

import org.springframework.data.repository.CrudRepository;

import demo.model.Visita;

public interface VisitasRepository extends CrudRepository<Visita, Long>{

	
}
