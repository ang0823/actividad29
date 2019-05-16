package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz RMI para el servidor
 * Declara dos métodos para registrar y quitar del cliente.
 * @author Luis Bonilla
 */
public interface IServer extends Remote {
    
    /**
     * Registra el cliente remoto para sy posterior llamada.
     * @throws RemoteException 
     * @param cliente necesaria para el paso del cliente.
     */
    public void registrarCallBackCliente(IClient cliente) throws RemoteException;
    /**
     * Quita el cliente remoto.
     * @param cliente Interfaz necesaria para el paso del cliente.
     * @throws RemoteException 
     */
    public void desregistrarForCallBack(IClient cliente) throws RemoteException;
    
    
    /**
     * Envía al cliente el avance de la descarga de imagenes.
     * @param porcentaje Porcentaje de la descarga.
     * @throws RemoteException 
     */
    public void notificarPorcentaje(int porcentaje) throws RemoteException;
}
