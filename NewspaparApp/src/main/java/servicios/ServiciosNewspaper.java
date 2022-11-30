package servicios;

import dao.DaoArticle;
import dao.DaoClientes;
import dao.DaoNewspaper;
import jakarta.inject.Inject;
import modelo.Newspaper;

import java.util.List;

public class ServiciosNewspaper implements servicios.impl.ServiciosNewspaperImpl {

    private final DaoNewspaper daoNewspaper;

    private DaoArticle daoArticle;

    @Inject
    public ServiciosNewspaper(DaoNewspaper daoNewspaper,DaoArticle daoArticle) {
        this.daoArticle = daoArticle;
        this.daoNewspaper = daoNewspaper;
    }

    @Override
    public List<Newspaper> getNewspaperList(){
        return daoNewspaper.getNewspaper();
    }

    @Override
    public boolean addNewspaper(Newspaper newspaper){
        if (daoNewspaper.getNewspaper().contains(newspaper)) {
            return false;
        }else {
            daoNewspaper.addNewspaper(newspaper);
            return true;
        }
    }

    @Override
    public boolean deleteNewspaper(Newspaper newspaper){
        if (daoNewspaper.getNewspaper().contains(newspaper)) {
            daoNewspaper.deleteNewspaper(newspaper);
            daoArticle.deleteArticle(newspaper.getId());
            return true;
        }else {
            return false;
        }
    }


}
