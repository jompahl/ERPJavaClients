package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {
	private Main main;

	public void setMain(Main main) {
		this.main = main;
		//tableViewEmployee.setItems(this.main.getEmployeeList());
	}
	
	@FXML
	public void btnERPClient2(ActionEvent event) {
		try {
			this.main.navigate(Main.Pages.ERP1CLIENT2);
		} catch (Exception e){
			
		}
	}
}
