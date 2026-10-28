package pe.edu.upeu.project_final.implementacion;

import pe.edu.upeu.project_final.interfaces.IClienteDao;
import pe.edu.upeu.project_final.model.Cliente;
import pe.edu.upeu.project_final.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements IClienteDao {
    @Override
    public boolean insert(Cliente c) {
        boolean flag = false;
        String query = "INSERT INTO cliente(nombres, apellidos, dni, email, genero) VALUES(?, ?, ?, ?, ?)";
        try(Connection cn = ConexionBD.getConnection();
            PreparedStatement st = cn.prepareStatement(query);
        ){
            st.setString( 1,c.getNombres());
            st.setString( 2,c.getApellidos());
            st.setString( 3,c.getDni());
            st.setString( 4,c.getEmail());
            st.setString( 5,c.getGenero());
            st.executeUpdate();
             flag = true;
        } catch (SQLException e){
            System.out.println("Error al listar clientes"+e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean update(Cliente c) {
        boolean flag = false;
        String query = "UPDATE cliente SET nombres= ?,apellidos=?,dni =?,email=?,genero=? " +
                "WHERE idcliente=?";
        try(Connection cn = ConexionBD.getConnection();
            PreparedStatement st = cn.prepareStatement(query);
        ){
            st.setString( 1,c.getNombres());
            st.setString( 2,c.getApellidos());
            st.setString( 3,c.getDni());
            st.setString( 4,c.getEmail());
            st.setString( 5,c.getGenero());
            st.setInt( 6,c.getIdcliente());
            st.executeUpdate();
            flag = true;
        } catch (SQLException e){
            System.out.println("Error al Actualizar clienete"+e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean delete(Cliente c) {
        boolean flag = false;
        String query = "DELETE FROM cliente WHERE idcliente=?";
        try(Connection cn = ConexionBD.getConnection();
            PreparedStatement st = cn.prepareStatement(query);
        ){
            st.setInt( 1,c.getIdcliente());
            st.executeUpdate();
          flag=true;
        } catch (SQLException e){
            System.out.println("Error al listar clientes"+e.getMessage());
        }
        return flag;
    }


    @Override
    public Cliente searchById(int id) {
        Cliente cl = null;
        String query = "SELECT * FROM cliente WHERE idcliente=?; ";
        try(Connection cn= ConexionBD.getConnection();
            PreparedStatement st= cn.prepareStatement(query)){
            st.setInt(1,id);
            try (ResultSet rs = st.executeQuery()){
                if (rs.next()) {
                    cl = new Cliente();
                    cl.setIdcliente(rs.getInt("idcliente"));
                    cl.setNombres(rs.getString("nombres"));
                    cl.setApellidos(rs.getString("apellidos"));
                    cl.setDni(rs.getString("dni"));
                    cl.setEmail(rs.getString("email"));
                    cl.setGenero(rs.getString("genero"));
                }
            }

            } catch (SQLException e){
                System.out.println("Error al listar clientes" + e.getMessage());
            }
        return cl;
    }

    @Override
    public Cliente searchByDni(String dni) {
        Cliente cl = null;
        String query = "SELECT * FROM cliente WHERE dni=?; ";
        try(Connection cn= ConexionBD.getConnection();
            PreparedStatement st= cn.prepareStatement(query)){
            st.setString(1,dni);
            try (ResultSet rs = st.executeQuery()){
                if (rs.next()) {
                    cl = new Cliente();
                    cl.setIdcliente(rs.getInt("idcliente"));
                    cl.setNombres(rs.getString("nombres"));
                    cl.setApellidos(rs.getString("apellidos"));
                    cl.setDni(rs.getString("dni"));
                    cl.setEmail(rs.getString("email"));
                    cl.setGenero(rs.getString("genero"));
                }
            }

        } catch (SQLException e){
            System.out.println("Error al listar clientes" + e.getMessage());
        }
        return cl;
    }

    @Override
    public List<Cliente> listAll() {
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM cliente; ";
        try(Connection cn= ConexionBD.getConnection();
            PreparedStatement st= cn.prepareStatement(query);
            ResultSet rs= st.executeQuery();
        ){
            while(rs.next()){
                Cliente cl = new Cliente();
                cl.setIdcliente(rs.getInt("idcliente"));
                cl.setNombres(rs.getString("nombres"));
                cl.setApellidos(rs.getString("apellidos"));
                cl.setDni(rs.getString("dni"));
                cl.setEmail(rs.getString("email"));
                cl.setGenero(rs.getString("genero"));
                lista.add(cl);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes"+e.getMessage());
        }
        return lista;

    }
}