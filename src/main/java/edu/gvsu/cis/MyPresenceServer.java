package edu.gvsu.cis;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

/**
 * <p>Title: Lab2</p>
 * <p>Description: Old School Instant Messaging Application </p>
 * @author Jonathan Engelsma
 * @version 1.0
 */

import org.zeromq.ZMQ;

public class MyPresenceServer implements PresenceService {
	
	Hashtable<String,RegistrationInfo> regData;
	
	StringBuilder msg = null;

	public MyPresenceServer() {
		super();
		this.regData = new Hashtable<String,RegistrationInfo>();
		
	
	}

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
		    // Have the server start the RMI Registry
            LocateRegistry.createRegistry(1099);

            // create the presence service object and bind in RMI.
			String name = "PresenceService";
			PresenceService engine = new MyPresenceServer();
			PresenceService stub =
				(PresenceService) UnicastRemoteObject.exportObject(engine, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(name, stub);
			System.out.println("PresenceServiceEngine bound");
			Object o = new Object();
			synchronized (o) {
				o.wait();
			}
		} catch (Exception e) {
			System.err.println("PresenceServiceEngine exception:");
			e.printStackTrace();
		}
	}

	public Vector<RegistrationInfo> listRegisteredUsers() throws RemoteException {
		System.out.println("in listRegisteredUsers");
		Set<String> keys = this.regData.keySet();
		Vector<RegistrationInfo> retVal = new Vector<RegistrationInfo>();
		for(String key: keys) {
			retVal.add(this.regData.get(key));
		}
		return retVal;
	}

	public RegistrationInfo lookup(String name) throws RemoteException {
		System.out.println("Lookup");
		RegistrationInfo entry = this.regData.get(name);
		return entry;
	}

	public boolean register(RegistrationInfo reg) throws RemoteException {
		if(!this.regData.containsKey(reg.getUserName())) {
			this.regData.put(reg.getUserName(), reg);
			return true;
		} else {
			return false;
		}
	} 

	public void unregister(String userName) throws RemoteException {
		this.regData.remove(userName);
	}

	public boolean updateRegistrationInfo(RegistrationInfo reg)
	throws RemoteException {
		if(this.regData.get(reg.getUserName()) != null) {
			this.regData.put(reg.getUserName(), reg);
			return true;
		} else {
			return false;
		}
		
	}
	public void broadcast(String msg) throws RemoteException{
		System.out.println("in broadcast");
		
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket publisherSocket=context.socket(ZMQ.PUB);	
		try {
			String myHost = InetAddress.getLocalHost().getHostName();
			String p = "tcp://"+myHost+":"+"1001";
			publisherSocket.bind(p);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		publisherSocket.send(" "+msg);
		publisherSocket.close();
		context.term();
		
		
		
		
	}
}

	

