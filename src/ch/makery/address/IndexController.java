package ch.makery.address;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class IndexController  implements Initializable 
{		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
	}
	
	public void login(ActionEvent event) throws IOException 
	{
	    BorderPane sceneLayout = new BorderPane();
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    //Stage stage = new Stage();
	    Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    
	    stage.setTitle("Index");
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/Home.fxml")); 
	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
}
