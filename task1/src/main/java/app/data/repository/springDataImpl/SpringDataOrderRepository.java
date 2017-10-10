package app.data.repository.springDataImpl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.data.entities.FoodOrder;
import app.data.repository.OrderRepository;

@Repository
public interface SpringDataOrderRepository extends OrderRepository, CrudRepository<FoodOrder, Integer> {
	@Override
	FoodOrder findById(int id) throws DataAccessException;
	@Override
	List<FoodOrder> findAll() throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	@Override
	FoodOrder save(FoodOrder order) throws DataAccessException;
}
