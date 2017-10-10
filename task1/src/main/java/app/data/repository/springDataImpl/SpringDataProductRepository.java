package app.data.repository.springDataImpl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.data.entities.Product;
import app.data.repository.ProductRepository;
@Repository
public interface SpringDataProductRepository extends ProductRepository, CrudRepository<Product, Integer> {
	@Override
	Product findById(int id) throws DataAccessException;
	@Override
	List<Product> findAll() throws DataAccessException;
}
