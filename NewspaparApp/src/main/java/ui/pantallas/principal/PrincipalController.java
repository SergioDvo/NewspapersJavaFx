package ui.pantallas.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import servicios.ServiciosClientes;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Log4j2
public class PrincipalController {

    // objeto especial para DI
    Instance<Object> instance;

    @FXML
    private MenuBar menuPrincipal;
    private Stage primaryStage;

    @FXML
    private BorderPane root;


    private Alert alert;

    private Pane pantallaBienvenida;

    @FXML
    private Menu menuAdministrador;

    @FXML
    private Menu menuCliente;

    private ServiciosClientes serviciosClientes;


    @Inject
    public PrincipalController(Instance<Object> instance,ServiciosClientes serviciosClientes) {
        this.instance = instance;
        this.serviciosClientes = serviciosClientes;
        alert = new Alert(Alert.AlertType.NONE);
    }



    private void cargarPantalla(Pantallas pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }
    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void sacarAlertOkay(String mensaje) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }


    public void logout() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }


    public void initialize() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    private void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Quit application");
        alert.setContentText("Close without saving?");
        alert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();


        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.CANCEL) {
                event.consume();
            }
        });
    }

    public boolean askForDelete(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Delete newspaper");
        alert.setContentText("Are you sure you want to delete this newspaper?,it will delete also de articles inside");
        alert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();
        AtomicBoolean delete = new AtomicBoolean(false);
        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.CANCEL) {
                delete.set(false);
            }
            if (buttonType == ButtonType.YES) {
                delete.set(true);
            }
        });
        return delete.get();
    }

    public void exit(ActionEvent actionEvent) {
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

//    @FXML
//    private void cambiarcssModoOscuro(ActionEvent actionEvent) {
//
//        primaryStage.getScene().getRoot().getStylesheets().clear();
//        primaryStage.getScene().getRoot().getStylesheets().add(getClass().getResource("/css/darkmode.css").toExternalForm());
//
//    }

//    @FXML
//    private void cambiarcssModoBonito(ActionEvent actionEvent) {
//        primaryStage.getScene().getRoot().getStylesheets().clear();
//        primaryStage.getScene().getRoot().getStylesheets().add(getClass().getResource("/css/niceModo.css").toExternalForm());
//    }


    public double getHeight() {
        return root.getScene().getWindow().getHeight();
    }

    public double getWidth() {
        return root.getScene().getWindow().getWidth();
    }


    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "menuItemLogout":
                logout();
                break;
            case "listArticle":
                cargarPantalla(Pantallas.PANTALLAARTICLE);
                break;
            case "addArticle":
                cargarPantalla(Pantallas.PANTALLAADDARTICLE);
                break;
            case "listNewspaper":
                cargarPantalla(Pantallas.PANTALLANEWSPAPER);
                break;
            case "addNewspaper":
                cargarPantalla(Pantallas.PANTALLAADDNEWSPAPER);
                break;
            case "deleteNewspaper":
                cargarPantalla(Pantallas.PANTALLADELETENEWSPAPER);
                break;
            case "listReader":
                cargarPantalla(Pantallas.PANTALLAREADER);
                break;
            case "addReader":
                cargarPantalla(Pantallas.PANTALLAADDREADER);
                break;
            case "deleteReader":
                cargarPantalla(Pantallas.PANTALLADELETEREADER);
                break;
            case "listReaderOfNewspaper":
                cargarPantalla(Pantallas.PANTALLAREADEROFNEWSPAPER);
                break;
        }
    }


    public void onLoginHecho(String username,String password) {

        cargarPantalla(Pantallas.PANTALLANEWSPAPER);
        menuPrincipal.setVisible(true);
    }

}
