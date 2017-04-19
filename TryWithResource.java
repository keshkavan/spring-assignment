package com.nymisha.main;
import java.io.*;
public class Trywith {
	
	
	public static void main(String[] args)
	{
	try{
	String str;
	//opening file in read mode using BufferedReader stream
	BufferedReader br=new BufferedReader(new FileReader("d:\\myfile.txt"));   
	while((str=br.readLine())!=null)
	 {
	System.out.println(str);
	 }      
	br.close();	//closing BufferedReader stream
	}catch(IOException ie)
	{  System.out.println("exception");  }
	 }
	}
