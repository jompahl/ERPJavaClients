package controller;
import controller.Controller;
import java.lang.reflect.Method;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;
import se.lu.ics.grupp14.*;

public class Controller implements Initializable {
	@FXML
	private TextField textFieldID;
	@FXML
	private TextField textFieldFirstName;
	@FXML
	private TextField textFieldLastName;
	@FXML
	private TextField textFieldAddress;
	@FXML
	private TextField textFieldPhoneNbr;
	@FXML
	private ComboBox<String> comboBoxJob;
	@FXML
	private Label responseLabel;
	@FXML
	private Label labelFirst;
	@FXML
	private TableView<Employee> tableViewEmployee;
	@FXML
	private TableColumn<Employee, String> columnID;
	@FXML
	private TableColumn<Employee, String> columnFirst;
	@FXML
	private TableColumn<Employee, String> columnLast;
	@FXML
	private TableColumn<Employee, String> columnJob;
	@FXML
	private TableColumn<Employee, String> columnAddress;
	@FXML
	private TableColumn<Employee, String> columnPhoneNbr;
		
	ERP1WebServiceSoapProxy proxy = new ERP1WebServiceSoapProxy();
	private ObservableList<Employee> employeeList;
	private ObservableList<String> jobTitles = FXCollections.observableArrayList();
	private Main main;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		columnID.setCellValueFactory(new PropertyValueFactory<>("No"));
		columnFirst.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		columnLast.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		columnJob.setCellValueFactory(new PropertyValueFactory<>("JobTitle"));
		columnAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
		columnPhoneNbr.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
		
		jobTitles.add("Försäljningschef");
		jobTitles.add("Sekreterare");
		jobTitles.add("Produktionschef");
		jobTitles.add("Designer");
		jobTitles.add("Produktionsassistent");
		jobTitles.add("Verkställande direktör");
		jobTitles.add("Servicechef");
		
		comboBoxJob.setItems(jobTitles);
		
		MenuItem miUpdate = new MenuItem("Update");
		miUpdate.setOnAction((ActionEvent event) -> {
		    System.out.println("Menu item 1");
		    Employee employee = tableViewEmployee.getSelectionModel().getSelectedItem();
		    
		    popupUpdate(employee);
		});
		
		MenuItem miDelete = new MenuItem("Delete");
		miDelete.setOnAction((ActionEvent event) -> {
			Employee employee = tableViewEmployee.getSelectionModel().getSelectedItem();

			// Creates an alert confirmation
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Are you sure?");
			alert.setHeaderText("This will remove the employee with id: " + employee.getNo());
			alert.setContentText("Is this ok?");

			Optional<ButtonType> deleteEmployee = alert.showAndWait();
			if (deleteEmployee.get() == ButtonType.OK) {
				try {
					if(proxy.deleteEmployee(employee.getNo())) {
						responseLabel.setTextFill(Color.GREEN);
						responseLabel.setText("Sucessfully deleted employee");
						updateList();
					} else {
						responseLabel.setText("Could not deleted employee");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				responseLabel.setTextFill(Color.RED);
				responseLabel.setText("Nothing was deleted");
			}

		});

		ContextMenu menu = new ContextMenu();
		menu.getItems().add(miUpdate);
		menu.getItems().add(miDelete);
		tableViewEmployee.setContextMenu(menu);	
	}
	
	public void popupUpdate(Employee e) {
        final Stage dialog = new Stage();
        dialog.setTitle("Update?");
        
        Label labelID = new Label("ID:");
        Label labelFirstName = new Label("First Name:");
        Label labelLastName = new Label("Last Name:");
        Label labelJob = new Label("Job Title:");
        Label labelAddress = new Label("Address:");
        Label labelPhoneNbr = new Label("Phone Number:");  
        Label labelResponse = new Label("hej");
	    
        Label fieldID = new Label(e.getNo());
        TextField fieldFirstName = new TextField();
        TextField fieldLastName = new TextField();
        ComboBox<String> comboBoxJobUpdate = new ComboBox<String>();
        TextField fieldAddress = new TextField();
        TextField fieldPhoneNbr = new TextField();
        Button updateButton = new Button("Update");
        
        fieldID.setText(e.getNo());
        fieldFirstName.setText(e.getFirstName());
        fieldLastName.setText(e.getLastName());
        comboBoxJobUpdate.setItems(jobTitles);
        comboBoxJobUpdate.setValue(e.getJobTitle());
        fieldAddress.setText(e.getAddress());
        fieldPhoneNbr.setText(e.getPhoneNumber());      
        
        Label displayLabel = new Label("Update Employee");
        displayLabel.setFont(Font.font(null, FontWeight.BOLD, 14));

        dialog.initModality(Modality.NONE);
        dialog.initOwner((Stage) tableViewEmployee.getScene().getWindow());

        HBox dialogHbox = new HBox(20);
        dialogHbox.setAlignment(Pos.CENTER);

        VBox labels = new VBox(20);
        labels.setAlignment(Pos.CENTER_RIGHT);
        labels.setSpacing(28);

        VBox inputs = new VBox(20);
        inputs.setAlignment(Pos.CENTER_LEFT);
        
        labels.getChildren().add(labelID);
        labels.getChildren().add(labelFirstName);
        labels.getChildren().add(labelLastName);
        labels.getChildren().add(labelJob);
        labels.getChildren().add(labelAddress);
        labels.getChildren().add(labelPhoneNbr);
        labels.getChildren().add(labelResponse);
        
        inputs.getChildren().add(fieldID);
        inputs.getChildren().add(fieldFirstName);
        inputs.getChildren().add(fieldLastName);
        inputs.getChildren().add(comboBoxJobUpdate);
        inputs.getChildren().add(fieldAddress);
        inputs.getChildren().add(fieldPhoneNbr);
        inputs.getChildren().add(updateButton);

        updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseClick) {
                    	String no = fieldID.getText();
                		String firstName = fieldFirstName.getText();
                		String lastName = fieldLastName.getText();
                		String job = comboBoxJobUpdate.getSelectionModel().getSelectedItem();
                		String address = fieldAddress.getText();
                		String phoneNbr = fieldPhoneNbr.getText();
                		
                		System.out.println(no + firstName + lastName + job + address + phoneNbr);
                		
                		if(no.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || job == null || address.isEmpty() || phoneNbr.isEmpty()) {
                			labelResponse.setTextFill(Color.RED);
                			labelResponse.setText("Please fill all fields");
                		} else { 
                			if(		no.equals(e.getNo()) && 
                					firstName.equals(e.getFirstName()) && 
                					lastName.equals(e.getLastName()) && 
                					job.equals(e.getJobTitle()) && 
                					address.equals(e.getAddress()) && 
                					phoneNbr.equals(e.getPhoneNumber())) {
                				
                				responseLabel.setTextFill(Color.RED);
                				responseLabel.setText("Noting was updated");
                				dialog.close();
                				
                			} else {
                				try {
                					if(proxy.updateEmployee(no, firstName, lastName, job, address, phoneNbr)) {
                						responseLabel.setTextFill(Color.GREEN);
                						responseLabel.setText("Sucessfully updated employee");
                						updateList();
                						clearFields();	
                						dialog.close();
                					}
                				} catch (RemoteException ex) {
                					ex.printStackTrace();
                				}
                			}
                		}
                    }
                });

        dialogHbox.getChildren().addAll(labels, inputs);
        Scene dialogScene = new Scene(dialogHbox, 370, 350);
        dialogScene.getStylesheets().add("//style sheet of your choice");
        dialog.setScene(dialogScene);
        dialog.show();
    }
	
	/*public void setDataTable(ObservableList<Employee> e) {
		tableViewEmployee.setItems(e);
		System.out.println("hejjompahl");
	}
	
    private void populateColumns(Class clazz, TableView table) {

        for (Method method : clazz.getMethods()) {
            String name = method.getName();
            System.out.println(name);
            if (name.endsWith("Property")) {
              String propName = name.replace("Property", "");

              for(TableColumn t : hej) {
            	  
              }
              
              System.out.println("joooomahp");
              //TableColumn column = new TableColumn(propName);
              //columnID = new TableColumn(propName);
              columnID.setCellValueFactory(new PropertyValueFactory<>(propName));
              //tableViewEmployee.getColumns().add(column);
            }
        }
    }*/
    
    public void updateList() {
    	try {
			employeeList = FXCollections.observableArrayList(Arrays.asList(proxy.readEmployee()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	tableViewEmployee.getItems().clear();	
    	tableViewEmployee.setItems(employeeList);	
    }
	
	public Main getMain() {
		return main;		
	}

	public void setMain(Main main) {
		this.main = main;
		updateList();
	}
	
	public void clearFields() {
		textFieldID.setText("");
		textFieldFirstName.setText("");
		textFieldLastName.setText("");
		textFieldAddress.setText("");
		textFieldPhoneNbr.setText("");
		comboBoxJob.setValue(null);
	}
	
	@FXML
	public void btnAddEmployee() {
		String no = textFieldID.getText();
		String firstName = textFieldFirstName.getText();
		String lastName = textFieldLastName.getText();
		String job = comboBoxJob.getSelectionModel().getSelectedItem();
		String address = textFieldAddress.getText();
		String phoneNbr = textFieldPhoneNbr.getText();
		
		if(no.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || job == null || address.isEmpty() || phoneNbr.isEmpty()) {
			responseLabel.setTextFill(Color.RED);
			responseLabel.setText("Please fill all fields");
		} else {
			try {
				if(proxy.createEmployee(no, firstName, lastName, job, address, phoneNbr)) {
					responseLabel.setTextFill(Color.GREEN);
					responseLabel.setText("Sucessfully added employee");
					updateList();
					clearFields();	
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void btnERPClient2(ActionEvent event) {
		try {
			this.main.navigate(Main.Pages.ERP1CLIENT2);
		} catch (Exception e){
			
		}
	}
	
	@FXML
	public void btnGoBack(ActionEvent event) {
		this.main.navigate(Main.Pages.HOME);
	}
}
