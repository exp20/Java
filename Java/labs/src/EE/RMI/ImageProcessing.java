package EE.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ImageProcessing extends Remote {
    byte[] ImgProcess(byte[] byteImg) throws RemoteException;
}
