package ui.pantallas.addReaderArticles;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Article;
import modelo.Reader;
import servicios.ServiciosArticle;
import servicios.ServiciosReaders;
import ui.pantallas.readersList.ReadersListState;

import java.util.List;

public class AddReaderArticleViewModel {

    private final ServiciosReaders serviciosReaders;

    private final ServiciosArticle serviciosArticle;
    private final ObservableList<Reader> readers;

    private final ObservableList<Article> articles;
    private final ObjectProperty<AddReaderArticleState> state;

    @Inject
    public AddReaderArticleViewModel(ServiciosReaders serviciosReaders, ServiciosArticle serviciosArticle) {
        this.serviciosReaders = serviciosReaders;
        this.serviciosArticle = serviciosArticle;
        readers = FXCollections.observableArrayList();
        articles = FXCollections.observableArrayList();
        state = new SimpleObjectProperty<>(new AddReaderArticleState(null, null));
    }

    public ReadOnlyObjectProperty<AddReaderArticleState> getState() {
        return state;
    }
    public ObservableList<Article> getArticleList(){
        articles.addAll(serviciosArticle.getArticleList());
        return articles;
    }

    public ObservableList<Reader> getReaderList() {
        readers.addAll(serviciosReaders.getReadersList().get());
        return readers;
    }
    public void addReaderArticle(Article article,Reader reader,int rating,int id){
        if (serviciosReaders.addReaderArticle(reader,article,rating,id)){
            state.setValue(new AddReaderArticleState(null,"Articule added properly to "+reader.getName()));
        }else
            state.setValue(new AddReaderArticleState(("Error adding article to " +reader.getName()),null));

    }
}

