package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnClear;

    @FXML
    private ChoiceBox<String> cmbBox;

    @FXML
    private Label lblError;

    @FXML
    private Label lblTime;

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleSpellCheck(ActionEvent event) {
    	
    	long start = System.nanoTime();
    	
    	List<String> listWords = new LinkedList<>();
    	
    	String inputText = this.txtInput.getText();
    	String language = this.cmbBox.getValue();
    	
    	String textLower = inputText.toLowerCase();
    	String textReplace = textLower.replaceAll("[.,\\/#?!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	
    	String[] parts = textReplace.split(" ");
    	
    	for(String part : parts) {
    		listWords.add(part);
    	}
    	
    	this.model.loadDictionary(language);
    	
    	List<RichWord> wordsChecked = this.model.spellCheckText(listWords);
    	
    	int cont = 0;
    	for(RichWord word : wordsChecked) {
    		if(word.isWrong()) {
    			this.txtResult.appendText(word.getWord() + "\n");
    			cont++;
    		}
    	}
    	
    	long end = System.nanoTime();
		
		double time = (double) ((end - start)/1000000000);
    	
    	this.lblError.setText("The text contains " + cont + " errors.");
    	this.lblTime.setText("Spell check completed in " + time + " seconds.");
    	
    }

    @FXML
    void handleClearText(ActionEvent event) {
    	
    	this.txtInput.clear();
    	this.txtResult.clear();

    }

    @FXML
    void initialize() {
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBox != null : "fx:id=\"cmbBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblError != null : "fx:id=\"lblError\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
        cmbBox.getItems().add("English");
        cmbBox.getItems().add("Italian");

    }
    
    public void setModel(Dictionary model) {
		this.model = model;
	}

    

}
