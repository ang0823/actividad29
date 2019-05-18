package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaz RMI para ser implementada por el cliente.
 * @author Luis Bonilla
 */
public interface IClient extends Remote {
    
    /**
     * Procesa las imágenes, esta función puede ser llamada por el Servidor.
     * @param images La imagen en parámetro.
     * @throws RemoteException  Exception causada por RMI.
     */
    public void processImages(Image image) throws RemoteException;
}
