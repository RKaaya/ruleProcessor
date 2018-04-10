package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.processCentral.ProcessCentral;

public class MainFromFile {
	private final static Logger LOGGER = Logger.getLogger(MainFromFile.class.getName());
	
	public static void main(String[] args) {
			ProcessCentral pc = new ProcessCentral();
	        File file = new File(".\\test\\case7.txt");

	        try {
	            Scanner sc = new Scanner(file);

	            while (sc.hasNextLine()) {
	            	try{
	            		pc.addRule(sc.nextLine());
	            	} catch(Exception e){
	            		LOGGER.log(Level.INFO, e.getMessage());
	            	}
	            }
	            sc.close();
	            pc.process();
	        } 
	        catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	}
}
