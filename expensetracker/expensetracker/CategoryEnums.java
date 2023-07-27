package expensetracker;

import java.util.Scanner;

public class CategoryEnums {
		
	public enum ExpenseCategory {
       GROCERIES("Groceries"),
       RENT("Rent"),
       ENTERTAINMENT("Entertainment"),
       TAKEAWAYS("Takeaways"),
       TRANSPORT("Transport"),
       HEALTHCARE("Healthcare"),
       OTHER("Other");
       
		private final String displayName;
		
		ExpenseCategory(String displayName) {
	        this.displayName = displayName;
	    }

	    public String getDisplayName() {
	        return displayName;
	    }  
	}
}

