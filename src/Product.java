import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
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
		ResultSet rs = stmt.executeQuery("SELECT * FROM produtos");
		
		return rs;
	}
	
	public boolean insertProduct(String descricao, double preco, boolean ativo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            con = objcon.conectar();
            pstmt = con.prepareStatement("INSERT INTO produtos (descricao, preco, ativo) VALUES (?, ?, ?)");
            pstmt.setString(1, descricao);
            pstmt.setDouble(2, preco);
            pstmt.setBoolean(3, ativo);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return success;
    }
	
	public boolean updateProduct(int id, String newDescricao, double newPreco, boolean newAtivo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            con = objcon.conectar();
            pstmt = con.prepareStatement("UPDATE produtos SET descricao=?, preco=?, ativo=? WHERE id=?");
            pstmt.setString(1, newDescricao);
            pstmt.setDouble(2, newPreco);
            pstmt.setBoolean(3, newAtivo);
            pstmt.setInt(4, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return success;
    }
	
	 public boolean deleteProduct(int id) {
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        boolean success = false;

	        try {
	            con = objcon.conectar();
	            pstmt = con.prepareStatement("DELETE FROM produtos WHERE id=?");
	            pstmt.setInt(1, id);
	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                success = true;
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (pstmt != null)
	                    pstmt.close();
	                if (con != null)
	                    con.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }

	        return success;
	    }

	    public ResultSet searchProduct(String descricao) {
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = objcon.conectar();
	            pstmt = con.prepareStatement("SELECT * FROM produtos WHERE descricao LIKE ?");
	            pstmt.setString(1, "%" + descricao + "%");
	            rs = pstmt.executeQuery();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        return rs;
	    }
	    
	    public ResultSet searchProductById(int id) {
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
	            con = objcon.conectar();
	            pstmt = con.prepareStatement("SELECT * FROM produtos WHERE id = ?");
	            pstmt.setInt(1, id);
	            rs = pstmt.executeQuery();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        return rs;
	    }

}
