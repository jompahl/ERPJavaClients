package controller;
import controller.Controller;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import se.lu.ics.grupp14.*;

public class Controller {
	ERP1WebServiceSoapProxy proxy = new ERP1WebServiceSoapProxy();
	private Main main;

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	@FXML
	public void btnERPClient2(ActionEvent event) {
		try {
			this.main.navigate(Main.Pages.ERP1CLIENT2);
		} catch (Exception e){
			
		}
	}

}
