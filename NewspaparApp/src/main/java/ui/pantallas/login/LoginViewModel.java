package ui.pantallas.login;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import servicios.ServiciosClientes;

public class LoginViewModel {


    private final ServiciosClientes serviciosClientes;

    @Inject
    public LoginViewModel(ServiciosClientes serviciosClientes) {
        this.serviciosClientes = serviciosClientes;
        state = new SimpleObjectProperty<>(new LoginState(false,null));
    }
    private final ObjectProperty<LoginState> state;
    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }
    public void doLogin(String username,String password) {
        if (serviciosClientes.doLogin(username,password))
        {
            state.setValue(new LoginState(true,null));
        }
        else
        {
            state.setValue(new LoginState(false,"ese correo no esta registrado, use root root"));
        }
    }
}
