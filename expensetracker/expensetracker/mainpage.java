package expensetracker;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class mainpage {

	private JFrame frame;
	private JTextField txtDisplay;
	double firstnum;
	double secondnum;
	double result;
	String operations;
	String answer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage window = new mainpage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		});
	}

	/**
	 * Create the application.
	 */
	public mainpage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1355, 812);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
        JLabel lblUnibudg = new JLabel("Uni-Budg£");
        lblUnibudg.setFont(new Font("Papyrus", lblUnibudg.getFont().getStyle(), 30));
        lblUnibudg.setBounds(599, 11, 182, 44);
        panel.setLayout(null);
        panel.add(lblUnibudg);
        frame.getContentPane().add(panel);
        
        JPanel piechartpanel = new JPanel();
        piechartpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        piechartpanel.setBounds(43, 66, 528, 253);
        panel.add(piechartpanel);
        piechartpanel.setLayout(null);
        
        ////////CALCULATOR PANEL
        JPanel calculatorpanel = new JPanel();
        calculatorpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        calculatorpanel.setBounds(46, 354, 312, 413);
        panel.add(calculatorpanel);
        calculatorpanel.setLayout(null);
        
        txtDisplay = new JTextField();
        txtDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDisplay.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDisplay.setBounds(10, 11, 287, 70);
        calculatorpanel.add(txtDisplay);
        txtDisplay.setColumns(10);
        
        //------------------Row 1-------------------
        JButton btnNewButtonbck = new JButton("<-");
        btnNewButtonbck.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String backspace=null;
        		
        		if(txtDisplay.getText().length() > 0) {
        			StringBuilder str8 = new StringBuilder(txtDisplay.getText());
        			str8.deleteCharAt(txtDisplay.getText().length() -1);
        			backspace = str8.toString();
        			txtDisplay.setText(backspace);
        		}
        	}
        });
        btnNewButtonbck.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButtonbck.setBackground(SystemColor.inactiveCaption);
        btnNewButtonbck.setBounds(10, 92, 74, 60);
        calculatorpanel.add(btnNewButtonbck);
        
        JButton btnC = new JButton("C");
        btnC.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtDisplay.setText(null);
        	}
        });
        btnC.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnC.setBackground(SystemColor.inactiveCaption);
        btnC.setBounds(79, 92, 74, 60);
        calculatorpanel.add(btnC);
        
        JButton btnNewButtonper = new JButton("%");
        btnNewButtonper.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		firstnum = Double.parseDouble(txtDisplay.getText());
        		txtDisplay.setText("");
        		operations = "%";
        	}
        });
        btnNewButtonper.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButtonper.setBackground(SystemColor.inactiveCaption);
        btnNewButtonper.setBounds(152, 92, 74, 60);
        calculatorpanel.add(btnNewButtonper);
        
        JButton btnNewButtonplus = new JButton("+");
        btnNewButtonplus.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		firstnum = Double.parseDouble(txtDisplay.getText());
        		txtDisplay.setText("");
        		operations = "+";
        	}
        });
        btnNewButtonplus.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButtonplus.setBackground(SystemColor.inactiveCaption);
        btnNewButtonplus.setBounds(226, 92, 74, 60);
        calculatorpanel.add(btnNewButtonplus);
        
        //-------------Row 2------------------------
        JButton btnNewButton7 = new JButton("7");
        btnNewButton7.setBackground(SystemColor.scrollbar);
        btnNewButton7.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String EnterNumber = txtDisplay.getText() + btnNewButton7.getText();
        		txtDisplay.setText(EnterNumber);
        	}
        });
        btnNewButton7.setBounds(10, 152, 74, 60);
        calculatorpanel.add(btnNewButton7);
        
        JButton btnNewButton8 = new JButton("8");
        btnNewButton8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String EnterNumber = txtDisplay.getText() + btnNewButton8.getText();
        		txtDisplay.setText(EnterNumber);
        	}
        });
        btnNewButton8.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton8.setBackground(SystemColor.scrollbar);
        btnNewButton8.setBounds(79, 152, 74, 60);
        calculatorpanel.add(btnNewButton8);
        
        JButton btnNewButton9 = new JButton("9");
        btnNewButton9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {        		
            		String EnterNumber = txtDisplay.getText() + btnNewButton9.getText();
            		txtDisplay.setText(EnterNumber);           	
        	}
        });
        btnNewButton9.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton9.setBackground(SystemColor.scrollbar);
        btnNewButton9.setBounds(152, 152, 74, 60);
        calculatorpanel.add(btnNewButton9);
        
        JButton btnNewButtonsub = new JButton("-");
        btnNewButtonsub.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		firstnum = Double.parseDouble(txtDisplay.getText());
        		txtDisplay.setText("");
        		operations = "-";
        	}
        });
        btnNewButtonsub.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButtonsub.setBackground(SystemColor.inactiveCaption);
        btnNewButtonsub.setBounds(226, 152, 74, 60);
        calculatorpanel.add(btnNewButtonsub);
        
        
        //----------------------row 3 -----------------
        JButton btnNewButton4 = new JButton("4");
        btnNewButton4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String EnterNumber = txtDisplay.getText() + btnNewButton4.getText();
        		txtDisplay.setText(EnterNumber);
        		
        	}
        });
        btnNewButton4.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton4.setBackground(SystemColor.scrollbar);
        btnNewButton4.setBounds(10, 213, 74, 60);
        calculatorpanel.add(btnNewButton4);
        
        JButton btnNewButton5 = new JButton("5");
        btnNewButton5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String EnterNumber = txtDisplay.getText() + btnNewButton5.getText();
        		txtDisplay.setText(EnterNumber);
        	}
        });
        btnNewButton5.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton5.setBackground(SystemColor.scrollbar);
        btnNewButton5.setBounds(79, 213, 74, 60);
        calculatorpanel.add(btnNewButton5);
        
        JButton btnNewButton6 = new JButton("6");
        btnNewButton6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String EnterNumber = txtDisplay.getText() + btnNewButton6.getText();
        		txtDisplay.setText(EnterNumber);
        	}
        });
        btnNewButton6.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton6.setBackground(SystemColor.scrollbar);
        btnNewButton6.setBounds(152, 213, 74, 60);
        calculatorpanel.add(btnNewButton6);
        
        JButton btnNewButtonmul = new JButton("*");
        btnNewButtonmul.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		firstnum = Double.parseDouble(txtDisplay.getText());
        		txtDisplay.setText("");
        		operations = "*";
        	}
        });
        btnNewButtonmul.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButtonmul.setBackground(SystemColor.inactiveCaption);
        btnNewButtonmul.setBounds(226, 213, 74, 60);
        calculatorpanel.add(btnNewButtonmul);
        
               
        //--------------------------Row 4--------------------
        JButton btnNewButton1 = new JButton("1");
        btnNewButton1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String EnterNumber = txtDisplay.getText() + btnNewButton1.getText();
        		txtDisplay.setText(EnterNumber);
        	}
        });
        btnNewButton1.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton1.setBackground(SystemColor.scrollbar);
        btnNewButton1.setBounds(10, 274, 74, 60);
        calculatorpanel.add(btnNewButton1);
        
        JButton btnNewButton2 = new JButton("2");
        btnNewButton2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String EnterNumber = txtDisplay.getText() + btnNewButton2.getText();
        		txtDisplay.setText(EnterNumber);
        	}
        });
        btnNewButton2.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton2.setBackground(SystemColor.scrollbar);
        btnNewButton2.setBounds(79, 274, 74, 60);
        calculatorpanel.add(btnNewButton2);
        
        JButton btnNewButton3 = new JButton("3");
        btnNewButton3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String EnterNumber = txtDisplay.getText() + btnNewButton3.getText();
        		txtDisplay.setText(EnterNumber);
        	}
        });
        btnNewButton3.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton3.setBackground(SystemColor.scrollbar);
        btnNewButton3.setBounds(152, 274, 74, 60);
        calculatorpanel.add(btnNewButton3);
        
        JButton btnNewButtondiv = new JButton("/");
        btnNewButtondiv.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		firstnum = Double.parseDouble(txtDisplay.getText());
        		txtDisplay.setText("");
        		operations = "/";
        	}
        });
        btnNewButtondiv.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButtondiv.setBackground(SystemColor.inactiveCaption);
        btnNewButtondiv.setBounds(226, 274, 74, 60);
        calculatorpanel.add(btnNewButtondiv);
                       
        //--------------------------Row 5----------------------
        JButton btnNewButton0 = new JButton("0");
        btnNewButton0.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String EnterNumber = txtDisplay.getText() + btnNewButton0.getText();
        		txtDisplay.setText(EnterNumber);
        	}
        });
        btnNewButton0.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton0.setBackground(SystemColor.scrollbar);
        btnNewButton0.setBounds(10, 335, 74, 60);
        calculatorpanel.add(btnNewButton0);
        
        JButton btnNewButtondec = new JButton(".");
        btnNewButtondec.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String EnterNumber = txtDisplay.getText() + btnNewButtondec.getText();
        		txtDisplay.setText(EnterNumber);      		
        	}
        });
        btnNewButtondec.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButtondec.setBackground(SystemColor.scrollbar);
        btnNewButtondec.setBounds(79, 335, 74, 60);
        calculatorpanel.add(btnNewButtondec);
        
        JButton btnNewButtonboth = new JButton("+/-");
        btnNewButtonboth.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		double ops = Double.parseDouble(String.valueOf(txtDisplay.getText()));
        		ops =ops * (-1);
        		txtDisplay.setText(String.valueOf(ops));
        	}
        });
        btnNewButtonboth.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButtonboth.setBackground(SystemColor.scrollbar);
        btnNewButtonboth.setBounds(152, 335, 74, 60);
        calculatorpanel.add(btnNewButtonboth);
        
        JButton btnNewButtonequals = new JButton("=");
        btnNewButtonequals.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String answer;
        		secondnum = Double.parseDouble(txtDisplay.getText());
        		if (operations == "+")
        		{
        			result = firstnum + secondnum;
        			answer = String.format("%.2f", result);
        			txtDisplay.setText(answer);
        			
        		}
        		else if (operations == "-")
        		{
        			result = firstnum - secondnum;
        			answer = String.format("%.2f", result);
        			txtDisplay.setText(answer);
        		}
        		else if (operations == "*")
        		{
        			result = firstnum * secondnum;
        			answer = String.format("%.2f", result);
        			txtDisplay.setText(answer);
        		}
        		else if (operations == "/")
        		{
        			result = firstnum / secondnum;
        			answer = String.format("%.2f", result);
        			txtDisplay.setText(answer);
        		}
        		else if (operations == "%")
        		{
        			result = firstnum % secondnum;
        			answer = String.format("%.2f", result);
        			txtDisplay.setText(answer);
        		}
        		 
        		
        	}
        });
        btnNewButtonequals.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButtonequals.setBackground(SystemColor.inactiveCaption);
        btnNewButtonequals.setBounds(226, 335, 74, 60);
        calculatorpanel.add(btnNewButtonequals);
                                               
        ////End of calculator stuff
        
        JButton btnNewButton = new JButton("see analysis");
        btnNewButton.setBounds(663, 76, 158, 37);
        panel.add(btnNewButton);
        
        JButton btnNewButtonsee = new JButton("view my expenses");
        btnNewButtonsee.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
                    // When the button is pressed, create and open ClassB
                     DataEntryforms DataEntryforms = new DataEntryforms();
                     DataEntryforms.open();
        	}
        });
        btnNewButtonsee.setBounds(663, 149, 158, 37);
        panel.add(btnNewButtonsee);
        
        JButton btnNewButtonlog = new JButton("Log future expenses");
        btnNewButtonlog.setBounds(663, 225, 158, 37);
        panel.add(btnNewButtonlog);
        
        JPanel incomepanel = new JPanel();
        incomepanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        incomepanel.setBounds(432, 354, 340, 413);
        panel.add(incomepanel);
        incomepanel.setLayout(null);
        
        JPanel futurepanel = new JPanel();
        futurepanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        futurepanel.setBounds(842, 354, 426, 413);
        panel.add(futurepanel);
        futurepanel.setLayout(null);
        
        JLabel totalexpenseslabel = new JLabel("Total Expenses");
        totalexpenseslabel.setBounds(956, 87, 151, 30);
        panel.add(totalexpenseslabel);
        
        JButton expensereportButton = new JButton("Generate Expense Report");
        expensereportButton.setBounds(956, 204, 173, 37);
        panel.add(expensereportButton);
        
        JPanel expenseresultpanel = new JPanel();
        expenseresultpanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        expenseresultpanel.setBounds(947, 122, 195, 37);
        panel.add(expenseresultpanel);
	}
}
