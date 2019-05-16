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
    public void processImages(List<Image> images) throws RemoteException {
        int i = 1;
        for (Image img : images) {
            new Thread(new FrameImage(img, i++)).start();
        }
    }
    
}
