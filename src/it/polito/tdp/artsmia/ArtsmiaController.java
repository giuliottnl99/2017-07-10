/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Percorso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="boxLUN"
	private ChoiceBox<Integer> boxLUN; // Value injected by FXMLLoader

	@FXML // fx:id="btnCalcolaComponenteConnessa"
	private Button btnCalcolaComponenteConnessa; // Value injected by FXMLLoader

	@FXML // fx:id="btnCercaOggetti"
	private Button btnCercaOggetti; // Value injected by FXMLLoader

	@FXML // fx:id="btnAnalizzaOggetti"
	private Button btnAnalizzaOggetti; // Value injected by FXMLLoader

	@FXML // fx:id="txtObjectId"
	private TextField txtObjectId; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doAnalizzaOggetti(ActionEvent event) {
		this.txtResult.setText("Grafo in corso");
		System.out.println("doAnalizzaOggetti");
		model.creaGrafo();
		this.txtResult.setText("grafo completato");
		
		
	}

	@FXML
	void doCalcolaComponenteConnessa(ActionEvent event) {
		this.txtResult.setText("Calcolo in corso");
		System.out.println("doCalcolaComponenteConnessa");
		int id = Integer.valueOf(this.txtObjectId.getText()); 
		double lun = model.trovaNumVicini(id);
		String result = model.ritornaVicini(id);
		this.txtResult.setText(result);
		//Creo valori associati all'oggetto
		List<Integer> listaInt = new ArrayList<Integer>();
		for(int i=2; i<lun; i++) {
			listaInt.add(i);
		}
		this.boxLUN.getItems().setAll(listaInt);
	}

	@FXML
	void doCercaOggetti(ActionEvent event) {
		txtResult.setText("doCercaOggetti");
		int valScelto = this.boxLUN.getValue();
		Integer aoId = Integer.valueOf(this.txtObjectId.getText());
		String result = model.risultatoRicorsione(aoId, valScelto);
		this.txtResult.setText(result);
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert boxLUN != null : "fx:id=\"boxLUN\" was not injected: check your FXML file 'Artsmia.fxml'.";
		assert btnCalcolaComponenteConnessa != null : "fx:id=\"btnCalcolaComponenteConnessa\" was not injected: check your FXML file 'Artsmia.fxml'.";
		assert btnCercaOggetti != null : "fx:id=\"btnCercaOggetti\" was not injected: check your FXML file 'Artsmia.fxml'.";
		assert btnAnalizzaOggetti != null : "fx:id=\"btnAnalizzaOggetti\" was not injected: check your FXML file 'Artsmia.fxml'.";
		assert txtObjectId != null : "fx:id=\"txtObjectId\" was not injected: check your FXML file 'Artsmia.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

	}

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model = model;
		
		
	}
}
