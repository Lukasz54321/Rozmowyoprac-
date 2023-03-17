package referencje;

import java.util.concurrent.atomic.AtomicInteger;

public class ParametryInt {
	
	static void zmien(int a, Integer b, AtomicInteger c) {
		System.out.println("a = " + a +" , b = " + b + " , c = " + c);		
		a += 1;
		b += 2; // to działa jak b = b + 2, a jeszcze dosadniej: b = new Integer(b + 2)
		c.addAndGet(3);
		System.out.println("a = " + a +" , b = " + b + " , c = " + c);		
	}

	public static void main(String[] args) {
		int a = 100;
		Integer b = 200;
		AtomicInteger c = new AtomicInteger(300);
		
		System.out.println("a = " + a +" , b = " + b + " , c = " + c);		
		zmien(a, b, c);
		System.out.println("a = " + a +" , b = " + b + " , c = " + c);		
	}

}










