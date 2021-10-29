package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import threads.Thread1;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ApplicationGUI {

    AppAdministrator administrator;

    private ObservableList<Player> players;

    public ApplicationGUI() throws IOException {
        this.administrator = new AppAdministrator();
        count = 0;
    }

    @FXML
    private TableView<Player> tvPrincipalTable;

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
    private JFXTextField tfPlayerName;

    @FXML
    private JFXTextField tfPlayerLastName;

    @FXML
    private JFXTextField tfPlayerAge;

    @FXML
    private JFXTextField tfPlayerTeam;

    @FXML
    private JFXTextField tfPlayerPoints;

    @FXML
    private JFXTextField tfPlayerRebounds;

    @FXML
    private JFXTextField tfPlayerAssists;

    @FXML
    private JFXTextField tfPlayerRobberies;

    @FXML
    private JFXTextField tfPlayerBlocks;

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
    private JFXButton btnImportData;

    @FXML
    void actAddPlayer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./jfx/addPlayerScreen.fxml"));
        fxmlLoader.setController(this);
        Parent window = fxmlLoader.load();

        Scene scene = new Scene(window);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Añadir jugadores");
        stage.show();
    }
    @FXML
    void actAddPlayerAddScreen(ActionEvent event) throws IOException {
        /*
        AÑADIR VALIDACION DE DATOS NO VACIOS
        */
        String name = tfPlayerName.getText();
        String lastName = tfPlayerLastName.getText();
        int age = Integer.parseInt(tfPlayerAge.getText());
        String team = tfPlayerTeam.getText();
        double points = Double.parseDouble(tfPlayerPoints.getText());
        double rebound = Double.parseDouble(tfPlayerRebounds.getText());
        double assists = Double.parseDouble(tfPlayerAssists.getText());
        double robberies = Double.parseDouble(tfPlayerRobberies.getText());
        double blocks = Double.parseDouble(tfPlayerBlocks.getText());
        administrator.addPlayer(name,lastName,age,team,points,rebound,assists,robberies,blocks);
        System.out.println(administrator.getArrayList().toString());
        System.out.println(administrator.getByPoints().treeToList().toString());
        //setupTable(1);
        System.out.println("Funciono pri");

    }
    private int count;
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

        tvPrincipalTable.refresh();
        setupTable(1, administrator.getArrayList());
        count++;
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
            setupTable(1,administrator.getArrayList());
        }else{
            System.out.println("No funciona rey");
        }
    }
    @FXML
    void actFilterbyAssists(ActionEvent event) throws IOException {
        Thread1 thread1 = new Thread1(administrator.getByAssits(), administrator.getArrayList(), 3);
        thread1.run();
        administrator.setByAssits(thread1.getOthers());
        System.out.println(administrator.getByAssits().printInOrder());
        System.out.println(administrator.getByAssits().getRollingFactor());
        setupTable(1, administrator.getByAssits().treeToList());
    }

    @FXML
    void actFilterbyBlocks(ActionEvent event) {

    }

    @FXML
    void actFilterbyPoints(ActionEvent event) throws IOException {
        Thread1 thread1 = new Thread1(administrator.getByPoints(), administrator.getArrayList());
        thread1.run();
        administrator.setByPoints(thread1.getByPoints());
        //System.out.println(administrator.getByPoints().treeToList().toString());
        setupTable(1, administrator.getByPoints().treeToList());
    }

    @FXML
    void actFilterbyRebounds(ActionEvent event) {

    }

    @FXML
    void actFilterbyRobberies(ActionEvent event) {

    }

    public void setupTable(int stat, List<Player> list) throws IOException {
        tvPrincipalTable.refresh();
        //players = tvPrincipalTable.getItems();
        players = FXCollections.observableArrayList(list);

        //tvPrincipalTable.getItems().addAll(players);
        tcPlayerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcPlayerLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcPlayerAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tcPlayerTeam.setCellValueFactory(new PropertyValueFactory<>("team"));
        tcPlayerPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
        tcPlayerRebounds.setCellValueFactory(new PropertyValueFactory<>("rebounds"));
        tcPlayerAssist.setCellValueFactory(new PropertyValueFactory<>("assists"));
        tcPlayerRobberies.setCellValueFactory(new PropertyValueFactory<>("robberies"));
        tcPlayerBlocks.setCellValueFactory(new PropertyValueFactory<>("blocks"));
        tvPrincipalTable.setItems(players);
        tvPrincipalTable.refresh();
    }


    public BorderPane getBorderpane(){
        //List<Player> aux = tvPrincipalTable.getItems();
        return initialPane;}

}
