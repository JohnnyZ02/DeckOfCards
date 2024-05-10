
/**
 * CardTracker sets the stage of the pop-up window
 */
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CardTracker extends BorderPane  {
  
  Text header = new Text();

  public CardTracker() throws FileNotFoundException {
    
    Stage stage = new Stage();
    stage.setTitle("Drawn Cards");
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setHeight(710);
    stage.setWidth(1400);
    stage.setAlwaysOnTop(true);
   
    CardCollection collection = new CardCollection();
    BorderPane bPane = new BorderPane(collection);
    BorderPane.setAlignment(collection, Pos.CENTER);
    bPane.setTop(header);
    
    header.setText("Click Anywhere to Close");
    header.setFont(Font.font(20));
    BorderPane.setAlignment(header, Pos.CENTER);
    
    Scene scene = new Scene(bPane);
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
    
    bPane.setOnMouseClicked(e -> stage.close()); // Closes the pop-up window
  }  
}