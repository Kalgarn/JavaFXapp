package control;

import java.io.IOException;

import config.ConnexionBDD;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import model.dao.MysqlUserDaoImp;
import model.dao.UserDao;
import view.UserEditDialogController;
import view.UserOverviewController;

public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ObservableList<User> userData;
	private UserDao userDao;
	

    /**
     * Constructor
     * @throws Exception 
     */
    public Main() throws Exception {
        // Add some sample data
//        userData.add(new User("Hans", "Muster"));
//        userData.add(new User("Ruth", "Mueller"));
//        userData.add(new User("Heinz", "Kurz"));
//        userData.add(new User("Cornelia", "Meier"));
//        userData.add(new User("Werner", "Meyer"));
//        userData.add(new User("Lydia", "Kunz"));
//        userData.add(new User("Anna", "Best"));
//        userData.add(new User("Stefan", "Meier"));
//        userData.add(new User("Martin", "Mueller"));
        userDao = new MysqlUserDaoImp();
    }
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Ann App");
		
		
		  initRootLayout();

	      showUserOverview();
	      try {
			if(ConnexionBDD.getConnexion() == null) {
				  //statusText.setText("Status: DATABASE NOT CONNECTED!");
				  System.out.println("Status: DATABASE NOT CONNECTED!");
			  }
				else {
					//statusText.setText("Status: DATABASE CONNECTED!");
					System.out.println("Status: DATABASE CONNECTED!");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<User> getUserData() {
    	userData = FXCollections.observableArrayList(userDao.findAll());
        return userData;
    }
    
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showUserOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/UserOverview.fxml"));
            AnchorPane userOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(userOverview);
            
            // Give the controller access to the main app.
            UserOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Opens a dialog to edit details for the specified user. If the user
     * clicks OK, the changes are saved into the provided user object and true
     * is returned.
     *
     * @param user the user object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showUserEditDialog(User user) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/UserEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit User");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            UserEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(user);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        	}
        
        }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	public static void main(String[] args) {
		launch(args);
	}
}
