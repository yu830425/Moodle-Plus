package ch.makery.address;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.eclipse.jetty.util.security.Password;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ntou.chupei.Crawler;

public class IndexController  implements Initializable 
{	
	@FXML
	private TextField acc;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label status;
	
	private Services services;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		services = new Services();
	}
	
	public void login(ActionEvent event) throws IOException 
	{
		
		if(services.initCrawler(acc.getText(),password.getText()))
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
		else
		{
			status.setText("µn¤J¥¢±Ñ");
		}
	}
	 
}
