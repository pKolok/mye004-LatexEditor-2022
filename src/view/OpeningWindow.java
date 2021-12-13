package view;

import java.awt.EventQueue;
import javax.swing.JFrame;

import controller.LatexEditorController;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.VersionsManager;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;

public class OpeningWindow {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpeningWindow window = new OpeningWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpeningWindow() {
		VersionsStrategy versionsStrategy = new VolatileVersionsStrategy();
		
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.init(versionsStrategy);
		
		initialize();
		
		frame.setVisible(true);
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnCreateNewDocument = new JButton("Create New Document");
		btnCreateNewDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ChooseTemplate chooseTemplate = new ChooseTemplate("opening");
				frame.dispose();
			}
		});
		btnCreateNewDocument.setBounds(60, 30, 280, 36);
		frame.getContentPane().add(btnCreateNewDocument);
		
		
		JButton btnOpenExistingDocument = new JButton("Open Existing Document");
		btnOpenExistingDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
//				filechooser.showOpenDialog(null);
			
				int option = filechooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					LatexEditorController latexEditorController = LatexEditorController.getInstance();
					latexEditorController.setFilename(filename);
					latexEditorController.enact("load");
					latexEditorController.setType("emptyTemplate");
					MainWindow mainWindow = new MainWindow();
					mainWindow.getEditorPane().setText(
							latexEditorController.getCurrentDocument().getContents());
					frame.dispose();
				}
				
			}
		});
		btnOpenExistingDocument.setBounds(60, 80, 280, 36);
		frame.getContentPane().add(btnOpenExistingDocument);
		
		
		JButton btnOpenHTMLDocument = new JButton("Open HTML Document");
		btnOpenHTMLDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
			
				int option = filechooser.showOpenDialog(null);
				if(option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					LatexEditorController latexEditorController = LatexEditorController.getInstance();
					latexEditorController.setFilename(filename);
					latexEditorController.enact("load");
//					latexEditorController.setType("emptyTemplate");
					MainWindow mainWindow = new MainWindow();
					mainWindow.getEditorPane().setText(
							latexEditorController.getCurrentDocument().getContents());
					frame.dispose();
				}
				
			}
		});
		btnOpenHTMLDocument.setBounds(60, 130, 280, 36);
		frame.getContentPane().add(btnOpenHTMLDocument);
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(60, 190, 280, 25);
		frame.getContentPane().add(btnExit);
	}
}