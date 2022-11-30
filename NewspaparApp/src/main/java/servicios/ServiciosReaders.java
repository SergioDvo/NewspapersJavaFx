package servicios;

import dao.DaoReaders;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBException;
import modelo.Article;
import modelo.Newspaper;
import modelo.ReadArticles;
import modelo.Reader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServiciosReaders implements servicios.impl.ServiciosReadersImpl {

    private final DaoReaders daoReaders;
    private final ServiciosArticle serviciosArticle;

    @Inject
    public ServiciosReaders(DaoReaders daoReaders, ServiciosArticle serviciosArticle) {
        this.daoReaders = daoReaders;
        this.serviciosArticle = serviciosArticle;
    }

    @Override
    public Either<String, List<Reader>> getReadersList() {
        return daoReaders.getReaders();
    }

    @Override
    public List<Reader> getReadersListByArticleType(int type) {
        List<Article> articles = serviciosArticle.getArticleListByType(type);

        return daoReaders.getReaders().get().stream()
                .filter(reader -> reader.getReadArticles().stream()
                        .anyMatch(readArticle -> articles.stream()
                                .anyMatch(article -> article.getId() == readArticle.getIdArticle()))).collect(Collectors.toList());
    }
    @Override
    public boolean addReaderArticle(Reader reader, Article article, int rating, int id){
        Optional<ReadArticles> articleFound = reader.getReadArticles().stream().filter(readArticle -> readArticle.getIdArticle() == article.getId()).findFirst();
        if (articleFound.isPresent()){
            return false;
        }else {
            if (reader.getReadArticles().stream().anyMatch(readArticle -> readArticle.getId() == id)){
                return false;
            }else {
                List<Reader> readerList = daoReaders.getReaders().get();
                readerList.stream()
                        .filter(reader1 -> reader1.getId() == reader.getId())
                        .findFirst().get()
                        .getReadArticles().add(new ReadArticles(id, reader.getId(), article.getId(), rating));
                daoReaders.saveReaderList(readerList);
                return true;
            }
        }
    }
    @Override
    public void deleteReader(Reader reader){
        List<Reader> readerList = daoReaders.getReaders().get();
        readerList.remove(reader);
        daoReaders.saveReaderList(readerList);
    }
    @Override
    public List<Reader> getReadersListByNewspaper(Newspaper newspaper) {
        return daoReaders.getReaders().get().stream()
                .filter(reader -> reader.getSubscriptions().stream()
                        .anyMatch(subscription -> subscription.getIdNewspaper() == newspaper.getId())).collect(Collectors.toList());

    }

}
