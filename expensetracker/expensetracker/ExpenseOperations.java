package expensetracker;
import java.util.Stack;

import java.math.BigDecimal;
import java.util.Scanner;


public class ExpenseOperations {
	private static String dateofexpense;
	private static BigDecimal amount;
	private static String category;
	private static String description;
	
	public static void main(String[] args) {
		ExpenseInitial Expense = new ExpenseInitial();
		Scanner input = new Scanner(System.in);
		
		int StackSize = 4;
		Stack <String> stack = new Stack<>();
		
		int i = 0;
		while(i < StackSize) {
			if(i == 0) {
				System.out.println("Enter the date of the expense in the format of dd/mm/yyyy:");
				dateofexpense = input.next();
				Expense.setdateofexpense(dateofexpense);
				stack.push(dateofexpense);
				
				i++;
			}
			else if(i == 1) {
				System.out.println("Enter the amount of the expense");
				amount = input.nextBigDecimal();
				Expense.setamount(amount);
				stack.push(amount.toString());
				
				i++;
			}
			else if(i == 2) {
				System.out.println("Enter the category of the expense");
				input.nextLine();
				category = input.nextLine();
				Expense.setcategory(category);
				stack.push(category);
				
				i++;
			}
			else if(i == 3) {
				System.out.println("Enter a brief description of the expense");
				description = input.nextLine();
				Expense.setdescription(description);
				stack.push(description);
				
				i++;
			}
			else {
				System.out.println("Error somewhere");
			}
		}
		
		
	    System.out.println(stack);
		input.close();
	}
		
}

/* System.out.println("Enter the date of the expense in the format of dd/mm/yyyy:");
dateofexpense = input.next();
Expense.setdateofexpense(dateofexpense);

System.out.println("Enter the amount spent:");
amount = input.nextBigDecimal();
Expense.setamount(amount);

System.out.println("Enter the category of the expense:");
category = input.next();
Expense.setcategory(category);

System.out.println("Enter a brief description of the expense:");
description = input.next();
description = description + input.nextLine();
Expense.setdescription(description);

System.out.println("Expense Information:");
System.out.println("Date: " + Expense.getdateofexpense());
System.out.println("Amount: £" + Expense.getamount());
System.out.println("Category: " + Expense.getcategory());
System.out.println("Description: " + Expense.getdescription()); */

/*int i = 0;
while(i < StackSize){
    
        String info = input.nextLine();
        stack.push(info);
        
        i++;
    } */