import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class CheckFileInterface {
	JFileChooser fc= new JFileChooser();;;
	JFrame frmRecm;
	private JTextField textField;
	private JTextField textField_1;
	static JTextArea textArea;
	static JTextArea textArea_1;
	File f;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckFileInterface window = new CheckFileInterface();
					window.frmRecm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	static void parseFile(NFA nfa,String inputFile) throws IOException {
		File file = new File(inputFile);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if (nfa.matches(line)){
							textArea.append(line);
							textArea.append("\n");
			}
			else{
				textArea_1.append(line);
				textArea_1.append("\n");
			}
		}
		fileReader.close();

	}
	public CheckFileInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRecm = new JFrame();
		frmRecm.getContentPane().setBackground(SystemColor.window);
		frmRecm.setTitle("RecM++");
		frmRecm.setResizable(false);
		frmRecm.setBounds(100, 100, 722, 460);
		frmRecm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRecm.getContentPane().setLayout(null);
		
		JLabel lblRegularExpression = new JLabel("Regular Expression");
		lblRegularExpression.setBounds(10, 29, 125, 14);
		frmRecm.getContentPane().add(lblRegularExpression);
		
		textField = new JTextField();
		textField.setBounds(145, 26, 419, 20);
		frmRecm.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblInputFile = new JLabel("Input File");
		lblInputFile.setBounds(10, 70, 99, 14);
		frmRecm.getContentPane().add(lblInputFile);
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 67, 419, 20);
		frmRecm.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Convert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main m=new Main();
				try {
					NFA nfa = m.buildNFA(textField.getText());
					parseFile(nfa,textField_1.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(574, 25, 122, 23);
		frmRecm.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Browse");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = fc.showOpenDialog(null);
				 if (x == JFileChooser.APPROVE_OPTION) {
	                f = fc.getSelectedFile();
	                textField_1.setText(f.getAbsolutePath());
	            } 
			}
		});
		btnNewButton_1.setBounds(574, 66, 122, 23);
		frmRecm.getContentPane().add(btnNewButton_1);
		
		textArea = new JTextArea();
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setEditable(false);
		
		JScrollPane  scroll = new JScrollPane(textArea);
		scroll.setBounds(10, 141, 350, 257);
		frmRecm.getContentPane().add(scroll);
		
		textArea_1 = new JTextArea();
		textArea_1.setBackground(SystemColor.controlHighlight);
		textArea_1.setEditable(false);
		JScrollPane  scroll_1 = new JScrollPane(textArea_1);
		scroll_1 .setBounds(370, 141, 326, 257);
		frmRecm.getContentPane().add(scroll_1 );
		
		JLabel lblStringsAccepted = new JLabel("Strings Accepted");
		lblStringsAccepted.setBounds(10, 105, 168, 14);
		frmRecm.getContentPane().add(lblStringsAccepted);
		
		JLabel lblStringsRejected = new JLabel("Strings Rejected");
		lblStringsRejected.setBounds(370, 105, 229, 14);
		frmRecm.getContentPane().add(lblStringsRejected);
	}
}
