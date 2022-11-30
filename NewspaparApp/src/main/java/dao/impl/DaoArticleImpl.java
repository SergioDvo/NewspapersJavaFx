package dao.impl;

import modelo.Article;

import java.util.List;

public interface DaoArticleImpl {
    List<Article> getArticle();

    int addArticle(Article article);

    int deleteArticle(int idNewspaper);
}
