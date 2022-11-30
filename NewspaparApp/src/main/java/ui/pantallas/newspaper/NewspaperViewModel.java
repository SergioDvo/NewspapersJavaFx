package ui.pantallas.newspaper;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.*;
import servicios.*;

public class NewspaperViewModel {

    private final ServiciosNewspaper serviciosNewspaper;
    private final ObservableList<Newspaper> newspapers;
    private final ObjectProperty<NewspaperListState> state;

    @Inject
    public NewspaperViewModel(ServiciosNewspaper serviciosNewspaper) {
        this.serviciosNewspaper=serviciosNewspaper;
        newspapers= FXCollections.observableArrayList();
        state = new SimpleObjectProperty<>(new NewspaperListState(false, null));
    }
    public ReadOnlyObjectProperty<NewspaperListState> getState() {
        return state;
    }

    public ObservableList<Newspaper> getNewspaperList(){
        newspapers.addAll(serviciosNewspaper.getNewspaperList());
        return newspapers;
    }

}
