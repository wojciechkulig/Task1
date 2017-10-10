package app.service;

import java.util.List;

import app.data.DTO.MenuItemDTO;
import app.data.entities.FoodOrder;
import app.data.entities.Product;

public interface OrderingService {
	public List<Product> getAllProducts();
	public void saveOrder(FoodOrder order);
	public MenuItemDTO getMenuItemDTO();
}
