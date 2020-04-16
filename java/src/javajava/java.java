package javajava;

import java.util.Scanner;

public class java {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num1 = sc.nextInt();	//472
		int num2 = sc.nextInt();	//385
		
		int num3 = num2 % 10;
		int num4 = (num2 / 10) % 10;
		int num5 = num2 / 100;
		
		System.out.println(num3);
		System.out.println(num4);
		System.out.println(num5);
		
		System.out.println(num1*num2);
	}
	
	
	
	

}
