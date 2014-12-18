import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class NFA {


	public NFAState entry;
	public NFAState exit;
	public NFA(NFAState entry, NFAState exit) {
		this.entry = entry;
		this.exit = exit;
		
	}
	public static void writeToFile(String text) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"NFA.txt"), true));
			bw.write(text);
			bw.newLine();
			bw.close();
		} catch (Exception e) {
		}
	}
	public static final NFA kleens(NFA nfa) {
		nfa.exit.addEmptyEdge(nfa.entry);
		writeToFile(""+nfa.exit.stateName+",~,"+nfa.entry.stateName);
		nfa.entry.addEmptyEdge(nfa.exit);
		writeToFile(""+nfa.entry.stateName+",~,"+nfa.exit.stateName);
		return nfa;
	}

	public static final NFA concat(NFA first, NFA second) {
		first.exit.isFinal = false;
		second.exit.isFinal = true;
		writeToFile(""+first.exit.stateName+",~,"+second.entry.stateName);
		first.exit.addEmptyEdge(second.entry);
		return new NFA(first.entry, second.exit);
	}

	public static final NFA choice(NFA choice1, NFA choice2,int stateName) {
		choice1.exit.isFinal = false;
		choice2.exit.isFinal = false;
		NFAState entry = new NFAState();
		entry.setStateName(++stateName);
		Main m=new Main();
		m.states.add(entry.stateName);
		NFAState exit = new NFAState();
		exit.setStateName(++stateName);
		m.states.add(exit.stateName);
		exit.isFinal = true;
		writeToFile(""+entry.stateName+",~,"+choice1.entry.stateName);
		writeToFile(""+entry.stateName+",~,"+choice2.entry.stateName);
		entry.addEmptyEdge(choice1.entry);
		entry.addEmptyEdge(choice2.entry);
		writeToFile(""+choice1.exit.stateName+",~,"+exit.stateName);
		writeToFile(""+choice2.exit.stateName+",~,"+exit.stateName);
		choice1.exit.addEmptyEdge(exit);
		choice2.exit.addEmptyEdge(exit);
		return new NFA(entry, exit);
	}

	public boolean matches(String str) {
		return entry.matches(str);
	}

	
}
