package be.petshop.webshop.daos;

import be.petshop.webshop.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {
    @Override
    <S extends User> S save(S s);

    @Override
    Iterable<User> findAll();
}
