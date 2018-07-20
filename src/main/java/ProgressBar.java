import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProgressBar{
    private int suankipozisyon, width, height, xPosition, yPosition;
    private Color numberColor;
    private boolean visible;



    public ProgressBar(int xPosition, int yPosition, int width, int height, Color color) {
        super();
        this.suankipozisyon = 0;
        this.width = width;
        this.height = height;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.numberColor = color;
        this.visible=true;

    }

    public void reset() {
        this.suankipozisyon = 0;
    }

    public void ilerle() {
        if (this.suankipozisyon < 30) {
            this.suankipozisyon++;
        }
    }

    public void draw(Graphics g) {
        if(visible){
            Color clr = g.getColor();
            for (int i = 30; i > 0; i--) {
                if (i <= this.suankipozisyon) {
                    g.setColor(Color.getHSBColor(1.0f, i / 30f, 1.0f));
                    g.fillOval(xPosition + i * width / 32, yPosition + height * 19/20, width / 32, height / 32);
                } else {
                    g.setColor(Color.GRAY);
                    g.fillOval(xPosition + i * width / 32, yPosition + height * 19/20, width / 32, height / 32);
                }
            }
            for (int i = 1; i <= 30; i++) {
                if (i <= this.suankipozisyon) {
                    g.setColor(Color.getHSBColor(1.0f, i / 30f, 1.0f));
                    g.fillOval((int) (xPosition + width / 2 + width / 4 * Math.cos(Math.PI / 2 - 2 * Math.PI / 30 * i)),
                            (int) (yPosition + width / 4 - width / 4 * Math.sin(Math.PI / 2 - 2 * Math.PI / 30 * i)),
                            width / 24, height / 24);
                } else {
                    g.setColor(Color.GRAY);
                    g.fillOval((int) (xPosition + width / 2 + width / 4 * Math.cos(Math.PI / 2 - 2 * Math.PI / 30 * i)),
                            (int) (yPosition + width / 4 - width / 4 * Math.sin(Math.PI / 2 - 2 * Math.PI / 30 * i)),
                            width / 24, height / 24);
                }
            }
            g.setColor(numberColor);
            g.setFont(new Font("Helvetica", Font.BOLD, 120));
            if (suankipozisyon <= 20) {
                g.drawString(String.valueOf(30 - suankipozisyon), xPosition + width / 3 + 20, yPosition + width / 3);
            } else {
                g.drawString(String.valueOf(30 - suankipozisyon), xPosition + width / 3 + 60, yPosition + width / 3);
            }
            g.setColor(clr);

        }


    }
    public void setVisible(boolean visible){
        this.visible=visible;
    }
    public void setColor(Color color){
        numberColor=color;
    }

    public int getSuankipozisyon() {
        return suankipozisyon;
    }

}
