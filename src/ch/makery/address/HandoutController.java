package ch.makery.address;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import ntou.basic.Course;
import ntou.basic.Handout;
import ntou.basic.SceneStatus;

public class HandoutController implements Initializable
{
	@FXML
	private AnchorPane handouts;
	
	@FXML
	private Label title;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		String courseName = SceneStatus.statusString;
		int index = SceneStatus.statusCode;
		
		SceneStatus.resetStatus();
		
		title.setText("Á¿¸q - " + courseName);
		title.setPrefWidth(452.0);
		title.setAlignment(Pos.CENTER);
		title.setTextAlignment(TextAlignment.CENTER);
		
		Course course = Services.crawler.courseList.get(index);
		
		Iterator it = course.handoutList.iterator();
		
		int offset = 0;
		
		while(it.hasNext())
		{
			Handout tempHandout = (Handout) it.next();
			
			Pane handoutPane = new Pane();
			handoutPane.setLayoutX(30.0);
			handoutPane.setLayoutY(0.0 + offset * 60.0);
			handoutPane.setPrefHeight(55.0);
			handoutPane.setPrefWidth(452.0);
			handoutPane.getStyleClass().add("Pane");
			handoutPane.getStylesheets().add("@LectureTheme.css");
			
			Label handoutName = new Label();
			handoutName.setLayoutX(33.0);
			handoutName.setLayoutY(17.0);
			handoutName.setText(tempHandout.fileName);
			handoutName.setTextFill(Paint.valueOf("GREY"));
			handoutName.setFont(new Font(18.0));
			
			Button openFile = new Button("¶}±Ò");
			openFile.setAlignment(Pos.CENTER_LEFT);
			openFile.setTextAlignment(TextAlignment.LEFT);
			openFile.setLayoutX(378.0);
			openFile.setLayoutY(32.0);
			openFile.setMnemonicParsing(false);
			openFile.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent arg0) 
				{
					Desktop dt = Desktop.getDesktop();
					
					try
					{
						dt.open((File)tempHandout);
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				
			});
			
			handoutPane.getChildren().addAll(handoutName,openFile);
			handouts.getChildren().add(handoutPane);
			
			offset++;
		}
		
		handouts.setPrefHeight(60.0*offset+30.0);
	}
}
