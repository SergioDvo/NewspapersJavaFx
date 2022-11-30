package ui.pantallas.addArticles;

import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Article;
import ui.pantallas.common.BasePantallaController;

import java.util.ArrayList;
import java.util.List;

public class AddArticlesController extends BasePantallaController {
    private final AddArticlesViewModel addArticlesViewModel;
    @FXML
    private TableView<Article> tableArticle;
    @FXML
    private TableColumn<Article, Integer> id;
    @FXML
    private TableColumn<Article, String> nameArticle;
    @FXML
    private TableColumn<Article, String> descriptionArticle;
    @FXML
    private TableColumn<Article, Integer> idNewspaper;
    @FXML
    private TableColumn<Article, Integer> idType;
    @FXML
    private MFXTextField txtID;
    @FXML
    private MFXTextField txtArticleName;
    @FXML
    private MFXTextField txtDescription;
    @FXML
    private MFXTextField txtIdNw;
    @FXML
    private MFXTextField txtIdType;



    @Inject
    public AddArticlesController(AddArticlesViewModel addArticlesViewModel) {
        this.addArticlesViewModel = addArticlesViewModel;
    }

    public void initialize() {
        tableArticle.setItems(addArticlesViewModel.getArticleList());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameArticle.setCellValueFactory(new PropertyValueFactory<>("name_article"));
        descriptionArticle.setCellValueFactory(new PropertyValueFactory<>("description"));
        idNewspaper.setCellValueFactory(new PropertyValueFactory<>("id_newspaper"));
        idType.setCellValueFactory(new PropertyValueFactory<>("id_type"));
        List<Integer> types = new ArrayList<>();
        for (Article article : addArticlesViewModel.articleList()) {
            if (!types.contains(article.getId_type())) {
                types.add(article.getId_type());
            }
        }
        addArticlesViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.getError() != null) {
                this.getPrincipalController().sacarAlertError(newState.getError());
            }
            if (newState.isSeleccionOK()) {
                this.getPrincipalController().sacarAlertOkay("Done it");
            }
        });

    }
    public void addArticle(){
        if (txtID.getText().isEmpty() || txtArticleName.getText().isEmpty() || txtDescription.getText().isEmpty() || txtIdNw.getText().isEmpty() || txtIdType.getText().isEmpty()) {
            this.getPrincipalController().sacarAlertError("You have to select all camps");
        } else {
            if (!addArticlesViewModel.isArticleIdInList(Integer.parseInt(txtID.getText()))) {
                addArticlesViewModel.addArticle(new Article(Integer.parseInt(txtID.getText()), txtArticleName.getText(), txtDescription.getText(), Integer.parseInt(txtIdNw.getText()), Integer.parseInt(txtIdType.getText())));
            } else {
                this.getPrincipalController().sacarAlertError("The newspaper id is in the database");
            }
        }
    }
}
