package expensetracker;

import java.math.BigDecimal;
import java.util.Scanner;

public class ExpenseInitial {
	private String dateofexpense;
	private BigDecimal amount;
	private String category;
	private String description;

	/*public ExpenseInitial(String dateofexpense,BigDecimal amount, String category, String description) {
		this.dateofexpense = dateofexpense;
		this.amount = amount;
		this.category = category;
		this.description = description;
		
	} */
	
	//public String ConverttoString() {
		//return getdateofexpense() + getamount() + getcategory() + getdescription() + "@" + Integer.toHexString(hashCode());
	//}
	
	public static void main(String[] args) {
		ExpenseInitial Expense = new ExpenseInitial();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the date of the expense in the format of dd/mm/yyyy:");
		String dateofexpense = input.next();
		Expense.setdateofexpense(dateofexpense);
		
		System.out.println("Enter the amount spent:");
		BigDecimal amount = input.nextBigDecimal();
		Expense.setamount(amount);
		
		System.out.println("Enter the category of the expense:");
		String category = input.next();
		Expense.setcategory(category);
		
		System.out.println("Enter a brief description of the expense:");
		String description = input.next();
		description = description + input.nextLine();
		Expense.setdescription(description);
		
		System.out.println("Expense Information:");
		System.out.println("Date: " + Expense.getdateofexpense());
		System.out.println("Amount: £" + Expense.getamount());
		System.out.println("Category: " + Expense.getcategory());
		System.out.println("Description: " + Expense.getdescription());
		
		input.close();
	} 
	
	public void setdateofexpense(String dateofexpense) {
		this.dateofexpense=dateofexpense;
	}
	
	public void setamount(BigDecimal amount) {
		this.amount=amount;
	}
	
	public void setcategory(String category) {
		this.category=category;
	}
	
	public void setdescription(String description) {
		this.description=description;
	}

	public BigDecimal getamount() {
		return amount;
	}

	public String getdateofexpense() {
		return dateofexpense;
	}

	public String getcategory() {
		return category;
	}

	public String getdescription() {
		return description;
	}
}
