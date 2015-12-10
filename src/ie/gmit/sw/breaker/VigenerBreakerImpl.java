package ie.gmit.sw.breaker;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class VigenerBreakerImpl extends UnicastRemoteObject implements VigenereBreaker {

	private static final long serialVersionUID = 1L;
	private KeyEnumerator breaker;
	
	public VigenerBreakerImpl() throws Exception{
		breaker = new KeyEnumerator();
		//UnicastRemoteObject.exportObject(this);
	}
	@Override
	public String decrypt(String cypherText, int maxKeylength) throws RemoteException {
		return breaker.crackCypher(cypherText, maxKeylength);
	}
	
	public static void main(String[] args) throws MalformedURLException, AlreadyBoundException, Exception {
		System.out.println("starting remote service");
		
		LocateRegistry.createRegistry(1099);
		Naming.bind("cypher-service", new VigenerBreakerImpl());
		
		System.out.println("service started...");
	}
}
