package es.olgierd.aboynator;

public class Statics {

    public static int leftPlayerFields[] = {0, 5, 11, 18, 27, 35, 43, 50, 56 };
    public static int rightPlayerFields[] = {4, 10, 17, 25, 33, 42, 49, 55, 60};    
    
    public static int leftLimits[] = { 0, 5, 11, 18, 26, 35, 43, 50, 56 };    

    
    public static int getRow(int fieldID) {
	
	int x = leftLimits.length;

	while(x > 0) {
	    x--;
	    if(fieldID >= leftLimits[x]) break;
	}

	
	return x;
    }
    
    
    
}
