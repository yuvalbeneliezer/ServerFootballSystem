package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    public Button manageTeamBtn;

    @FXML
    public void manageTeamClick(){
        // TODO: 5/3/2020 need to validate the permission of the user
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ManageTeam.fxml"));
        Stage stage = new Stage();
        stage.initOwner(manageTeamBtn.getScene().getWindow());
        try {
            stage.setScene(new Scene((Parent) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }
}
