
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * 
 * @author Johnny Zheng
 * @Version: June 2022
 * 
 * Cards class
 * Sets the stage of the main window
 */

public class Main extends Application {
  @Override
  public void start(Stage stage) throws FileNotFoundException{
    
    stage.setTitle("Deck of Cards");
    stage.setHeight(800);
    stage.setWidth(484);
    
    CardDeck deck = new CardDeck();
    BorderPane bPane = new BorderPane(deck);
    BorderPane.setAlignment(deck, Pos.CENTER);
    Scene scene = new Scene(bPane);
    
    
    HBox hBox = new HBox();
    hBox.getChildren().addAll(deck.deckContentsBtn, deck.restockDeckBtn);
    hBox.setAlignment(Pos.CENTER);
    bPane.setBottom(hBox);
    hBox.setSpacing(10);
    hBox.setPadding(new Insets(10));
    
    
    bPane.setTop(deck.header);
    BorderPane.setAlignment(deck.header, Pos.CENTER);
    deck.header.setText("Click on the Deck to Draw from It");
    deck.header.setFont(Font.font(25));
    
    
    stage.setResizable(false);
    stage.setAlwaysOnTop(true);
    stage.setScene(scene);
    stage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}