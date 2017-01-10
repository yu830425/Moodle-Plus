package ch.makery.address;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ntou.basic.Course;
import ntou.basic.SceneStatus;

public class LectureController implements Initializable 
{
	@FXML
	private AnchorPane courses;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		Iterator it = Services.crawler.courseList.iterator();
		
		int offset = 0;
		
		while(it.hasNext())
		{
			Course tempCourse = (Course) it.next();
			
			Pane coursePane = new Pane();
			coursePane.getStylesheets().add("@LectureManTheme.css");
			coursePane.setLayoutX(30.0);
			coursePane.setLayoutY(0.0 + offset * 60.0);
			coursePane.getStyleClass().add("Pane");
			coursePane.setPrefHeight(55.0);
			coursePane.setPrefWidth(452.0);
			
			Label courseName = new Label();
			courseName.setText(tempCourse.courseName);
			courseName.setFont(new Font(18.0));
			courseName.setLayoutX(33.0);
			courseName.setLayoutY(17.0);
			courseName.setTextFill(Paint.valueOf("GREY"));
			
			Button moreBtn = new Button("more...");
			moreBtn.setLayoutX(378.0);
			moreBtn.setLayoutY(32.0);
			moreBtn.setMnemonicParsing(false);
			moreBtn.setPrefWidth(70.0);
			moreBtn.setTextAlignment(TextAlignment.LEFT);
			moreBtn.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event) 
				{
					try 
					{
						gotoLecture(event,tempCourse);
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			});
			
			coursePane.getChildren().addAll(courseName,moreBtn);
			
			courses.getChildren().add(coursePane);
			
			offset++;
		}
		
		courses.setPrefHeight(60.0*offset+30.0);
	}
	
	public void gotoLecture(ActionEvent event,Course course) throws IOException 
	{
		SceneStatus.changeStatus(course.courseName, Services.crawler.courseList.indexOf(course));
		
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	    BorderPane sceneLayout = new BorderPane();
	    stage.setTitle("Lecture");
	    sceneLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));
	    
	    Scene scene = new Scene(sceneLayout, 800, 500);
	    stage.setScene(scene);
	    AnchorPane myLayout = FXMLLoader.load(getClass().getResource("view/Lecture.fxml")); 
	    
	    stage.show();
	    sceneLayout.setCenter(myLayout);
	}	
}
