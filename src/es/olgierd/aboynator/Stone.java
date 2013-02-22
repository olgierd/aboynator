package es.olgierd.aboynator;

public class Stone {


    public Field field;
    public Player owner;
    private boolean locked;
    
    public void lock() {
	this.locked = true;
    }

    public void unlock() {
	this.locked = false;
    }
    
    public boolean isLocked() {
	return this.locked;
    }

    
    public Stone(Field field) {
	this.field = field;
	field.stone = this;
	
	locked = false;
    }
    
    
    
}
