import java.lang.String;

import java.rmi.Remote;

import java.rmi.RemoteException;

public interface ClientI extends Remote {

	public void printMessage() throws RemoteException;
	public void printalarmMessage(int balance) throws RemoteException;
	public void printCheckBalance(int balance) throws RemoteException;
	public void printSend(int balance, int machineNumber) throws RemoteException;
	public void printEnd() throws RemoteException;
	public void allEnd() throws RemoteException;
	public void lackMoney() throws Exception;
}
