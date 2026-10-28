package pe.edu.upeu.project_final.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Habitacion {
    private final IntegerProperty idhabitacion = new SimpleIntegerProperty();
    private final StringProperty numero = new SimpleStringProperty();
    private final IntegerProperty numeroPiso = new SimpleIntegerProperty();
    private final IntegerProperty numeroHabitacion = new SimpleIntegerProperty();
    private final StringProperty tipo = new SimpleStringProperty();
    private final DoubleProperty precio = new SimpleDoubleProperty();
    private final StringProperty estado = new SimpleStringProperty("DISPONIBLE");

    public Habitacion() {
    }

    public Habitacion(int idhabitacion, String numero, int numeroPiso, int numeroHabitacion,
                      String tipo, double precio, String estado) {
        setIdhabitacion(idhabitacion);
        setNumero(numero);
        setNumeroPiso(numeroPiso);
        setNumeroHabitacion(numeroHabitacion);
        setTipo(tipo);
        setPrecio(precio);
        setEstado(estado);
    }

    public final int getIdhabitacion() { return idhabitacion.get(); }
    public final void setIdhabitacion(int value) { idhabitacion.set(value); }
    public final IntegerProperty idhabitacionProperty() { return idhabitacion; }

    public final String getNumero() { return numero.get(); }
    public final void setNumero(String value) { numero.set(value); }
    public final StringProperty numeroProperty() { return numero; }

    public final int getNumeroPiso() { return numeroPiso.get(); }
    public final void setNumeroPiso(int value) { numeroPiso.set(value); }
    public final IntegerProperty numeroPisoProperty() { return numeroPiso; }

    public final int getNumeroHabitacion() { return numeroHabitacion.get(); }
    public final void setNumeroHabitacion(int value) { numeroHabitacion.set(value); }
    public final IntegerProperty numeroHabitacionProperty() { return numeroHabitacion; }

    public final String getTipo() { return tipo.get(); }
    public final void setTipo(String value) { tipo.set(value); }
    public final StringProperty tipoProperty() { return tipo; }

    public final double getPrecio() { return precio.get(); }
    public final void setPrecio(double value) { precio.set(value); }
    public final DoubleProperty precioProperty() { return precio; }

    public final String getEstado() { return estado.get(); }
    public final void setEstado(String value) { estado.set(value); }
    public final StringProperty estadoProperty() { return estado; }
}
