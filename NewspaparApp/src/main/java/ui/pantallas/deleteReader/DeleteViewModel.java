package ui.pantallas.deleteReader;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Article;
import modelo.Reader;
import modelo.Type;
import servicios.ServiciosArticle;
import servicios.ServiciosReaders;
import servicios.ServiciosType;
import ui.pantallas.articles.ArticlesState;
import ui.pantallas.readersList.ReadersListState;

import java.util.List;

public class DeleteViewModel {
    private final ServiciosReaders serviciosReaders;

    private final ServiciosArticle serviciosArticle;
    private final ObservableList<Reader> readers;
    private final ObjectProperty<DeleteReaderState> state;

    @Inject
    public DeleteViewModel(ServiciosReaders serviciosReaders,ServiciosArticle serviciosArticle) {
        this.serviciosReaders=serviciosReaders;
        this.serviciosArticle=serviciosArticle;
        readers= FXCollections.observableArrayList();
        state = new SimpleObjectProperty<>(new DeleteReaderState(false, null));
    }
    public ReadOnlyObjectProperty<DeleteReaderState> getState() {
        return state;
    }

    public ObservableList<Reader> getReaderList() {
        readers.addAll(serviciosReaders.getReadersList().get());
        return readers;
    }
    public List<Article> getArticleList(){
        return serviciosArticle.getArticleList();
    }

    public void deleteReader(Reader reader){
        serviciosReaders.deleteReader(reader);
        if (!serviciosReaders.getReadersList().get().isEmpty()){
            readers.clear();
            readers.addAll(serviciosReaders.getReadersList().get());
            state.setValue(new DeleteReaderState(true, null));
        }else{
            state.setValue(new DeleteReaderState(false, "Error reading xml"));
        }
    }
}
