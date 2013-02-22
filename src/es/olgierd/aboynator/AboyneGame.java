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
	
	s.field.stone = null;	// usun informacje o kamieniu z pola na ktorym stal
	
	// jezeli na nowym polu stal jakis kamien, to usuwamy go
	if(f.stone != null) {
	    f.stone.owner.stones.remove(f.stone);
	}

	for(int x=0; x<6; x++) {

	    // jezeli sasiednie pole jest niepuste
	    if(f.neighbors[x] != null) {
		
		// i jest to kamien przeciwnika
		if(f.neighbors[x].stone != null && f.neighbors[x].stone.owner != s.owner) {
		    
		    // zablokuj sasiednie kamienie 
		    f.neighbors[x].stone.lock();
		    s.lock();
		    
		}
		
	    }
	    
	}
	
	s.field = f;		// ustaw kamieniowi nowe pole 
	s.field.stone = s;	// ustaw polu nowy kamien
	
	markBlocked(playerLeft);
	markBlocked(playerRight);
	
	if(s.owner == playerLeft) {
	    
	    // jezeli ruszyl sie lewy gracz, to tutaj mozemy wyslac do AI zadanie wykonania ruchu
	    
	}
	
    }

    public void markBlocked(Player player) {
	
	for(Stone s : player.stones) {
	    
	    s.unlock();
	    
	    for(Field f : s.field.neighbors) {
		if(f != null && f.stone != null && f.stone.owner != s.owner) {
		    f.stone.lock();
		    s.lock();
		}
	    }
	}
	
    }
    
    public ArrayList<Field> getAvailableMoves(Stone s) {
	
	ArrayList<Field> availableMoves = new ArrayList<Field>();

	Field nextf;

	for (int x = 0; x < 6; x++) {
	    
	    
	    // puste pole
	    if (s.field.neighbors[x] != null && s.field.neighbors[x].stone == null) {
		availableMoves.add(s.field.neighbors[x]);
	    } 
	    
	    // pole niepuste
	    else if (s.field.neighbors[x] != null) {
		
		nextf = s.field.neighbors[x];	// nastepne pole, poruszajac sie w linii prostej
		
		while (true) {
		    if (nextf == null)		// kraniec planszy
			break;
		    if (nextf.stone == null || nextf.stone.owner != s.owner) {		// puste pole lub kamien przeciwnika
			availableMoves.add(nextf);
			break;
		    }
		    if (nextf.neighbors[x] != null)	// nasz kamien - przeskakujemy go
			nextf = nextf.neighbors[x];
		    else
			break;
		}
	    }
	}
	
	return availableMoves;
	
    }

}
