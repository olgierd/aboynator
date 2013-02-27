package es.olgierd.aboynator;

import java.util.ArrayList;
import java.util.Random;

public class IdioticStrategy implements Strategy {

    private Player p;
    private AboyneGame game;
    private ArrayList<Stone> stones;
    
    public IdioticStrategy(AboyneGame ag, Player player) {
	game = ag;
	p = player;
	stones = p.stones;
    }
    
    @Override
    public void makeMove() {
	
	Random r = new Random();
	
	Stone s = stones.get(r.nextInt(stones.size()));
	
	Field fTarget;
	
	ArrayList<Field> moves = game.getAvailableMoves(s);
	
	game.move(s, moves.get(r.nextInt(moves.size())));
	
    }
    
    
    
    
    
}
