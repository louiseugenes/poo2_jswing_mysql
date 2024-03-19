import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Product {
	private Connector objcon;
	
	public Product() {
		objcon = new Connector();
	}
	
	public ResultSet listDescProduct() throws SQLException {
		Connection con=null;
		con.objcon.conectar();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM vendas.products");
		
		return rs;
	}

}
