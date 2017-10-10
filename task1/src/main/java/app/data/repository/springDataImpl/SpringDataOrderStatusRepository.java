package app.data.repository.springDataImpl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import app.data.entities.OrderStatus;
import app.data.repository.OrderStatusRepository;

public interface SpringDataOrderStatusRepository extends CrudRepository<OrderStatus, Integer>, OrderStatusRepository {
	
	@Override
	public List<OrderStatus> findAll() throws DataAccessException;
	
	@Override
	public OrderStatus findById(int id) throws DataAccessException;
}
