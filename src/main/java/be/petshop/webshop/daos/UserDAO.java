package be.petshop.webshop.daos;

import be.petshop.webshop.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDAO extends CrudRepository<User, Integer> {
    @Override
    <S extends User> S save(S s);

    @Override
    Iterable<User> findAll();

    Optional<User> findByEmail(String email);
}
