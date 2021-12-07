package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.LatexEditorController;
import model.VersionsManager;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpeningWindow {

	private JFrame frame;
	private LatexEditorView latexEditorView;
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
		
		// TODO - Remove
//		latexEditorView = new LatexEditorView();
		LatexEditorView latexEditorView = LatexEditorView.getInstance();
		
		// TODO - remove
//		VersionsManager versionsManager = new VersionsManager(versionsStrategy, latexEditorView);
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.init(versionsStrategy);
		
		// TODO - Remove
//		LatexEditorController controller = new LatexEditorController(versionsManager);
		LatexEditorController controller = new LatexEditorController();
		
		latexEditorView.setController(controller);
		latexEditorView.setVersionsManager(versionsManager);
		
		initialize();
		
		frame.setVisible(true);
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCreateNewDocument = new JButton("Create New Document");
		btnCreateNewDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ChooseTemplate chooseTemplate = new ChooseTemplate(latexEditorView, "opening");
				ChooseTemplate chooseTemplate = new ChooseTemplate("opening");
				frame.dispose();
			}
		});
		btnCreateNewDocument.setBounds(89, 26, 278, 36);
		frame.getContentPane().add(btnCreateNewDocument);
		
		JButton btnOpenExistingDocument = new JButton("Open Existing Document");
		btnOpenExistingDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
			}
		});
		btnOpenExistingDocument.setBounds(89, 92, 278, 36);
		frame.getContentPane().add(btnOpenExistingDocument);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(99, 169, 268, 25);
		frame.getContentPane().add(btnExit);
	}
}

// test
