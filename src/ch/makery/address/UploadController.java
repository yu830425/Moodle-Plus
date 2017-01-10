package ch.makery.address;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UploadController implements Initializable 
{
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
	}
	
	public void download(ActionEvent event)
	{
		
	
	}
	
	
	public void readRecord(ActionEvent event) 
	{
		
		
	}

	
	public void readContent(ActionEvent event) 
	{
		
	}
	
	public void gotoUpload(ActionEvent event) throws IOException 
	{
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("CurrentHomwork");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/Upload.fxml")); 
	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
	
	public void gotoHistoryHomework(ActionEvent event) throws IOException 
	{
		
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("CurrentHomwork");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/HomeworkMan.fxml")); 
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
	
	
}
