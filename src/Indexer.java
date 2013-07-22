import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Indexer {
	public static void main(String [] args) throws Exception
	{
	 //create a file which will hold the index files.make sur the path is correct
    File file = new File("/home/abhiroop/Java_Workspace/GSOC/index");
    //establish the mysql connection
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");
    StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, analyzer);
    IndexWriter writer = new IndexWriter(FSDirectory.open(file), config);
    String query = "select * from stock left join stock_detail on stock.STOCK_ID = stock_detail.STOCK_ID";
    Statement statement = (Statement) connection.createStatement();
    ResultSet result = (ResultSet) statement.executeQuery(query);
    //according to the results create the index files
    while (result.next()) {
        System.out.println("Retrieved ID:["+result.getString("STOCK_ID")+"]");
        Document document = new Document();
    //add the fields to the index as you required
        document.add((Fieldable) new Field("id", result.getString("STOCK_ID"), Field.Store.YES, Field.Index.NOT_ANALYZED));
        document.add((Fieldable) new Field("code", result.getString("STOCK_CODE"), Field.Store.YES, Field.Index.ANALYZED));
        document.add((Fieldable) new Field("name", result.getString("STOCK_NAME"), Field.Store.YES, Field.Index.ANALYZED));
        document.add((Fieldable) new Field("comp", result.getString("COMP_DESC"), Field.Store.YES, Field.Index.ANALYZED));
       //create the index files
        writer.updateDocument(new Term("id", result.getString("STOCK_ID")), document);
    }
    writer.close();
}		
}


