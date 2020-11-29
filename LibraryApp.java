import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class LibraryApp
{
    private JFrame frame = new JFrame();
    private JLabel text1 = new JLabel();
    private JLabel text2 = new JLabel();
    private JTextField field1 = new JTextField();
    private JPasswordField field2 = new JPasswordField();
    private JButton blogin = new JButton();
    
    public static void main(String args[]){
        LibraryApp library = new LibraryApp();
        library.prepareGUI();
    }
    
    public LibraryApp(){
        frame = new JFrame("Login Authentification");
        text1 = new JLabel("Student ID:");
        text2 = new JLabel("Password:");
        blogin = new JButton("LOGIN");
        prepareGUI();
        loginButton();
    }
    
    public void prepareGUI(){
      frame.setSize(350,200);
      frame.setLocation(470,280);
      frame.setLayout(null);
     
      //Set position
      text1.setBounds(20,30,150,20);
      text2.setBounds(20,65,150,20);
      field1.setBounds(110,30,150,20);
      field2.setBounds(110,65,150,20);
      blogin.setBounds(110,100,80,20);
      
      //Display
      frame.add(text1);
      frame.add(text2);
      frame.add(field1);
      frame.add(field2);
      frame.add(blogin);
      frame.setVisible(true);
      
    }
    
    public void loginButton()
    {
        blogin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try
                {
                    //Open and read data from text file named "student.txt"
                    BufferedReader br = new BufferedReader(new FileReader("text file\\student.txt"));
                    String input = null;
                    String studID=null,studPass=null,studName=null;
                    int i=0;
                    while((input = br.readLine()) != null)
                    {
                        //Read and  assign data from text file
                        StringTokenizer st = new StringTokenizer(input,",");
                        studID = st.nextToken();
                        studPass = st.nextToken();
                        studName = st.nextToken();
                    
                        //Get string from field text
                        String pUser = field1.getText();
                        String pPass = field2.getText();
                        
                        //The ID & password are the same as in text file
                        if(pUser.equals(studID) && pPass.equals(studPass)) {
                            Menu displayMenu = new Menu(studName);
                            JOptionPane.showMessageDialog(null,"Login Successfull! :)");
                            frame.dispose();
                            i=1;
                        }
                    }
                    //The ID & password are not same as in input file
                    if(i == 0)
                    {
                      JOptionPane.showMessageDialog(null,"Password / Username is Incorrect!");
                      field1.setText("");
                      field2.setText("");
                      field1.requestFocus();
                    }
                    
                    br.close();
                }
                catch(EOFException eof)
                {
                    JOptionPane.showMessageDialog(null,"Error!");
                }
                catch(FileNotFoundException fnf)
                {
                    JOptionPane.showMessageDialog(null,"File Not Found!");
                }
                catch(IOException io)
                {
                    JOptionPane.showMessageDialog(null,"Error!");
                }
            }
        });
    }
}
