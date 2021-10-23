package fr.ensimag.core;

import fr.ensimag.cellular_automata.Case;

public class GridArea extends Area {
	protected Case[][] cases;
	private Case[][] buffer;
	
	protected int caseSize;
	
	public GridArea(int width, int height, int caseSize) {
		super(width, height);
		this.caseSize = caseSize;
		
		cases = new Case[width/caseSize][height/caseSize];
		buffer = new Case[width/caseSize][height/caseSize];
		
		this.restart();
		this.copyToBuffer();
	}
	
	@Override
	public void next() {
		for (Case[] caseColumns : buffer) {
			for (Case c : caseColumns) {
				c.update(this);
			}
		}
		this.copyToEntities();
	}
	
	public Entity[][] getCases() {
		return cases; // /!\ the modification is not prevented
	}
	
	private void copyToEntities() {
		// Deepcopy of all Entity in the buffer into entities
		for (int x = 0; x < width/caseSize; x++) {
			for (int y = 0; y < height/caseSize; y++) {
				cases[x][y] = buffer[x][y].copy();
			}
		}
	}
	
	private void copyToBuffer() {
		// Deepcopy of all Entity in entities into buffer
		for (int x = 0; x < width/caseSize; x++) {
			for (int y = 0; y < height/caseSize; y++) {
				buffer[x][y] = cases[x][y].copy();
			}
		}
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}
}
