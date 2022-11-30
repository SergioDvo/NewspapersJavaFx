package ui.pantallas.readersList;

import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Article;
import modelo.Reader;
import servicios.ServiciosArticle;
import servicios.ServiciosReaders;

import java.util.List;

public class ReadersListViewModel {

    private final ServiciosReaders serviciosReaders;

    private final ServiciosArticle serviciosArticle;
    private final ObservableList<Reader> readers;
    private final ObjectProperty<ReadersListState> state;

    @Inject
    public ReadersListViewModel(ServiciosReaders serviciosReaders,ServiciosArticle serviciosArticle) {
        this.serviciosReaders=serviciosReaders;
        this.serviciosArticle=serviciosArticle;
        readers= FXCollections.observableArrayList();
        state = new SimpleObjectProperty<>(new ReadersListState(null, null));
    }
    public ReadOnlyObjectProperty<ReadersListState> getState() {
        return state;
    }

    public ObservableList<Reader> getReaderList() {
        readers.addAll(serviciosReaders.getReadersList().get());
        return readers;
    }
    public List<Article> getArticleList(){
        return serviciosArticle.getArticleList();
    }
    public void getArticleListByType(int type) {
        readers.clear();
        readers.addAll(serviciosReaders.getReadersListByArticleType(type));
    }
}
