package pe.joedayz.repository;

import org.springframework.data.repository.CrudRepository;

import pe.joedayz.modelo.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String name);
}
