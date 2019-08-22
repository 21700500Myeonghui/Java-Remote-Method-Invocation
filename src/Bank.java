import java.lang.String;

import java.rmi.Remote;

import java.rmi.RemoteException;

public interface Bank extends Remote {
	public void withdraw (int amount, ClientI client) throws RemoteException;

	public void deposit(int amount, ClientI client) throws RemoteException;

	public void checkBalance(ClientI client) throws RemoteException;

	public void addClient(ClientI client) throws RemoteException;

	public void disconnect(ClientI client) throws RemoteException;

	public void printList() throws RemoteException;

	public void alarm() throws RemoteException;
	  
	public void allDisconnect() throws RemoteException;


}
