package briar.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Briar briar = new Briar();

    // Source: https://leagueoflegends.fandom.com/wiki/Briar/Development?file=I_WAS_ROOTING_FOR_YOU_Emote.png
    private Image briarIcon = new Image(this.getClass().getResourceAsStream("/images/Briar.png"));

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Briar");
            stage.getIcons().add(briarIcon);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBriar(briar); // inject the Briar instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
