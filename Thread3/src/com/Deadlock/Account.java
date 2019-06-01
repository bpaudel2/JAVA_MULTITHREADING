package com.Deadlock;

public class Account {
	private int balance = 10000;
	
	private void deposit(int amount) {
		balance += amount;
	}
	
	private void withdraw(int amount) {
		balance -= amount;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public static void transfer(Account acc1, Account acc2, int amount) {
		acc1.withdraw(amount);
		acc2.deposit(amount);
	}
	
}
