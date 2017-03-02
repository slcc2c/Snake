import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by spencer on 2/19/17.
 */
public class Snake {
    ArrayList<Rectangle2D> rects = new ArrayList<>();
    int size = 15;
    int max_score = 20;
    int score = 0;
    int pos_x = 0;
    int pos_y = 0;
    int pellet_x = 0;
    int pellet_y = 0;
    int pellet_size = 15;
    int direction = 0;
    int d_x = 0;
    int d_y = 0;
    boolean trunc = false;
    Rectangle2D pellet = new Rectangle(pellet_x, pellet_y, pellet_size, pellet_size);


    public Snake() {
        rects.add(new Rectangle(0, 0, size, size));
    }


    void ate(int w, int h) {
        score++;
        rects.add(new Rectangle(pos_x, pos_y, 0, 0));
        new_pellet(w, h);
    }

    void new_pellet(int w, int h) {
        Random rand = new Random();
        pellet = new Rectangle((rand.nextInt(w - pellet_size) / pellet_size) * pellet_size, (rand.nextInt(h - pellet_size) / pellet_size) * pellet_size, pellet_size, pellet_size);
    }

    boolean test_eat() {
        return pellet.intersects(rects.get(0));
    }

    void update_snake(int speed) {
        switch (direction) {
            case 0:
                d_x = speed*4;
                d_y = 0;
                break;
            case 1:
                d_x = 0;
                d_y = speed*4;
                break;
            case 2:
                d_x = -1 * speed*4;
                d_y = 0;
                break;
            case 3:
                d_x = 0;
                d_y = -1 * speed*4;
                break;
            default:
                break;
        }
            //balance speed
            pos_x += d_x;
            pos_y += d_y;

        for (int i = rects.size() - 1; i > 0; i--) {
            rects.set(i, rects.get(i - 1));

            //rects.get(i).setRect(rects.get(i).getMinX(), rects.get(i).getMinY() + d_y*(5), size, size);
        }
        rects.set(0, new Rectangle(pos_x, pos_y, size, size));


    }

    void setDirection(int direction) {
        if (direction >= 0 && direction < 4) {
            this.direction = direction;
            trunc=true;
        }
    }

}
