package servicios.impl;

import io.vavr.control.Either;
import modelo.Article;
import modelo.Newspaper;
import modelo.Reader;

import java.util.List;

public interface ServiciosReadersImpl {
    Either<String, List<Reader>> getReadersList();

    List<Reader> getReadersListByArticleType(int type);

    boolean addReaderArticle(Reader reader, Article article, int rating, int id);

    void deleteReader(Reader reader);

    List<Reader> getReadersListByNewspaper(Newspaper newspaper);
}
