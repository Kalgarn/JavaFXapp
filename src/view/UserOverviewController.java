package view;

import control.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.User;
import util.DateUtil;


public class UserOverviewController {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    
    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public UserOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear person details.
        showUserDetails(null);

        // Listen for selection changes and show the person details when changed.
        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showUserDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        // Add observable list data to the table
        userTable.setItems(mainApp.getUserData());
    }
    
    /**
     * Fills all text fields to show details about the user.
     * If the specified user is null, all text fields are cleared.
     * 
     * @param user the user or null
     */
    private void showUserDetails(User user) {
        if (user != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(user.getFirstName());
            lastNameLabel.setText(user.getLastName());
            streetLabel.setText(user.getStreet());
            postalCodeLabel.setText(Integer.toString(user.getPostalCode()));
            cityLabel.setText(user.getCity());
            birthdayLabel.setText(DateUtil.format(user.getBirthday()));
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteUser() {
        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            userTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new user.
     */
    @FXML
    private void handleNewUser() {
        User tempPerson = new User();
        boolean okClicked = main.showUserEditDialog(tempPerson);
        if (okClicked) {
            main.getUserData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected user.
     */
    @FXML
    private void handleEditUser() {
        User selectedPerson = userTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = main.showUserEditDialog(selectedPerson);
            if (okClicked) {
                showUserDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
