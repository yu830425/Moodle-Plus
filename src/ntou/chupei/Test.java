package ntou.chupei;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Test extends JFrame {

    private JPanel contentPane;

    

    public Test(String mesg) {

        
        
                ToastMessage toastMessage = new ToastMessage(mesg,3000);
                toastMessage.setVisible(true);
         
    }

}