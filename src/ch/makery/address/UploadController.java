package ch.makery.address;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ntou.basic.Assignment;
import ntou.basic.Course;
import ntou.basic.SceneStatus;

public class UploadController implements Initializable 
{
	@FXML
	Button browse;
	
	@FXML
	Button upload;
	
	@FXML
	TextField path;
	
	private Assignment tempAss;
	private Course tempCourse;
	
	private File file;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		tempAss = SceneStatus.bufferAssignment;
		tempCourse = SceneStatus.bufferCourse;
		
		SceneStatus.resetStatus();
	}
	
	public void selectFile(ActionEvent event) throws IOException 
	{
		Stage stage = new Stage();
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		
		stage.show();
		
		File fileN = fileChooser.showOpenDialog(stage);	
		
		file = fileN;
		
		stage.close();
		
		path.setText(fileN.getAbsolutePath());
	}
	
	public void uploadFile(ActionEvent event) throws IOException
	{
		tempAss.upload(file.getAbsolutePath());
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
