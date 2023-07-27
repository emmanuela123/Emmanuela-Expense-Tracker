package expensetracker;
import java.awt.EventQueue;


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

public class DataEntryforms {

	private JFrame frame;
	private JTable table;
	private JTextField jtxtAmount;
	private JTextField jtxtDescription;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	
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

	/**
	 * Create the application.
	 */
	public DataEntryforms() {
		initialize();
		
		Object col[] = {"Date", "Amount", "Category", "Description"};
		model.setColumnIdentifiers(col);
		table.setModel(model);
		conn = DisplayExpenses.ConnectDB();
		
		
		updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1460, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(25, 25, 837, 163);
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
		dateChooser.setDateFormatString("dd-MMM-yyyy");
		dateChooser.setBounds(191, 21, 96, 20);
		panel_1.add(dateChooser);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Groceries", "Rent", "Entertainment", "Takeouts", "Transport", "Other"}));
		comboBox.setBounds(191, 103, 96, 18);
		panel_1.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(32, 227, 830, 60);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Expense");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat Dformat = new SimpleDateFormat("dd-MM-yyyy");
				
				String sql = "INSERT INTO expensedata(Date, Amount, Category, Description)VALUES(?,?,?,?)";
				
				try {
					pst = conn.prepareStatement(sql);
					
					 //Date selectedDate = dateChooser.getDate();
			         //String formattedDate = Dformat.format(selectedDate);
					
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
		
		JButton btnNewButton_3 = new JButton("Search Expense");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3.setBounds(434, 11, 116, 38);
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Exit");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm your exit", "Data Entry Form", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
				
	
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3_1.setBounds(580, 11, 116, 38);
		panel_2.add(btnNewButton_3_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(32, 329, 633, 425);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 444, 386);
		panel.add(scrollPane);
		
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
