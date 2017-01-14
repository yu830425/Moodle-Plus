package ntou.chupei;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.border.LineBorder;
 
public class ToastMessage extends JDialog {
    int miliseconds;
    public ToastMessage(String toastString, int time) {
        this.miliseconds = time;
        setUndecorated(true);
        getContentPane().setLayout(new BorderLayout(0, 0));//�ŧi�s��layout BorderLayout �޲ztoast��m
        //�]�wtoast (toast=(panel+label))�˦�
        JPanel panel = new JPanel();
        //panel.setSize(1000,1000);
        panel.setBackground(Color.GRAY);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel toastLabel = new JLabel("");
        toastLabel.setText(toastString);
        toastLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        toastLabel.setForeground(Color.WHITE);

        setBounds(100, 100, toastLabel.getPreferredSize().width+20, 31);
        
        setAlwaysOnTop(true);
        //�ھڿù��j�p��toast�X�{��m
        //getSize()�|���toast��x y
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("width: " + dim.width);
        System.out.println("height: " + dim.height);
        System.out.println("width: " + getSize().width);
        System.out.println("height: " + getSize().height);
        setLocation(dim.width-getSize().width, dim.height-getSize().height-50);
        panel.add(toastLabel);
        setVisible(false);
        
        //�g�L miliseconds ��|�Ntoast����
        new Thread(){
            public void run() {
                try {
                    Thread.sleep(miliseconds);
                    dispose();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
