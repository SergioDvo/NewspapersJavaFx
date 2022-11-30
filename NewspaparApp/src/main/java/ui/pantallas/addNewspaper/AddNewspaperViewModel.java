package ui.pantallas.addNewspaper;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Newspaper;
import servicios.ServiciosNewspaper;
import ui.pantallas.newspaper.NewspaperListState;

public class AddNewspaperViewModel {

    private final ServiciosNewspaper serviciosNewspaper;
    private final ObservableList<Newspaper> newspapers;
    private final ObjectProperty<NewspaperListState> state;

    @Inject
    public AddNewspaperViewModel(ServiciosNewspaper serviciosNewspaper) {
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
    public boolean addNewspaper(Newspaper newspaper){
        if (newspaper ==null){
            state.setValue(new NewspaperListState(false, "Complete the textfields for add newspaper"));
            return false;
        }
        if (serviciosNewspaper.addNewspaper(newspaper)) {
            newspapers.clear();
            newspapers.addAll(serviciosNewspaper.getNewspaperList());
            state.setValue(new NewspaperListState(true, null));
            return true;
        }else {
            state.setValue(new NewspaperListState(false, "This newspaper is already in the database"));
            return false;
        }
    }
}
