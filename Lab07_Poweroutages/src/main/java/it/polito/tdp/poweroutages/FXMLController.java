/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.EventoBlackOut;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;
	private ObservableList<Nerc> list = FXCollections.observableArrayList();

	public void setModel(Model model) {
		this.model = model;
		list.addAll(model.getNercList());
		MenuTendina.setItems(list);
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="MenuTendina"
	private ComboBox<Nerc> MenuTendina; // Value injected by FXMLLoader

	@FXML // fx:id="txtYears"
	private TextField txtYears; // Value injected by FXMLLoader

	@FXML // fx:id="txtHours"
	private TextField txtHours; // Value injected by FXMLLoader

	@FXML // fx:id="btnWCA"
	private Button btnWCA; // Value injected by FXMLLoader

	@FXML // fx:id="txtArea"
	private TextArea txtArea; // Value injected by FXMLLoader

	@FXML
	void doWorstcaseAnalysis(ActionEvent event) {

		Nerc nerc = MenuTendina.getValue();
		int maxYears;
		int maxHours;

		if (nerc == null) {
			txtArea.appendText("Devi scegliere un nerc!");
			return;
		}

		try {
			maxYears = Integer.parseInt(txtYears.getText());
			maxHours = Integer.parseInt(txtHours.getText());
		} catch (NumberFormatException e) {
			txtArea.appendText("Devi inserire un numero per gli anni e per le ore!!");
			return;
		}
		Long start = System.currentTimeMillis();
		List<EventoBlackOut> soluzioneOttima = model.trovaWorstCase(nerc, maxYears, maxHours);
		Long end = System.currentTimeMillis();
		if (soluzioneOttima == null) {
			txtArea.appendText("questo Nerc non presenta eventi");
			return;
		}
		txtArea.appendText("TEMPO IMPIEGATO: "+ (end-start)+ " ms\n");
		txtArea.appendText("Tot people affected: " + model.getNumMax() + "\n");
		txtArea.appendText("Tot hours of outage: " + model.getOre() + "\n");
		for (EventoBlackOut e : soluzioneOttima)
			txtArea.appendText(e.toString() + "\n");

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert MenuTendina != null : "fx:id=\"MenuTendina\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnWCA != null : "fx:id=\"btnWCA\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";

	}
}
