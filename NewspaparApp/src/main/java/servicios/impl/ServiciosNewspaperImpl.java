package servicios.impl;

import modelo.Newspaper;

import java.util.List;

public interface ServiciosNewspaperImpl {
    List<Newspaper> getNewspaperList();

    boolean addNewspaper(Newspaper newspaper);

    boolean deleteNewspaper(Newspaper newspaper);
}
