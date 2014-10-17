/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nhuan
 */
public class ImagePanel extends JPanel {
       private Image img;
       public ImagePanel(String src)
       {
           this.img = new ImageIcon(src).getImage();
          
       }
       public ImagePanel(Image img)
       {
           
       }
       @Override
       public void paintComponent(Graphics g)
       {
           g.drawImage(img, 0, 0, this);
       }
}
class ImageTest
{
    public static void main(String [] args)
    {
        ImagePanel ImagePanel = new ImagePanel("C:\\Users\\Nhuan\\Documents\\NetBeansProjects\\AndroidElevator\\build\\classes\\view\\Car.jpeg");
        JFrame frame = new JFrame();
        frame.getContentPane().add(ImagePanel);
        frame.setVisible(true);
    }
}