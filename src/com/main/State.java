package com.main;

public class State {

	private Transition[] transition;
	private String name;
	private String toBlock;
	
	public State(Transition transit1, Transition transit2, String name) {
		this.transition=new Transition[2];
		this.transition[0]=transit1;
		this.transition[1]=transit2;
		this.name=name;
		this.toBlock="";
	}

	public Transition[] getTransition() {
		return transition;
	}

	public void setTransition(Transition[] transition) {
		this.transition = transition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void concatToBlock(String s){
		this.toBlock+=s;
	}
	
	public void clearToBLock(){
		this.toBlock = "";
	}
	
	public String getToBlock(){
		return this.toBlock;
	}
	
	public String getNextState(int stimulus){
		return this.transition[stimulus].getToStateName();
	}
	
	
	public int getOutput(int stimulus){
		return this.transition[stimulus].getOutput();
	}

	public String getOutputs(){
		return Integer.toString(this.transition[0].getOutput())+Integer.toString(this.transition[1].getOutput());
	}
	
	public String getTransitionString(int stimulus){
		return this.transition[stimulus].toString();
	}
	
	public String toString(){
		return name+"|"+transition[0].toString()+"|"+transition[1].toString()+"||";
	}
}
