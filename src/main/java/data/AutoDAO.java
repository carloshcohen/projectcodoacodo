
package data;

import static data.Conexion.close;
import static data.Conexion.getConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Auto;


public class AutoDAO {
    
    private static final String SQL_SELECT = "SELECT * FROM autos where disponible=1";
    private static final String SQL_SELECT_BY_ID = "SELECT id, marca, modelo, caja, precio, puertas, disponible FROM autos WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO autos(marca, modelo, caja, precio, puertas, disponible) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE autos SET marca = ?, modelo = ?, caja = ?, precio = ?, puertas = ?, disponible=1  WHERE id = ?";
    private static final String SQL_LOGIC_DELETE= "UPDATE autos set disponible = ? WHERE id = ?";
    
    
    //OBTENGO EL LISTADO DE AUTOS
    public List<Auto> seleccionar() throws ClassNotFoundException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auto auto = null;
        List<Auto> autos = new ArrayList();

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String caja = rs.getString("caja");
                double precio = rs.getDouble("precio");
                int puertas = rs.getInt("puertas");
                boolean disponible = rs.getBoolean("disponible");

                auto = new Auto(id, marca, modelo, caja, precio, puertas, disponible);

                autos.add(auto);
            }
        } catch (SQLException ex) {
            System.out.println("HAY QUILOMBO EN LISTAR DAO");
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return autos;
    }
    
    // INSERTO LOS AUTOS
    public int insertarAuto(Auto auto) throws ClassNotFoundException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, auto.getMarca());
            stmt.setString(2, auto.getModelo());
            stmt.setString(3, auto.getCaja());
            stmt.setDouble(4, auto.getPrecio());
            stmt.setInt(5, auto.getPuertas());
            stmt.setBoolean(6,auto.getDisponible());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("HAY QUILOMBO EN INSERTAR DAO");
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    // ELIMINAR REGISTROS 
    public int eliminar(int idauto) throws ClassNotFoundException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            
            stmt = conn.prepareStatement(SQL_LOGIC_DELETE);
            stmt.setInt(1, 0);
            stmt.setInt(2, idauto);
            registros = stmt.executeUpdate();
            System.out.println("Vendiendo " +idauto);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                System.out.println("Problemas en Eliminar");
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    // SELECT BY ID
    public Auto seleccionarPorId(int id) throws ClassNotFoundException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auto auto = null;

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int idautos = rs.getInt("id");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String caja = rs.getString("caja");
                double precio = rs.getDouble("precio");
                int puertas = rs.getInt("puertas");
                boolean disponible = rs.getBoolean("disponible");

                auto = new Auto(idautos, marca, modelo, caja, precio, puertas, disponible);

            }
        } catch (SQLException ex) {
            System.out.println("Problemas en seleccionar por id");
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return auto;
    }
    
    public int actualizar (Auto auto) throws ClassNotFoundException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros=0;
        try{
            conn = getConexion();
            stmt=conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, auto.getMarca());
            stmt.setString(2, auto.getModelo());
            stmt.setString(3, auto.getCaja());
            stmt.setDouble(4, auto.getPrecio());
            stmt.setInt(5, auto.getPuertas());
            stmt.setInt(6, auto.getId());
            registros = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                System.out.println("Problemas en actualizar");
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
