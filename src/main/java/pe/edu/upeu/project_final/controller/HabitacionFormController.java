package pe.edu.upeu.project_final.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pe.edu.upeu.project_final.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.project_final.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final.model.Habitacion;
import pe.edu.upeu.project_final.utils.AlertsU;

public class HabitacionFormController {

    private final IHabitacionDao habitacionDao = new HabitacionDaoImpl();

    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtNumero, txtPiso, txtNumeroHabitacion, txtPrecio;
    @FXML
    private ComboBox<String> cbTipo, cbEstado;

    private Habitacion habitacion;
    private boolean saved = false;

    @FXML
    public void initialize() {
        cbTipo.getItems().addAll("SIMPLE", "DOBLE", "MATRIMONIAL", "SUITE");
        // deben coincidir con el CHECK de la tabla
        cbEstado.getItems().addAll("DISPONIBLE", "OCUPADA", "MANTENIMIENTO", "LIMPIEZA");
        cbEstado.setValue("DISPONIBLE");
    }

    public boolean isSaved() {
        return saved;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion h) {
        this.habitacion = h;
        if (h != null) {
            lblTitulo.setText("Editar habitación");
            txtNumero.setText(h.getNumero());
            txtPiso.setText(String.valueOf(h.getNumeroPiso()));
            txtNumeroHabitacion.setText(String.valueOf(h.getNumeroHabitacion()));
            cbTipo.setValue(h.getTipo());
            txtPrecio.setText(String.valueOf(h.getPrecio()));
            cbEstado.setValue(h.getEstado());
        }
    }

    @FXML
    private void handleCancel() {
        cerrar();
    }

    @FXML
    private void handleSave() {
        String numero = txtNumero.getText().trim();
        if (numero.isEmpty()) {
            error("El número de habitación es obligatorio");
            return;
        }
        if (cbTipo.getValue() == null) {
            error("Seleccione el tipo de habitación");
            return;
        }

        int numeroPiso;
        int numeroHabitacion;
        double precio;
        try {
            numeroPiso = Integer.parseInt(txtPiso.getText().trim());
        } catch (NumberFormatException e) {
            error("El piso debe ser un número entero");
            return;
        }
        try {
            numeroHabitacion = Integer.parseInt(txtNumeroHabitacion.getText().trim());
        } catch (NumberFormatException e) {
            error("El número de habitación debe ser un número entero");
            return;
        }
        try {
            precio = Double.parseDouble(txtPrecio.getText().trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            error("El precio debe ser un número (ej: 120.50)");
            return;
        }
        if (numeroPiso < 1) {
            error("El piso debe ser al menos 1");
            return;
        }
        if (numeroHabitacion < 1) {
            error("El número de habitación debe ser al menos 1");
            return;
        }
        if (precio <= 0) {
            error("El precio debe ser mayor a 0");
            return;
        }

        Habitacion existente = habitacionDao.searchByNumero(numero);
        boolean esOtra = existente != null
                && (habitacion == null || existente.getIdhabitacion() != habitacion.getIdhabitacion());
        if (esOtra) {
            error("Ya existe una habitación con el número " + numero);
            return;
        }

        if (habitacion == null) {
            habitacion = new Habitacion();
        }
        habitacion.setNumero(numero);
        habitacion.setNumeroPiso(numeroPiso);
        habitacion.setNumeroHabitacion(numeroHabitacion);
        habitacion.setTipo(cbTipo.getValue());
        habitacion.setPrecio(precio);
        habitacion.setEstado(cbEstado.getValue());
        saved = true;
        cerrar();
    }

    private void error(String mensaje) {
        AlertsU.viewAlerts("Validación", mensaje, "ERROR").showAndWait();
    }

    private void cerrar() {
        Stage stage = (Stage) txtNumero.getScene().getWindow();
        stage.close();
    }
}


