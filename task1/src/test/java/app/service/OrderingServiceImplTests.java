package app.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.data.DTO.MenuItemDTO;
import app.data.entities.FoodOrder;
import app.data.entities.OrderStatus;
import app.data.entities.Product;
import app.data.entities.ProductCategory;
import app.data.repository.OrderRepository;
import app.data.repository.OrderStatusRepository;
import app.data.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderingServiceImplTests {
	@Mock
	OrderRepository orderRepository;
	@Mock
	OrderStatusRepository orderStatusRepository;
	@Mock
	ProductRepository productRepository;
	@InjectMocks
	OrderingServiceImpl orderingServiceImpl;
	
	@Test
	public void areMocksInitialized(){
		assertNotNull(orderRepository);
		assertNotNull(orderStatusRepository);
		assertNotNull(productRepository);
	}
	@Test
	public void testGetAllProducts_ProductRepositoryFindAllCalledOnce(){
		orderingServiceImpl.getAllProducts();
		verify(productRepository,times(1)).findAll();
	}	
	@Test
	public void testGetMenuItemDTO_ProductRepositoryFindAllCalledOnce(){
		orderingServiceImpl.getMenuItemDTO();
		verify(productRepository,times(1)).findAll();
	}
	
	@Test
	public void testMenuItemDTO_CaseEmptyMenu(){
		List<Product> menu = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(menu);
		MenuItemDTO dto = orderingServiceImpl.getMenuItemDTO();
		assertThat(dto.getDesserts(),is(menu));
		assertThat(dto.getDrinkAdditives(),is(menu));
		assertThat(dto.getDrinks(),is(menu));
		assertThat(dto.getMainOrders(),is(menu));
	}
	
	@Test
	public void testGetMenuItemDTO_ReturnsCorrectDesserts(){
		List<Product> desserts = getListWithOneDessert();
		when(productRepository.findAll()).thenReturn(desserts);
		MenuItemDTO dto = orderingServiceImpl.getMenuItemDTO();
		assertThat(dto.getDesserts(),is(desserts));
	}
	private List<Product> getListWithOneDessert(){
		Product dessert = new Product();
		dessert.setName("Dessert");
		ProductCategory category = new ProductCategory();
		category.setCategory("dessert");
		dessert.setProductCategory(category);
		List<Product> desserts = Arrays.asList(dessert);
		return desserts;
	}
	@Test
	public void testGetMenuItemDTO_ReturnsCorrectDessertsCaseRepositoryReturnsProductsWithDifferentProductCategories(){
		List<Product> products = getExampleMenu();
		when(productRepository.findAll()).thenReturn(products);
		MenuItemDTO dto = orderingServiceImpl.getMenuItemDTO();
		assertThat(dto.getDesserts(),is(not(products)));
	}
	
	private List<Product> getExampleMenu(){
		List<Product> menu = new ArrayList<>();
		
		Product dessert = new Product();
		dessert.setName("Dessert");
		ProductCategory categoryDessert = new ProductCategory();
		categoryDessert.setCategory("dessert");
		dessert.setProductCategory(categoryDessert);
		menu.add(dessert);
		
		Product additive = new Product();
		additive.setName("drink_additive");
		ProductCategory categoryAdditive = new ProductCategory();
		categoryAdditive.setCategory("drink_additive");
		additive.setProductCategory(categoryAdditive);
		menu.add(additive);
		
		Product drink = new Product();
		drink.setName("drink");
		ProductCategory categoryDrink = new ProductCategory();
		categoryDrink.setCategory("drink");
		drink.setProductCategory(categoryDrink);
		menu.add(drink);
		
		Product mainCourse = new Product();
		mainCourse.setName("main course");
		ProductCategory categoryMainCourse = new ProductCategory();
		categoryMainCourse.setCategory("main_course");
		mainCourse.setProductCategory(categoryMainCourse);
		menu.add(mainCourse);		
		return menu;
	}
	@Test
	public void testGetMenuItemDTO_ReturnsCorrectDrinkAdditives(){
		List<Product> additives = getListWithOneAdditive();
		when(productRepository.findAll()).thenReturn(additives);
		MenuItemDTO dto = orderingServiceImpl.getMenuItemDTO();
		assertThat(dto.getDrinkAdditives(),is(additives));
	}
	List<Product> getListWithOneAdditive(){
		Product dessert = new Product();
		dessert.setName("Additive");
		ProductCategory category = new ProductCategory();
		category.setCategory("drink_additive");
		dessert.setProductCategory(category);
		List<Product> desserts = Arrays.asList(dessert);
		return desserts;
	}
	@Test
	public void testGetMenuItemDTO_ReturnsCorrectDrinks(){
		List<Product> drinks = getListWithOneDrink();
		when(productRepository.findAll()).thenReturn(drinks);
		MenuItemDTO dto = orderingServiceImpl.getMenuItemDTO();
		assertThat(dto.getDrinks(),is(drinks));
	}
	List<Product> getListWithOneDrink(){
		Product drink = new Product();
		drink.setName("Drink");
		ProductCategory category = new ProductCategory();
		category.setCategory("drink");
		drink.setProductCategory(category);
		List<Product> desserts = Arrays.asList(drink);
		return desserts;
	}
	@Test
	public void testGetMenuItemDTO_ReturnsCorrectMainCourses(){
		List<Product> mainCourses = getListWithOneMainCourse();
		when(productRepository.findAll()).thenReturn(mainCourses);
		MenuItemDTO dto = orderingServiceImpl.getMenuItemDTO();
		assertThat(dto.getMainOrders(),is(mainCourses));
	}
	List<Product> getListWithOneMainCourse(){
		Product mainCourse = new Product();
		mainCourse.setName("Main course");
		ProductCategory category = new ProductCategory();
		category.setCategory("main_course");
		mainCourse.setProductCategory(category);
		List<Product> desserts = Arrays.asList(mainCourse);
		return desserts;
	}
	@Test
	public void testSaveOrder_OrderStatusRepositoryFindByIdIsCalled(){
		FoodOrder order = new FoodOrder();
		orderingServiceImpl.saveOrder(order);
		verify(orderStatusRepository,times(1)).findById(anyInt());
	}
	@Test
	public void testSaveOrder_OrderDateIsAdded(){
		FoodOrder order = new FoodOrder();
		orderingServiceImpl.saveOrder(order);
		assertNotNull(order.getOrderingDate());
	}
	@Test
	public void testSaveOrder_OrderStatusIsAdded(){
		FoodOrder order = new FoodOrder();
		when(orderStatusRepository.findById(anyInt())).thenReturn(new OrderStatus());
		orderingServiceImpl.saveOrder(order);
		assertNotNull(order.getOrderStatus());
	}
	@Test
	public void testSaveOrder_OrderIsSaved(){
		FoodOrder order = new FoodOrder();
		orderingServiceImpl.saveOrder(order);
		verify(orderRepository,times(1)).save(order);
	}
	
	
}
