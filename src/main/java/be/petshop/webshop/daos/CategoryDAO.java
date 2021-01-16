package be.petshop.webshop.daos;

import be.petshop.webshop.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDAO extends CrudRepository<Category, Integer> {
    @Override
    List<Category> findAll();

    Category getByCategoryID(int id);
}
