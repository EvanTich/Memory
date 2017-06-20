import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Evan Tichenor (evan.tichenor@gmail.com)
 * @version 1.0, 6/19/2017
 */
public class Card extends ImageView {

    private static final String emptyCard = "file:imgs/unknown.png";

    private String url;
    private boolean flipped;

    public Card(String url) {
        super(emptyCard);
        this.url = url;
    }

    public void flip() {
        setImage(new Image(url));
        flipped = true;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void show() {
        setImage(new Image(url));
    }

    public void hide() {
        setImage(new Image(emptyCard));
    }

    public boolean equals(Object obj) {
        return obj instanceof Card
                && ((Card)obj).url.equals(url);
    }
}
