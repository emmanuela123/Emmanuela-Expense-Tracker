package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import expensetracker.ExpenseInitial;

class ExpenseInitialTests {

	@Test
	@DisplayName("constrcutor test")
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
		System.out.println("Amount: " + Expense.getamount());
		System.out.println("Category: " + Expense.getcategory());
		System.out.println("Description: " + Expense.getdescription());
		
		input.close();
		
		
	}
	}

