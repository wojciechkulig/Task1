package app.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.View.OrderingSystemComunicator;
import app.data.entities.FoodOrder;
import app.data.entities.MenuItem;
import app.service.OrderingService;

@RunWith(MockitoJUnitRunner.class)
public class OrderingSystemControllerTests {
	@Mock
	OrderingSystemComunicator view;
	@Mock
	OrderingService service;
	@InjectMocks
	OrderingSystemController controller;
	
	@Test
	public void makeAnOrderTest_ViewDisplayWelcomeMessageIsCalled(){
		controller.makeAnOrder();
		verify(view,times(1)).displayWelcomeMessage();
	}
	
	@Test
	public void makeAnOrderTest_OrderIsSavedWithMenuItemsSelectedByUser(){
		ArgumentCaptor<FoodOrder> foodOrderCaptor = ArgumentCaptor.forClass(FoodOrder.class);
		List<MenuItem> selectedMenuItems = new ArrayList<>();
		selectedMenuItems.add(new MenuItem());
		when(view.askForOrder(anyObject())).thenReturn(selectedMenuItems);
		controller.makeAnOrder();
		then(service).should().saveOrder(foodOrderCaptor.capture());
		assertThat(foodOrderCaptor.getValue().getOrderList(),is(selectedMenuItems));
	}

}
