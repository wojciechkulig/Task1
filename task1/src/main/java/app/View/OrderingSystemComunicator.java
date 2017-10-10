package app.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import app.data.DTO.MenuItemDTO;
import app.data.entities.FoodOrder;
import app.data.entities.MenuItem;
import app.data.entities.Product;

@Component
public class OrderingSystemComunicator {
	private Scanner scanner;
	private OrderingSystemInputValidator validator;
	private boolean continuingOrder;

	public OrderingSystemComunicator(OrderingSystemInputValidator validator) {
		scanner = new Scanner(System.in);
		this.validator = validator;
		continuingOrder = true;
	}

	public void displayWelcomeMessage() {
		System.out.println("Welcome in our ordering system.");
	}
	public List<MenuItem> askForOrder(MenuItemDTO dto){
		List<MenuItem> menuItems = new ArrayList<>();
		while(isCustomerContinuingOrder()){
			MenuItem menuItem = askForMenuItem(dto);
			menuItems.add(menuItem);
			continuingOrder = askIfOrderFinished();
		}
		return menuItems;
	}

	private boolean isCustomerContinuingOrder() {
		return continuingOrder;
	}
	private MenuItem askForMenuItem(MenuItemDTO dto) {
		String orderType = askForOrderType();
		dto.setOrderingDrink(isOrderingDrink(orderType));
		return askCustomerToSelectProducts(dto);
	}
	
	private String askForOrderType() {
		printOrderTypeQuestion();
		String orderType = getUserInput();
		if (validator.isOrderTypeInputValid(orderType))
			return orderType;
		printTypingErrorMessage();
		return askForOrderType();
	}
	private void printOrderTypeQuestion() {
		System.out.println(
				"If you would like to order a drink type 'drink'. If you would like to order a meal type 'meal'.");
	}
	private String getUserInput() {
		String userInput = scanner.nextLine();
		return userInput;
	}
	
	private void printTypingErrorMessage() {
		System.out.println("There were a mistake while typing. Please try again");
	}

	private boolean isOrderingDrink(String orderType) {
		return orderType.equals("drink");
	}

	public MenuItem askCustomerToSelectProducts(MenuItemDTO dto) {
		if (dto.isOrderingDrink())
			return askCustomerToSelectDrink(dto);
		return askCustomerToSelectMeal(dto);
	}

	private MenuItem askCustomerToSelectDrink(MenuItemDTO dto) {
		MenuItem menuItem = new MenuItem();
		printAskToSelectDrink();
		menuItem.getProducts().add(askForProduct(dto.getDrinks()));
		printAskIfWantDrinkAdditives();
		menuItem.getProducts().addAll((askForDrinkAdditives(dto.getDrinkAdditives())));
		return menuItem;
	}

	private void printAskToSelectDrink() {
		System.out.println("Select a drink:");
	}

	private Product askForProduct(List<Product> products) {
		printProducts(products);
		printAskToChoseProductNumber();
		String productNumber = getUserInput();
		if (validator.isProductNumberValid(productNumber, products.size()))
			return products.get(Integer.parseInt(productNumber));
		printTypingErrorMessage();
		return askForProduct(products);
	}

	private void printProducts(List<Product> product) {
		for (int i = 0; i < product.size(); i++) {
			System.out.println(i + ". " + product.get(i).getName() + " Price: " + product.get(i).getPrice());
		}
	}

	private void printAskToChoseProductNumber() {
		System.out.println("Type number of the product you want to order.");
	}

	private void printAskIfWantDrinkAdditives() {
		System.out.println("Would you like to add something to a drink?");
	}

	private List<Product> askForDrinkAdditives(List<Product> additives) {
		printAskToSelectAdditives();
		printProducts(additives);
		printAskToChooseAdditivesNumbers(additives.size());
		String additivesNumbers = getUserInput();
		if (validator.areAdditivesNumbersInputValid(additivesNumbers, additives.size()))
			return getSelectedAdditives(additives, additivesNumbers);
		printTypingErrorMessage();
		return askForDrinkAdditives(additives);
	}

	private void printAskToSelectAdditives() {
		System.out.println("Select 0 or more drink additives.");
	}

	private void printAskToChooseAdditivesNumbers(int numberOfAdditives) {
		System.out.println("If you don't want to add anything type " + numberOfAdditives);
		System.out.println("Otherwise type numbers of additives you want to add separated by space");
	}

	private List<Product> getSelectedAdditives(List<Product> additives, String additivesNumbersString) {
		List<Product> selectedAdditives = new LinkedList<>();
		int[] additivesNumbers = convertAdditivesNumbersStringToIntArray(additivesNumbersString.split(" "));
		if (userDoesntWantAdditives(additives, additivesNumbers))
			return selectedAdditives;
		for (int i = 0; i < additivesNumbers.length; i++) {
			selectedAdditives.add(additives.get(i));
		}
		return selectedAdditives;

	}

	private int[] convertAdditivesNumbersStringToIntArray(String[] additivesNumbers) {
		int[] number = new int[additivesNumbers.length];
		for (int i = 0; i < additivesNumbers.length; i++) {
			number[i] = Integer.parseInt(additivesNumbers[i]);
		}
		return number;
	}

	private boolean userDoesntWantAdditives(List<Product> additives, int[] additivesNumbers) {
		return additivesNumbers[0] == additives.size();
	}

	private MenuItem askCustomerToSelectMeal(MenuItemDTO dto) {
		MenuItem menuItem = new MenuItem();
		printAskToSelectCuisine();
		String cuisine = askForCuisine(dto.getAvailableCuisines());
		printAskToSelectMainCourse();
		menuItem.getProducts().add(askForProduct(getProductForGivenCountry(dto.getMainOrders(), cuisine)));
		printAskToSelectADessert();
		menuItem.getProducts().add(askForProduct(getProductForGivenCountry(dto.getDesserts(), cuisine)));
		return menuItem;
	}

	private void printAskToSelectCuisine() {
		System.out.println("Select a cuisine");
	}

	private String askForCuisine(List<String> availableCuisines) {
		printCuisines(availableCuisines);
		printAskToSelectCuisineNumber();
		String cuisineNumber = getUserInput();
		if (validator.isProductNumberValid(cuisineNumber, availableCuisines.size()))
			return availableCuisines.get(Integer.parseInt(cuisineNumber));
		printTypingErrorMessage();
		return askForCuisine(availableCuisines);
	}

	private void printCuisines(List<String> availableCuisines) {
		for (int i = 0; i < availableCuisines.size(); i++) {
			System.out.println(i + ". " + availableCuisines.get(i));
		}
	}

	private void printAskToSelectCuisineNumber() {
		System.out.println("Type number of the cuisine.");
	}

	private void printAskToSelectMainCourse() {
		System.out.println("Select a main course");
	}

	private List<Product> getProductForGivenCountry(List<Product> products, String cuisine) {
		List<Product> mainOrdersForGivenCountry = products.stream()
				.filter(p -> p.getCountry().getCountryName().equals(cuisine)).collect(Collectors.toList());
		return mainOrdersForGivenCountry;
	}

	private void printAskToSelectADessert() {
		System.out.println("Select a dessert");
	}
	
	private boolean askIfOrderFinished() {
		printAskIfOrderFinished();
		String orderFinished = getUserInput();
		if (validator.isBooleanAbbreviationInputValid(orderFinished))
			return convertUserAnswerToBoolean(orderFinished);
		printTypingErrorMessage();
		return askIfOrderFinished();
	}

	private void printAskIfOrderFinished() {
		System.out.println("Would you like to order something else? If yes type 'y', if no type 'n'");
	}

	private boolean convertUserAnswerToBoolean(String answer) {
		return answer.equals("y");
	}

	public void displayOrder(FoodOrder order) {
		System.out.println("Order confirmed!");
		System.out.println("Your order details:");
		System.out.println(order.toString());
	}

}
