package pe.edu.upeu.project_final.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pe.edu.upeu.project_final.HelloApplication;
import pe.edu.upeu.project_final.implementacion.ClienteDaoImpl;
import pe.edu.upeu.project_final.interfaces.IClienteDao;
import pe.edu.upeu.project_final.model.Cliente;
import pe.edu.upeu.project_final.utils.AlertsU;

import java.awt.*;
import java.util.Optional;

public class ClienteController {

    private static AlertsU alertsU = new AlertsU();

    //definimos la tabla
    @FXML
    private TableView<Cliente> tableCliente;
    //variable correlativa
    @FXML
    private TableColumn<Cliente,Number> ColNumero;
    @FXML
    private TableColumn<Cliente,Integer> ColId;
    @FXML
    private TableColumn<Cliente,String> ColNombres;
    @FXML
    private TableColumn<Cliente,String> ColApellidos;
    @FXML
    private TableColumn<Cliente,String> ColDni;
    @FXML
    private TableColumn<Cliente,String> ColEmail;
    @FXML
    private TableColumn<Cliente,String> ColGenero;

    private final IClienteDao clienteDao = new ClienteDaoImpl();
    private ObservableList<Cliente> listaClientes;


    @FXML
    public void initialize(){
        ColNumero.setCellFactory(colum -> new TableCell<>(){
            @Override
            protected void updateItem(Number item, boolean empty){
                super.updateItem(item,empty);
                if (empty){
                    setText(null);
                }else{
                    setText(String.valueOf(getIndex() +1));
                }
            }
        });

        ColId.setCellValueFactory(
                cellData -> cellData.getValue().idclienteProperty().asObject());
        ColNombres.setCellValueFactory(
                cellData -> cellData.getValue().nombresProperty());
        ColApellidos.setCellValueFactory(
                cellData -> cellData.getValue().apellidosProperty());
        ColDni.setCellValueFactory(
                cellData -> cellData.getValue().dniProperty());
        ColEmail.setCellValueFactory(
                cellData -> cellData.getValue().emailProperty());
        ColGenero.setCellValueFactory(
                cellData -> cellData.getValue().generoProperty());
        //llamar al metodo
        cargarDatos();
    }
    private void cargarDatos(){
        listaClientes = FXCollections.observableArrayList(clienteDao.listAll());
        tableCliente.setItems(listaClientes);
    }
    @FXML
    private void handleDelete(){
        Cliente selectId = tableCliente.getSelectionModel().getSelectedItem();
        if (selectId !=null){
            Alert confirm = AlertsU.viewAlerts("Confirmar eliminacion",
                    "Estas seguro de eliminar el cliente:"+selectId.getNombres()+"?",
                    "CONFIRMATION");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                if (clienteDao.delete(selectId)){
                    listaClientes.remove(selectId);

                }
            }else{
                Alert warning =  AlertsU.viewAlerts("Seleccion requerida",
                        "Seleccione un cliente para eliminar","WARNING");
                warning.showAndWait();
            }
        }
    }

    @FXML
    private void handleNew(){
        // System.out.println("abriendo el formulario");
        showForm(null);

    }
    @FXML
    private void handleEdit(){
        Cliente selectClient = tableCliente.getSelectionModel().getSelectedItem();
        if (selectClient !=null){
            Cliente clientFull = clienteDao.searchById(selectClient.getIdcliente());
            showForm(clientFull);
        }else{
            Alert alert = AlertsU.viewAlerts("Seleccion requerida","","CONFIRMATION");
            alert.show();
        }
    }
    private void showForm(Cliente c){
        try{
            FXMLLoader loader = new FXMLLoader(HelloApplication.class
                    .getResource("/pe/edu/upeu/project_final/views/cliente-form.fxml"));
            Parent root = loader.load();
            ClienteFormController controller = loader.getController();
            controller.setCliente(c); //pasamos el cliente null si es nuevo
            Stage stage = new Stage();
            stage.setTitle(c==null ? "Nuevo Cliente": "Editar Cliente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene( new Scene(root));
            stage.showAndWait();
            if (controller.isSaved()){
                Cliente clienteProcesado = controller.getCliente();
                if (c == null){
                    boolean result =  clienteDao.insert(clienteProcesado);
                    if (result){
                        Alert alert = AlertsU.viewAlerts("Success","Registro satisfactorio!!!","CONFIRMATION");
                        alert.show();
                    }else{
                        Alert alert = AlertsU.viewAlerts("Error","Error al insertar!!!","ERROR");
                        alert.show();
                    }
                }else{
                    boolean result =  clienteDao.update(clienteProcesado);
                    if (result){
                        Alert alert = AlertsU.viewAlerts("Update",
                                "Registro actualizado!!!","WARNING");
                        alert.showAndWait();
                    }else{
                        Alert alert = AlertsU.viewAlerts("Error",
                                "Error al actualizar!!!","ERROR");
                        alert.show();
                    }
                }
                cargarDatos();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}





