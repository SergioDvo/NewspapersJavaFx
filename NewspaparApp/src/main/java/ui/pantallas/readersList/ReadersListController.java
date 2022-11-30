package ui.pantallas.readersList;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Article;
import modelo.Reader;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadersListController extends BasePantallaController {

    private ReadersListViewModel readersListViewModel;
    @FXML
    private TableView<Reader> tablaReader;
    @FXML
    private TableColumn<Reader, Integer> id;
    @FXML
    private TableColumn<Reader, String> nameReader;
    @FXML
    private TableColumn<Reader, LocalDate> dateReader;
    @FXML
    private MFXComboBox<Integer> comboBoxType;


    @Inject
    public ReadersListController(ReadersListViewModel readersListViewModel) {
        this.readersListViewModel = readersListViewModel;
    }

    public void initialize() {
        tablaReader.setItems(readersListViewModel.getReaderList());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameReader.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateReader.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        List<Integer> types = new ArrayList<>();
        for (Article article : readersListViewModel.getArticleList()) {
            if (!types.contains(article.getId_type())) {
                types.add(article.getId_type());
            }
        }
        comboBoxType.getItems().addAll(types);
        readersListViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.getError() != null) {
                this.getPrincipalController().sacarAlertError(newState.getError());
            }
            if (newState.getReaderList() != null) {
                tablaReader.setItems(readersListViewModel.getReaderList());
                this.getPrincipalController().sacarAlertOkay("Se realizo con exitooo");
            }
        });
    }
    public void filterByType(){
        int type = comboBoxType.getValue();
        readersListViewModel.getArticleListByType(type);
    }
}
