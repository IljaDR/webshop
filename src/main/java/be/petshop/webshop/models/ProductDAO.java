package be.petshop.webshop.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;

import java.util.List;

public interface ProductDAO extends CrudRepository<Product, Integer> {
    List<Product> findAll();

    @Override
    <S extends Product> S save(S s);
}
