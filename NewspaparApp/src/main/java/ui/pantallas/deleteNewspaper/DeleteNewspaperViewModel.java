package ui.pantallas.deleteNewspaper;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Newspaper;
import servicios.ServiciosNewspaper;
import ui.pantallas.newspaper.NewspaperListState;

public class DeleteNewspaperViewModel {

    private final ServiciosNewspaper serviciosNewspaper;
    private final ObservableList<Newspaper> newspapers;
    private final ObjectProperty<DeleteNewspaperState> state;

    @Inject
    public DeleteNewspaperViewModel(ServiciosNewspaper serviciosNewspaper) {
        this.serviciosNewspaper=serviciosNewspaper;
        newspapers= FXCollections.observableArrayList();
        state = new SimpleObjectProperty<>(new DeleteNewspaperState(false, null));
    }
    public ReadOnlyObjectProperty<DeleteNewspaperState> getState() {
        return state;
    }

    public ObservableList<Newspaper> getNewspaperList(){
        newspapers.addAll(serviciosNewspaper.getNewspaperList());
        return newspapers;
    }

    public void deleteNewspaper(Newspaper newspaper){
        if (serviciosNewspaper.deleteNewspaper(newspaper)) {
            state.set(new DeleteNewspaperState(true, null));
            newspapers.clear();
            newspapers.addAll(serviciosNewspaper.getNewspaperList());
        }else {
            state.set(new DeleteNewspaperState(false, "This newspaper is not in the database"));
        }

    }
}
