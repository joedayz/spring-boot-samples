package demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import demo.domain.Nota;

@Repository
public class JpaNotaRepository implements NotaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Nota> findAll() {
		return this.entityManager.createQuery("SELECT n FROM Nota n", Nota.class)
				.getResultList();
	}

}
