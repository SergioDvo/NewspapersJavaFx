package dao.impl;

import modelo.Article;
import modelo.Type;

import java.util.List;

public interface DaoTypeImpl {
    List<Type> getTypeList();

    int addNewspaper(Article article);
}
