import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

/**
 * Created by spencer on 2/26/17.
 */
public class Main extends java.applet.Applet
        implements Runnable, KeyListener {
    int WIDTH = 500;
    int HEIGHT = 500;
    int x_pos = 0;
    int y_pos = 0;
    int size = 10;

    Snake s = new Snake();

    int speed = 1;




    Thread runner;
    Graphics bufferGraphics;
    Image offscreen;

    @Override
    public void init() {
        this.setSize(WIDTH, HEIGHT);
        addKeyListener(this);
        offscreen = createImage(WIDTH, HEIGHT);
        bufferGraphics = offscreen.getGraphics();
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    @Override
    public void stop() {
        if (runner != null) {
            runner.interrupt();
            runner = null;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {

            }
            repaint();
        }
    }

    //TODO Rework loop to move 1 px each time
    @Override
    public void paint(Graphics g) {
        s.update_snake(speed);

        bufferGraphics.clearRect(0,0,WIDTH,HEIGHT);
        bufferGraphics.setColor(Color.RED);
        for (Rectangle2D rec : s.rects) {
            bufferGraphics.fillRect((int) rec.getX(), (int) rec.getY(), s.size, s.size);
        }
        g.drawImage(offscreen,0,0,this);

    }

    public void update(Graphics g)
    {
        paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 38) {
            if (s.d_y ==0) {
                s.setDirection(3);
            }
        } else if (e.getKeyCode() == 40) {
            if (s.d_y == 0) {
                s.setDirection(1);
            }
        } else if (e.getKeyCode() == 39) {
            if (s.d_x == 0) {
                s.setDirection(0);
            }
        } else if (e.getKeyCode() == 37) {
            if (s.d_x == 0) {
                s.setDirection(2);
            }
        }
        else if (e.getKeyCode() == 32){
            s.ate(WIDTH,HEIGHT);
        }
        else {
            System.out.println(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}