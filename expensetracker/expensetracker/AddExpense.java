package expensetracker;
import java.lang.Math;
import java.math.BigDecimal;
import java.util.Scanner;

public class AddExpense {
	private String dateofexpense;
	private BigDecimal amount;
	private String category;
	private String description;

public static void main(String[] args) {
	
	Scanner scan = new Scanner(System.in);
	
	System.out.println("Enter the date of the expense in the format of dd/mm/yyyy:");
	String dateofexpense = scan.next();
	
	System.out.println("Enter the amount spent:");
	BigDecimal amount = scan.nextBigDecimal();
	
	System.out.println("Enter the category of the expense:");
	String category = scan.next();
	
	System.out.println("Enter a brief description of the expense:");
	String description = scan.next();
	description = description + scan.nextLine();
	
	System.out.println("On the " + dateofexpense + " you spent £" + amount + " for " + description);
	System.out.println("This will be recorded as a " + category + " spend");
	//random comment2
	scan.close();
}

public BigDecimal amount() {
	return amount;
}

public String dateofexpense() {
	return dateofexpense;
}

public String category() {
	return category;
}

public String description() {
	return description;
}

}