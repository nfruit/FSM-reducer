package com.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;

public class Driver {

	public static void main(String[] args) {
		ArrayList<State> a;
		String filename = "m1.csv";
		a=Parser.parse(filename);
		reduce(a);
	}

	public static void printStateTable(ArrayList<State> states){
		for(State b: states){
			System.out.println(b.toString());
		}
	}
	
	public static ArrayList<State> reduce(ArrayList<State> states){
		//partition 1 -- outputs are same
		String outputs;
		ArrayList<ArrayList<State>> partition = new ArrayList<>();
		int blockno=0;
		HashMap<String, Integer> transitionMap= new HashMap<>();
		HashMap<String, State> stateMap = new HashMap<>();
		while(!states.isEmpty()){
			outputs=states.get(0).getOutputs();
			ArrayList<State> block = new ArrayList<>();
			for(int i=0;i<states.size();i++){
				if(states.get(i).getOutputs().equals(outputs)){
					transitionMap.put(states.get(i).getName(),blockno);
					stateMap.put(states.get(i).getName(),states.get(i));
					block.add(states.remove(i));
					i--;
					
				}
			}
			if(!block.isEmpty()){
				partition.add(block);
			}
			blockno++;
		}
	
		//for printing partition 1
		int z=0;
		System.out.println("=====Partition 1=====");
		for(ArrayList<State> list: partition){
			System.out.println("----Block#"+z+"----");
			printStateTable(list);
			System.out.println("---------------");
			z++;
		}
		
		
		//partition 2 and beyond -- same block destination
		int partitionNo = 2;
		ArrayList<ArrayList<State>> newPartition = new ArrayList<>();
		HashMap<String, Integer> newTransitionMap=null;
		while(!transitionMap.equals(newTransitionMap)){ //partition level - arraylist of arraylist of states
		
			if(newTransitionMap!=null){
				partition=newPartition;
				transitionMap.clear();
				transitionMap.putAll(newTransitionMap);
			}
		newPartition = new ArrayList<>();
		newTransitionMap = new HashMap<String, Integer>();
		
		blockno=0;
			for(int i=0;i<partition.size();i++){ //outerblock level - arraylist of states
				
				ArrayList<State> block = partition.get(i);
				//step 1: labeling of states' toBlock
				for(int j=0;j<block.size();j++){ //innerblock level - states	
					block.get(j).clearToBLock();
					block.get(j).concatToBlock(Integer.toString(transitionMap.get(block.get(j).getTransition()[0].getToStateName())));
					block.get(j).concatToBlock(Integer.toString(transitionMap.get(block.get(j).getTransition()[1].getToStateName())));
				}
				
				//step 2: assign newTransitionMap value
				while(!block.isEmpty()){
					String toBlockString = block.get(0).getToBlock();
					
					for(int k=0;k<block.size();k++){
						if(toBlockString.equals(block.get(k).getToBlock())){
							newTransitionMap.put(block.remove(k).getName(), blockno);
							k--;
						}
					}
					blockno++;
				}
			}
			//System.out.println("New Transition Map:"+newTransitionMap);
			
			//step 3: create new partition
			HashMultimap<Integer, String> multiMap = Multimaps.invertFrom(Multimaps.forMap(newTransitionMap),HashMultimap.<Integer, String> create());
			
			for(Entry<Integer,Collection<String>> entry : multiMap.asMap().entrySet()){
				Iterator<String> keyList = entry.getValue().iterator();
				ArrayList<State> block = new ArrayList<>();
				while(keyList.hasNext()){
					block.add(stateMap.get(keyList.next()));
				}
				newPartition.add(block);
			}
			System.out.println("=====Partition "+partitionNo+"=====");
			for(int i=0;i<newPartition.size();i++){
				System.out.println("----Block#"+i+"----");
				printStateTable(newPartition.get(i));
				System.out.println("---------------");
			}
			partitionNo++;
			
		}
		
		System.out.println("end!");
		return states;
		
	}
	
}
