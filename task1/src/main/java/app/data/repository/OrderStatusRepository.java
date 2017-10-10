package app.data.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import app.data.entities.OrderStatus;

public interface OrderStatusRepository {
	public OrderStatus findById(int id) throws DataAccessException;
	public List<OrderStatus> findAll() throws DataAccessException;
}
