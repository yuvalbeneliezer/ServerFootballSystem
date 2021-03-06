package Controllers;

import AssociationAssets.Game;
import DB.GameDB;
import Model.RecordException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import System.FootballSystem;
import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class ManageGameController extends Controller {

    @FXML
    public Button createReport;
    public Button removeEvent;
    public Button editEvent;
    public Button addEvent;
    public ComboBox cmbGameIDType;
    public RequiredField requiredField1;

    @FXML
    public void createReport() {
        requiredField1.eval();
        if (requiredField1.getHasErrors()) {
            return;
        }
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        stage.initOwner(createReport.getScene().getWindow());
        fileChooser.setTitle("Create Report"); //set the title of the Dialog window
        String defaultSaveName = "Report GameID_" + cmbGameIDType.getValue().toString();
        fileChooser.setInitialFileName(defaultSaveName); //set the default name for file to be saved
        //create extension filters. The choice will be appended to the end of the file name
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files (*.csv)", "*.csv"));
        try {
            //Actually displays the save dialog - blocking
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                File dir = file.getParentFile();//gets the selected directory
                //update the file chooser directory to user selected so the choice is "remembered"
                fileChooser.setInitialDirectory(dir);
                //handle saving data to disk or DB etc.
                int gameID = Integer.parseInt(this.cmbGameIDType.getValue().toString());
                try {
                    model.exportGameReport(gameID, file.getAbsolutePath(), file.getName());
                } catch (RecordException e) {
                    raiseAlert(e);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void addEvent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CreateNewEventUI.fxml"));
        Stage stage = getStage(loader, addEvent);
        stage.setTitle("Create New Event");
        CreateNewEventController controller = loader.getController();
        controller.init();
        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }


    @FXML
    public void removeEvent() {
        stillWorkingOnIt();
    }

    @FXML
    public void editEvent() {
        stillWorkingOnIt();
    }

    public void init(){
        // init game DB
        initGameIdCB(this.cmbGameIDType);
    }
}

