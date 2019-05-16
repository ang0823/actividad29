package rmi;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 *
 * @author Luis Aduana
 */
public class FrameImage extends JInternalFrame implements Runnable {

    private Image imagen;
    private int windowsNumber;
    
    public FrameImage(Image imagen, int windowsNumber) {
        this.imagen = imagen;
        this.windowsNumber = windowsNumber;
    }
    
    @Override
    public void run() {
        try {
            java.awt.Image awtImage = null;
            URL url = new URL(imagen.getUrl());
            awtImage = ImageIO.read(url);
            
            JLabel nombreLabel = new JLabel(imagen.getName());
            this.add(nombreLabel, BorderLayout.NORTH);
            
            ImageIcon imageIcon = new ImageIcon(awtImage);
            JLabel picLabel = new JLabel(imageIcon);
            
            // Guardar la imagen en Disco Duro
            BufferedImage bufferedImage = new BufferedImage( 
                    imageIcon.getIconWidth(), 
                    imageIcon.getIconHeight(), 
                    BufferedImage.TYPE_INT_RGB);
            
            bufferedImage.getGraphics().drawImage(awtImage, 0, 0, null);
            ImageIO.write(bufferedImage, "jpg", new File( imagen.getName() + ".jpg"));
            
            this.add(picLabel);
            setBounds(windowsNumber * 20, windowsNumber * 30, 350, 350);
            setVisible(true);
            setClosable(true);
            
            ClienteMain.desktopPane.add(this);
            ClienteMain.servidor.notificarPorcentaje(10);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FrameImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrameImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
