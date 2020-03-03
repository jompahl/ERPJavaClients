package application;
	
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;

import controller.Controller;
import controller.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.*;
import se.lu.ics.grupp14.*;

public class Main extends Application {
	
	// Hashmap to store our different scenes
	private HashMap<Pages, String> resources = new HashMap<>();
	//private ObservableList<Employee> employeeList;// = FXCollections.observableArrayList();
	ERP1WebServiceSoapProxy proxy = new ERP1WebServiceSoapProxy();
	private Stage primaryStage;
	
	public static enum Pages {
		HOME, ERP1CLIENT2
	}
	
	public void updateList() {
		
	}
	
	// Constructor for main, the first thing that runs
	public Main() {
		// Add our scenes to a hashMap
		resources.put(Pages.HOME, "/view/ERPJavaClients.fxml");
		resources.put(Pages.ERP1CLIENT2, "/view/ERP1Client2.fxml");
	}
	
	// Runs after constructor
	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;

		try {
			this.navigate(Pages.HOME);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void navigate(Pages pageName) {

		String resource = this.resources.get(pageName);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
		Parent pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(pane, 782, 601);
		this.primaryStage.setScene(scene);

		switch (pageName) {
		case HOME:
			MainController controller = loader.getController();
			controller.setMain(this);
			break;
		case ERP1CLIENT2:
			Controller controllerERP1Client2 = loader.getController();
			controllerERP1Client2.setMain(this);
			//controllerERP1Client2.setDataTable(employeeList);
			break;
		}
	}	
	
	public static void main(String[] args) {
		launch(args);
		System.out.println("Hejsan");
		
		/*ERP1WebServiceSoapProxy proxy = new ERP1WebServiceSoapProxy();
		try {
			for (Employee e : proxy.readEmployee()) {
				System.out.println(e.getFirstName() + e.getLastName() + e.getAddress() + e.getJobTitle() + e.getNo() + e.getPhoneNumber());
				//employeeList().add(e);
				
			}
		} catch (RemoteException e) {
			System.out.println("fail");
			e.printStackTrace();
		}*/
	}
	
	public void hej() {
		System.out.println("jompahl");
	}

}
