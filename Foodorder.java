package saiyadu;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Foodorder {

	private JFrame frame;
	private JTextField fname;
	private JTextField qname;
	private JTextField pname;
	private JTextField mname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Foodorder window = new Foodorder();
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
	public Foodorder() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	
	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/foodorder", "root","");
        }
        catch (ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
 
    }
	
	public void table_load()
    {
     try
     {
    pst = con.prepareStatement("select * from orders");
    rs = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 815, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FOOD ORDER");
		lblNewLabel.setBounds(305, 11, 177, 53);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(66, 93, 338, 216);
		panel.setBorder(new TitledBorder(null, "Registation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Food Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 49, 90, 29);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quantity");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 106, 90, 29);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 157, 90, 29);
		panel.add(lblNewLabel_1_2);
		
		fname = new JTextField();
		fname.setBounds(110, 49, 218, 35);
		panel.add(fname);
		fname.setColumns(10);
		
		qname = new JTextField();
		qname.setColumns(10);
		qname.setBounds(110, 106, 218, 29);
		panel.add(qname);
		
		pname = new JTextField();
		pname.setColumns(10);
		pname.setBounds(110, 159, 218, 29);
		panel.add(pname);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setBounds(66, 320, 89, 44);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String foodname,quantity,price;
				
				foodname = fname.getText();
				quantity = qname.getText();
				price = pname.getText();
				
				try {
					pst = con.prepareStatement("insert into orders(foodname,quantity,price)values(?,?,?)");
					pst.setString(1, foodname);
					pst.setString(2, quantity);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
					          
					fname.setText("");
					qname.setText("");
					pname.setText("");
					mname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
				
				
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
				}
		});
		btnExit.setBounds(191, 320, 89, 44);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fname.setText("");
				qname.setText("");
				pname.setText("");
				mname.requestFocus();
				
			}
		});
		btnClear.setBounds(315, 320, 89, 44);
		frame.getContentPane().add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(66, 386, 338, 58);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("MENU");
		lblNewLabel_1_2_1.setBounds(23, 23, 78, 24);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_2_1);
		
		mname = new JTextField();
		mname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
		            String id = mname.getText();
		 
		                pst = con.prepareStatement("select foodname,quantity,price from orders where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String foodname = rs.getString(1);
		                String quantity = rs.getString(2);
		                String price = rs.getString(3);
		                
		                fname.setText(foodname);
		                qname.setText(quantity);
		                pname.setText(price);
		                
		                
		            }  
		            else
		            {
		             fname.setText("");
		             qname.setText("");
		                pname.setText("");
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }
		}
			//}
		});
		mname.setColumns(10);
		mname.setBounds(79, 18, 249, 29);
		panel_1.add(mname);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
            String foodname,quantity,price,bid;
				
				foodname = fname.getText();
				quantity = qname.getText();
				price = pname.getText();
				bid = mname.getText();
				
				try {
					pst = con.prepareStatement("update orders set foodname=?,quantity=?,price=? where id = ?");
					pst.setString(1, foodname);
					pst.setString(2, quantity);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated!!!!!");
					table_load();
					          
					fname.setText("");
					qname.setText("");
					pname.setText("");
					mname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
				
				
				
				
				
				
			}
		});
		btnUpdate.setBounds(483, 375, 89, 44);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bid;
				bid = mname.getText();
					
					try {
						pst = con.prepareStatement("delete from orders where id =?");
						
						pst.setString(1, bid);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record deleted!!!!!");
						table_load();
						          
						fname.setText("");
						qname.setText("");
						pname.setText("");
						mname.requestFocus();
						   }
						 
						catch (SQLException e1)
						        {
						e1.printStackTrace();
						}
					
				
				
				
				
				
				
				
			}
		});
		btnDelete.setBounds(613, 375, 89, 44);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(414, 93, 375, 269);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
