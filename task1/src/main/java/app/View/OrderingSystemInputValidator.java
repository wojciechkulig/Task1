package app.View;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
@Component
public class OrderingSystemInputValidator {
	public boolean isOrderTypeInputValid(String orderType) {
		return orderType.equals("drink") || orderType.equals("meal");
	}
	public boolean isBooleanAbbreviationInputValid(String orderFinished) {
		orderFinished = orderFinished.toLowerCase();
		return orderFinished.equals("y") || orderFinished.equals("n");
	}
	public boolean isProductNumberValid(String productNumber, int numberOfProducts) {
		return isANumber(productNumber) && hasMenuGivenProduct(productNumber, numberOfProducts);

	}

	private boolean isANumber(String productNumber) {
		return Pattern.matches("[0-9]+", productNumber);
	}

	private boolean hasMenuGivenProduct(String productNumber, int numberOfProducts) {
		int number = Integer.parseInt(productNumber);
		return numberOfProducts > number;
	}
	public boolean areAdditivesNumbersInputValid(String additivesNumbersString, int numberOfProducts){
		String[] additivesNumbers = additivesNumbersString.split(" ");
		for(int i =0; i<additivesNumbers.length;i++){
			if(!(isANumber(additivesNumbers[i])&&hasMenuGivenAdditive(additivesNumbers[i],numberOfProducts))) return false;
		}
		return true;
	}
	private boolean hasMenuGivenAdditive(String productNumber, int numberOfProducts) {
		int number = Integer.parseInt(productNumber);
		return numberOfProducts >= number;
	}

}
