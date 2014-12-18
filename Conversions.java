import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextArea;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Conversions {

	JFrame frmConvertRegularExpression;
	private JTextField textField;
	private JTextField nfaStart;
	private JTextField nfaFinal;
	private JTextField dfaStart;
	private JTextField dfaFinal;
	private JTextField dfamStart;
	private JTextField dfamFinal;
	JTextArea nfaEdges,nfaStates,nfaTransitions,dfaTransitions;
	static ArrayList<Transition> transitions = new ArrayList<Transition>();
Main m=new Main();
	/**
	 * Launch the application.
	 */
static void readNFA() throws IOException{
	// Open the file
	FileInputStream fstream = new FileInputStream("NFA.txt");
	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

	String strLine;

	//Read File Line By Line
	while ((strLine = br.readLine()) != null)   {
	  // Print the content on the console
		if(!strLine.startsWith("E") && !strLine.startsWith("S")&& !strLine.startsWith("F")  )
	  {
			String[] tokens = strLine.split(",");
			Transition t=new Transition();
				t.state1=tokens[0];
				t.edge=tokens[1];
				t.state2=tokens[2];
				transitions.add(t);
			
		}
	}

	//Close the input stream
	br.close();
}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversions window = new Conversions();
					window.frmConvertRegularExpression.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Conversions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frmConvertRegularExpression = new JFrame();
		frmConvertRegularExpression.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmConvertRegularExpression.setTitle("Convert Regular Expression");
		frmConvertRegularExpression.setBounds(100, 100, 729, 600);
		frmConvertRegularExpression.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConvertRegularExpression.getContentPane().setLayout(null);
		
		JLabel lblRegularExpression = new JLabel("Regular Expression");
		lblRegularExpression.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblRegularExpression.setForeground(Color.RED);
		lblRegularExpression.setBackground(Color.LIGHT_GRAY);
		lblRegularExpression.setBounds(10, 22, 141, 14);
		frmConvertRegularExpression.getContentPane().add(lblRegularExpression);
		JTextArea dfaEdges = new JTextArea();
		textField = new JTextField();
		textField.setBounds(145, 19, 397, 20);
		frmConvertRegularExpression.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Convert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NFA nfa;
				try {
					nfa = m.buildNFA(textField.getText());
					Main m=new Main();
					m.nfatodfa(nfa);
					try {
						readNFA();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					nfaStart.setText(nfa.entry.stateName+"");
					nfaFinal.setText(nfa.exit.stateName+"");
					for (int i = 0; i < m.edges.size(); i++) {
						nfaEdges.append(""+m.edges.get(i));
						dfaEdges.append(""+m.edges.get(i));
						if(i!=m.edges.size()-1){
							nfaEdges.append(",");
							dfaEdges.append(",");
						}
					}
					for (int i = 0; i < m.states.size(); i++) {
						nfaStates.append(""+m.states.get(i));
						if(i!=m.states.size()-1)
							nfaStates.append(",");
					}
					for (int i = 0; i < transitions.size(); i++) {
						nfaTransitions.append(transitions.get(i).state1+","+transitions.get(i).edge+","+transitions.get(i).state2+"\n");
					}
					FileInputStream fstream = new FileInputStream("DFA.txt");
					BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

					String strLine;

					//Read File Line By Line
					while ((strLine = br.readLine()) != null)   {
					  // Print the content on the console
						dfaTransitions.append(strLine+"\n");
					}

					//Close the input stream
					br.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(552, 18, 151, 23);
		frmConvertRegularExpression.getContentPane().add(btnNewButton);
		
		JLabel lblNfa = new JLabel("NFA");
		lblNfa.setForeground(Color.RED);
		lblNfa.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNfa.setBounds(10, 58, 46, 14);
		frmConvertRegularExpression.getContentPane().add(lblNfa);
		
		JLabel lblStartState = new JLabel("Start State");
		lblStartState.setBounds(10, 90, 64, 14);
		frmConvertRegularExpression.getContentPane().add(lblStartState);
		
		nfaStart = new JTextField();
		nfaStart.setBounds(84, 87, 119, 20);
		frmConvertRegularExpression.getContentPane().add(nfaStart);
		nfaStart.setColumns(10);
		
		JLabel lblFinalState = new JLabel("Final State");
		lblFinalState.setBounds(10, 127, 64, 14);
		frmConvertRegularExpression.getContentPane().add(lblFinalState);
		
		nfaFinal = new JTextField();
		nfaFinal.setBounds(84, 124, 119, 20);
		frmConvertRegularExpression.getContentPane().add(nfaFinal);
		nfaFinal.setColumns(10);
		
		JLabel lblStates = new JLabel("States");
		lblStates.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblStates.setBounds(232, 59, 46, 23);
		frmConvertRegularExpression.getContentPane().add(lblStates);
		frmConvertRegularExpression.setResizable(false);
		nfaStates = new JTextArea();
		
		nfaStates.setEditable(false);
		JScrollPane  scroll2 = new JScrollPane(nfaStates);
		scroll2.setBounds(230, 85, 95, 60);
		frmConvertRegularExpression.getContentPane().add(scroll2);
		
		nfaEdges = new JTextArea();
		nfaEdges.setEditable(false);
		JScrollPane  scroll3 = new JScrollPane(nfaEdges);
		scroll3.setBounds(360, 85, 95, 60);
		frmConvertRegularExpression.getContentPane().add(scroll3);
		
		JLabel lblEdges = new JLabel("Edges");
		lblEdges.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblEdges.setBounds(358, 59, 46, 23);
		frmConvertRegularExpression.getContentPane().add(lblEdges);
		 // set textArea non-editable
		 nfaTransitions = new JTextArea();
		 nfaTransitions.setEditable(false);
		
		JScrollPane  scroll = new JScrollPane(nfaTransitions);
		scroll.setBounds(465, 85, 238, 60);
		frmConvertRegularExpression.getContentPane().add(scroll);
		
		JLabel lblTransition = new JLabel("Transitions");
		lblTransition.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblTransition.setBounds(465, 59, 95, 23);
		frmConvertRegularExpression.getContentPane().add(lblTransition);
		
		 dfaTransitions = new JTextArea();
		dfaTransitions.setEditable(false);
			
			JScrollPane  scroll4 = new JScrollPane(dfaTransitions);
			scroll4.setBounds(465, 201, 238, 60);
			frmConvertRegularExpression.getContentPane().add(scroll4);
		
		
		JLabel label = new JLabel("Transitions");
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		label.setBounds(465, 175, 95, 23);
		frmConvertRegularExpression.getContentPane().add(label);
		
		
		dfaEdges.setEditable(false);
		
		JScrollPane  scroll5 = new JScrollPane( dfaEdges);
		scroll5.setBounds(360, 201, 95, 60);
		frmConvertRegularExpression.getContentPane().add(scroll5);
		
		
		JLabel label_1 = new JLabel("Edges");
		label_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		label_1.setBounds(358, 175, 46, 23);
		frmConvertRegularExpression.getContentPane().add(label_1);
				
		JLabel label_2 = new JLabel("States");
		label_2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		label_2.setBounds(232, 175, 46, 23);
		frmConvertRegularExpression.getContentPane().add(label_2);
		
		JTextArea dfaStates = new JTextArea();
		 //dfaStates.setEditable(false);
		JScrollPane  scroll6 = new JScrollPane(  dfaStates);
		scroll6.setBounds(230, 201, 95, 60);
		frmConvertRegularExpression.getContentPane().add(scroll6);
		
		
		dfaStart = new JTextField();
		dfaStart.setColumns(10);
		dfaStart.setBounds(84, 203, 119, 20);
		frmConvertRegularExpression.getContentPane().add(dfaStart);
		
		dfaFinal = new JTextField();
		dfaFinal.setColumns(10);
		dfaFinal.setBounds(84, 240, 119, 20);
		frmConvertRegularExpression.getContentPane().add(dfaFinal);
		
		JLabel label_3 = new JLabel("Final State");
		label_3.setBounds(10, 243, 64, 14);
		frmConvertRegularExpression.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Start State");
		label_4.setBounds(10, 206, 64, 14);
		frmConvertRegularExpression.getContentPane().add(label_4);
		
		JLabel lblDfa = new JLabel("DFA");
		lblDfa.setForeground(Color.RED);
		lblDfa.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblDfa.setBounds(10, 174, 46, 14);
		frmConvertRegularExpression.getContentPane().add(lblDfa);
		
		JTextArea dfamTransitions = new JTextArea();
		dfamTransitions.setEditable(false);
		JScrollPane  scroll7 = new JScrollPane(  dfamTransitions);
		scroll7.setBounds(465, 305, 238, 60);
		frmConvertRegularExpression.getContentPane().add(scroll7);
		
		JLabel label_5 = new JLabel("Transitions");
		label_5.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		label_5.setBounds(465, 279, 95, 23);
		frmConvertRegularExpression.getContentPane().add(label_5);
		
		JTextArea dfamEdges = new JTextArea();
		dfamEdges.setEditable(false);
		JScrollPane  scroll8 = new JScrollPane(  dfamEdges);
		scroll8.setBounds(360, 305, 95, 60);
		frmConvertRegularExpression.getContentPane().add(scroll8);
		
		JLabel label_6 = new JLabel("Edges");
		label_6.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		label_6.setBounds(358, 279, 46, 23);
		frmConvertRegularExpression.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("States");
		label_7.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		label_7.setBounds(232, 279, 46, 23);
		frmConvertRegularExpression.getContentPane().add(label_7);
		
		JTextArea dfamStates = new JTextArea();
		dfamStates.setEditable(false);
		JScrollPane  scroll10 = new JScrollPane(  dfamStates);
		scroll10.setBounds(230, 305, 95, 60);
		frmConvertRegularExpression.getContentPane().add(scroll10);
		
		
		dfamStart = new JTextField();
		dfamStart.setColumns(10);
		dfamStart.setBounds(84, 307, 119, 20);
		frmConvertRegularExpression.getContentPane().add(dfamStart);
		
		dfamFinal = new JTextField();
		dfamFinal.setColumns(10);
		dfamFinal.setBounds(84, 344, 119, 20);
		frmConvertRegularExpression.getContentPane().add(dfamFinal);
		
		JLabel label_8 = new JLabel("Final State");
		label_8.setBounds(10, 347, 64, 14);
		frmConvertRegularExpression.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("Start State");
		label_9.setBounds(10, 310, 64, 14);
		frmConvertRegularExpression.getContentPane().add(label_9);
		
		JLabel lblDfaMinimized = new JLabel("DFA Minimized");
		lblDfaMinimized.setForeground(Color.RED);
		lblDfaMinimized.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblDfaMinimized.setBounds(10, 278, 129, 14);
		frmConvertRegularExpression.getContentPane().add(lblDfaMinimized);
		
		JLabel lblContextFreeGrammarcfg = new JLabel("Context Free Grammar(CFG)");
		lblContextFreeGrammarcfg.setForeground(Color.RED);
		lblContextFreeGrammarcfg.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblContextFreeGrammarcfg.setBounds(10, 393, 193, 14);
		frmConvertRegularExpression.getContentPane().add(lblContextFreeGrammarcfg);
		
		JTextArea CFG = new JTextArea();
		CFG.setEditable(false);
		JScrollPane  scroll9 = new JScrollPane(  CFG);
		scroll9.setBounds(10, 418, 693, 132);
		frmConvertRegularExpression.getContentPane().add(scroll9);
		
	}

}
