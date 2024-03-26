import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Connector {
	
	private Connection connection = null;
	private Statement statement = null;
	
	public Connection conectar() {
		String returning = null;
		String server = "jdbc:mysql://localhost:3306/sakila";
		String user = "root";
		String password = "7978";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(server, user, password);
			
			this.statement = this.connection.createStatement();
			
			returning = "OK";
		}
		catch(Exception e) {
			returning = "Não foi possível conectar ao banco" + e.getMessage();
		}
		return this.connection;
	}

	
}
