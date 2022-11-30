package ui.pantallas.articles;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Article;
import modelo.Type;
import servicios.ServiciosArticle;
import servicios.ServiciosType;

import java.util.List;

public class ArticlesViewModel {
    private final ServiciosArticle serviciosArticle;
    private final ObservableList<Article> articles;
    private final ObjectProperty<ArticlesState> state;
    private final ServiciosType serviciosType;

    @Inject
    public ArticlesViewModel(ServiciosArticle serviciosArticle,ServiciosType serviciosType) {
        this.serviciosArticle =serviciosArticle;
        this.serviciosType=serviciosType;
        articles = FXCollections.observableArrayList();
        state = new SimpleObjectProperty<>(new ArticlesState(false, null));
    }
    public ReadOnlyObjectProperty<ArticlesState> getState() {
        return state;
    }

    public ObservableList<Article> getArticleList(){
        articles.addAll(serviciosArticle.getArticleList());
        return articles;
    }
    public ObservableList<Article> articleList(){
        return articles;
    }
    public void getArticleListByType(int type){
        articles.clear();
        articles.addAll(serviciosArticle.getArticleListByType(type));
    }
    public List<Type> getTypeList(){
        return serviciosType.getTypeList();
    }
}
