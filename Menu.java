import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class Menu implements ActionListener
{
    private String name;
    private JFrame frame2,frame3,frame4;
    private JButton bborrow,breturn,blogout,bconfirm,bcancel;
    private JLabel label1;
    private JTextField field3 = new JTextField();
    private JTextField field4 = new JTextField();
    private JTextField field5 = new JTextField();
    
    public Menu(){
    }
    
    //Normal constructor
    public Menu(String n)
    {
        frame2 = new JFrame("Welcome to Jasin Library!");
        frame3 = new JFrame("Jasin Library - BORROW");
        frame4 = new JFrame("Jasin Library - RETURN");
        bborrow = new JButton("BORROW");
        breturn = new JButton("RETURN");
        blogout = new JButton("LOGOUT");
        bconfirm = new JButton("CONFIRM");
        bcancel = new JButton("CANCEL");
        label1 = new JLabel("Enter book ISBN (max. 3 books):");
        name=n;
        menuGUI();
    }
    
    public String getbookTitle(){
        return name;
    }
    
    //GUI
    public void menuGUI(){
        JLabel welcome = new JLabel("HELLO, " + name + "!");
        //Menu
        frame2.setSize(600,300);
        frame2.setLocation(430,250);
        frame2.setLayout(null);
        frame2.add(welcome);
        frame2.add(bborrow);
        frame2.add(breturn);
        frame2.add(blogout);
        welcome.setBounds(10,10,350,60);
        bborrow.setBounds(250,80,100,30);
        breturn.setBounds(250,110,100,30);
        blogout.setBounds(250,140,100,30);
        bborrow.addActionListener(this);
        breturn.addActionListener(this);
        blogout.addActionListener(this);
        
        //Borrow book
        frame3.setSize(600,300);
        frame3.setLocation(430,250);
        frame3.setLayout(null);
        frame3.add(label1);
        frame3.add(field3);
        frame3.add(field4);
        frame3.add(field5);
        frame3.add(bconfirm);
        frame3.add(bcancel);
        label1.setBounds(210,10,350,60);
        field3.setBounds(210,70,180,20);
        field4.setBounds(210,100,180,20);
        field5.setBounds(210,130,180,20);
        bconfirm.setBounds(205,200,90,30);
        bcancel.setBounds(305,200,90,30);
        bconfirm.addActionListener(this);
        bcancel.addActionListener(this);
        
        //Return book
        frame4.setSize(600,300);
        frame4.setLocation(430,250);
        frame4.setLayout(null);
        
        
        frame2.setVisible(true);
    }
    
    //Action buttons
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == bborrow){
            frame2.dispose();
            field3.setText("");
            field4.setText("");
            field5.setText("");
            frame3.setVisible(true);
        }
        if(ae.getSource() == breturn){
            frame2.dispose();
        }
        if(ae.getSource() == blogout){
            LibraryApp library = new LibraryApp();
            frame2.dispose();
        }
        if(ae.getSource() == bconfirm){
            try{
                BufferedReader br2 = new BufferedReader(new FileReader("text file\\available.txt"));
                String input = null,title[] = new String[6],author[] = new String[6],isbn[] = new String[6];
            
                BorrowBook b[] = new BorrowBook[6];
                //Read from text file "available.txt"
                int i=0;
                while((input=br2.readLine()) != null){
                    StringTokenizer st2 = new StringTokenizer(input,",");
                    title[i] = st2.nextToken();
                    author[i] = st2.nextToken();
                    isbn[i] = st2.nextToken();
                
                    b[i] = new BorrowBook(title[i],author[i],isbn[i]);
                    i++;
                }
                //Close text file "available.txt"
                br2.close();
                
                //Get and convert data received from text field
                String book1 = field3.getText();
                String book2 = field4.getText();
                String book3 = field5.getText();
                
                //If the book is available, write the book details to text file "notavailable.txt"
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("text file\\notavailable.txt",true)));
                if(book1.equals("") && book2.equals("") && book3.equals("")){
                    JOptionPane.showMessageDialog(null,"Please enter at least 1 book!");
                }
                int x=0,z=0;
                for(i=0; i<6; i++){
                    if(book1.equals(isbn[i]) || book2.equals(isbn[i]) || book3.equals(isbn[i])){
                        pw.println();
                        if(book1.equals(isbn[i]) && !book1.equals(book2) && !book1.equals(book3)){
                            pw.print(title[i] + "," + author[i] + "," + isbn[i]);
                            z=1;
                        }
                        else if(book2.equals(isbn[i]) && !book2.equals(book1) && !book2.equals(book3)){
                            pw.print(title[i] + "," + author[i] + "," + isbn[i]);
                            z=1;
                        }
                        else if(book3.equals(isbn[i]) && !book3.equals(book1) && !book3.equals(book2)){
                            pw.print(title[i] + "," + author[i] + "," + isbn[i]);
                            z=1;
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"You've entered the same book more than 1.\nTry again!");
                            field3.setText("");
                            field4.setText("");
                            field5.setText("");
                            z=0;
                        }
                    }
                    else{
                        if(!book2.equals("") || !book3.equals("")){
                            x=1;
                        }
                    }
                }
                //Close output file "notavailable.txt"
                pw.close();
                
                if(x == 1){
                    JOptionPane.showMessageDialog(null,"One or more book you've entered is NOT AVAILABLE. \nTry again.");
                    field3.setText("");
                    field4.setText("");
                    field5.setText("");
                }
                
                if(z == 1){
                    JOptionPane.showMessageDialog(null,"Booking success!");
                    frame3.dispose();
                    frame2.setVisible(true);
                }
                
                //Read from text file "notavailable.txt"
                BufferedReader br3 = new BufferedReader(new FileReader("text file\\notavailable.txt"));
                i=0;
                String title2[] = new String[6],author2[] = new String[6],isbn2[] = new String[6];
                while((input=br3.readLine()) != null){
                    StringTokenizer st3 = new StringTokenizer(input,",");
                    title2[i] = st3.nextToken();
                    author2[i] = st3.nextToken();
                    isbn2[i] = st3.nextToken();
                
                    b[i] = new BorrowBook(title[i],author[i],isbn[i]);
                    i++;
                }
                br3.close();
                
                //Re-write "available.txt"
                PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter("text file\\available.txt")));
                for(i=0; i<6; i++){
                    if(!book1.equals(isbn2[i]) && !book2.equals(isbn2[i]) && !book3.equals(isbn2[i])){
                        pw2.println(title2[i] + "," + author2[i] + "," + isbn2[i]);
                    }
                }
                pw2.close();
            }
            catch(EOFException eof){
                JOptionPane.showMessageDialog(null,"Error!");
            }
            catch(FileNotFoundException fnf){
                JOptionPane.showMessageDialog(null,"File Not Found!");
            }
            catch(IOException io){
                JOptionPane.showMessageDialog(null,"Error!");
            }
        }
        if(ae.getSource() == bcancel){
            JOptionPane.showMessageDialog(null,"You have canceled the operation.");
            frame3.dispose();
            frame2.setVisible(true);
        }
    }
}
