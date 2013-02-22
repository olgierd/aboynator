package es.olgierd.aboynator;

import java.awt.Point;
import java.util.ArrayList;

public class Field {

    public Point location;
    public int fieldID;
    private Board board;
    public Stone stone;
    
    public Field neighbors[];
    
    Field(int x, int y, Board b) {
	location = new Point(x, y);
	board = b;

	neighbors = new Field[6];
	
    }
    
    void createNeighbors() {
	
	int tab[] = board.getNeighborsForField(this);
	
	for(int x=0; x<6; x++) {
	    if(tab[x] != -1)
		neighbors[x] = board.getFields()[tab[x]];
	    else
		neighbors[x] = null;
	    
	}
	
	for(int f : board.getNeighborsForField(this)) {
	    
	}
    }
    
    
}