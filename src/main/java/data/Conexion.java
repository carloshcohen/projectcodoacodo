package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class Conexion {
    
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/concecionario?useSSL=false&useTimeZone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER="root";
    private static final String JDBC_PASS="root";
    
    public static DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASS);
        
        ds.setInitialSize(20);
        return ds;
    }
    
    public static Connection getConexion() throws SQLException, ClassNotFoundException{   
        Class.forName("com.mysql.cj.jdbc.Driver");
        return getDataSource().getConnection();
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement st) throws SQLException{
        st.close();
    } 
    
    public static void close(Connection cn) throws SQLException{
        cn.close();
    }
}
