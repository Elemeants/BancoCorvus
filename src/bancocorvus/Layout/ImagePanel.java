/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Layout;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private Image image;

    public ImagePanel() {
        try {
            System.out.println("Working Directory = " +
            System.getProperty("user.dir"));
            image = ImageIO.read(new File("Logo.jpeg")).getScaledInstance(300,150,Image.SCALE_SMOOTH);
       } catch (IOException ex) {
            System.err.println(ex.toString());
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
}
