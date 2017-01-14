package ch.makery.address;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CalendarController implements Initializable
{
	
	@FXML
	Text yearAndMonth,nowDate;
	@FXML
	TextField chooseYear;
	@FXML
	ComboBox<Integer> chooseMonth;
	@FXML
	Pane datePane;
	@FXML
	Pane mainPane;
	@FXML
	TextArea mainText;
	@FXML
	Button search,clear,save;
	
	private int chooseDate;
	ObservableList<Integer> months = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		getdate();
		chooseYear.setText("2017");
		chooseMonth.setValue(1);
		setdate();
		date_btn_create(Integer.parseInt(chooseYear.getText()),chooseMonth.getValue());
		search.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent event) {
						mainPane.getChildren().remove(datePane);
						date_btn_create(Integer.parseInt(chooseYear.getText()),chooseMonth.getValue());
						setdate();
					}
			
				});
		clear.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>()
		{
				@Override
				public void handle(MouseEvent evt) {
		
					mainText.setText("");
					String filename,insert_str;
					int btn_date = chooseDate;
					filename = new String().format("%d%02d%02d",Integer.parseInt(chooseYear.getText()),chooseMonth.getValue(),btn_date);
					File file=new File(filename+".txt");
					file.delete();
				}
		});
		save.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent evt){
			    String filename,insert_str;
			    System.out.println(((Button)evt.getSource()).getId());
			    int btn_date = chooseDate;
			    filename = new String().format("%d%02d%02d",Integer.parseInt(chooseYear.getText()),chooseMonth.getValue(),btn_date);
			    insert_str = mainText.getText();
			    if (insert_str.length() != 0 )
			    {
			      try
			      {
			        FileWriter fw=new FileWriter(filename+".txt");
			        BufferedWriter bfw=new BufferedWriter(fw);
			        bfw.write(insert_str); //撠extarea�摰孵神�蝺抵��鋆�
			        bfw.flush();//撠楨銵�鞈�神�瑼��
			        fw.close();//������
			        /*jLabel6.setText("銵��歇閮��");//閮剖����
			        jLabel7.setText("");
			        jLabel8.setText("���������");
			        new_btn();*/
			      }catch(IOException e)
			      {
			        e.printStackTrace();
			      }
			    }
			    /*else//��閮�摰寞�������
			    {
			      if (day.length() == 0)
			        jLabel6.setText("�������");//閮剖����
			      else
			        jLabel6.setText("銵���摰�");
			    }*/
			  }//�摮����孛�鈭辣蝯��
	    //new_btn();//��������
	    //jLabel6.setText("銵��歇皜");//閮剖����
	});
		
	}
	
	public void date_btn_create(int year,int month)//��������
	 {
		 
	    int y=0,x=0,x_add=0,y_add=0,week_add=0,date_acc=0,week_of_day=0;
	    String syear,smonth,sday,filename;
	    syear = String.valueOf(year);
	    smonth = String.valueOf(month);
	    if (smonth.length() == 1)//�撠10���(銝�雿)撠勗�����0
	      smonth = "0"+smonth;
	    //jDesktopPane1.remove(jDesktopPane2);//蝘駁獢2(���������停�������宏�)
	    datePane = new Pane();//��������
	    //jDesktopPane1.add(jDesktopPane2);
	    datePane.setLayoutX(18);
	    datePane.setLayoutY(89); 
	    datePane.setPrefWidth(316);
	    datePane.setPrefHeight(237);//閮剖�之撠��蔭
	    
	    datePane.setStyle("-fx-background-color:#83efb4");	      
	    
	    switch(month)//閮剖��遢憭拇
	    {
	      case 1://憭扳��31憭�
	      case 3:
	      case 5:
	      case 7:
	      case 8:
	      case 10:
	      case 12:
	        date_acc = 31;
	        break;
	        
	      case 4://撠��30憭�
	      case 6:
	      case 9:
	      case 11:
	        date_acc = 30;
	        break;
	        
	      case 2:
	        if (leap_year(year))
	          date_acc = 29;
	        else
	          date_acc = 28;
	    }
	    
	    week_of_day = dow(year,month,1);//������(�����洵銝�憭拍���嗾)
	    
	    switch(week_of_day)//閮剖����1�雿蔭(����)
	    {
	      case 0:
	        week_add = 0;
	        break;
	      case 1:
	        week_add = 34;
	        break;
	      case 2:
	        week_add = 68;
	        break;
	      case 3:
	        week_add = 97;
	        break;
	      case 4:
	        week_add = 131;
	        break;
	      case 5:
	        week_add = 160;
	        break;
	      case 6:
	        week_add = 190;
	        break;
	    }
	    
	    Button btn[] = new Button[date_acc];//撱箇��������
	    for (int i=0;i<date_acc;i++)//撱箇����������
	    {
	      btn[i] = new Button();//撱箇��������
	      btn[i].setText(String.valueOf(i+1));//閮剖���������
	      if ((i-week_of_day>0 && (i+week_of_day)%7==0) || ((i+week_of_day)%7==0 && i != 0))
	      {//閮剖���洵銝�憭拇�����蔭
	        x=0;//X頠詨漣璅�
	        x_add=0;//銝����漣璅�(X頠�)����
	        y++;//Y頠詨漣璅�
	        y_add+=10;//���漣璅�(Y頠�)����
	        week_add=0;//���洵銝�����漣璅���
	      }//銝閮剖���之撠����(X�韏瑕��10+蝚砍嗾����*璈怠�祝摨�25+����+���洵銝�憭拇��嗾����)
	      btn[i].setLayoutX((int)((x*23+x_add+week_add)*1.5));
	      btn[i].setLayoutY((int)(1.5*(y*20+y_add)));
	      btn[i].setPrefWidth(36);
	      btn[i].setPrefHeight(28);//(Y�蝚砍嗾����*擃漲20+������)���祝�25擃20
	      btn[i].setFont(new Font("arial",6));//閮剖���之撠�見撘�
	      btn[i].setId(String.valueOf(i+1));
	      final int s = i;
	      btn[i].addEventHandler(MouseEvent.MOUSE_CLICKED,(new EventHandler<MouseEvent>() {//閮剖����(閫貊鈭辣��)
		      
				@Override
				public void handle(MouseEvent event) {
					
					mainText.setText("");
				    int year,month;
				    String filename,read_str;
				    year = Integer.parseInt(chooseYear.getText());
				    month = chooseMonth.getValue();
				    chooseDate = Integer.parseInt(((Button)event.getSource()).getId());
				    filename = new String().format("%d%02d%02d",year,month,chooseDate);
				    nowDate.setText(new String().format("%d 年 %d 月 %d 日",year,month,chooseDate));
				    try
				    {
				      FileReader fr = new FileReader(filename+".txt");//霈������������
				      BufferedReader bfr = new BufferedReader(fr);//撠����蝺抵��
				      boolean flag=false;//����
				      while((read_str = bfr.readLine())!=null) // 瘥活霈����銵��瑼����
				      {
				        if (flag)//敺洵鈭�����銵洵銝���蔭���銵�
				        	mainText.appendText("\n");
				        mainText.appendText(read_str);//��閰脰��
				        flag=true;
				        
				      }
				      
				      fr.close();
				    }catch(FileNotFoundException e)//憒����������停�����銵���(靘����)
				    {
				      ;
				    }catch(IOException e)//靘����
				    {
				      e.printStackTrace();
				    }
					
				}
	      		}
	     ));
	     //btn[i].setBorder(BorderFactory.createTitledBorder(""));//閮剖��������矽�憭批��
	      int[] now = new int[3];
	      now = getdate();//���憭拇���
	      if (year == now[0] && month == now[1] && i+1 == now[2])
	        btn[i].setStyle("-fx-background-color:RGB(233,238,164)");//���憭拙�身摰��暺
	      else
	        btn[i].setStyle("-fx-background-color:RGB(148,205,176)");//�銝�憭拙�身摰��蝬
	        
	      sday = String.valueOf(i+1);
	      filename = new String().format("%d%02d%02d",Integer.parseInt(syear),Integer.parseInt(smonth),Integer.parseInt(sday));
	      //閮����迂(撟�+���+�.txt)
	      File file=new File(filename+".txt");//撱箇���隞�
	      if (file.exists())//��憭拇������身摰��������
	        btn[i].setStyle("-fx-background-color:RGB(0,0,255)");
	      
	      x++;//銝����摨扳����
	      x_add+=9;//銝������摨扳����
	      datePane.getChildren().add(btn[i]);//��獢2銝�
	    }
	    mainPane.getChildren().add(datePane);
	  }
	
	public boolean leap_year(int year)//����僑������
	  {
	    boolean leap_year;//4���嚗�100�������4�������僑
	    if (year%4 == 0)
	    {
	      if (year%100 == 0)
	      {
	        if (year%400 == 0)
	          leap_year = true;
	        else
	          leap_year = false;
	      }
	      else
	        leap_year = true;
	    }
	    else
	      leap_year = false;
	    return leap_year;
	  }//����僑��蝯��
	  
	public int dow(int y,int m,int d)//�����嗾
	  {
	    int[] ww={6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};//憭拇�������
	    int w;
	    w=ww[m-1]+y+(y/4)-(y/100)+(y/400);//��僑閮剖��
	    if( ((y%4)==0) && (m<3) )//�撘�
	    {
	      w--;
	      if((y%100)==0) w++;
	      if((y%400)==0) w--;
	    }
	    return (w+d)%7;//�����嗾(0����嚗�1�����隞交迨憿)
	  }
	
	public int[] getdate()//���頂蝯望�������
	{
	    int[] date_array = new int[3];
	    Calendar ca = new GregorianCalendar();  
	    date_array[0] = ca.get(Calendar.YEAR);//撟�
	    date_array[1] = ca.get(Calendar.MONTH)+1;//���
	    date_array[2] = ca.get(Calendar.DAY_OF_MONTH);//�
	    
		chooseMonth.setItems(months);
		return date_array;
	    
    }//���頂蝯望���蝯��
	
	public void setdate()
	{
		
		 yearAndMonth.setText(String.format("%d年%d月",Integer.parseInt(chooseYear.getText()),chooseMonth.getValue()));
	}
	
	

}