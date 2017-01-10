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

public class HomeworkController implements Initializable
{
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
	}
	
	public void gotoCurrentHomework(ActionEvent event) throws IOException 
	{
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("HistoryHomework");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/Homework.fxml")); 
	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
	
	public void readContent(ActionEvent event)
	{
		System.out.println("open file and read");
	}
	
	public void readRecord(ActionEvent event)
	{
		System.out.println("read record");
	}	
}
