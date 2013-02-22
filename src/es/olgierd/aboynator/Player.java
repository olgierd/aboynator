package es.olgierd.aboynator;

import java.util.ArrayList;

public class Player {
 
    public ArrayList<Stone> stones;
    private Board board;
    
    public Player(Board board) {
	stones = new ArrayList<Stone>();
	this.board = board;
    }
    
    public void initLeftPlayer() {
	
	stones.clear();
	Stone newStone;
	
	for(int i : Statics.leftPlayerFields) {
	    newStone = new Stone(board.getFields()[i]);
	    newStone.owner = this;
	    stones.add(newStone);
	}
	
    }
    
    public void initRightPlayer() {

	stones.clear();
	Stone newStone;
	
	for(int i : Statics.rightPlayerFields) {
	    newStone = new Stone(board.getFields()[i]);
	    newStone.owner = this;
	    stones.add(newStone);
	}	
	
    }
    
    
    
}
