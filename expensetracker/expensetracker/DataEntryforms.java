package expensetracker;
import java.awt.EventQueue;
import java.util.Calendar;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JTextArea;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;

public class DataEntryforms {

	private JFrame frame;
	private JTable table;
	private JTable table1;
	private JTextField jtxtAmount;
	private JTextField jtxtRef;
	private JTextField jtxtDescription;
	private JTextField txtdate;
	private JTextField txtamount;
	private JTextField txtcategory;
	private JTextField txtdescription;
	private JDateChooser dateChooser;
	private JComboBox comboBox;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	DefaultTableModel model = new DefaultTableModel();
	private JTextField jtxtsbar;
	private JTextField jtxtscat;
	private JTextField jtxtsamo;

	/**
	 * Launch the application.
	 */
	
	
//The function updateTable() updates the table according to any changes made, when called	
	public void updateTable() 
		{
		conn = DisplayExpenses.ConnectDB();
		if(conn !=null) 
		{
			String sql = "Select Date, Amount, Category, Description from expensedata";
		
		try 
		{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			Object[] columnData = new Object[4];
			
			while (rs.next()) {
				columnData[0] = rs.getString("Date");
				columnData[1] = rs.getString("Amount");
				columnData[2] = rs.getString("Category");
				columnData[3] = rs.getString("Description");
				
				model.addRow(columnData);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	}
	
	public void open() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DataEntryforms window = new DataEntryforms();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataEntryforms window = new DataEntryforms();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	
//The function DataEntryforms() Creates the design of the table 	
	public DataEntryforms() {
		initialize();
		
		Object col[] = {"Date", "Amount", "Category", "Description"};
		model.setColumnIdentifiers(col);
		table.setModel(model);
			
// This section of code allows the user to search for an expense by the date of the expense		
		JButton sbDate = new JButton("Search by Date");
	    sbDate.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	try {
	                String dateToSearch = jtxtsbar.getText();
	                SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	                SimpleDateFormat dbDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	                
	                java.util.Date selectedDate = inputDateFormat.parse(dateToSearch);
	                String formattedDate = dbDateFormat.format(selectedDate); 

	                String sql = "SELECT * FROM expensedata WHERE Date =?";
	                pst = conn.prepareStatement(sql);
	                pst.setString(1, formattedDate);
	                rs = pst.executeQuery();

	                System.out.println("Date\t\tAmount\t\tCategory\t\tDescription");
	                
	                DefaultTableModel model = (DefaultTableModel) table1.getModel();
	                model.setRowCount(0);
	               
	                boolean foundData = false;
	                while (rs.next()) {
	                    String Date = rs.getString("Date");
	                    String Amount = rs.getString("Amount");
	                    String Category = rs.getString("Category");
	                    String Description = rs.getString("Description");

	                    System.out.println(Date + "\t" + Amount + "\t" + Category + "\t" + Description);
	                    model.addRow(new Object[]{Date, Amount, rs.getString("Category"), Description});
	                    foundData = true;
	                }
	                if(!foundData) {
	                	System.out.println("cant find record with date: " + jtxtsbar.getText());
	                }
	            } catch (Exception ev) {
	                System.out.println("Error executing query:");
	                ev.printStackTrace();
	            }
	        }

	    });
				
		sbDate.setBounds(590, 369, 126, 39);
		frame.getContentPane().add(sbDate);
		
		sbDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
	//The textbox where the user inputs the date of expense they're looking for
		jtxtsbar = new JTextField();
		jtxtsbar.setBounds(591, 329, 179, 39);
		frame.getContentPane().add(jtxtsbar);
		jtxtsbar.setColumns(10);
	//End of searchbyDate section of code	
		
	//This is the panel that stores the table which displays the results of the search functions	
		JPanel panelforsearch = new JPanel();
		panelforsearch.setBackground(new Color(204, 204, 255));
		panelforsearch.setLayout(null);
		panelforsearch.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelforsearch.setBounds(805, 313, 543, 441);
		frame.getContentPane().add(panelforsearch);
		
		JScrollPane scrollPaneforsearch = new JScrollPane();
		scrollPaneforsearch.setBounds(10, 11, 523, 402);
		panelforsearch.add(scrollPaneforsearch);
		
//This is the table that stores the results of the query search
		table1 = new JTable();
		table1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Date", "Amount", "Category", "Description"
			}
		));
		scrollPaneforsearch.setViewportView(table1);
		
//This section of code allows the user to search for an expense by category		
		JButton sbCategory = new JButton("Search by category");
		sbCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql ="Select * from expensedata where Category=?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, jtxtscat.getText());
					rs = pst.executeQuery();
					
					System.out.println("Date\t\tAmount\t\tCategory\t\tDescription");
					DefaultTableModel model = (DefaultTableModel) table1.getModel();
	                model.setRowCount(0);
					
					boolean foundData = false;
					while(rs.next()) {
						
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						String dateString = rs.getString("Date");
					
					    
					    java.util.Date Date = dateFormat.parse(dateString);				    
						String Amount = rs.getString("Amount");
						String Description = rs.getString("Description");
						String Category = rs.getString("Category");
						
						
						System.out.println(Date + "\t" + Amount + "\t" + Category + "\t" + Description);
						model.addRow(new Object[]{Date, Amount, rs.getString("Category"), Description});
						foundData = true;
						
					}
					if (!foundData) {
		                System.out.println("No records found with Category = " + jtxtscat.getText() );
		            }
					}
					catch(Exception ev) {
						System.out.println("Error executing query:");
				        ev.printStackTrace();
					}
				}
			});
		
		sbCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sbCategory.setBounds(590, 476, 142, 39);
		frame.getContentPane().add(sbCategory);
		
		//The textbox that the user enters the category by
		jtxtscat = new JTextField();
		jtxtscat.setColumns(10);
		jtxtscat.setBounds(590, 433, 179, 39);
		frame.getContentPane().add(jtxtscat);
		//End of section for search by category
		
		//This is the textbox that the user enters the amount to search for
		jtxtsamo = new JTextField();
		jtxtsamo.setColumns(10);
		jtxtsamo.setBounds(590, 540, 179, 39);
		frame.getContentPane().add(jtxtsamo);
		
		JButton sbAmount = new JButton("Search by amount");
		sbAmount.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
			String sql ="Select * from expensedata where Amount="+(jtxtsamo.getText());
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			System.out.println("Date\t\tAmount\t\tCategory\t\tDescription");
			DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);
			
			boolean foundData = false;
			while(rs.next()) {
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String dateString = rs.getString("Date");
			
			    
			    java.util.Date Date = dateFormat.parse(dateString);				    
				String Amount = rs.getString("Amount");
				String Description = rs.getString("Description");
				String Category = rs.getString("Category");
				
				
				System.out.println(Date + "\t" + Amount + "\t" + Category + "\t" + Description);
				model.addRow(new Object[]{Date, Amount, rs.getString("Category"), Description});
				foundData = true;
				
			}
			
			if(!foundData) {
	            System.out.println("No records found with Amount =" + jtxtsamo.getText());
	        }
			
			}
			catch(Exception ev) {
				System.out.println("Error executing query:");
		        ev.printStackTrace();
			}
		}
	});
		sbAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sbAmount.setBounds(590, 579, 142, 39);
		frame.getContentPane().add(sbAmount);
		//End of the section of code to search by amount
		
		//Label for the title of the application
		JLabel lblNewLabel_1 = new JLabel("Uni Budg£");
		lblNewLabel_1.setFont(new Font("Papyrus", lblNewLabel_1.getFont().getStyle(), 30));
		lblNewLabel_1.setBounds(595, 15, 254, 39);
		frame.getContentPane().add(lblNewLabel_1);
		conn = DisplayExpenses.ConnectDB();
		
		//The updateTable function called to show the new results of the query.
		updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	//Lables panels and other stuff for the input of data to create an expense and display all expenses.
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 204));
		frame.setBounds(0, 0, 1383, 812);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 204, 255));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(25, 65, 895, 163);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date of expense");
		lblNewLabel.setBounds(26, 21, 84, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(26, 50, 48, 14);
		panel_1.add(lblAmount);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(26, 105, 48, 14);
		panel_1.add(lblCategory);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(26, 80, 84, 14);
		panel_1.add(lblDescription);
		
		jtxtAmount = new JTextField();
		jtxtAmount.setColumns(10);
		jtxtAmount.setBounds(191, 47, 96, 20);
		panel_1.add(jtxtAmount);
		
		jtxtDescription = new JTextField();
		jtxtDescription.setColumns(10);
		jtxtDescription.setBounds(191, 77, 96, 20);
		panel_1.add(jtxtDescription);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(191, 21, 96, 20);
		panel_1.add(dateChooser);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Groceries", "Rent", "Entertainment", "Takeouts", "Transport", "Other"}));
		comboBox.setBounds(191, 103, 96, 18);
		panel_1.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 204, 255));
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(25, 250, 739, 60);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		//Section of code that deals with adding an expense
		JButton btnNewButton = new JButton("Add Expense");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat Dformat = new SimpleDateFormat("dd-MM-yyyy");
				
				String sql = "INSERT INTO expensedata(Date, Amount, Category, Description)VALUES(?,?,?,?)";
				
				try {
					pst = conn.prepareStatement(sql);
					
					
					pst.setString(1, Dformat.format(dateChooser.getDate()));
					pst.setString(2, jtxtAmount.getText());
					pst.setString(3, (String) comboBox.getSelectedItem());
					pst.setString(4, jtxtDescription.getText());
					
					pst.execute();
					rs.close();
					pst.close();
					
					
				}
				catch(Exception ev) {
					JOptionPane.showMessageDialog(null,"System update incomplete");
				}
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						
						dateChooser.getDate(),
						jtxtAmount.getText(),
						comboBox.getSelectedItem(),
						jtxtDescription.getText(),
					
				});
				
				if (table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Data Entry Form Updated", "Data Entry Form", JOptionPane.OK_OPTION);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(10, 11, 116, 38);
		panel_2.add(btnNewButton);
		//End of section
		
		//Section of code that allows for editing an expense
		JButton btnNewButton_1 = new JButton("Edit Expense");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateTable();
				
				DefaultTableModel iModel = (DefaultTableModel) table.getModel();
				if (table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Data Entry Form Updated", "Data Entry Form", JOptionPane.OK_OPTION);
					}
			}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(147, 11, 116, 38);
		panel_2.add(btnNewButton_1);
		//End of section
		
		//Section of code for deleting an expense
		JButton btnNewButton_2 = new JButton("Delete Expense");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel Mod = (DefaultTableModel) table.getModel();
				if (table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "No data to delete", "Data Entry Form", JOptionPane.OK_OPTION);
					}else {
						JOptionPane.showMessageDialog(null, "Select a row to delete", "Data Entry Form", JOptionPane.OK_OPTION);
					}
				
			}else {
				Mod.removeRow(table.getSelectedRow());
			}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBounds(291, 11, 116, 38);
		panel_2.add(btnNewButton_2);
		//End of section
		
		//Section of code dealing with exiting the application
		JButton btnNewButton_3_1 = new JButton("Exit");
	    btnNewButton_3_1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	            int result = JOptionPane.showConfirmDialog(frame, "Confirm your exit", "Data Entry Form", JOptionPane.YES_NO_OPTION);
	            if (result == JOptionPane.YES_OPTION) {
	                frame.dispose(); // Close the JFrame when "Yes" is clicked
	            }
	        }
	    });
				
	
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3_1.setBounds(435, 11, 116, 38);
		panel_2.add(btnNewButton_3_1);
		//end of section
		
		
		//This section of code deals with reseting an expense entry
		JButton btnNewButton_3_1_1 = new JButton("Reset");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dateChooser.setDate(null);
					jtxtAmount.setText(null);
					comboBox.setSelectedIndex(0);
					jtxtDescription.setText(null);
					
				}
				catch(Exception ev)
				{
					JOptionPane.showMessageDialog(null,ev);
				}
			}
		});
		btnNewButton_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3_1_1.setBounds(580, 11, 116, 38);
		panel_2.add(btnNewButton_3_1_1);
		//End of section
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(32, 329, 501, 425);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 444, 386);
		panel.add(scrollPane);
		
		//Table for the adding,editing,deleting etc functions.
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Date", "Amount", "Category", "Description"
			}
		));
		scrollPane.setViewportView(table);
	}
}
