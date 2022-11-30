package dao.impl;

import modelo.Newspaper;

import java.util.List;

public interface DaoNewspaperImpl {
    List<Newspaper> getNewspaper();

    int addNewspaper(Newspaper newspaper);

    int deleteNewspaper(Newspaper newspaper);
}
