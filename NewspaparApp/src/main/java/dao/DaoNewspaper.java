package dao;

import config.Configuracion;
import io.vavr.control.Either;
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
public class DaoNewspaper implements dao.impl.DaoNewspaperImpl {

    private Configuracion configuracion;

    @Inject
    public DaoNewspaper(Configuracion configuracion) {
        this.configuracion = Configuracion.getInstance();
    }

    @Override
    public List<Newspaper> getNewspaper() {
        List<Newspaper> newspaper = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(configuracion.getNewspaperList()));
            for (String line : lines) {
                Newspaper newspaper1 = new Newspaper(line);
                newspaper.add(newspaper1);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return newspaper;
    }
    @Override
    public int addNewspaper(Newspaper newspaper){
        try  {
            Files.write(Paths.get(configuracion.getNewspaperList()),newspaper.convertToLine().getBytes(), StandardOpenOption.APPEND);
            return 0;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return 1;
        }
    }
    @Override
    public int deleteNewspaper(Newspaper newspaper){
        try {
            List<String> lines = Files.readAllLines(Paths.get(configuracion.getNewspaperList()));
            String newspaperRight = newspaper.convertToLine().split("\n")[0];
            lines.remove(newspaperRight);
            Files.write(Paths.get(configuracion.getNewspaperList()),lines);
            return 0;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return 1;
        }

    }


}
