package ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private ApplicationGUI gui;

    public Main() {gui = new ApplicationGUI();}

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./jfx/mainPane.fxml"));

        fxmlLoader.setController(gui);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Store");
        primaryStage.setResizable(false);
        primaryStage.show();

        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("./jfx/principalPane.fxml"));
        fxmlLoader1.setController(gui);
        Parent input = fxmlLoader1.load();
        gui.getBorderpane().setCenter(input);

    }
}
