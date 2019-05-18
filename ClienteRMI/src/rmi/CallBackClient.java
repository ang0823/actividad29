package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * 
 * @author Luis Aduana
 */
public class CallBackClient extends UnicastRemoteObject implements IClient {

    public CallBackClient() throws RemoteException {
        super();
    }

    @Override
    public void processImages(Image image) throws RemoteException {
        int i = 1;
        
            new Thread(new FrameImage(image)).start();
        
    }
    
}
