import javax.swing.*;
import java.awt.*;

public class OutputPanel extends JPanel {

    //represents maximum distance from 0 for all axis
    final int scope = 4;

    //1/granularity is the scale of all axis
    int granularity = 8;

    //controls how many squares wide the -scope to scope range will be divided in
    int resolution = scope*granularity*2+1;

    Color[][] colors = new Color[resolution][resolution];
    Double[][] slopes = new Double[resolution][resolution];
    Translator translator = new Translator();

    public OutputPanel(){
        for(int col = 0; col < resolution; col++){
            for(int row = 0; row < resolution; row++){
                colors[col][row] = Color.BLACK;
                slopes[col][row] = 0.0;
            }
        }
    }

    public void displayFunction(Function f){
        for(int col = 0; col < resolution; col++){
            for(int row = 0; row < resolution; row++){
                Double result = f.evaluate((double)col/(double)granularity-scope, scope-(double)row/(double)granularity);
                colors[col][row] = translator.generateColor(result, scope*Math.sqrt(2));
                slopes[col][row] = result;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;

        double side = this.getWidth() / resolution;
        for(int col = 0; col < resolution; col++){
            for(int row = 0; row < resolution; row++) {
                g2.setColor(colors[col][row]);
                g2.fillRect((int) (col * side), (int) (row * side), (int) (side), (int) (side));
            }
        }

        g2.setColor(new Color(128, 128, 128, 128));
        g2.drawLine((int)(side*resolution/2), 0, (int)(side*resolution/2), this.getHeight());
        g2.drawLine(0, (int)(side*resolution/2), (int)(side*resolution), (int)(side*resolution/2));

        g2.setColor(Color.BLACK);
        for(int col = 0; col < resolution; col++){
            for(int row = 0; row < resolution; row++){
                if(slopes[col][row] != null) {
                    double centerX = col * side + side / 2;
                    double centerY = row * side + side / 2;
                    double halfLength = side / 4;
                    double angle = Math.atan(slopes[col][row]);

                    System.out.println("angle for slope " + slopes[col][row] + " is " + angle * 180 / Math.PI + " degrees");

                    double dX = Math.cos(angle) * halfLength;
                    double dY = Math.sin(angle) * halfLength;
                    g2.drawLine((int) (centerX - dX), (int) (centerY + dY), (int) (centerX + dX), (int) (centerY - dY));
                }
            }
        }

    }
}