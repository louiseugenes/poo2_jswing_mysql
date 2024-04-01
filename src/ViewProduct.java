import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ViewProduct extends JFrame {

	private JPanel contentPane;
	private JTextField txPesquisa;
	private JTable table;
	private Product product;
	 
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
		product = new Product();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton listBtn = new JButton("Listar");
		listBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	listProducts();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
		listBtn.setBounds(10, 46, 89, 23);
		contentPane.add(listBtn);
		
		JButton newBtn = new JButton("Novo");
        newBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String descricao = JOptionPane.showInputDialog("Informe a descrição do produto:");
                double preco = Double.parseDouble(JOptionPane.showInputDialog("Informe o preço do produto:"));
                boolean ativo = Boolean.parseBoolean(JOptionPane.showInputDialog("O produto está ativo? (true/false)"));
                product.insertProduct(descricao, preco, ativo);
                try {
                    listProducts();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        newBtn.setBounds(109, 11, 89, 23);
        contentPane.add(newBtn);

        JButton eventBtn = new JButton("Atualizar");
        eventBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    String newDescricao = JOptionPane.showInputDialog("Nova descrição:");
                    double newPreco = Double.parseDouble(JOptionPane.showInputDialog("Novo preço:"));
                    boolean newAtivo = Boolean.parseBoolean(JOptionPane.showInputDialog("Produto está ativo? (true/false)"));
                    product.updateProduct(id, newDescricao, newPreco, newAtivo);
                    try {
                        listProducts();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto na tabela para atualizar.");
                }
            }
        });
        eventBtn.setBounds(208, 11, 89, 23);
        contentPane.add(eventBtn);

        JButton deleteBtn = new JButton("Apagar");
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    product.deleteProduct(id);
                    try {
                        listProducts();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto na tabela para apagar.");
                }
            }
        });
        deleteBtn.setBounds(307, 11, 89, 23);
        contentPane.add(deleteBtn);

        txPesquisa = new JTextField();
        txPesquisa.setBounds(119, 47, 374, 20);
        contentPane.add(txPesquisa);
        txPesquisa.setColumns(10);

        JButton searchBtn = new JButton("Pesquisar");
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String descricao = txPesquisa.getText();
                try {
                    searchProducts(descricao);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        searchBtn.setBounds(494, 46, 89, 23);
        contentPane.add(searchBtn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 76, 570, 274);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
    }

    private void listProducts() throws SQLException {
        ResultSet rs = product.listDescProduct();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Descrição");
        model.addColumn("Preço");
        model.addColumn("Ativo");

        while (rs.next()) {
            model.addRow(new Object[]{rs.getInt("id"), rs.getString("descricao"), rs.getDouble("preco"),
                    rs.getBoolean("ativo")});
        }
        table.setModel(model);
    }

    private void searchProducts(String descricao) throws SQLException {
        ResultSet rs = product.searchProduct(descricao);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Descrição");
        model.addColumn("Preço");
        model.addColumn("Ativo");

        while (rs.next()) {
            model.addRow(new Object[]{rs.getInt("id"), rs.getString("descricao"), rs.getDouble("preco"),
                    rs.getBoolean("ativo")});
        }
        table.setModel(model);
    }
}
