package be.petshop.webshop.daos;

import be.petshop.webshop.models.Shoppingcart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingcartDAO extends CrudRepository<Shoppingcart, Integer> {
    @Override
    Iterable<Shoppingcart> findAll();

    List<Shoppingcart> findAllByUserID(int UserID);
}
