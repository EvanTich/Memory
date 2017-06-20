import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private static final int FLIPPED = 4;

    private Card selectedCard;

    @Override
    public void start(Stage primaryStage) throws Exception {
        startGame(primaryStage);
    }

    public void startGame(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();

        byte[] imgs = new byte[10];
        Random rnd = new Random();

        ColorAdjust adjust = new ColorAdjust();
        adjust.setBrightness(-0.25);

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                int num;
                // get valid random image number
                while(imgs[num = rnd.nextInt(10)] == 2);
                imgs[num]++;

                Card card = new Card( String.format("file:imgs/image%s.png", num) );

                card.setOnMouseClicked(x -> {
                    if(card.isFlipped())
                        return;

                    if(selectedCard != null && !selectedCard.isFlipped()) {
                        if (selectedCard != card && selectedCard.equals(card)) {
                            selectedCard.flip();
                            card.flip();
                        } else {
                            selectedCard.hide();
                            selectedCard.setEffect(null);
                            selectedCard = null;
                            /* TODO: alert player too */
                        }
                    } else {
                        selectedCard = card;
                        selectedCard.show();
                    }

                    if (allFlipped(root))
                        alertWin(primaryStage); // win
                });

                card.setOnMouseEntered(x -> {
                    if(selectedCard != card && !card.isFlipped())
                        card.setEffect(adjust);
                });

                card.setOnMouseExited(x -> {
                    if(selectedCard != card && !card.isFlipped())
                        card.setEffect(null);
                });

                root.add(card, j, i); // node, col, row
            }
        }

        // TODO: now flip a few

        primaryStage.setTitle("Easy Memory Puzzle");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public boolean allFlipped(GridPane g) {
        for(Node x : g.getChildren())
            if(!((Card)x).isFlipped())
                return false;
        return true;
    }

    public void alertWin(Stage stage)  {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You Win!");
        alert.setHeaderText("You Win!");
        alert.setContentText("Congratulations!");

        alert.showAndWait();

        try {
            startGame(stage);
        } catch(Exception e) {}
    }

    public static void main(String[] args) {
        launch(args);
    }
}
