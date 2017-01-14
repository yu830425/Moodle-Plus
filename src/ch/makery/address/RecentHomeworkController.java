package ch.makery.address;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ntou.basic.Course;
import ntou.basic.SceneStatus;

public class RecentHomeworkController implements Initializable 
{
	@FXML
	private AnchorPane homeworks;
	
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Date now = new Date();
		
		Iterator courseIt = Services.crawler.courseList.iterator();
		
		int offset = 0;
		while(courseIt.hasNext())
		{
			Course tempCourse = (Course) courseIt.next();
			
			Iterator it = tempCourse.assignmentList.iterator();
			
			while(it.hasNext())
			{								
				ntou.basic.Assignment assignment = (ntou.basic.Assignment) it.next();
				
				if(assignment.hasDeadLine == true && now.after(assignment.deadLine))
					continue;
				
				Pane assignmentPane = new Pane();
				assignmentPane.setLayoutX(30.0);
				assignmentPane.setLayoutY(0.0 + offset * 60.0);
				assignmentPane.setPrefHeight(55.0);
				assignmentPane.setPrefWidth(462.0);
				assignmentPane.getStyleClass().add("Pane");
				
				Label courseName = new Label();
				courseName.setLayoutX(43.0);
				courseName.setLayoutY(24.0);
				courseName.setText(tempCourse.courseName);
				courseName.setTextFill(Paint.valueOf("GREY"));
				courseName.setFont(new Font(18.0));
				
				Label assignmentName = new Label();
				assignmentName.setLayoutX(13.0);
				assignmentName.setLayoutY(4.0);
				assignmentName.setText(assignment.name);
				assignmentName.setTextFill(Paint.valueOf("GREY"));
				
				Button content = new Button();
				content.setText("內容");
				content.setLayoutX(296.0);
				content.setLayoutY(32.0);
				content.setMnemonicParsing(false);
				content.setPrefWidth(70.0);
				content.setTextAlignment(TextAlignment.LEFT);
				content.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent arg0) 
					{
						;
					}					
				});
				
				Button upload = new Button();
				upload.setText("上傳");
				upload.setLayoutX(374.0);
				upload.setLayoutY(32.0);
				upload.setMnemonicParsing(false);
				//upload.setPrefHeight(70.0);
				upload.setStyle("-fx-background-color: red;");
				upload.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent arg0) 
					{
						SceneStatus.bufferAssignment = assignment;
						SceneStatus.bufferCourse = tempCourse;
						
						try 
						{
							gotoUpload(arg0);
						} 
						catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
				assignmentPane.getChildren().addAll(courseName,assignmentName,content,upload);
				homeworks.getChildren().add(assignmentPane);
				
				offset++;
			}
		}
		
		homeworks.setPrefHeight(60.0*offset+30.0);
	}
	
	public void gotoUpload(ActionEvent event) throws IOException
	{
		BorderPane sceneLayout = new BorderPane();
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    //Stage stage = new Stage();
	    Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    
	    stage.setTitle("Upload");
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/Upload.fxml")); 
	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
	
	public void gotoHistoryHomework(ActionEvent event) throws IOException
	{
		BorderPane sceneLayout = new BorderPane();
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    //Stage stage = new Stage();
	    Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    
	    stage.setTitle("HistoryHomework");
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/HomeworkMan.fxml")); 
	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}
}
