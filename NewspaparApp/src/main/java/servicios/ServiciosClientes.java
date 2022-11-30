package servicios;

import dao.DaoClientes;
import jakarta.inject.Inject;

public class ServiciosClientes implements servicios.impl.ServiciosClientesImpl {

    private final DaoClientes daoClientes;

    @Inject
    public ServiciosClientes(DaoClientes daoClientes) {
        this.daoClientes = daoClientes;
    }

    @Override
    public boolean doLogin(String username, String password) {
        return daoClientes.getClientes().contains(username) && daoClientes.getClientes().contains(password);
    }

}
