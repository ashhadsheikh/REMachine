import java.util.ArrayList;

class DFAState
{
  
    public static final int MAX_CHAR = 255 ;
    public boolean isFinal               = false ;
    int stateName;
   ArrayList<NFAState> states = new ArrayList() ;
    private ArrayList<DFAState> onChar[] = new ArrayList[MAX_CHAR] ;


 
    ArrayList<DFAState> returnCharEdges(char c){
    	return onChar[(int)c];
    }
    void setStateName(int x){
    	stateName=x;
    }
    public void addCharEdge(char c, DFAState next) {
	onChar[(int)c].add(next) ;
    }

    public DFAState () {
	for (int i = 0; i < onChar.length; i++)
	    onChar[i] = new ArrayList() ;
    }
  
    	
    
}
