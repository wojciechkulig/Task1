package app.data.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import app.data.entities.Product;

public interface ProductRepository {
	Product findById(int id) throws DataAccessException;
	List<Product> findAll() throws DataAccessException;
}
