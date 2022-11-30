package ui.pantallas.readersList;

import lombok.Data;
import modelo.Reader;

import java.util.List;
@Data
public class ReadersListState {

    private final List<Reader> readerList;
    private final String error;
}
