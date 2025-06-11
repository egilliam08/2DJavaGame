package Main;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //screen settings
    final int originalTileSize = 16; //16x16 tile, default size
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionDetection cChecker = new CollisionDetection(this);
    public ObjectPlacer oSet = new ObjectPlacer(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; //display up to 10 obj at same time



    


    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void gameSetup()
    {
        oSet.setObject(); // place objects on map before game runs
    }
    public void startGameThread()
    {
        gameThread = new Thread(this); // passing gamepanel to gameThread
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // draw screen every .016 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null)
        {

            //UPDATE INFO SUCH AS CHARACTER POSITION
            update();
            //DRAW THE SCREEN WITH UPDATED INFO
            repaint();



            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // convert nanto to milli

                if (remainingTime < 0)
                {
                    remainingTime = 0; //set to 0 rather than negative
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void update()
    {
        player.update();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //tile
        tileM.draw(g2);
        //object
        for(int i = 0; i < obj.length; i++)
        {
            if (obj[i] != null)
            {
                obj[i].draw(g2, this);
            }
        }
        //player
        player.draw(g2);

        g2.dispose();
    }
}
