package dao;

import config.Configuracion;
import jakarta.inject.Inject;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaoClientes implements dao.impl.DaoClientesImpl {

    private Configuracion configuracion;

    @Inject
    public DaoClientes(Configuracion configuracion) {
        this.configuracion = Configuracion.getInstance();
    }

    @Override
    public List<String> getClientes() {
        String username  = String.valueOf((Paths.get(configuracion.getUsername())));
        String password = String.valueOf((Paths.get(configuracion.getPassword())));
        return new ArrayList<>(List.of(username,password));
    }
}
