package dao;

import config.Configuracion;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import modelo.Article;
import modelo.Newspaper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class DaoArticle implements dao.impl.DaoArticleImpl {

    private Configuracion configuracion;

    @Inject
    public DaoArticle(Configuracion configuracion) {
        this.configuracion = Configuracion.getInstance();
    }

    @Override
    public List<Article> getArticle() {
        List<Article> articles = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(configuracion.getArticleList()));
            for (String line : lines) {
                Article article = new Article(line);
                articles.add(article);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return articles;
    }
    @Override
    public int addArticle(Article article){
        try  {
            Files.write(Paths.get(configuracion.getArticleList()),article
                    .convertToLine().getBytes(), StandardOpenOption.APPEND);
            return 0;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return 1;
        }
    }
    @Override
    public int deleteArticle(int idNewspaper){
        List<Article> articlesList = getArticle();
        for (Article article : articlesList) {
            if (article.getId_newspaper() == idNewspaper) {
                try {
                    List<String> lines = Files.readAllLines(Paths.get(configuracion.getArticleList()));
                    String newspaperRight = article.convertToLine().split("\n")[0];
                    lines.remove(newspaperRight);
                    Files.write(Paths.get(configuracion.getArticleList()),lines);
                    return 1;
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                    return 0;
                }
            }
        }
        return 0;
    }
}
