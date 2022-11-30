package ui.pantallas.addArticles;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Article;
import servicios.ServiciosArticle;

public class AddArticlesViewModel {

    private final ServiciosArticle serviciosArticle;
    private final ObservableList<Article> articles;
    private final ObjectProperty<AddArticlesState> state;

    @Inject
    public AddArticlesViewModel(ServiciosArticle serviciosArticle) {
        this.serviciosArticle =serviciosArticle;
        articles = FXCollections.observableArrayList();
        state = new SimpleObjectProperty<>(new AddArticlesState(false, null));
    }
    public ReadOnlyObjectProperty<AddArticlesState> getState() {
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
    public void addArticle(Article article){
        if (serviciosArticle.addArticle(article)) {
            articles.clear();
            articles.addAll(serviciosArticle.getArticleList());
            state.setValue(new AddArticlesState(true, null));
        }else {
            state.setValue(new AddArticlesState(false, "This newspaper is already in the database"));
        }
    }
    public boolean isArticleIdInList(int id){
        return serviciosArticle.isArticleIdInList(id);
    }
}
