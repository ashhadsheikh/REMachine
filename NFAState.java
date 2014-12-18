import java.util.* ;

class NFAState
{
  
    public static final int MAX_CHAR = 255 ;
    public boolean isFinal               = false ;
    int stateName;
    private ArrayList<NFAState> onChar[] = new ArrayList[MAX_CHAR] ;
    private ArrayList<NFAState> onEmpty  = new ArrayList() ;

    ArrayList<NFAState> returnLambdaEdges(){
    	return onEmpty;
    }
    ArrayList<NFAState> returnCharEdges(char c){
    	return onChar[(int)c];
    }
    void setStateName(int x){
    	stateName=x;
    }
    public void addCharEdge(char c, NFAState next) {
	onChar[(int)c].add(next) ;
    }

    public void addEmptyEdge(NFAState next) {
	onEmpty.add(next) ;
    }
    public NFAState () {
	for (int i = 0; i < onChar.length; i++)
	    onChar[i] = new ArrayList() ;
    }
    public boolean matches(String s) {
    	return matches(s,new ArrayList()) ;
        }
    private boolean matches(String s, ArrayList visited) {

	if (visited.contains(this)) 

	    return false ;
	
	visited.add(this) ;

	if (s.length() == 0) {
	    if (isFinal)
		return true ;

	    for (NFAState next : onEmpty) {
		if (next.matches("",visited))
		    return true ;
	    }
	    return false ;
	} else {
	    int c = (int)s.charAt(0) ;

	    for (NFAState next : onChar[c]) {
		if (next.matches(s.substring(1)))
		    return true ;
	    }
	    for (NFAState next : onEmpty) {
		if (next.matches(s,visited))
		    return true ;
	    }
	    return false ;
	}
	
    }
}
