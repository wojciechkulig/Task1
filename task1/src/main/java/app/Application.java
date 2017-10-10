package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import app.controller.OrderingSystemController;

public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext("app");
		OrderingSystemController appController = ctx.getBean(OrderingSystemController.class);
		appController.makeAnOrder();
		((ConfigurableApplicationContext)ctx).close();
	}

}
