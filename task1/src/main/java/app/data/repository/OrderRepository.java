package app.data.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import app.data.entities.FoodOrder;

public interface OrderRepository {
	FoodOrder findById(int id) throws DataAccessException;
	List<FoodOrder> findAll() throws DataAccessException;
	FoodOrder save(FoodOrder order) throws DataAccessException;

}
