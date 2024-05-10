
/**
 * CardDeck manages the elements inside the main window
 *
 * Change the file directory in this file!
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class CardDeck extends BorderPane {

  Button deckContentsBtn = new Button("Display Drawn Cards");
  Button restockDeckBtn = new Button("Restock Deck");
  Text header = new Text();

  int cardHeight = 683;
  int cardWidth = 480;

  static String[] deck = new String[52];
  static String[] adjustedDeck = new String[52];

  static int cardsLeft = 52;


  public CardDeck() throws FileNotFoundException {

    ImageView cardBack = displayCard("card_back", cardHeight, cardWidth);

    BorderPane.setAlignment(cardBack, Pos.CENTER);
    this.getChildren().add(cardBack);

    fillDeck(deck);
    copyArray(adjustedDeck, deck);


    deckContentsBtn.setOnMouseClicked(e -> {
      try {
        new CardTracker();
      } catch (FileNotFoundException e2) {
      }
    });

    this.setOnMouseClicked(e -> {
      try {
        drawCard();
      } catch (FileNotFoundException e1) {
      }
    });

    restockDeckBtn.setOnMouseClicked(e -> {
      try {
        refillDeck();
      } catch (FileNotFoundException e1) {
      }
    });
  }

  /*
   * Takes the card name and formats it into the file name with it's file directory, and processes
   * it as an image
   * 
   * @return: The ImageView object of the card
   */
  public static ImageView displayCard(String cardName, int height, int width)
      throws FileNotFoundException {
    
    
    // ! Correct the file directory information below (don't forget to add in a second "\" everytime it shows up) !
    
    Image image = new Image(new FileInputStream(
        "PNG-cards-1.3/cards/" + cardName + ".png")); 

    ImageView card = new ImageView(image);

    card.setFitWidth(width);
    card.setFitHeight(height);
    card.setPreserveRatio(true);
    return card;
  }

  /*
   * Fills the deck with 52 strings containing the value of the card and its suit
   */
  public static void fillDeck(String arr[]) {
    int cardValue = 1;
    String[] suffix = {"_of_clubs", "_of_diamonds", "_of_hearts", "_of_spades"};
    String prefix;
    int x = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 13; j++, x++, cardValue++) {
        prefix = cardValueConversion(cardValue);
        arr[x] = prefix + suffix[i];
      }
      cardValue = 1;
    }
  }

  /*
   * If there are cards left in the deck, it will perform a series of method calls. Otherwise, it
   * displays the back of a card, and indicates to the user that the deck has run out of cards.
   */
  public void drawCard() throws FileNotFoundException {

    if (cardsLeft > 0) {
      int random = randomInt(cardsLeft);
      String drawnCard = adjustedDeck[random];
      removeDrawnCard(drawnCard);
      removeArrayElement(random);
      header.setText(formatCardName(drawnCard));
      cardsLeft--;

      ImageView card = displayCard(drawnCard, cardHeight, cardWidth);
      CardDeck.setAlignment(card, Pos.CENTER);
      this.getChildren().add(card);

    } else {
      header.setText("The Deck is Out of Cards");
      ImageView card = displayCard("card_back", cardHeight, cardWidth);
      CardDeck.setAlignment(card, Pos.CENTER);
      this.getChildren().add(card);
    }
  }

  /*
   * Converts an integer value of the card into a string representing the integer value
   * 
   * @param x: an integer representing the value of the card
   */
  public static String cardValueConversion(int x) {
    if (x == 1)
      return "ace";
    else if (x == 11)
      return "jack";
    else if (x == 12)
      return "queen";
    else if (x == 13)
      return "king";
    else
      return "" + x;
  }


  public int randomInt(int x) {
    Random random = new Random();
    int rand = random.nextInt(x);
    return rand;
  }

  /*
   * "Removes" the drawn card by setting the corresponding string in deck to be "DRAWN"
   */
  public void removeDrawnCard(String card) {
    for (int i = 0; i < 52; i++) {
      if (card.equals(deck[i])) {
        deck[i] = "DRAWN";
        break;
      }
    }
  }

  /*
   * Creates a new adjustedDeck array with one less index and copies everything from the old
   * adjustedDeck array except for the card that was drawn
   */
  public void removeArrayElement(int drawn) {
    if (cardsLeft > 1) {
      String temp[] = new String[adjustedDeck.length];
      copyArray(temp, adjustedDeck);
      adjustedDeck = new String[adjustedDeck.length - 1];
      int x = 0;
      for (int i = 0; i < temp.length; i++) {
        if (i != drawn) {
          adjustedDeck[x] = temp[i];
          x++;
        }
      }
    }
  }

  public static void copyArray(String newArr[], String oldArr[]) {
    for (int i = 0; i < newArr.length; i++) {
      newArr[i] = oldArr[i];
    }
  }

  /*
   * Resets the arrays back to a full deck and display the card back
   */
  public void refillDeck() throws FileNotFoundException {
    fillDeck(deck);
    adjustedDeck = new String[52];
    copyArray(adjustedDeck, deck);
    cardsLeft = 52;
    header.setText("Click on the Deck to Draw from It");

    ImageView card = displayCard("card_back", cardHeight, cardWidth);
    BorderPane.setAlignment(card, Pos.CENTER);
    this.getChildren().add(card);
  }

  /*
   * Formats the card name to get rid of the "_" and capitalizes appropriate letters
   * 
   * @param str: The raw name of the card
   * @return str: The formatted name of the card
   */
  public String formatCardName(String str) {
    if (!Character.isDigit(str.charAt(0)))
      str = capitalize(str, 0, 1);
    str = capitalize(str, str.lastIndexOf("_") + 1, str.lastIndexOf("_") + 2);
    str = str.replaceAll("_", " ");
    return str;
  }

  /*
   * Capitalizes the characters between beginningIndex and endingIndex
   */
  public String capitalize(String str, int beginningIndex, int endingIndex) {
    str =
        str.substring(0, beginningIndex) + str.substring(beginningIndex, endingIndex).toUpperCase()
            + str.substring(endingIndex, str.length());
    return str;
  }
}