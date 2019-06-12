import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MyNewFrame extends JFrame{
	
	Connection conn = null;
	PreparedStatement state = null;
	
	 ResultSet result = null;
	
	int id = -1;
	
	JTabbedPane tab=new JTabbedPane();
	
	JTable tableCustomers = new JTable();
	JScrollPane scrollCustomers = new JScrollPane(tableCustomers);
	
	JTable tableProducts = new JTable();
	JScrollPane scrollProducts = new JScrollPane(tableProducts);
	
	JTable tableOrders = new JTable();
	JScrollPane scrollOrders = new JScrollPane(tableOrders);
	
	
	JPanel customers = new JPanel();
	JPanel orders = new JPanel();
	JPanel products = new JPanel();
	JPanel spravka = new JPanel();
	
	JPanel customersTop = new JPanel();
	JPanel customersMid = new JPanel();
	JPanel customersDown = new JPanel();
	
	JPanel ordersTop = new JPanel();
	JPanel ordersMid = new JPanel();
	JPanel ordersDown = new JPanel();
	
	JPanel productsTop = new JPanel();
	JPanel productsMid = new JPanel();
	JPanel productsDown = new JPanel();
	
	JLabel customersName = new JLabel("Name: ");
	JLabel customersAge = new JLabel("Age: "); 
	JTextField customersNameTF = new JTextField(20);
	JTextField customersAgeTF = new JTextField(20);
	JComboBox<String> customerCombo = new JComboBox<String>();
	JComboBox<String> productCombo = new JComboBox<String>();
	
	JButton customersAdd = new JButton ("Add");
	JButton customersDel = new JButton ("Delete");
	JButton customersUpd = new JButton ("Update");
	JButton customersSearch = new JButton ("Search");
	
	
	
	//fill orders tab
	
	JLabel ordersDate = new JLabel("Date: ");
	JTextField ordersDateTF = new JTextField(20);
	JLabel customersOrders = new JLabel("Customers: ");
	JLabel productsOrders = new JLabel("Products: ");
	
	JButton ordersAdd = new JButton ("Add");
	JButton ordersDel = new JButton ("Delete");
	JButton ordersUpd = new JButton ("Update");
	JButton ordersSearch = new JButton ("Search");
	
	//fill products tab
	
	JLabel productsName = new JLabel("Name: ");
	JLabel productsPrice = new JLabel("Price: ");
	JTextField productsNameTF = new JTextField(20);
	JTextField productsPriceTF = new JTextField(20);
	
	JButton productsAdd = new JButton ("Add");
	JButton productsDel = new JButton ("Delete");
	JButton productsUpd = new JButton ("Update");
	JButton productsSearch = new JButton ("Search");
	
	public MyNewFrame ( ) {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		scrollProducts.setPreferredSize(new Dimension(450, 150));
		scrollCustomers.setPreferredSize(new Dimension(450, 150));
		scrollOrders.setPreferredSize(new Dimension(450, 150));
		
		customersTop.setLayout(new GridLayout(2, 2));
		customersTop.add(customersName);
		customersTop.add(customersNameTF);
		customersTop.add(customersAge);
		customersTop.add(customersAgeTF);
		
		customersMid.add(customersAdd);
		customersMid.add(customersDel);
		customersMid.add(customersUpd);
		customersMid.add(customersSearch);
		
		customersDown.add(scrollCustomers);
		
		customers.setLayout(new GridLayout(3, 1));
		customers.add(customersTop);
		customers.add(customersMid);
		customers.add(customersDown);
		
		
		
		productsTop.setLayout(new GridLayout(2, 2));
		productsTop.add(productsName);
		productsTop.add(productsNameTF);
		productsTop.add(productsPrice);
		productsTop.add(productsPriceTF);
		
		productsMid.add(productsAdd);
		productsMid.add(productsDel);
		productsMid.add(productsUpd);
		productsMid.add(productsSearch);
		
		productsDown.add(scrollProducts);
		
		products.setLayout(new GridLayout(3, 1));
		products.add(productsTop);
		products.add(productsMid);
		products.add(productsDown);
		
		
		
		ordersTop.setLayout(new GridLayout(3, 2));
		ordersTop.add(customersOrders);
		ordersTop.add(customerCombo);
		ordersTop.add(productsOrders);
		ordersTop.add(productCombo);
		ordersTop.add(ordersDate);
		ordersTop.add(ordersDateTF);
		
		
		ordersMid.add(ordersAdd);
		ordersMid.add(ordersDel);
		ordersMid.add(ordersUpd);
		ordersMid.add(ordersSearch);
		
		ordersDown.add(scrollOrders);
		
		orders.setLayout(new GridLayout(3, 1));
		orders.add(ordersTop);
		orders.add(ordersMid);
		orders.add(ordersDown);
				
		
		
		
		tab.add("Customers", customers);
		tab.add("Products", products);
		tab.add("Orders", orders);
		tab.add("Spravka", spravka);
		
		
		//customers addActionListener
		
		customersAdd.addActionListener(new AddCustomers());
		customersDel.addActionListener(new DeleteCustomers());
		customersUpd.addActionListener(new UpdateCustomers());
		customersSearch.addActionListener(new CustomersSearch());
		tableCustomers.setModel(DBConnector.getAllModel("Customers"));
		tableCustomers.addMouseListener(new MouseCustomersTableAction());
		
		//products addActionListener
		
		productsAdd.addActionListener(new AddProducts());
		productsDel.addActionListener(new DeleteProducts());
		productsUpd.addActionListener(new UpdateProducts());
		
		productsSearch.addActionListener(new ProductsSearch());
		
		tableProducts.setModel(DBConnector.getAllModel("Products"));
		tableProducts.addMouseListener(new MouseProductsTableAction());
		
		
		//orders addActionListener
		
		ordersAdd.addActionListener(new AddOrders());
		ordersDel.addActionListener(new DeleteOrders());
		tableOrders.setModel(DBConnector.getAllModel("Orders"));
		tableOrders.addMouseListener(new MouseOrdersTableAction());
				
		
		this.add(tab);
		customersCombo();
		this.setVisible(true);
		
		this.add(tab);
		productsCombo();
		this.setVisible(true);
		
	}
	
	void customersCombo() {
		String item = null;
		customerCombo.removeAllItems();
		String sql = "select * from Customers";
		conn = DBConnector.getConnection();
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			while(result.next()) {
				item = result.getObject(1).toString()+"." + result.getObject(2).toString();
				customerCombo.addItem(item);
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	class AddCustomers implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = customersNameTF.getText();
			int age = Integer.parseInt(customersAgeTF.getText());
			String sql = "insert into customers values (null,?,?);";
			
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, name);
				state.setInt(2, age);
				state.execute();
				tableCustomers.setModel(DBConnector.getAllModel("Customers"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		//	clearForm();
			
			customersCombo();
			
		}
		
	}//end AddCustomer
	
	class DeleteCustomers implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String sql = "delete from customers where customer_id=?";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				id = -1;
				tableCustomers.setModel(DBConnector.getAllModel("Customers"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			customersCombo();
			
		}
		
	}
	
	
	class UpdateCustomers implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = customersNameTF.getText();
			int age = Integer.parseInt(customersAgeTF.getText());
			String sql = "UPDATE CUSTOMERS SET NAME=?,  AGE=?  WHERE CUSTOMER_ID=?";
			conn = DBConnector.getConnection();
			
			try {
				state = conn.prepareStatement(sql);
				
				state.setString(1, name);
				state.setInt(2, age);
				state.setInt(3, id);
				state.execute();
				id = -1;
				tableCustomers.setModel(DBConnector.getAllModel("Customers"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			customersCombo();
			
		}
		
	}
	
		void productsCombo() {
		String item = null;
		productCombo.removeAllItems();
		String sql = "select * from Products";
		conn = DBConnector.getConnection();
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			while(result.next()) {
				item = result.getObject(1).toString()+"." + result.getObject(2).toString();
				productCombo.addItem(item);
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	class AddProducts implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = productsNameTF.getText();
			float price = Float.parseFloat(productsPriceTF.getText());
			String sql = "insert into products values (null,?,?);";
			
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, name);
				
				state.setFloat(2, price);
				
				state.execute();
				tableProducts.setModel(DBConnector.getAllModel("Products"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			productsCombo();
			
		//	clearForm();
			
		}
		
	}//end AddProduct
	
	class DeleteProducts implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String sql = "delete from products where product_id=?";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				id = -1;
				tableProducts.setModel(DBConnector.getAllModel("Products"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			productsCombo();
			
		}
		
	}
	
	class UpdateProducts implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String name = productsNameTF.getText();
			float price = Float.parseFloat(productsPriceTF.getText());
			
			String sql = "UPDATE PRODUCTS SET NAME=?,  PRICE=?  WHERE PRODUCT_ID=?";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, name);
				state.setFloat(2, price);
				state.setInt(3, id);
				state.execute();
				id = -1;
				tableProducts.setModel(DBConnector.getAllModel("Products"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//customersCombo();
			
		}
		
	}
	
	class AddOrders implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String customercb = customerCombo.getSelectedItem().toString();
			String productcb = productCombo.getSelectedItem().toString();
			String date = ordersDateTF.getText();
			String sql = "insert into orders values (null,?,?,?);";
			
			
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				
				state.setInt(1, Integer.parseInt(customercb.substring(0,customercb.indexOf('.') )));
				state.setInt(2, Integer.parseInt(productcb.substring(0,productcb.indexOf('.') )));
				state.setString(3, date);
				
				state.execute();
				tableOrders.setModel(DBConnector.getAllModel("orders"));
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		//	clearForm();
			
		}
		
	}//end AddProduct
	
	class DeleteOrders implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String sql = "delete from orders where order_id=?";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				id = -1;
				tableOrders.setModel(DBConnector.getAllModel("Orders"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//productsCombo();
			
		}
		
	}
	
	
	class UpdateOrders implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			//String customercb = customerCombo.getSelectedItem().toString();
			//String productcb = productCombo.getSelectedItem().toString();
			String date = ordersDateTF.getText();
			
			//String name = productsNameTF.getText();
			//float price = Float.parseFloat(productsPriceTF.getText());
			
			String sql = "UPDATE ORDERS SET DATE=? WHERE CUSTOMER_ID=?;";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setString(3, date);
				state.execute();
				id = -1;
				tableProducts.setModel(DBConnector.getAllModel("Products"));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//customersCombo();
			
		}
		
	}
	
	
	class MouseProductsTableAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tableProducts.getSelectedRow();
			id = Integer.parseInt(tableProducts.getValueAt(row, 0).toString());
			
			
			if(e.getClickCount() > 1) {
				productsNameTF.setText(tableProducts.getValueAt(row, 1).toString());
				productsPriceTF.setText(tableProducts.getValueAt(row, 2).toString());
				
			}
		}
	
	



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
		

}
	
	class MouseCustomersTableAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tableCustomers.getSelectedRow();
			id = Integer.parseInt(tableCustomers.getValueAt(row, 0).toString());
			
			if(e.getClickCount() > 1) {
				customersNameTF.setText(tableCustomers.getValueAt(row, 1).toString());
				//String name = tableCustomers.getValueAt(row, 2).toString();
				customersAgeTF.setText(tableCustomers.getValueAt(row, 2).toString());
				//customerCombo.setSelectedItem(name);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	class MouseOrdersTableAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tableOrders.getSelectedRow();
			id = Integer.parseInt(tableOrders.getValueAt(row, 0).toString());
			String customercb = customerCombo.getSelectedItem().toString();
			String productcb = productCombo.getSelectedItem().toString();
			String date = ordersDateTF.getText();
			
			if(e.getClickCount() > 1) {
				customerCombo.setSelectedItem(tableCustomers.getValueAt(row, 1).toString());
				
				productCombo.setSelectedItem(tableProducts.getValueAt(row, 2).toString());
				
				ordersDateTF.setText((String) tableOrders.getValueAt(row, 3));
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		

}
	
	class ProductsSearch implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			{	String name = productsNameTF.getText();
				String sql = "select * from products where name=?";
				conn = DBConnector.getConnection();
				try {
					state = conn.prepareStatement(sql);
					state.setString(1, name);
					state.execute();
					tableProducts.setModel(DBConnector.getAllModel("Products"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}//end method
			
		}
		
	}
	
	class CustomersSearch implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			{	String name = customersNameTF.getText();
				String sql = "select * from customers where name=?";
				conn = DBConnector.getConnection();
				try {
					state = conn.prepareStatement(sql);
					state.setString(1, name);
					state.execute();
					tableCustomers.setModel(DBConnector.getAllModel("Customers"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}//end method
			
		}
		
	}
	
}
	

