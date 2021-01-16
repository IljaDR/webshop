package be.petshop.webshop.daos;

import be.petshop.webshop.models.Category;
import be.petshop.webshop.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDAO extends CrudRepository<Product, Integer> {
    List<Product> findAll();

    List<Product> findByCategory(Category category);
    @Override
    <S extends Product> S save(S s);
}
