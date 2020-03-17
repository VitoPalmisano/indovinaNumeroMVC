package it.polito.tdp.indovinanumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX = 100; // range numeri da 1 a NMAX
	private final int TMAX = 8; // numero di tentativi massimi
	private int segreto; // numero generato dal computer
	private int tentativiFatti;
	private boolean inGioco = false; // all'inizio non ho ancora cliccato nuova partita
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    @FXML
    void doNuova(ActionEvent event) {
    	
    	// gestione dell'inizio di una nuova partita - logica del sistema;
    	this.segreto = (int)(Math.random() * NMAX) + 1; // random arriva fino a 0.99999
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	
    	// gestione dell'interfaccia
    	layoutTentativo.setDisable(false); // nell'hbox, imposto disable su false (l'avevamo preimpostato a true
    	txtRisultato.clear();
    	txtRimasti.setText(Integer.toString(TMAX));
    	
    }

    @FXML
    void doTentativo(ActionEvent event) {

    	String ts = txtTentativi.getText();
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	}catch (NumberFormatException nfe) {
    		txtRisultato.appendText("Devi inserire un numero");
    		return;
    	}
    	
    	this.tentativiFatti++;
    	
    	if(tentativo == this.segreto) {
    		// Ho indovinato
    		txtRisultato.appendText("HAI VINTO!!! Hai utilizzato "+this.tentativiFatti+" tentativi");
    		layoutTentativo.setDisable(true);
    		this.inGioco = false;
    		return;
    	}
    	
    	if(tentativiFatti == TMAX) {
    		// Ho esaurito i tentativi => Ho perso
    		txtRisultato.appendText("HAI PERSO!!! Il numero segreto era: "+this.segreto);
    		layoutTentativo.setDisable(true);
    		this.inGioco = false;
    		return;
    	}
    	
    	// Devo informare l'utente se il tentativo e' troppo alto o troppo basso
    	
    	if(tentativo < this.segreto)
    		txtRisultato.appendText("Tentativo troppo BASSO\n");
    	else
    		txtRisultato.appendText("Tentativo troppo ALTO\n");
    	
    	txtRimasti.setText(Integer.toString(TMAX - tentativiFatti));
    }

    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
