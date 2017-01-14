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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeController implements Initializable
{
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
	}
	
	public void gotoLectureMan(ActionEvent event) throws IOException 
	{
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("LectureMan");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/LectureMan.fxml")); 
	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
	
	public void gotoUpdate(ActionEvent event) throws IOException 
	{
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("Update");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/Update.fxml")); 
	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
	
	public void gotoHistoryHomework(ActionEvent event) throws IOException 
	{
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("HistoryHomework");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/HomeworkMan.fxml")); 
	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
	
	public void gotoCalendar(ActionEvent event) throws IOException
	{
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("Calendar");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    Pane myLayout = FXMLLoader.load(getClass().getResource("view/Calendar.fxml")); 
	    System.out.println("This is Calendar,but it is not a AnchorPane class.");
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
	
	public void gotoIndex(ActionEvent event) throws IOException
	{
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("Index");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/Home.fxml")); 
	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
	
	
	
	
	public void logout(ActionEvent event) throws IOException
	{
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("Index");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/IndexLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/index.fxml")); 
	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout); 
	    System.out.println("logout success!");
	}
	
	
}
