package app.data.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import app.data.entities.MenuItem;
import app.data.entities.Product;

public class MenuItemDTO {
	private boolean isOrderingDrink;
	private List<Product> mainOrders;
	private List<Product> drinks;
	private List<Product> drinkAdditives;
	private List<Product> desserts;
	private List<MenuItem> selectedItems;
	private Product selectedMainOrder;
	private List<Product> selectedExtras;
	public boolean isOrderingDrink() {
		return isOrderingDrink;
	}
	public void setOrderingDrink(boolean isOrderingDrink) {
		this.isOrderingDrink = isOrderingDrink;
	}

	public List<Product> getMainOrders() {
		return mainOrders;
	}
	public void setMainOrders(List<Product> mainOrders) {
		this.mainOrders = mainOrders;
	}
	public List<Product> getDrinks() {
		return drinks;
	}
	public void setDrinks(List<Product> drinks) {
		this.drinks = drinks;
	}
	public List<Product> getDrinkAdditives() {
		return drinkAdditives;
	}
	public void setDrinkAdditives(List<Product> drinkAdditives) {
		this.drinkAdditives = drinkAdditives;
	}
	public List<Product> getDesserts() {
		return desserts;
	}
	public void setDesserts(List<Product> desserts) {
		this.desserts = desserts;
	}
	public List<MenuItem> getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(List<MenuItem> selectedItems) {
		this.selectedItems = selectedItems;
	}
	public Product getSelectedMainOrder() {
		return selectedMainOrder;
	}
	public void setSelectedMainOrder(Product selectedMainOrder) {
		this.selectedMainOrder = selectedMainOrder;
	}
	public List<Product> getSelectedExtras() {
		return selectedExtras;
	}
	public void setSelectedExtras(List<Product> selectedExtras) {
		this.selectedExtras = selectedExtras;
	}
	public List<String> getAvailableCuisines(){
		List<String> cuisines = new ArrayList<>(mainOrders.stream().map(p->p.getCountry().getCountryName()).collect(Collectors.toSet()));
		return cuisines;
	}
	
	

}
