import java.awt.*;

public class Translator {
    public Color generateColor(Double result, double scale){
        if(result == null) return Color.BLACK;
        double offset = 128*((result/scale)/(1+Math.abs(result/scale)));
        return new Color(128 + (int)offset, 0, 128 - (int)offset);
    }
}