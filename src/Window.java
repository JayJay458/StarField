import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Vector;
import java.awt.event.*;

/**
 * Panel
 */
public class Window extends JFrame {

    class Space extends JPanel {

        int width = 1000;
        int height = 1000;
        Vector<Star> stars;

        Space() {
            stars = new Vector<Star>();
            for (int index = 0; index < 400; index++) {
                stars.add(new Star(width, height));
            }

            this.setPreferredSize(new Dimension(width, height));
        }

        Space(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            Graphics2D graphics2d = (Graphics2D) g;
            graphics2d.setStroke(new BasicStroke(1));
            graphics2d.setPaint(new Color(0, 0, 0));
            graphics2d.fill(new Rectangle(1000, 1000));
            graphics2d.setStroke(new BasicStroke(0));
            graphics2d.translate(width / 2, height / 2);
            graphics2d.setPaint(new Color(255, 255, 255));
            for (Star star : stars) {
                double ellispeSize = 16 - 16 * star.z / (width / 2);
                int previousX = (int) (2*star.x*star.pz/width);
                int previousY = (int) (2*star.y*star.pz/height);
                int currentX = (int) (star.x + ellispeSize / 2);
                int currentY = (int) (star.y + ellispeSize / 2);
                graphics2d.drawLine(previousX, previousY, currentX, currentY);

                graphics2d.fill(new Ellipse2D.Double(star.x, star.y, ellispeSize, ellispeSize));
                star.update();

            }

        }
    }

    Window() {
        Space space = new Space();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(1));
        this.add(space);
        this.add(new JLabel("Speed"));
        JSlider speedSldier = new JSlider(1, 100, Star.speed);
        this.add(speedSldier);
        this.pack();
        this.setVisible(true);

        ActionListener refresher = new AbstractAction() {
            public void actionPerformed(ActionEvent event) {
                // space.removeAll();
                Star.speed = speedSldier.getValue();

                space.repaint();
                space.updateUI();

            }
        };
        Timer timer = new Timer(50, refresher);
        timer.start();
        this.setLocationRelativeTo(null);
    }
}