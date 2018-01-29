package kolokwium;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;


public class Questions extends JFrame implements Runnable
{
	
	private JPanel contentPane; 
	private JTextArea textArea;
	private JButton btnDalej;
	private ButtonGroup bgroup;
	private JToggleButton btnA;
	private JToggleButton btnB;
	private JToggleButton btnC;
	private JToggleButton btnD;

	 String[] a;
	 String[] q;
	static int score=0;
	int count=0;
	int c=0;

	public static void main(String[] args) throws IOException
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try 
				{
					Questions frame = new Questions();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
		});
		
	}
	
	public Questions() throws FileNotFoundException, IOException {
		setTitle("Kolokwium");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 515, 114);
		contentPane.add(textArea);
        
        bgroup=new ButtonGroup();
        
		btnA=new JToggleButton();
		btnA.setForeground(new Color(255, 255, 255));
		btnA.setBackground(new Color(0, 0, 0));
		btnA.setBounds(10, 130, 70, 20);
		bgroup.add(btnA);
        btnA.setText("option1");
        btnA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	selectedBtnA(e);
            }
        });
        contentPane.add(btnA);
		
		
		btnB=new JToggleButton();
		btnB.setForeground(new Color(255, 255, 255));
		btnB.setBackground(new Color(0, 0, 0));
        btnB.setBounds(85, 130, 70, 20);
        bgroup.add(btnB);
        btnB.setText("option2");
        btnB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	selectedBtnB(e);
            }
        });
        contentPane.add(btnB);
		
		
		btnC=new JToggleButton();
		btnC.setForeground(new Color(255, 255, 255));
		btnC.setBackground(new Color(0, 0, 0));
		btnC.setBounds(160, 130, 70, 20);
		bgroup.add(btnC);
	    btnC.setText("option3");
	    btnC.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	selectedBtnC(e);
	            }
	        });
	    contentPane.add(btnC);
	    
		btnD=new JToggleButton();
		btnD.setForeground(new Color(255, 255, 255));
		btnD.setBackground(new Color(0, 0, 0));
		btnD.setBounds(235, 130, 70, 20);
		bgroup.add(btnD);
	    btnD.setText("option4");
	    btnD.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	selectedBtnD(e);
	            }
	        });
	    contentPane.add(btnD);
	    
		btnDalej=new JButton();
		btnDalej.setForeground(new Color(255, 255, 255));
		btnDalej.setBackground(new Color(0, 0, 0));
		btnDalej.setBounds(432, 130, 78, 20);
        btnDalej.setText("Dalej");
        btnDalej.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDalejClicked(e);
            }
        });
        contentPane.add(btnDalej);
        
//        textArea_1 = new JTextArea();
//        textArea_1.setBounds(363, 30, 116, 57);
//        contentPane.add(textArea_1);
//        
//  
//        try (BufferedReader br = new BufferedReader(new FileReader("bazaPytan.txt"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//               textArea_1.setText(line);
//            }
//        }
//        
      q =new String[7];
     
      q[0]="Ktore z ponizszych NIE jest jezykiem programowania? \n\nA) HTML \nB) JAVA \nC) C++ \nD) Python";  
      q[1]="Program do tlumaczenia kodu zrodlowego w jezyku programowania na jezyk maszynowy? \n\nA) Translator \nB) Slownik \nC) Kompilator \nD) Generator";
      q[2]="Kto jest generalnym dyrektorem Googlea? \n\nA) Elon Mask \nB) Sundar Pichai \nC) Larry Page \nD) Linus Torvalds";
      q[3]="Kto jest tworca jadra Linux oraz systemu kontroli wersji git.? \n\nA) Elon Mask \nB) Sundar Pichai \nC) Larry Page \nD) Linus Torvalds";     
      q[4]="Klasa potomna dziedziczy po pierwotnej: \n\nA) Metody \nB) Wszystkie metody i pola skladowe \nC) Pola skladowe  \nD) Inne";     
      q[5]="Jedno potokowy system operacyjny pracujacy tylko w srodowisku tekstowym to: \n\nA) Linux \nB) Mac OS \nC) Windows  \nD) DOS";   
      q[6]="Kto jest prodziekanem WIEiKu? \n\nA) M.Drabowski  \nB) W.Samotyj \nC) A.Szromba \nD) A.Schwarzenegger ";  
      
     
      a=new String[7];
      a[0]="A";
      a[1]="C";
      a[2]="B";
      a[3]="D";
      a[4]="B";
      a[5]="D";
      a[6]="A";

      
      start(0);
//      for (int i=0;i<7;i++) {
//			textArea.setText(q[i]);
//			 btnA.setText("A");
//		        btnB.setText("B");
//		        btnC.setText("C");
//		        btnD.setText("D");
		}
//     
//      try {
//
//          new Thread(new Questions()).start(); }
//      catch(IOException e) {
//    	  
//      }
      
      
	
	

	protected void btnDalejClicked(ActionEvent e) {
		
		if(getSelectedButtonText(bgroup)==a[c]) {
			score++;
			c++;
		}
		else
		{
			c++;
		}
			if(c!=7)
		{
				start(c);
		System.out.println(score);
		}
		else {
			System.out.println(score);
			new Results().setVisible(true);
			this.dispose();
		}
	}
	protected void selectedBtnD(ActionEvent e) {
	
		
	}
	protected void selectedBtnC(ActionEvent e) {
	
		
	}
	protected void selectedBtnB(ActionEvent e) {
		
		
	}
	protected void selectedBtnA(ActionEvent e) {
	
		
	}
	String getSelectedButtonText(ButtonGroup buttonGroup) {
		    for (Enumeration buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
		        AbstractButton button = (AbstractButton) buttons.nextElement();

		        if (button.isSelected()) {
		            return button.getText();
		        }
		    }
		    return null;
	}
	public void start(int c) {
		
		
		 textArea.setText(q[c]);
	        btnA.setText("A");
	        btnB.setText("B");
	        btnC.setText("C");
	        btnD.setText("D");
	        bgroup.clearSelection();


		
	}
	private static String format(int i) {
        String result = String.valueOf(i);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }

	@Override
	public void run() {
		
		try {
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
		

	

		 }
