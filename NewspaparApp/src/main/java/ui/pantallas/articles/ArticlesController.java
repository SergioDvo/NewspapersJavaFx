package ui.pantallas.articles;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Article;
import ui.pantallas.common.BasePantallaController;

import java.util.ArrayList;
import java.util.List;

public class ArticlesController extends BasePantallaController {

    private final ArticlesViewModel articlesViewModel;
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
    private MFXComboBox<Integer> comboBoxType;



    @Inject
    public ArticlesController(ArticlesViewModel articlesViewModel) {
        this.articlesViewModel = articlesViewModel;
    }

    public void initialize() {
        tableArticle.setItems(articlesViewModel.getArticleList());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameArticle.setCellValueFactory(new PropertyValueFactory<>("name_article"));
        descriptionArticle.setCellValueFactory(new PropertyValueFactory<>("description"));
        idNewspaper.setCellValueFactory(new PropertyValueFactory<>("id_newspaper"));
        List<Integer> types = new ArrayList<>();
        for (Article article : articlesViewModel.articleList()) {
            if (!types.contains(article.getId_type())) {
                types.add(article.getId_type());
            }
        }
        comboBoxType.getItems().addAll(types);
        articlesViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.getError() != null) {
                this.getPrincipalController().sacarAlertError(newState.getError());
            }
            if (newState.isSeleccionOK()) {
                this.getPrincipalController().sacarAlertOkay("Se realizo con exitooo");
            }
        });

    }
    public void filterByType(){
        int type = comboBoxType.getValue();
        articlesViewModel.getArticleListByType(type);
    }
}
