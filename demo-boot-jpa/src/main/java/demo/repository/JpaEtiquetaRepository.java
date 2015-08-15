package demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import demo.domain.Etiqueta;
import demo.domain.Nota;

@Repository
public class JpaEtiquetaRepository implements EtiquetaRepository {


	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Etiqueta> findAll() {
		return this.entityManager.createQuery("SELECT e FROM Etiqueta e",Etiqueta.class)
				.getResultList();
	}

}
