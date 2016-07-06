package com.main;

public class Transition {

	private String toStateName;
	private int output;
	
	public Transition(String toStateName, int output) {
		this.toStateName=toStateName;
		this.output=output;
	}

	public String getToStateName() {
		return toStateName;
	}

	public void setToState(String toStateName) {
		this.toStateName = toStateName;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	public String toString(){
		return toStateName+Integer.toString(output);
	}

}
