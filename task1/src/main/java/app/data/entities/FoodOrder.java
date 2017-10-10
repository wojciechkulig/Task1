package app.data.entities;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FoodOrder")
public class FoodOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "FoodOrderID")
	private int id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="FoodOrderID", nullable = false)
	private List<MenuItem> orderList = new ArrayList<>();
	
	@Column(name="orderDate")
	private LocalDateTime orderingDate;
	
	@ManyToOne()
	@JoinColumn(name="OrderStatusID")
	private OrderStatus orderStatus;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<MenuItem> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<MenuItem> orderList) {
		this.orderList = orderList;
	}

	public LocalDateTime getOrderingDate() {
		return orderingDate;
	}

	public void setOrderingDate(LocalDateTime orderingDate) {
		this.orderingDate = orderingDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order nr. " + id +"\n" +"Date of purchase: " + orderingDate.toString() + "\n" + "Ordered following menu items:\n"
				+ orderList.stream().map(Object::toString).collect(Collectors.joining("\n")) + "\nGross Amount: "+ getGrossAmount();
	}
	private double getGrossAmount(){
		return orderList.stream().mapToDouble(m->m.getProducts().stream().mapToDouble(p->p.getPrice()).sum()).sum();
	}
	
	

}
