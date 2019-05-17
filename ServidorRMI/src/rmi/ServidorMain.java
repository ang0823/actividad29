package rmi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Esta clase inicia lo necesario para que el Servidor funcione con RMI.
 *
 * @author Luis Bonilla
 */
public class ServidorMain extends UnicastRemoteObject implements IServer {

    private static JTextField usuariosConectados;
    private static JTextField porcentajeT;

    private int porcentajeGuardado = 0;

    private final List<IClient> clientes;
    private final List<Image> imagen;
    private final List<Imagen> listaImagenes;
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public ServidorMain() throws RemoteException {
        super();
        
        inicializarJpa();
        
        clientes = new ArrayList<>();
        imagen = new ArrayList<>();
        listaImagenes = obtenerImagenes();

        try {
            //listaImagenes = obtenerImagenes();
            for (int i = 0; i < listaImagenes.size(); i++) {
                imagen.add(new Image(listaImagenes.get(i).getNombre(), listaImagenes.get(i).getUrl()));
            }
        } catch (Exception ex) {
            System.out.println("Error en consulta JPA: " + ex);
        }
        
        /**imagenes.add(new Image("Imagen1", "https://image.flaticon.com/icons/png/512/381/381572.png"));
        imagenes.add(new Image("Imagen2", "https://image.flaticon.com/icons/png/512/90/90603.png"));
        imagenes.add(new Image("Imagen3", "https://image.flaticon.com/icons/png/512/802/802192.png"));
        imagenes.add(new Image("Imagen4", "https://image.flaticon.com/icons/png/512/644/644617.png"));
        imagenes.add(new Image("Imagen5", "https://www.pinclipart.com/picdir/middle/50-502598_icono-cliente-png-clipart.png"));
        imagenes.add(new Image("Imagen6", "https://images.vexels.com/media/users/3/132335/isolated/preview/4af43ce1082231cba5e5aa60fbb03f2f-c--rculos-de-personal-iconos-by-vexels.png"));
        imagenes.add(new Image("Imagen7", "https://image.flaticon.com/icons/png/512/91/91059.png"));
        imagenes.add(new Image("Imagen8", "https://image.flaticon.com/icons/png/512/105/105283.png"));
        imagenes.add(new Image("Imagen9", "https://i.pinimg.com/originals/59/57/75/595775041933aeb57cee83e4934d006b.png"));
        imagenes.add(new Image("Imagen10", "https://images.vexels.com/media/users/3/151879/isolated/lists/db10b16cc41213b78786bff651b02a2b-icono-de-maleta-medico.png"));*/

        gui();
        rmi();
    }

    private void rmi() throws RemoteException {

        try {
            String direccion = (InetAddress.getLocalHost()).toString();
            int puerto = 3333;
            Registry registro = LocateRegistry.createRegistry(puerto);
            registro.bind("MiServidor", (IServer) this);
        } catch (Exception ex) {
            Logger.getLogger(ServidorMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gui() {
        JFrame frame = new JFrame("Render Client");
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        usuariosConectados = new JTextField();
        usuariosConectados.setText("");
        usuariosConectados.setLayout(null);
        usuariosConectados.setBounds(50, 50, 170, 25);
        
        porcentajeT = new JTextField();
        porcentajeT.setText("");
        porcentajeT.setLayout(null);
        porcentajeT.setBounds(50, 190, 250, 25);

        JButton iniciar = new JButton();
        iniciar.setText("Iniciar");
        iniciar.setLayout(null);
        iniciar.setBounds(50, 80, 80, 60);
        iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mandarProcesamiento();
                } catch (RemoteException ex) {
                    Logger.getLogger(ServidorMain.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        frame.add(usuariosConectados);
        frame.add(iniciar);
        frame.add(porcentajeT);

        frame.setVisible(true);
    }
    
    private List<Imagen> obtenerImagenes() {
        String query = "Imagen.findAll";
        Query q = em.createNamedQuery(query);
        List<Imagen> imagenes = q.getResultList();
        return imagenes;
    }
    
    private void inicializarJpa() {
        emf = Persistence.createEntityManagerFactory("ServidorRMIPU");
        em = emf.createEntityManager();

        em.getTransaction().begin();
    }

    /**
     * Método encargado de iniciar el programa.
     *
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     */
    public static void main(String[] args) throws RemoteException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ServidorMain();
                } catch (RemoteException ex) {
                    Logger.getLogger(ServidorMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void registrarCallBackCliente(IClient cliente) throws RemoteException {
        if (!clientes.contains(cliente)) {
            if (clientes.size() <= 10) {
                clientes.add(cliente);
                usuariosConectados.setText("Clientes : " + clientes.size());
            }
        }
    }

    private void mandarProcesamiento() throws RemoteException {
        for (IClient cliente : clientes) {
            cliente.processImages(imagen);
        }

    }

    @Override
    public void desregistrarForCallBack(IClient cliente) throws RemoteException {
        clientes.remove(cliente);
    }

    @Override
    public void notificarPorcentaje(int porcentaje) throws RemoteException {
        porcentajeGuardado += porcentaje;
        porcentajeT.setText("Descarga " + porcentajeGuardado +"%.");
    }

}
