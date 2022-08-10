
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class main extends JFrame {

	private JPanel contentPane;
	JLabel lbluserT;
	private JTextField txtStudentNo;
	private JTextField txtStudentName;
	private JTextField txtAddress;
	private JTextField textField_3;
	private JTable tableView;
	@SuppressWarnings("rawtypes")
	private JComboBox txtGender;
	private JTextField txtContactNo;
	private JLabel lblClock;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\FILES\\ECLIPSE\\COPL\\img\\login1.png"));
		setTitle("Student Record System");
		initialize();
		clock();
		viewRecords();
	}

	/**
	 * Create the frame.
	 * 
	 * @return
	 */

	public void searchRecord(String str) {
		DefaultTableModel mod = (DefaultTableModel) tableView.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(mod);
		tableView.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1017, 472);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbluserT = new JLabel();
		lbluserT.setBounds(824, 25, 151, 29);
		lbluserT.setFont(new Font("Courier New", Font.BOLD, 16));
		contentPane.add(lbluserT);

		JLabel lblNewLabel = new JLabel("STUDENT RECORD SYSTEM");
		lblNewLabel.setBounds(119, 30, 421, 42);
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD, 32));
		contentPane.add(lblNewLabel);

		txtStudentNo = new JTextField();
		txtStudentNo.setBounds(149, 119, 197, 34);
		contentPane.add(txtStudentNo);
		txtStudentNo.setColumns(10);

		txtStudentName = new JTextField();
		txtStudentName.setBounds(149, 171, 197, 34);
		txtStudentName.setColumns(10);
		contentPane.add(txtStudentName);

		txtAddress = new JTextField();
		txtAddress.setBounds(149, 223, 197, 34);
		txtAddress.setColumns(10);
		contentPane.add(txtAddress);

		JLabel lblNewLabel_1 = new JLabel("Student Number");
		lblNewLabel_1.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_1.setBounds(41, 128, 98, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Student Name");
		lblNewLabel_1_1.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(41, 180, 98, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Student Address");
		lblNewLabel_1_2.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(41, 232, 108, 14);
		contentPane.add(lblNewLabel_1_2);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setIcon(new ImageIcon("E:\\FILES\\ECLIPSE\\COPL\\img\\updated.png"));
		btnUpdate.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnUpdate.setBounds(463, 381, 98, 23);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableView.getSelectedRow() >= 0) {
					updateRecord(txtStudentNo.getText());
				}
			}
		});
		contentPane.add(btnUpdate);

		JButton btnView = new JButton("REFRESH");
		btnView.setBackground(Color.WHITE);
		btnView.setIcon(new ImageIcon("E:\\FILES\\ECLIPSE\\COPL\\img\\refresh.png"));
		btnView.setBounds(356, 381, 98, 23);
		btnView.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStudentNo.setEnabled(true);
				viewRecords();
			}
		});
		contentPane.add(btnView);

		JButton btnNewButton_2 = new JButton("ADD");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setIcon(new ImageIcon("E:\\FILES\\ECLIPSE\\COPL\\img\\plus.png"));
		btnNewButton_2.setBounds(149, 350, 89, 23);
		btnNewButton_2.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRecord();
			}
		});
		contentPane.add(btnNewButton_2);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setIcon(new ImageIcon("E:\\FILES\\ECLIPSE\\COPL\\img\\delete.png"));
		btnDelete.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnDelete.setBounds(778, 381, 98, 23);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableView.getSelectedRow() >= 0) {
					deleteRecord(txtStudentNo.getText());
				}
			}
		});
		contentPane.add(btnDelete);

		JButton btnNewButton_3 = new JButton("CLEAR");
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setIcon(new ImageIcon("E:\\FILES\\ECLIPSE\\COPL\\img\\clear.png"));
		btnNewButton_3.setBounds(257, 350, 89, 23);
		btnNewButton_3.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextfield();
			}
		});
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("EXIT");
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setIcon(new ImageIcon("E:\\FILES\\ECLIPSE\\COPL\\img\\button.png"));
		btnNewButton_4.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_4.setBounds(886, 381, 89, 23);
		contentPane.add(btnNewButton_4);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(356, 119, 619, 246);
		contentPane.add(scrollPane_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);

		tableView = new JTable();
		scrollPane.setViewportView(tableView);
		tableView.setColumnSelectionAllowed(true);
		tableView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtStudentNo.setEnabled(false);
				String id = tableView.getValueAt(tableView.getSelectedRow(), 0).toString();
				textField(id);
			}
		});
		tableView.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tableView.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableView.setBackground(new Color(255, 182, 193));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(41, 11, 80, 75);
		lblNewLabel_2.setIcon(new ImageIcon("E:\\FILES\\ECLIPSE\\COPL\\img\\id-card.png"));
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton_5 = new JButton("Search");
		btnNewButton_5.setIcon(new ImageIcon("E:\\FILES\\ECLIPSE\\COPL\\img\\search.png"));
		btnNewButton_5.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setBounds(869, 83, 106, 21);
		contentPane.add(btnNewButton_5);

		txtGender = new JComboBox();
		txtGender.setBackground(Color.WHITE);
		txtGender.setFont(new Font("Courier New", Font.BOLD, 11));
		txtGender.setBounds(149, 268, 197, 22);
		txtGender.setModel(new DefaultComboBoxModel(new String[] { "Choose", "Male", "Female", "Other" }));
		contentPane.add(txtGender);

		txtContactNo = new JTextField();
		txtContactNo.setBounds(149, 301, 197, 34);
		contentPane.add(txtContactNo);
		txtContactNo.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_3.setBounds(41, 271, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Contact Number");
		lblNewLabel_4.setFont(new Font("Courier New", Font.BOLD, 12));
		lblNewLabel_4.setBounds(41, 310, 98, 14);
		contentPane.add(lblNewLabel_4);

		lblClock = new JLabel("");
		lblClock.setFont(new Font("Courier New", Font.ITALIC, 13));
		lblClock.setBounds(686, 11, 289, 14);
		contentPane.add(lblClock);

		JLabel lblNewLabel_4_1 = new JLabel("Search");
		lblNewLabel_4_1.setFont(new Font("Courier New", Font.BOLD, 13));
		lblNewLabel_4_1.setBounds(356, 87, 86, 14);
		contentPane.add(lblNewLabel_4_1);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String look = txtSearch.getText();
				searchRecord(look);
			}
		});
		txtSearch.setColumns(10);
		txtSearch.setBounds(412, 83, 447, 23);
		contentPane.add(txtSearch);
	}

	// db connection
	static Connection connect() {
		try {
			String myDriver = "com.mysql.cj.jdbc.Driver";
			// connection string
			String url = "jdbc:mysql://localhost:3306/copl_db";
			Class.forName(myDriver);
			return (Connection) DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			System.out.print("Cannot connect to the database");
		}
		return null;
	}

	// add record
	public void addRecord() {
		Connection con = connect();
		Calendar date = Calendar.getInstance();
		java.sql.Date datecreated = new java.sql.Date(date.getTime().getTime());

		try {
			String sql = "INSERT INTO student_tbl (student_no, student_name,gender,contact_no,address,date_created) VALUES (?,?,?,?,?,NOW())";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);

			ps.setString(1, txtStudentNo.getText());
			ps.setString(2, txtStudentName.getText());
			ps.setString(3, txtGender.getSelectedItem().toString());
			ps.setString(4, txtContactNo.getText());
			ps.setString(5, txtAddress.getText());
			ps.execute();

			JOptionPane.showMessageDialog(null, "Record successfully added...");
			clearTextfield();
		} catch (Exception e) {
			System.out.print("Error... add" + e);
		}
	}

	// View Records
	public void viewRecords() {
		// TODO Auto-generated method stub
		Connection con = connect();
		DefaultTableModel mod = new DefaultTableModel();
		mod.addColumn("Id");
		mod.addColumn("Student Number");
		mod.addColumn("Student Name");
		mod.addColumn("Gender");
		mod.addColumn("Contact Number");
		mod.addColumn("Address");
		mod.addColumn("Date of Registration");

		// String colunm[]= {"Number","Student No", "Student Name","Address","Date of
		// Registration"};
		try {
			String sql = "SELECT * from student_tbl";
			Statement st = (Statement) con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				mod.addRow(new Object[] {
						rs.getInt("id"),
						rs.getString("student_no"),
						rs.getString("student_name"),
						rs.getString("gender"),
						rs.getString("contact_no"),
						rs.getString("address"),
						rs.getString("date_created")
				});
			}
			rs.close();
			st.close();
			con.close();

			tableView.setModel(mod);
			// tableView.setAutoResizeMode(0);
			tableView.getColumnModel().getColumn(0).setPreferredWidth(10);
			tableView.getColumnModel().getColumn(1).setPreferredWidth(30);
			tableView.getColumnModel().getColumn(2).setPreferredWidth(30);
			tableView.getColumnModel().getColumn(3).setPreferredWidth(30);
			tableView.getColumnModel().getColumn(4).setPreferredWidth(30);

		} catch (Exception ex) {
			System.out.print("Error...view" + ex);
		}

	}

	private void clearTextfield() {
		// TODO Auto-generated method stub
		txtStudentNo.setText("");
		txtStudentName.setText("");
		txtAddress.setText("");
		txtGender.setSelectedItem("Choose");
		txtContactNo.setText("");
		// JOptionPane.showMessageDialog(null, "Clear successful");
	}

	// delete

	private void deleteRecord(String id) {
		Connection con = connect();
		try {
			String sql = "DELETE from student_tbl where student_no = ?";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, id);
			ps.execute();

			ps.close();
			con.close();
			JOptionPane.showConfirmDialog(null, "Record deleted from database...");
			clearTextfield();
		} catch (Exception ex) {
			System.out.print("Error...delete" + ex);
		}
	}

	// update
	private void updateRecord(String id) {
		Connection con = connect();
		try {
			String sql = "UPDATE student_tbl SET student_no = ?, student_name = ?, gender = ?, contact_no = ?, address = ? where student_no = ?";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, txtStudentNo.getText());
			ps.setString(2, txtStudentName.getText());
			ps.setString(3, txtGender.getSelectedItem().toString());
			ps.setString(4, txtContactNo.getText());
			ps.setString(5, txtAddress.getText());
			ps.setString(6, txtStudentNo.getText());
			ps.execute();

			ps.close();
			con.close();
			JOptionPane.showMessageDialog(null, "Record successfully updated...");
			clearTextfield();
		} catch (Exception ex) {
			System.out.print("Error...update" + ex);
		}
	}

	// click event form table to input field
	private void textField(String id) {
		Connection con = connect();
		try {
			String sql = "SELECT * from student_tbl where id = ?";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				txtStudentNo.setText(rs.getString("student_no"));
				txtStudentName.setText(rs.getString("student_name"));
				if (rs.getString("gender").trim().equals("Male")) {
					txtGender.setSelectedItem("Male");
				} else {
					txtGender.setSelectedItem("Female");
				}

				txtContactNo.setText(rs.getString("contact_no"));
				txtAddress.setText(rs.getString("address"));
			}
		} catch (Exception ex) {
			System.out.print("Error...txt" + ex);
		}
	}

	public void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					while (true) {
						Calendar cl = new GregorianCalendar();
						int day = cl.get(Calendar.DAY_OF_MONTH);
						int month = cl.get(Calendar.MONTH);
						int year = cl.get(Calendar.YEAR);

						int sec = cl.get(Calendar.SECOND);
						int min = cl.get(Calendar.MINUTE);
						int hr = cl.get(Calendar.HOUR);

						lblClock.setText(
								"Time : " + hr + ":" + min + ":" + sec + " | Date : " + month + "/" + day + "/" + year);
						sleep(1000);

					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		clock.start();
	}
}