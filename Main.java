import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Main {
	static int n = 0;
	static int dfaStateName = 0;
	static ArrayList edges = new ArrayList();
	static ArrayList states = new ArrayList();
	static ArrayList<DFAState> DFAstates = new ArrayList<DFAState>();

	public static void writeTerminals(ArrayList ex, int k) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"NFA.txt"), true));
			if (k == 1)
				bw.write("Edges={");
			else
				bw.write("States={");
			for (int i = 0; i < ex.size(); i++) {
				bw.write("" + ex.get(i));
				if (i != ex.size() - 1)
					bw.write(",");
			}
			bw.write("}");
			bw.newLine();
			bw.close();
		} catch (Exception e) {
		}
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
	public static void writeempty() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"DFA.txt")));
			bw.write("");
			bw.close();
		} catch (Exception e) {
		}
	}
	public static void writeToDFAFile(DFAState dfa1, char a, DFAState dfa2) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"DFA.txt"), true));
			bw.write("[");
			for (int i = 0; i < dfa1.states.size(); i++) {
				bw.write(dfa1.states.get(i).stateName + "");
				if (i != dfa1.states.size() - 1)
					bw.write(",");
			}
			bw.write("],");
			bw.write("  " +  a + "  ");
			bw.write(",[");
			for (int i = 0; i < dfa2.states.size(); i++) {
				bw.write(dfa2.states.get(i).stateName + "");
				if (i != dfa2.states.size() - 1)
					bw.write(",");
			}
			bw.write("]");
			bw.newLine();
			bw.close();
		} catch (Exception e) {
		}
	}

	public static void emptyFile() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"NFA.txt")));
			bw.write("");
			bw.close();
		} catch (Exception e) {
		}
	}

	public static void writestate(String text, int j) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					"NFA.txt"), true));
			if (j == 0)
				bw.write("Start={");
			else
				bw.write("Final={");
			bw.write(text);
			bw.write("}");
			bw.newLine();
			bw.close();
		} catch (Exception e) {
		}
	}

	public static NFA buildNFA(String s) throws IOException {
		emptyFile();
		infix2postfix testobj = new infix2postfix();

		String postfix = testobj.postfix(s);
		System.out.println("Postfix Expression: " + postfix);
		char p[] = postfix.toCharArray();

		Stack<NFA> stack = new Stack<NFA>();
		NFA nfa = null;
		for (int i = 0; i < postfix.length(); i++) {
			if (p[i] != '|' && p[i] != '+' && p[i] != '*') {
				NFAState state1 = new NFAState();
				state1.setStateName(++n);
				states.add(state1.stateName);
				NFAState state2 = new NFAState();
				state2.setStateName(++n);
				states.add(state2.stateName);
				state2.isFinal = true;
				state1.addCharEdge(p[i], state2);
				if (!edges.contains(p[i]))
					edges.add(p[i]);
				writeToFile("" + state1.stateName + "," + p[i] + ","
						+ state2.stateName);
				nfa = new NFA(state1, state2);
				stack.push(nfa);
			} else {

				switch (p[i]) {
				case '|':
					NFA nfa1 = (NFA) stack.pop();
					NFA nfa2 = (NFA) stack.pop();
					nfa = NFA.concat(nfa2, nfa1);
					stack.push(nfa);
					break;
				case '+':
					NFA nfa3 = (NFA) stack.pop();
					NFA nfa4 = (NFA) stack.pop();
					nfa = NFA.choice(nfa4, nfa3, n);
					n = nfa.exit.stateName;
					stack.push(nfa);
					break;
				case '*':
					NFA nfa5 = (NFA) stack.pop();
					nfa = NFA.kleens(nfa5);
					stack.push(nfa);
					break;
				}
			}
		}
		writeTerminals(edges, 1);
		writeTerminals(states, 0);
		writestate("" + nfa.entry.stateName, 0);
		writestate("" + nfa.exit.stateName, 1);
		return nfa;
	}

	static void parseFile(NFA nfa) throws IOException {
		File file = new File("TestFile.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (nfa.matches(line))
				System.out.println(line);
		}
		fileReader.close();

	}

	public static <T> void removeDuplicates(ArrayList<T> list) {
		int size = list.size();
		int out = 0;
		{
			final Set<T> encountered = new HashSet<T>();
			for (int in = 0; in < size; in++) {
				final T t = list.get(in);
				final boolean first = encountered.add(t);
				if (first) {
					list.set(out++, t);
				}
			}
		}
		while (out < size) {
			list.remove(--size);
		}
	}

	public static void nfatodfa(NFA nfa ) throws IOException {
		// TODO Auto-generated method stub
		// parseFile(nfa);
		writeempty();
		ArrayList<NFAState> A = nfa.entry.returnLambdaEdges();
		DFAState dfa = new DFAState();
		dfa.stateName = ++dfaStateName;
		dfa.states = A;
		DFAstates.add(dfa);
		DFAState dfa2 = new DFAState();
		ArrayList<DFAState> temp = new ArrayList<DFAState>();
		temp.addAll(DFAstates);
		// ////////////////////////////
	for(int ch=0;ch<3;ch++) {
			DFAState d = (DFAState) DFAstates.get(0);
			for (int iterator = 0; iterator < edges.size(); iterator++) {
				ArrayList<NFAState> B = new ArrayList<NFAState>();
				ArrayList<NFAState> a = new ArrayList<NFAState>();
				for (int i = 0; i < d.states.size(); i++) {
					a = d.states.get(i).returnCharEdges(
							(char) edges.get(iterator));
					B.addAll(a);
				}
				int n = 0;
				while (n < states.size()) {
					ArrayList<NFAState> K = new ArrayList<NFAState>();
					K.addAll(B);
					ArrayList<NFAState> c = new ArrayList<NFAState>();
					for (int i = 0; i < B.size(); i++) {
						c = B.get(i).returnLambdaEdges();
						for (int j = 0; j < c.size(); j++) {
							K.add(c.get(j));
						}
					}
					B = K;
					n++;
				}
				removeDuplicates(B);
				
				dfa2 = new DFAState();
				dfa2.stateName = ++dfaStateName;
				dfa2.states = B;
				writeToDFAFile(d, (char) edges.get(iterator), dfa2);
				if(!DFAstates.contains(dfa2))
				DFAstates.add(dfa2);
				
				// /////////////////////////////////////////////////////////////
				
			}
			DFAstates.remove(d);
		}
	
	}
}
