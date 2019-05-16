package rmi;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Esta clase inicia lo necesario para que el Cliente funcione con RMI.
 * @author Luis Bonilla
 */
public class ClienteMain extends UnicastRemoteObject {
    
    private static final int PORT = 3333;
    private static final String NOMBRE_SERVICIO = "MiServidor";
    private static final String HOSTNAME = "localhost";
    
    static JDesktopPane desktopPane;
    static IServer servidor;
    private CallBackClient callBackClient;  
    
    public ClienteMain() throws RemoteException {
        gui();
        rmi();
    }
    
    private void rmi() {
        try {
            callBackClient = new CallBackClient();
            Registry registro = LocateRegistry.getRegistry(HOSTNAME, PORT);
            servidor = (IServer) registro.lookup(NOMBRE_SERVICIO);
            servidor.registrarCallBackCliente(callBackClient);
        } catch (Exception ex) {
            Logger.getLogger(ClienteMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void gui() {
        JFrame frame = new JFrame("Render Client");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        desktopPane = new JDesktopPane();
        
        frame.setContentPane(desktopPane);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(desktopPane, 
                        "¿Salir de la aplicación?");
                if ( i == 0 ) {
                    try {
                        servidor.desregistrarForCallBack(callBackClient);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ClienteMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ClienteMain();
                } catch (RemoteException ex) {
                    Logger.getLogger(ClienteMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        });
    }   
    
}
