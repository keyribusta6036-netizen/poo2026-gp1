package pe.edu.upeu.project_final.test;

import pe.edu.upeu.project_final.implementacion.ReservaDaoImpl;
import pe.edu.upeu.project_final.interfaces.IReservaDao;
import pe.edu.upeu.project_final.model.Cliente;
import pe.edu.upeu.project_final.model.Habitacion;
import pe.edu.upeu.project_final.model.Reserva;

import java.time.LocalDate;
import java.util.List;

public class TestReserva {
    static IReservaDao Dao = new ReservaDaoImpl();
    public void insert (){
        Reserva r = new Reserva();
        Cliente c = new Cliente();
        Habitacion h = new Habitacion();

        r.setFechaReserva(LocalDate.now());
        r.setFechaIngreso(LocalDate.parse("2026-10-23"));
        r.setFechaSalida(LocalDate.parse("2026-10-25"));
        r.setEstado("R");
        r.setTotal(200.00);


        c.setIdcliente(1);
        r.setCliente(c);

        h.setIdhabitacion(1);
        r.setHabitacion(h);
        boolean result = Dao.insert(r);
        if (result){
            System.out.println("Reserva sucess");
        }else {
            System.out.println("Reserva error");
        }

    }
    public void updateStatus(){
        Reserva r= new Reserva();
        r.setEstado("R");
        r.setIdreserva(1);
        boolean result = Dao.updateStatus(r);
        if (result){
            System.out.println("update success");
        }else{
            System.out.println("update error");
        }

    }
    public void validateHab(){
        Reserva r= new Reserva();
        Habitacion h = new Habitacion();
        h.setIdhabitacion(1);
        r.setHabitacion(h);

        r.setFechaIngreso(LocalDate.parse("2026-10-23"));
        r.setFechaSalida(LocalDate.parse("2026-10-25"));
        boolean ocupado = Dao.validatedHabitacion(r);
        if (ocupado){
            System.out.println("ocupado");
        }else{
            System.out.println("disponible");
        }

    }

    public void listar() {
        List<Reserva> list = Dao.listAll();
        if (list !=null && !list.isEmpty()) {
            for (Reserva res : list) {
                System.out.println("ID_RESRVA:" + res.getIdreserva());
                System.out.println("FECHA SALIDA: : " + res.getFechaReserva());
                System.out.println("FECHA INGRESO: " + res.getFechaIngreso());
                System.out.println("FECHA SALIDA: " + res.getFechaSalida());
                System.out.println("NUM: " + res.getHabitacion().getNumero());
                System.out.println("HAB_NUM: " + res.getHabitacion().getNumeroHabitacion());
                System.out.println("PISO_NUM: " + res.getHabitacion().getNumeroPiso());
                System.out.println("PRECIO: " + res.getHabitacion().getPrecio());
                System.out.println("CLIENTE: " + res.getCliente().getNombres());
                System.out.println("DNI: " + res.getCliente().getDni());
                System.out.println("ESTADO: " + res.getEstado());
                System.out.println("TOTAL: s/" + res.getTotal());
                System.out.println("--------------------------");

            }
        }else{
            System.out.println("no hay registros");
        }
    }

    public static void main(String[] args) {
        TestReserva t = new TestReserva();
        //t.insert();
        t.listar();
         //t.updateStatus();
        //t.validateHab();
    }

}
