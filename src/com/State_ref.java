/*
package com;

import java.util.Collection;
import java.util.HashMap;

public interface State_ref {

	public String getName();
	public void setName(String name);
	
	public State_ref getState(String stimulus);
	public void setState(String stimulus, State_ref state);
	
	public String getValue(String stimulus);
	public void setValue(String stimulus, String value);

	public Collection<String> getStimulusSet();
	public Collection<State_ref> getStateSet();
	public Collection<String> getValueSet();
	
	public static void main(String args[]) {
		HashMap<String, Integer> a = new HashMap<>();
		a.put("A", 1);
		a.put("B", 3);
		a.put("C", 4);
		
		State_ref b =null;
		int i = 0;
	//	for(State q : Q<-set of states)
		for(State_ref s : b.getStateSet()) {
			i = i * 10 + a.get(s.getName());
		}
		System.err.println(a.get("B"));
	}
}
*/