package servicios;

import dao.DaoArticle;
import dao.DaoNewspaper;
import jakarta.inject.Inject;
import modelo.Article;

import java.util.ArrayList;
import java.util.List;

public class ServiciosArticle implements servicios.impl.ServiciosArticleImpl {

    private final DaoArticle daoArticle;

    private ServiciosType serviciosType;

    private DaoNewspaper daoNewspaper;

    @Inject
    public ServiciosArticle(DaoArticle daoArticle, ServiciosType serviciosType, DaoNewspaper daoNewspaper) {
        this.daoArticle = daoArticle;
        this.serviciosType = serviciosType;
        this.daoNewspaper = daoNewspaper;
    }

    @Override
    public List<Article> getArticleList(){
        return daoArticle.getArticle();
    }

    @Override
    public boolean addArticle(Article article){
        List<Integer> allIdsType = serviciosType.getAllIdsType();
        List<Integer> allIdsNewspaper = getAllIdNewspaper();
        if (allIdsType.contains(article.getId_type()) && allIdsNewspaper.contains(article.getId_newspaper())) {
            if (!daoArticle.getArticle().contains(article)) {
                daoArticle.addArticle(article);
                return true;
            }else {
                return false;
            }
        } else {
            return false;
        }
    }
    @Override
    public List<Integer> getAllIdNewspaper(){
        List<Integer> allIdsNewspaper = new ArrayList<>();
        for (int i = 0; i < daoNewspaper.getNewspaper().size(); i++) {
            allIdsNewspaper.add(daoNewspaper.getNewspaper().get(i).getId());
        }
        return allIdsNewspaper;
    }
    @Override
    public List<Article> getArticleListByType(int type){
        List<Article> articleList = getArticleList();
        articleList.removeIf(article -> article.getId_type() != type);
        return articleList;
    }
    @Override
    public boolean isArticleIdInList(int id){
        return getArticleList().stream().anyMatch(article -> article.getId() == id);
    }

}
