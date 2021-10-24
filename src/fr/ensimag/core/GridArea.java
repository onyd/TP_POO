package fr.ensimag.core;

import fr.ensimag.cellular_automata.Case;

public class GridArea extends Area {
	protected Case[][] cases;
	
	protected int caseSize;
	
	public GridArea(int width, int height, int caseSize) {
		super(width, height);
		this.caseSize = caseSize;
		
		cases = new Case[width/caseSize][height/caseSize];
		
		this.restart();
	}
	
	@Override
	public void next() {
		for (Case[] caseColumns : cases) {
			for (Case c : caseColumns) {
				c.update(this);
			}
		}
		for (Case[] caseColumns : cases) {
			for (Case c : caseColumns) {
				c.updateState();
			}
		}
	}
	
	public Entity[][] getCases() {
		return cases; // /!\ the modification is not prevented
	}
	
	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}
}
