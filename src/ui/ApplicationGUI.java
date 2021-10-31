package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.ownImplementation.classes.BinarySearchTree;
import model.ownImplementation.classes.Node;
import model.source.AppAdministrator;
import model.source.Player;
import threads.Thread1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicationGUI {

    private AppAdministrator administrator;

    private Player playerToEdit;
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
    private TableView<Player> tvPlayerSelected;

    @FXML
    private TableColumn<Player, String> tc_namePlayerSelected;

    @FXML
    private TableColumn<Player, String> tc_lastnamePlayerSelected;

    @FXML
    private TableColumn<Player, Integer> tc_agePlayerSelected;

    @FXML
    private TableColumn<Player, String> tc_teamPlayerSelected;

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
    private Label timeInNano;

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
    private Label lbNamePlayerToEdit;

    @FXML
    private JFXSlider slPoints;

    @FXML
    private JFXSlider slRebouns;

    @FXML
    private JFXSlider slAssists;

    @FXML
    private JFXSlider slRobberies;

    @FXML
    private JFXSlider slBlocks;


    @FXML
    private JFXTextField tfOtherFilter;

    @FXML
    private JFXTextField tfSuperiorLimit;

    @FXML
    private JFXTextField tfInferiorLimit;



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
        //System.out.println(administrator.getArrayList().toString());
        //System.out.println(administrator.getByPoints().treeToList().toString());
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
    void actEditPlayer(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(administrator.getArrayList().isEmpty()){
            alert.setTitle("Lo sentimos");
            alert.setHeaderText(null);
            alert.setContentText("No hay datos cargados, por favor agregue un jugador o importelos en el area de realizar consulta");
            alert.showAndWait();
        }else{
            boolean out = false;
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Editar ");
            dialog.setHeaderText("Ingrese el nombre del jugador al que le va a editar sus datos");
            dialog.setContentText("Nombre:");


            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()){
                for (int i = 0; i <administrator.getArrayList().size() && !out ; i++) {
                    if(result.get().equals(administrator.getArrayList().get(i).getName())){
                        playerToEdit = administrator.getArrayList().get(i);
                        out = true;

                    }
                }
                if(out){
                    FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("./jfx/editPlayerPane.fxml"));
                    fxmlLoader1.setController(this);
                    Parent input = fxmlLoader1.load();
                    getBorderpane().setCenter(input);
                    lbNamePlayerToEdit.setText(result.get());
                    slAssists.setValue(playerToEdit.getAssists());
                    slBlocks.setValue(playerToEdit.getBlocks());
                    slPoints.setValue(playerToEdit.getPoints());
                    slRebouns.setValue(playerToEdit.getRebounds());
                    slRobberies.setValue(playerToEdit.getRobberies());


                    ObservableList<Player> playertoList = FXCollections.observableArrayList(playerToEdit);
                    tc_namePlayerSelected.setCellValueFactory(new PropertyValueFactory<>("name"));
                    tc_lastnamePlayerSelected.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                    tc_agePlayerSelected.setCellValueFactory(new PropertyValueFactory<>("age"));
                    tc_teamPlayerSelected.setCellValueFactory( new PropertyValueFactory<>("team"));

                    tvPlayerSelected.setItems(playertoList);


                    tc_namePlayerSelected.setCellFactory(TextFieldTableCell.forTableColumn());
                    tc_lastnamePlayerSelected.setCellFactory(TextFieldTableCell.forTableColumn());
                    tc_agePlayerSelected.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
                        @Override
                        public String toString(Integer object) {
                            return object.toString();
                        }

                        @Override
                        public Integer fromString(String string) {
                            return Integer.parseInt(string);
                        }
                    }));
                    tc_teamPlayerSelected.setCellFactory(TextFieldTableCell.forTableColumn());

                    tc_namePlayerSelected.setOnEditCommit(data ->{
                       // int saveIndex;
                        System.out.println("Antiguo nombre: "+data.getOldValue());
                        playerToEdit.setName(data.getNewValue());
                        System.out.println("Nuevo nombre: "+playerToEdit.getName());

                    });

                    tc_lastnamePlayerSelected.setOnEditCommit(data ->{
                        // int saveIndex;
                        System.out.println("Antiguo apellido: "+data.getOldValue());
                        playerToEdit.setLastName(data.getNewValue());
                        System.out.println("Nuevo apellido: "+playerToEdit.getLastName());

                    });

                    tc_agePlayerSelected.setOnEditCommit(data ->{
                        // int saveIndex;
                        System.out.println("Antigua edad: "+data.getOldValue());
                        playerToEdit.setAge(data.getNewValue());
                        System.out.println("Nueva edad: "+playerToEdit.getAge());

                    });

                    tc_teamPlayerSelected.setOnEditCommit(data ->{
                        // int saveIndex;
                        System.out.println("Antiguo team: "+data.getOldValue());
                        playerToEdit.setTeam(data.getNewValue());
                        System.out.println("Nuevo team: "+playerToEdit.getTeam());

                    });

                    tvPlayerSelected.setEditable(true);





                }else{
                    alert.setTitle("Lo sentimos");
                    alert.setHeaderText(null);
                    alert.setContentText("El jugador que ingresaste no está en nuestra base de datos, revise el nombre que escribió");
                    alert.showAndWait();
                }


            }

        }

    }

    @FXML
    void saveChanges(ActionEvent event) throws IOException {
        /*
        playerToEdit.setPoints(slPoints.getValue());
        playerToEdit.setAssists(slAssists.getValue());
        playerToEdit.setBlocks(slBlocks.getValue());
        playerToEdit.setRobberies(slRobberies.getValue());
        playerToEdit.setRebounds(slRebouns.getValue());

         */

        int index = administrator.getArrayList().indexOf(playerToEdit);

        administrator.getArrayList().get(index).setName(playerToEdit.getName());
        administrator.getArrayList().get(index).setLastName(playerToEdit.getLastName());
        administrator.getArrayList().get(index).setAge(playerToEdit.getAge());
        administrator.getArrayList().get(index).setTeam(playerToEdit.getTeam());
        administrator.getArrayList().get(index).setPoints(slPoints.getValue());
        administrator.getArrayList().get(index).setAssists(slAssists.getValue());
        administrator.getArrayList().get(index).setBlocks(slBlocks.getValue());
        administrator.getArrayList().get(index).setRobberies(slRobberies.getValue());
        administrator.getArrayList().get(index).setRebounds(slRebouns.getValue());

        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("./jfx/principalPane.fxml"));
        fxmlLoader1.setController(this);
        Parent input = fxmlLoader1.load();
        getBorderpane().setCenter(input);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cambios Guardados");
        alert.setHeaderText(null);
        alert.setContentText("Cambios guardados satisfactoriamente");
        alert.showAndWait();


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
        double time1 = System.currentTimeMillis();
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
                   playersFiltred.addAll(temp.getValue());
               }

           }
            double time2 = System.currentTimeMillis();
            double oficialTime = time2-time1;
            timeInNano.setText(String.valueOf(oficialTime));
            setupTable(1, playersFiltred);


        }
     }

    @FXML
    void actFilterbyBlocks(ActionEvent event) throws IOException {
        double time1 = System.currentTimeMillis();
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
                    playersFiltred.addAll(temp.getValue());
                }

            }
            double time2 = System.currentTimeMillis();
            double oficialTime = time2-time1;
            timeInNano.setText(String.valueOf(oficialTime));
            setupTable(1, playersFiltred);


        }

    }

    @FXML
    void actFilterbyPoints(ActionEvent event) throws IOException {
        double time1 = System.currentTimeMillis();
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
                    playersFiltred.addAll(temp.getValue());
                }
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./jfx/otherFilterPane.fxml"));
            fxmlLoader.setController(this);
            Parent window = fxmlLoader.load();

            Scene scene = new Scene(window);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Filtro sobre filtro");
            stage.show();

            double time2 = System.currentTimeMillis();
            double oficialTime = time2-time1;
            timeInNano.setText(String.valueOf(oficialTime));
            setupTable(1, playersFiltred);

        }

    }

    @FXML
    void actFilterOverFilter(ActionEvent event) throws IOException {
        int otherFilter = Integer.parseInt(tfOtherFilter.getText());
        Stage stage = (Stage) tfOtherFilter.getScene().getWindow();
        if(otherFilter != 1){
            int statistic = Integer.parseInt(tfOtherFilter.getText());
            administrator.setByPoints(new BinarySearchTree<>());
            Thread1 thread1 = new Thread1(administrator.getByPoints(), playersFiltred);
            thread1.setStatistic(statistic);
            thread1.run();
            administrator.setByPoints(thread1.getByPoints());

            int upper = 0;
            int lower =0;
            String [] inputs = new String[2];
            inputs[0] = tfInferiorLimit.getText();
            inputs[1] = tfSuperiorLimit.getText();
            playersFiltred = new ArrayList<>();

            System.out.println("Antes de entrar" + inputs[0]+ " "+inputs[1]);
            if (inputs != null) {
                System.out.println("Luego de entrar");
                lower = Integer.parseInt(inputs[1]);
                upper = Integer.parseInt(inputs[0]);

                System.out.println("Antes del for");

                for (int i = lower; i <= upper; i++) {
                    //System.out.println("Entra al for");
                    Node<Player, Double> temp = administrator.getByPoints().search((double) i);
                    //System.out.println("Nodo: "+temp.getValue());
                    if (temp != null) {
                        playersFiltred.addAll(temp.getValue());
                    }
                }
                setupTable(1, playersFiltred);
                stage.close();
            }
        }else{
            stage.close();
        }
    }

    @FXML
    void actFilterbyRebounds(ActionEvent event) throws IOException {
        double time1 = System.currentTimeMillis();
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
                    playersFiltred.addAll(temp.getValue());
                }

            }

            double time2 = System.currentTimeMillis();
            double oficialTime = time2-time1;
            timeInNano.setText(String.valueOf(oficialTime));
            setupTable(1, playersFiltred);


        }

    }

    @FXML
    void actFilterbyRobberies(ActionEvent event) throws IOException {
        double time1 = System.currentTimeMillis();
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
                    playersFiltred.addAll(temp.getValue());
                }

            }
            double time2 = System.currentTimeMillis();
            double oficialTime = time2-time1;
            timeInNano.setText(String.valueOf(oficialTime));
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
