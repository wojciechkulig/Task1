package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.View.OrderingSystemComunicator;
import app.data.entities.FoodOrder;
import app.data.entities.MenuItem;
import app.service.OrderingService;

@Component
public class OrderingSystemController {
	private OrderingSystemComunicator view;
	private OrderingService service;

	@Autowired
	public OrderingSystemController(OrderingService service, OrderingSystemComunicator view) {
		this.service = service;
		this.view = view;
	}

	public void makeAnOrder() {
		view.displayWelcomeMessage();
		FoodOrder order = new FoodOrder();
		order.getOrderList().addAll(getMenuItemsFromClient());
		service.saveOrder(order);
		view.displayOrder(order);
	}

	private List<MenuItem> getMenuItemsFromClient() {		
		return view.askForOrder(service.getMenuItemDTO());
		
	}

}
