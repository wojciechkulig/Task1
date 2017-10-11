package app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.DTO.MenuItemDTO;
import app.data.entities.FoodOrder;
import app.data.entities.OrderStatus;
import app.data.entities.Product;
import app.data.repository.OrderRepository;
import app.data.repository.OrderStatusRepository;
import app.data.repository.ProductRepository;

@Service
public class OrderingServiceImpl implements OrderingService {
	private OrderRepository orderRepository;
	private ProductRepository productRepository;
	private OrderStatusRepository orderStatusRepository;
	
	@Autowired
	public OrderingServiceImpl(OrderRepository orderRepository,ProductRepository productRepository,OrderStatusRepository orderStatusRepository){
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.orderStatusRepository = orderStatusRepository;
	}
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	private OrderStatus getOrderStatusForNewOrder(){
		return orderStatusRepository.findById(1);
	}
	public void saveOrder(FoodOrder order){
		order.setOrderStatus(getOrderStatusForNewOrder());
		order.setOrderingDate(LocalDateTime.now());
		orderRepository.save(order);
	}
	public MenuItemDTO getMenuItemDTO(){
		MenuItemDTO dto = new MenuItemDTO();
		List<Product> products = productRepository.findAll();
		dto.setDesserts(getProductsByType("dessert",products));
		dto.setDrinkAdditives(getProductsByType("drink_additive",products));
		dto.setDrinks(getProductsByType("drink",products));
		dto.setMainOrders(getProductsByType("main_course",products));
		return dto;
	}

	private List<Product> getProductsByType(String type,List<Product> products){
		return products.stream().filter(p->p.getProductCategory().getCategory().equals(type)).collect(Collectors.toList());
	}

	

}
