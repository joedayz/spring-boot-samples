package demo.repository;

import java.util.List;

import demo.domain.Nota;

public interface NotaRepository {

	List<Nota> findAll();
}
