import java.rmi.*;

import java.rmi.server.*;
import java.util.Scanner;
import java.io.*;

public class Client extends UnicastRemoteObject implements ClientI {

	private Bank stub = null;

	public Client() throws RemoteException, Exception {

		stub = (Bank)Naming.lookup("rmi://ip:3000/sonoo"); 
		stub.addClient(this); 

	}  

	public void printMessage() throws RemoteException {
		char answer='x';
		int amount=0;

		Scanner scan = new Scanner(System.in);

		System.out.print("\nChoose your request:");
		System.out.println("(D)eposit, (W)ithdraw, (C)heckBalance or (Q)uitThisMachine (S)topAllMachine");

		answer=scan.next().charAt(0);

		if(answer== 'D'){
			System.out.print("Deposit_Amount:");
			amount = scan.nextInt();
			stub.deposit(amount, this);
			return;

		}else if(answer == 'W') {
			System.out.print("Withdraw_Amount:");
			amount = scan.nextInt();
			stub.withdraw(amount, this);
			return;
		}else if(answer == 'C'){
			stub.checkBalance(this);

		}else if(answer == 'Q') {
			stub.disconnect(this);
		}else if(answer == 'S') {
			stub.allDisconnect();
			
		}

	}  

	public void printalarmMessage(int balance) throws RemoteException{
		System.out.println("Receiving the balance from other Machine");
		System.out.println("The received amount is "+balance+" dollars.");
		System.out.println("The current balance is "+ balance+ "dollars.\n");
		return;
	}

	public void printCheckBalance(int balance) throws RemoteException{
		System.out.println("Check Balance: "+balance);
		System.out.println("The current balance is "+balance+ "dollars.\n");
		return;
	}

	public void printSend(int balance, int machineNumber) throws RemoteException{
		System.out.println("\nThe current balance is " + balance+ " dollars."); 
		System.out.println("Sending to other "+(machineNumber-1)+ " machines the balance...\n");
		stub.alarm();
	}
	
	public void printEnd() throws RemoteException{
		System.out.println("Quit : Machine stopped.");
		return;
	}

	public void allEnd() throws RemoteException{
		System.out.println("All machines stopped!");
		return;
	}
	
	public void lackMoney() throws Exception{
		System.out.println("I do not have enough money.\n");
		return;
	}
	
	public static void main(String[] args) throws Exception {

		new Client();
		return;
	}
	
	
}
