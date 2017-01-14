package ch.makery.address;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.GroupLayout.Alignment;

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
import net.sourceforge.htmlunit.corejs.javascript.ast.Assignment;
import ntou.basic.Course;

public class HomeworkController implements Initializable
{
	@FXML
	private AnchorPane assignments;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		Iterator courseIt = Services.crawler.courseList.iterator();
		
		int offset = 0;
		while(courseIt.hasNext())
		{
			Course tempCourse = (Course) courseIt.next();
			
			Iterator it = tempCourse.assignmentList.iterator();
			
			while(it.hasNext())
			{
				ntou.basic.Assignment assignment = (ntou.basic.Assignment) it.next();
				
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
				content.setText("¤º®e");
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
				
				assignmentPane.getChildren().addAll(courseName,assignmentName,content);
				assignments.getChildren().add(assignmentPane);
				
				offset++;
			}
		}
		
		assignments.setPrefHeight(60.0*offset+30.0);
	}
	
	public void gotoCurrentHomework(ActionEvent event) throws IOException 
	{
		/*
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("HistoryHomework");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/Homework.fxml")); 	    
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	    */
		
		BorderPane sceneLayout = new BorderPane();
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    //Stage stage = new Stage();
	    Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    
	    stage.setTitle("HistoryHomework");
	    
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
