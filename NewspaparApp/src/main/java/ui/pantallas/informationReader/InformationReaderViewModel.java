package ui.pantallas.informationReader;

import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Article;
import modelo.Newspaper;
import modelo.Reader;
import servicios.ServiciosArticle;
import servicios.ServiciosNewspaper;
import servicios.ServiciosReaders;
import ui.pantallas.deleteReader.DeleteReaderState;

import java.util.List;

public class InformationReaderViewModel {
    private final ServiciosReaders serviciosReaders;

    private final ServiciosArticle serviciosArticle;

    private final ServiciosNewspaper serviciosNewspaper;
    private final ObservableList<Reader> readers;

    private final ObservableList<Newspaper> newspapers;
    private final ObjectProperty<InformationReaderState> state;

    @Inject
    public InformationReaderViewModel(ServiciosReaders serviciosReaders,ServiciosArticle serviciosArticle,ServiciosNewspaper serviciosNewspaper) {
        this.serviciosReaders=serviciosReaders;
        this.serviciosArticle=serviciosArticle;
        this.serviciosNewspaper=serviciosNewspaper;
        readers= FXCollections.observableArrayList();
        newspapers= FXCollections.observableArrayList();
        state = new SimpleObjectProperty<>(new InformationReaderState(false, null));
    }
    public ReadOnlyObjectProperty<InformationReaderState> getState() {
        return state;
    }

    public List<Article> getArticleList(){
        return serviciosArticle.getArticleList();
    }
    public ObservableList<Newspaper> getNewspaperList(){
        newspapers.addAll(serviciosNewspaper.getNewspaperList());
        return newspapers;
    }
    public ObservableList<Reader> getReadersListByNewspaper(Newspaper newspaper) {
        readers.clear();
        readers.addAll(serviciosReaders.getReadersListByNewspaper(newspaper));
        return readers;

    }

}
