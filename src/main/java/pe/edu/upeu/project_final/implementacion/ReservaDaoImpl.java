package pe.edu.upeu.project_final.implementacion;

import pe.edu.upeu.project_final.interfaces.IReservaDao;
import pe.edu.upeu.project_final.model.Cliente;
import pe.edu.upeu.project_final.model.Habitacion;
import pe.edu.upeu.project_final.model.Reserva;
import pe.edu.upeu.project_final.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDaoImpl implements IReservaDao {

    @Override
    public boolean insert(Reserva r) {

        boolean flag = false;
        String query = " INSERT INTO reserva (clienteId,habitacionId,fecha_reserva,fecha_ingreso,fecha_salida,total,estado) " +
                " VALUES(?,?,?,?,?,?,?) ";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, r.getCliente().getIdcliente());
            st.setInt(2, r.getHabitacion().getIdhabitacion());
            // el localdate.toString devuelve "yyyy-MM-dd
            st.setString(3, r.getFechaReserva().toString());
            st.setString(4, r.getFechaIngreso().toString());
            st.setString(5, r.getFechaSalida().toString());
            st.setString(6, r.getEstado());
            st.setDouble(7, r.getTotal());

            st.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            System.out.println("Error al insertar reserva: " + e.getMessage());
        }


        return flag;
    }

    @Override
    public boolean update(Reserva r) {
        return false;
    }

    @Override
    public boolean delete(Reserva r) {
        return false;
    }

    @Override
    public Reserva searchById(int id) {
        return null;
    }

    @Override
    public List<Reserva> listAll() {
        List<Reserva> lista = new ArrayList<>();
        String query = " SELECT r.*, c.nombres ||' '||c.apellidos AS nombre_completo,c.dni, " +
                " h.numero,h.numeroPiso,h.numeroHabitacion,h.precio,  " +
                " h.estado AS estado_hab  " +
                " FROM reserva r,cliente c,habitacion h  " +
                " WHERE r.clienteId = c.idcliente  " +
                "  AND r.habitacionId = h.idhabitacion;  ";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query);
             ResultSet rs = st.executeQuery();
        ) {
            while (rs.next()) {

                Cliente cl = new Cliente();
                Habitacion h = new Habitacion();
                Reserva r = new Reserva();
                cl.setIdcliente(rs.getInt("clienteId"));
                cl.setNombres(rs.getString("nombre_completo"));
                cl.setDni(rs.getString("dni"));

                h.setIdhabitacion(rs.getInt("habitacionId"));
                h.setNumero(rs.getString("numero"));
                h.setNumeroPiso(rs.getInt("numeroPiso"));
                h.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
                h.setPrecio(rs.getDouble("precio"));


                r.setIdreserva(rs.getInt("idreserva"));
                r.setFechaReserva(LocalDate.parse(rs.getString("fecha_reserva")));
                r.setFechaIngreso(LocalDate.parse(rs.getString("fecha_ingreso")));
                r.setFechaSalida(LocalDate.parse(rs.getString("fecha_salida")));
                r.setTotal(rs.getDouble("total"));
                r.setEstado(rs.getString("estado"));

                r.setCliente(cl);
                r.setHabitacion(h);

                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes" + e.getMessage());
        }
        return lista;

    }

    @Override
    public boolean updateStatus(Reserva r) {
        boolean flag = false;
        String query = "UPDATE reserva SET estado = ? WHERE idreserva=?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query);
        ) {
            st.setString(1, r.getEstado());
            st.setInt(2, r.getIdreserva());
            flag = true;
        } catch (SQLException e) {
            System.out.println("Error al Actualizar cliente" + e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean validatedHabitacion(Reserva r) {

        boolean ocupado = false;
        String query = "SELECT count (1) FROM reserva " +
                "WHERE habitacionId = ? " +
                "AND estado IN ('R','O') " +
                "AND (?< fecha_salida " +
                " AND ? > fecha_ingreso) ";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, r.getHabitacion().getIdhabitacion());
            st.setString(2, r.getFechaIngreso().toString());
            st.setString(2, r.getFechaSalida().toString());
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    ocupado = rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al validar habitacion:" + e.getMessage());
        }
        return ocupado;

    }
    }





