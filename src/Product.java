import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Product {
	private Connector objcon;
	
	public Product() {
		objcon = new Connector();
	}
	
	public ResultSet listDescProduct() throws SQLException {
		Connection con;
		con = objcon.conectar();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM vendas.products");
		
		return rs;
	}
	
	public String updateProduct() {
		return null;
	}
	
	public String deleteProduct() {
		return null;
	}	
	
	
	public ResultSet searchProductId() {
		return null;
	}
	
	private void record() throws SQLException
	{
		Connection con=null;
		Connector objcon = new Connector();
		try {
			con=objcon.conectar();
			if (con == null) {
				JOptionPane.showMessageDialog(null, "Conexão não realizada");
			} else {
				Statement stmt = con.createStatement();
				String query="insert into db_pedido.product(descricao) values('"+txtDescricao.getText()+"')";
				stmt.executeUpdate(query);
				listDescProduct();
				txtDescricao.setText (null);
				desabilitarText();
			}
		} catch (Exception ex) {
			con.close();
			JOptionPane.showMessageDialog(null, "Não foi possivel gravar." + ex.getMessage());
		}
	}

}
