import javax.swing.*;
import java.awt.*;

public class SFMain {

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(680,702);
        frame.setVisible(true);

        Container fCP = frame.getContentPane();

        OutputPanel panel = new OutputPanel();
        fCP.add(panel);
        panel.setVisible(true);

        Function f = (double x, double y)-> Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) != x*y*Math.pow(1/Math.cos(Math.sqrt(Math.pow(x,2) + Math.pow(y,2))),2) ? (Math.sqrt(Math.pow(x,2) + Math.pow(y,2))*Math.tan(Math.sqrt(Math.pow(x,2) + Math.pow(y,2))) + Math.pow(x,2) * Math.pow(1/Math.cos(Math.sqrt(Math.pow(x,2) + Math.pow(y,2))),2))/(Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) - x*y*Math.pow(1/Math.cos(Math.sqrt(Math.pow(x,2) + Math.pow(y,2))),2)): null;
        panel.displayFunction(f);
    }

}