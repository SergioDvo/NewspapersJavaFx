package dao;

import config.Configuracion;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import modelo.Article;
import modelo.Type;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class DaoType implements dao.impl.DaoTypeImpl {

    private Configuracion configuracion;

    @Inject
    public DaoType() {
        this.configuracion = Configuracion.getInstance();
    }

    @Override
    public List<Type> getTypeList() {
        List<Type> types = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(configuracion.getTypeList()));
            for (String line : lines) {
                Type type = new Type(line);
                types.add(type);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return types;
    }

    @Override
    public int addNewspaper(Article article) {
        try  {
            Files.write(Paths.get(configuracion.getArticleList()),article.convertToLine().getBytes(), StandardOpenOption.APPEND);
            return 1;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }
}
