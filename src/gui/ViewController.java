package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Person;

public class ViewController implements Initializable {
	
	@FXML
	private Button btTest;
	
	@FXML
	private TextField txtNumero1;
	
	@FXML
	private TextField txtNumero2;
	
	@FXML
	private Label labelResult;
	
	@FXML
	private ComboBox<Person> comboBoxPerson;
	
	private ObservableList<Person> obsList;
	
	@FXML
	private Button btAll;
	
	@FXML
	private TextArea textAreaPessoa;
	
	@FXML
	public void onBtTestAction() {
		Alerts.showAlert("Os animaniacs", "somos", "demais ... Olá Enfermeira!", AlertType.INFORMATION);
	}
	
	@FXML
	public void onBtSumAction() {
		try {
			Locale.setDefault(Locale.US);
			
			double number1 = Double.parseDouble(txtNumero1.getText());
			double number2 = Double.parseDouble(txtNumero2.getText());
			
			double sum = number1 + number2;
			
			labelResult.setText("Resultado da Soma: " + String.format("%.2f", sum));
		} catch (NumberFormatException e) {
			Alerts.showAlert("Erro", "Erro de conversão", "Digita um número válido, carai!", AlertType.ERROR);
		}
	}
	
	@FXML
	public void onComboBoxPersonAction() {
		Person person = comboBoxPerson.getSelectionModel().getSelectedItem();
		Alerts.showAlert("Atenção", "Pessoa selecionada", person.getName() +" - " + person.getEmail(), AlertType.CONFIRMATION);
	}
	
	@FXML
	public void onBtAllAction() {
		for (Person person : comboBoxPerson.getItems()) {
			textAreaPessoa.appendText(person.toString() + "\n");
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Constraints.setTextFieldDouble(txtNumero1);
		Constraints.setTextFieldDouble(txtNumero1);
		Constraints.setTextFieldMaxLength(txtNumero1, 12);
		Constraints.setTextFieldMaxLength(txtNumero2, 12);
		
		List<Person> list = new ArrayList<>();
		list.add(new Person(1, "Maria", "maria@gmail.com"));
		list.add(new Person(2, "Alex", "alex@gmail.com"));
		list.add(new Person(3, "Bob", "bob@gmail.com"));
		
		obsList = FXCollections.observableArrayList(list);
		comboBoxPerson.setItems(obsList);
		
		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
		    @Override
		    protected void updateItem(Person item, boolean empty) {
		        super.updateItem(item, empty);
		        setText(empty ? "" : item.getName());
		    }
		};

		comboBoxPerson.setCellFactory(factory);
		comboBoxPerson.setButtonCell(factory.call(null));
	}
}
