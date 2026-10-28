package pe.edu.upeu.project_final.interfaces;

import pe.edu.upeu.project_final.model.Reserva;
import pe.edu.upeu.project_final.model.Reserva;

import java.util.List;

public interface IReservaDao {

    public boolean insert(Reserva r);
    public boolean update(Reserva r);
    public boolean delete(Reserva r);
    public Reserva searchById(int id);
    public List<Reserva> listAll();

    public boolean updateStatus(Reserva r);
    public boolean validatedHabitacion(Reserva r);

}



