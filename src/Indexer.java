import java.sql.DriverManager;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Field;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;


public class Indexer {
	public static void main(String[] args) throws Exception {

	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/reactome", "root", "");
	    StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
	    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, analyzer);
	    IndexWriter writer = new IndexWriter(FSDirectory.open(INDEX_DIR), config);

	    String query = "SELECT id, name FROM tag";
	    Statement statement = (Statement) connection.createStatement();
	    ResultSet result = (ResultSet) statement.executeQuery(query);

	    while (result.next()) {
	        Document document = new Document();
	        document.add((Fieldable) new Field("id", result.getString("id"), Field.Store.YES, Field.Index.NOT_ANALYZED));
	        document.add((Fieldable) new Field("name", result.getString("name"), Field.Store.NO, Field.Index.ANALYZED));
	        writer.updateDocument(new Term("id", result.getString("id")), document);
	    }

	    writer.close();

	}

}
