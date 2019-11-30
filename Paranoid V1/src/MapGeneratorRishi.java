import java.awt.*;

public class MapGeneratorRishi {
    public int map [][];
    public int brickWidth;
    public int brickHeight;

    public MapGeneratorRishi (int rows, int columns){
        map = new int [rows][columns];
        for(int i = 0; i<3; i++){
            for(int j = 0; j<7; j++){
                map[i][j]=4;
            }
        }

        brickWidth = 540/columns;
        brickHeight = 150/rows;
    }

    public void draw (Graphics2D g){
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if (map[i][j] > 0){
                    if (j ==0 || j==2 || j==4 || j==6)
                    g.setColor(Color.blue);

                    else g.setColor(Color.red);

                    g.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.orange);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int rows, int columns){
        map[rows][columns] = value;
    }
}

