import java.awt.*;
import java.util.Map;

public class MapGenerator {
    public int map [][];
    public int brickWidth;
    public int brickHeight;

    public MapGenerator (int rows, int columns){
        map = new int [rows][columns];
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j < map[0].length; j++){
               // if (i == 2 && j==6) {
                  //  map[i][j] = 0;
               // }
                //else
                   map[i][j]=1;

            }
        }

        brickWidth = 540/columns;
        brickHeight = 150/rows;
    }

    public void draw (Graphics2D g){
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if (map[i][j] > 0){
                    g.setColor(Color.white);
                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int rows, int columns){
        map[rows][columns] = value;
    }
}
