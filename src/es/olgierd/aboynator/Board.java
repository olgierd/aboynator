package es.olgierd.aboynator;

import java.awt.Point;
import java.util.List;


public class Board {

    public Field[] fields;
    private int fieldSize;
    
    public void generateFieldsLocations(Point start, int distance){
	
	fieldSize = distance;
	
	int fid = 0;
	
	int howMany = 5;
	for(int i=0; i<9; i++) {
	    
	    for(int j=0; j<howMany; j++) {
		
		Field f = new Field( (distance/2)*Math.abs(4-i) + start.x + distance*j , start.y + distance*i, this);
		f.fieldID = fid;
		
		fields[fid] = f;
		fid++;
		
	    }

	    if(i<4) howMany++;
	    else howMany--;
	    
	}
	
    }
    
    Board() {
	 fields = new Field[61];
        
    }
    
    Board(Board existingBoard) {
    }

    public void generateNeighboors() {
	
	for(Field f : fields)
	    f.createNeighbors();
	
    }

    public Field[] getFields() {
	return fields;
    }

    public int[] getNeighborsForField(Field field) {
	// konwencja: [LEWY_GÓRNY, PRAWY_GÓRNY, LEWY, PRAWY, LEWY_DOLNY, PRAWY_DOLNY]
	
	int f[] = new int[6];
	
	int row = Statics.getRow(field.fieldID);
	
	int rowDiff = (row<=4) ? 5+row : 14 - row;
	
	f[0] = field.fieldID - (rowDiff);
	f[1] = f[0]+1;
	f[2] = field.fieldID-1;
	f[3] = field.fieldID+1;
	
	rowDiff = (row<=3) ? 5+row : 12 - row;	
	
	f[4] = field.fieldID + rowDiff;
	f[5] = f[4]+1;
	
	for(int x=0; x<6; x++) {
	    if(f[x]<0 || f[x] > 60) f[x] = -1;
	}
	
	// checking bounds of plane
	if(Statics.getRow(f[0]) != row-1) { f[0] = -1; }
	if(Statics.getRow(f[1]) != row-1) { f[1] = -1; }
	if(Statics.getRow(f[2]) != row)   { f[2] = -1; }
	if(Statics.getRow(f[3]) != row)   { f[3] = -1; }
	if(Statics.getRow(f[4]) != row+1) { f[4] = -1; }
	if(Statics.getRow(f[5]) != row+1) { f[5] = -1; }
	
	return f;
    }
    
    public int getFieldSize() {
	return fieldSize;
    }
    
}