package app.data.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name="MenuItem")
public class MenuItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "MenuItemID")
	private int menuItemID;
	@ManyToMany()
	@JoinTable(name="MenuItem_Product",joinColumns=@JoinColumn(name="MenuItemID"), inverseJoinColumns=@JoinColumn(name="ProductID"))
	private List<Product> products = new ArrayList<>();
	
	public int getMenuItemID() {
		return menuItemID;
	}
	public void setMenuItemID(int menuItemID) {
		this.menuItemID = menuItemID;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return products.stream().map(Object::toString).collect(Collectors.joining(", "));
	}
	
	
	

}
