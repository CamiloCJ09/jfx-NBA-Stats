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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ownImplementation.classes.Node;
import model.source.AppAdministrator;
import model.source.Player;
import threads.Thread1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    private List<Player> playersFiltred;

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
       /// stage.setResizable(false);
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
        if(administrator.getByAssits().isEmpty()){
            System.out.println("Lo crea");
            Thread1 thread3 = new Thread1(administrator.getByAssits(), administrator.getArrayList(), 3);
            thread3.run();
            administrator.setByAssits(thread3.getOthers());
        }

        playersFiltred = new ArrayList<>();
        int upper = 0;
        int lower =0;
        String [] inputs = rangeInput();
        if (inputs != null) {
            lower = Integer.parseInt(inputs[0]);
            upper = Integer.parseInt(inputs[1]);

           for (int i = lower; i <=upper ; i++) {
               Node<Player, Double> temp = administrator.getByAssits().search((double) i);
               if (temp != null) {
                   playersFiltred.add(temp.getValue());
               }

           }

            setupTable(1, playersFiltred);


        }
     }

    @FXML
    void actFilterbyBlocks(ActionEvent event) throws IOException {
        if(administrator.getByBlocks().isEmpty()){
            System.out.println("Lo crea");
            Thread1 thread5 = new Thread1(administrator.getByBlocks(), administrator.getArrayList(),5);
            thread5.run();
            administrator.setByBlocks(thread5.getOthers());
        }
        playersFiltred = new ArrayList<>();

        int upper = 0;
        int lower =0;
        String [] inputs = rangeInput();

        if (inputs != null) {
            lower = Integer.parseInt(inputs[0]);
            upper = Integer.parseInt(inputs[1]);

            for (int i = lower; i <=upper ; i++) {
                Node<Player, Double> temp = administrator.getByBlocks().search((double) i);
                if (temp != null) {
                    playersFiltred.add(temp.getValue());
                }

            }

            setupTable(1, playersFiltred);


        }

    }

    @FXML
    void actFilterbyPoints(ActionEvent event) throws IOException {
        if(administrator.getByPoints().isEmpty()){
            System.out.println("Lo crea");
            Thread1 thread1 = new Thread1(administrator.getByPoints(), administrator.getArrayList());
            thread1.run();
            administrator.setByPoints(thread1.getByPoints());
        }
        playersFiltred = new ArrayList<>();

        int upper = 0;
        int lower =0;
        String [] inputs = rangeInput();

        if (inputs != null) {
            lower = Integer.parseInt(inputs[0]);
            upper = Integer.parseInt(inputs[1]);

            for (int i = lower; i <=upper ; i++) {
                Node<Player, Double> temp = administrator.getByPoints().search((double) i);
                if (temp != null) {
                    playersFiltred.add(temp.getValue());
                }

            }

            setupTable(1, playersFiltred);


        }

    }

    @FXML
    void actFilterbyRebounds(ActionEvent event) throws IOException {
        if(administrator.getByRebounds().isEmpty()){
            System.out.println("Lo crea");
            Thread1 thread2 = new Thread1(administrator.getByRebounds(), administrator.getArrayList(),2);
            thread2.run();
            administrator.setByRebounds(thread2.getOthers());
        }
        playersFiltred = new ArrayList<>();

        int upper = 0;
        int lower =0;
        String [] inputs = rangeInput();

        if (inputs != null) {
            lower = Integer.parseInt(inputs[0]);
            upper = Integer.parseInt(inputs[1]);

            for (int i = lower; i <=upper ; i++) {
                Node<Player, Double> temp = administrator.getByRebounds().search((double) i);
                if (temp != null) {
                    playersFiltred.add(temp.getValue());
                }

            }

            setupTable(1, playersFiltred);


        }

    }

    @FXML
    void actFilterbyRobberies(ActionEvent event) throws IOException {
        if(administrator.getByRobberies().isEmpty()){
            System.out.println("Lo crea");
            Thread1 thread4 = new Thread1(administrator.getByRobberies(), administrator.getArrayList(),4);
            thread4.run();
            administrator.setByRobberies(thread4.getOthers());
        }
        playersFiltred = new ArrayList<>();

        int upper = 0;
        int lower =0;
        String [] inputs = rangeInput();

        if (inputs != null) {
            lower = Integer.parseInt(inputs[0]);
            upper = Integer.parseInt(inputs[1]);

            for (int i = lower; i <=upper ; i++) {
                Node<Player, Double> temp = administrator.getByRobberies().search((double) i);
                if (temp != null) {
                    playersFiltred.add(temp.getValue());
                }

            }

            setupTable(1, playersFiltred);


        }

    }

    public void setupTable(int stat, List<Player> list) throws IOException {
        System.out.println("Vacio? "+list.isEmpty());
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
        return initialPane;
    }

    private String[] rangeInput(){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Ingrese el rango de busqueda");
        dialog.setHeaderText("Ingrese su rango separado por coma, ej: 5,7");
        dialog.setContentText("Ingrese su rango:");


        Optional<String> result = dialog.showAndWait();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡¡Informacion muy importante!!");
        alert.setHeaderText(null);
        alert.setContentText("Si desea aplicar un nuevo filtro debe remover el que ya se aplico pulsando el boton :)");

        alert.showAndWait();


        //System.out.println(administrator.getByAssits().printInOrder())
        return result.map(s -> s.split(",")).orElse(null);

    }

    @FXML
    void removeFilter(ActionEvent event) throws IOException {
        setupTable(1,administrator.getArrayList());
    }

}
