
public class BorrowBook
{
    protected String btitle,bauthor,bisbn;
    
    public BorrowBook(String a, String b, String c){
        btitle = a;
        bauthor = b;
        bisbn = c;
    }
    
    public void setTitle(String t){
        btitle = t;
    }
    
    public void setAuthor(String au){
        bauthor = au;
    }
    
    public void setISBN(String i){
        bisbn = i;
    }
    
    public String getTitle(){
        return btitle;
    }
    
    public String getAuthor(){
        return bauthor;
    }
    
    public String getISBN(){
        return bisbn;
    }
}
