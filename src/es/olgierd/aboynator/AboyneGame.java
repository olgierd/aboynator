package es.olgierd.aboynator;

import java.awt.Point;
import java.util.ArrayList;


public class AboyneGame {

    public Player playerLeft, playerRight;
    public Board board;
    
    public AboyneGame() {
	
	board = new Board();
	board.generateFieldsLocations(new Point(40, 40), 40);
	board.generateNeighboors();
	
	playerLeft = new Player(board);
	playerRight = new Player(board);
	playerLeft.initLeftPlayer();
	playerRight.initRightPlayer();

    }
    
    public void move(Stone s, Field f) {
	s.field.stone = null;
	s.field = f;
	s.field.stone = s;
	
	
	for(int x=0; x<6; x++) {

	    if(s.field.neighbors[x] != null) {
		if(s.field.neighbors[x].stone != null && s.field.neighbors[x].stone.owner != s.owner) {
		    s.field.neighbors[x].stone.lock();
		    s.lock();
		}
		
	    }
	    
	}
	
	
    }
    	
    public ArrayList<Field> getAvailableMoves(Stone s) {
	
	ArrayList<Field> availableMoves = new ArrayList<Field>();

	Field nextf;

	for (int x = 0; x < 6; x++) {
	    
	    if (s.field.neighbors[x] != null && s.field.neighbors[x].stone == null) {
		availableMoves.add(s.field.neighbors[x]);
	    } 
	    
	    else if (s.field.neighbors[x] != null) {
		nextf = s.field.neighbors[x];
		while (true) {
		    if (nextf == null)
			break;
		    if (nextf.stone == null || nextf.stone.owner != s.owner) {
			availableMoves.add(nextf);
			break;
		    }
		    if (nextf.neighbors[x] != null)
			nextf = nextf.neighbors[x];
		    else
			break;
		}
	    }
	}
	
	return availableMoves;
	
    }

}
