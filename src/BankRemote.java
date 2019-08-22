import java.rmi.*;

import java.rmi.server.*;

import java.util.*;

public class BankRemote extends UnicastRemoteObject implements Bank {
	private int money;
	private int selectNumber;

	private Vector<ClientI> clientList = new Vector();

	public BankRemote() throws RemoteException{
		super();
		this.money = 0;
	}

	public void deposit(int amount, ClientI client ) throws RemoteException {
		money += amount;
		int i = clientList.indexOf(client);
		((ClientI)clientList.elementAt(i)).printSend(money, clientList.size());
		return;
	} 

	public void withdraw(int amount, ClientI client) throws RemoteException {
		if(amount <= money) {
			money -= amount;
		}else {
			try {
				client.lackMoney();
				printList();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		int i = clientList.indexOf(client);
		((ClientI)clientList.elementAt(i)).printSend(money,clientList.size());
		return;
	}

	public void checkBalance(ClientI client) throws RemoteException{
		int i = clientList.indexOf(client);
		((ClientI)clientList.elementAt(i)).printCheckBalance(money);
		printList();
	}


	public void addClient(ClientI client) throws RemoteException{

		clientList.addElement(client);

		if(clientList.size()==3) {
			printList();
		}


	}

	public void disconnect(ClientI client) throws RemoteException{
		int size = clientList.size();
		int i = clientList.indexOf(client);
		clientList.removeElementAt(i);
		client.printEnd();
		printList();

	}
	public void allDisconnect() throws RemoteException{
		
		int size = clientList.size();
		for(int i = 0; i < size; i++){
				((ClientI)clientList.elementAt(i)).allEnd();

		}
		clientList.clear();

	}

	public void printList() throws RemoteException {

		double randomValue = Math.random();
		int size = clientList.size();
		
		do {
			selectNumber = (int)(randomValue * size);
			if(clientList.elementAt(selectNumber)!=null) {
				((ClientI)clientList.elementAt(selectNumber)).printMessage();
				break;
			}
		
		}while(true);


	}

	public void alarm() throws RemoteException{
		int size = clientList.size();

		for(int i = 0; i < size; i++){
			if((i!=selectNumber) && (clientList.elementAt(i)!=null ))
				
				((ClientI)clientList.elementAt(i)).printalarmMessage(money);

		}
		printList();

	}

	public static void main(String[] args){

		try{ 
			BankRemote stub = new BankRemote();         

			Naming.rebind("rmi://ip:3000/sonoo", stub);


		} catch (Exception e){

			System.out.println(e.getMessage());

			e.printStackTrace();

		}

	}
}
