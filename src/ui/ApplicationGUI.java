package ui;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.source.AppAdministrator;
import model.source.Player;

import java.io.File;
import java.io.IOException;

public class ApplicationGUI {

    AppAdministrator administrator;

    private ObservableList<Player> players;

    public ApplicationGUI(){
        this.administrator = new AppAdministrator();
    }

    @FXML
    private TableColumn<Player, String> tcPlayerName;

    @FXML
    private TableColumn<Player, String> tcPlayerLastName;

    @FXML
    private TableColumn<Player, Double> tcPlayerAge;

    @FXML
    private TableColumn<Player, String> tcPlayerTeam;

    @FXML
    private TableColumn<Player, Double> tcPlayerPoints;

    @FXML
    private TableColumn<Player, Double> tcPlayerRebounds;

    @FXML
    private TableColumn<Player, Double> tcPlayerAssist;

    @FXML
    private TableColumn<Player, Double> tcPlayerRobberies;

    @FXML
    private TableColumn<Player, Double> tcPlayerBlocks;


    @FXML
    private BorderPane initialPane;

    @FXML
    private BorderPane principalPane;

    @FXML
    private JFXButton btnAddPlayer;

    @FXML
    private JFXButton btnDeletePlayer;

    @FXML
    private JFXButton btnEditPlayer;

    @FXML
    private JFXButton btnComsultInfo;

    @FXML
    private TableView<?> tvPrincipalTable;

    @FXML
    private JFXButton btnImportData;

    @FXML
    void actAddPlayer(ActionEvent event) {

    }

    @FXML
    void actConsultInfo(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./jfx/principalTable.fxml"));
        fxmlLoader.setController(this);
        Parent window = fxmlLoader.load();

        Scene scene = new Scene(window);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Importar jugadores");
        stage.show();
    }

    @FXML
    void actDeletePlayer(ActionEvent event) {

    }

    @FXML
    void actEditPlayer(ActionEvent event) {

    }

    @FXML
    void actImportData(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona el archivo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Csv files", "*.csv"));
        Stage primaryStage = (Stage)initialPane.getScene().getWindow();
        File fileToSave = fileChooser.showOpenDialog(primaryStage);
        if(fileToSave != null){
            String url = fileToSave.toPath().toString();
            administrator.importPlayers(url);
            setupTable(1);
        }else{
            System.out.println("No funciona rey");
        }
    }

    public void setupTable(int stat) throws IOException {
        players = FXCollections.observableArrayList(administrator.getByPoints().treeToList());
        tcPlayerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcPlayerLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcPlayerAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tcPlayerTeam.setCellValueFactory(new PropertyValueFactory<>("team"));
        tcPlayerPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
        tcPlayerRebounds.setCellValueFactory(new PropertyValueFactory<>("rebounds"));
        tcPlayerAssist.setCellValueFactory(new PropertyValueFactory<>("assist"));
        tcPlayerRobberies.setCellValueFactory(new PropertyValueFactory<>("robberies"));
        tcPlayerBlocks.setCellValueFactory(new PropertyValueFactory<>("blocks"));

    }


    public BorderPane getBorderpane(){return initialPane;}

}
