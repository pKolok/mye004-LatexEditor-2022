package model;

import javax.swing.JOptionPane;

import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
import view.LatexEditorView;

public class VersionsManager {
	private static VersionsManager instance;
	private boolean enabled;
	private VersionsStrategy strategy;
	private LatexEditorView latexEditorView;

	// TODO - Remove
//	public VersionsManager(VersionsStrategy versionsStrategy, LatexEditorView latexEditorView) {
//		this.strategy = versionsStrategy;
//		this.latexEditorView = latexEditorView;
//	}
//	
	// Singleton constructor is private
	private VersionsManager() { instance = null; }
	
	public static VersionsManager getInstance() {
		if (instance == null) {
			instance = new VersionsManager();
		}
		return instance;
	}
	
	public void init(VersionsStrategy versionsStrategy) {
		this.strategy = versionsStrategy;
		this.latexEditorView = LatexEditorView.getInstance();
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	// TODO - remove (not used)
//	public void setStrategy(VersionsStrategy strategy) {
//		this.strategy = strategy;
//	}
	
	// TODO - remove
//	public void setCurrentVersion(Document document) {
//		latexEditorView.setCurrentDocument(document);
//	}

	// TODO - remove
//	public Document setPreviousVersion() {
//		return null;
//	}
//	
//	public void rollbackToPreviousVersion() {
//		
//	}

	//TODO - remove
//	public String getType() {
//		// TODO Auto-generated method stub
//		return latexEditorView.getType();
//	}

//	public void saveContents() {
//		// TODO Auto-generated method stub
//		latexEditorView.saveContents();
//	}

//	public void saveToFile() {
//		// TODO Auto-generated method stub
//		latexEditorView.saveToFile();
//	}

//	public void loadFromFile() {
//		// TODO Auto-generated method stub
//		latexEditorView.loadFromFile();
//	}

	public void enableStrategy() {
		// TODO Auto-generated method stub
		String strategyType = latexEditorView.getStrategy();
		if(strategyType.equals("volatile") && strategy instanceof VolatileVersionsStrategy) {
			enable();
		}
		else if(strategyType.equals("stable") && strategy instanceof VolatileVersionsStrategy) {
			//allagh apo to ena sto allo
			VersionsStrategy newStrategy = new StableVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("volatile") && strategy instanceof StableVersionsStrategy) {
			VersionsStrategy newStrategy = new VolatileVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("stable") && strategy instanceof StableVersionsStrategy) {
			enable();
		}
	}

	public void changeStrategy() {
		// TODO Auto-generated method stub
		String strategyType = latexEditorView.getStrategy();
		if(strategyType.equals("stable") && strategy instanceof VolatileVersionsStrategy) {
			VersionsStrategy newStrategy = new StableVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("volatile") && strategy instanceof StableVersionsStrategy) {
			VersionsStrategy newStrategy = new VolatileVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
	}

	public void  putVersion(Document document) {
		// TODO Auto-generated method stub
		strategy.putVersion(document);
	}

	public void rollback() {
		// TODO Auto-generated method stub
		if(isEnabled() == false) {
			JOptionPane.showMessageDialog(null, "Strategy is not enabled", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			Document doc = strategy.getVersion();
			if(doc == null) {
				JOptionPane.showMessageDialog(null, "No version available", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				strategy.removeVersion();
				latexEditorView.setCurrentDocument(doc);
			}
		}
		
	}

	// TODO - Remove (not used)
//	public VersionsStrategy getStrategy() {
//		return strategy;
//	}
}
