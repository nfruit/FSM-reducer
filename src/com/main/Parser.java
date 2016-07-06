package com.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public abstract class Parser {

	final static String DELIMITER=",";
	
	private static BufferedReader reader;

	public static ArrayList<State> parse(String filename) {
		ArrayList<State> states = new ArrayList<>();
		try {
			
			String tokens[];
			String line="";
			reader=new BufferedReader(new FileReader(filename));
			
			while((line=reader.readLine())!=null){
				tokens=line.split(DELIMITER);
				Transition transit1= new Transition(tokens[1].trim(), Integer.parseInt(tokens[2]));
				Transition transit2= new Transition(tokens[3].trim(), Integer.parseInt(tokens[4]));
				State s=new State(transit1,transit2,tokens[0].trim());
				states.add(s);
			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return states;
	}
}
