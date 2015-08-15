package demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import demo.domain.Etiqueta;

@Repository
public class JpaEtiquetaRepository implements EtiquetaRepository {

	@Override
	public List<Etiqueta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
