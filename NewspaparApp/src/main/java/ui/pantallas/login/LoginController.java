package ui.pantallas.login;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import lombok.extern.log4j.Log4j2;
import ui.pantallas.common.BasePantallaController;

@Log4j2
public class LoginController extends BasePantallaController {

    private LoginViewModel loginViewModel;

    @FXML
    private MFXButton btLogin;
    @FXML
    private MFXTextField txtUserName;
    @FXML
    private MFXTextField txtPassword;


    @Inject
    public LoginController(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    public void initialize() {
        loginViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.getError() != null) {
                this.getPrincipalController().sacarAlertError(newState.getError());
            }
            if (newState.isLoginOK()) {
                this.getPrincipalController().onLoginHecho(txtUserName.getText(),txtPassword.getText());
            }
        });
    }
    @FXML
    private void doLogin() {

        loginViewModel.doLogin( txtUserName.getText(), txtPassword.getText());
    }




}
