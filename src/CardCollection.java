
/**
 * CardCollection manages the elements inside the pop-up window
 * CardGrid manages the elements of each individual slot in the grid of the pop-up window
 */
import java.io.FileNotFoundException;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CardCollection extends GridPane {

  CardGrid[][] cards;
  static String fullDeck[] = new String[52];
  int cardHeight = 150;
  int cardWidth = 105;


  public CardCollection() throws FileNotFoundException {
    
    CardDeck.fillDeck(fullDeck);

    cards = new CardGrid[4][13]; // Creating a grid of CardGrid objects
    
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 13; j++) {
        cards[i][j] = new CardGrid(cardHeight, cardWidth, i, j);
        this.add(cards[i][j], cardWidth * j, cardHeight * i);
      }
    }
    this.setStyle("-fx-padding: 10 10 10 10;");
    
  }


  public class CardGrid extends Pane {

    public CardGrid(int height, int width, int i, int j) throws FileNotFoundException {
      setPrefSize(width, height);
      setStyle("-fx-border-color: white;-fx-background-color: white;");
     
      int index = (i * 13) + j; // Formula to convert the index of a 2d array to a 1d array
      
      // Compares the elements in deck to fullDeck to determine what should be displayed
      if (CardDeck.deck[index].equals("DRAWN")) {
        ImageView card = CardDeck.displayCard(fullDeck[index], height, width);
        this.getChildren().add(card);
      } else {
        ImageView card = CardDeck.displayCard("card_back", height, width);
        this.getChildren().add(card);
      }
    }
  }
}