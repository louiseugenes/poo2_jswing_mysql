import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;

public class ViewProduct extends JFrame {

	private JPanel contentPane;
	private JTextField txResult;
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewProduct frame = new ViewProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton listBtn = new JButton("Listar");
		listBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product prod = new Product();
				ResultSet rs = null;
				try {
					rs = prod.listDescProduct();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(rs != null) {
					try {
						while(rs.next()) {
							txResult.setText(rs.getString("Description").toString());
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		listBtn.setBounds(10, 46, 89, 23);
		contentPane.add(listBtn);
		
		JButton newBtn = new JButton("Novo");
		newBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame newframe = new JFrame();
				newframe.setVisible(true);
			}
		});
		newBtn.setBounds(106, 46, 89, 23);
		contentPane.add(newBtn);
		
		JButton eventBtn = new JButton("New btn");
		eventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame newframe = new JFrame();
				newframe.setVisible(true);
			}
		});
		eventBtn.setBounds(245, 46, 89, 23);
		contentPane.add(eventBtn);
	}
}
