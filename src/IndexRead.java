import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class IndexRead {
    public static void main(String[] arg) throws IOException, ParseException, org.apache.lucene.queryParser.ParseException {
       // the text you want to search
        String query = "kandy";
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
        Directory directory = FSDirectory.open(new File("/home/abhiroop/Java_Workspace/GSOC/index"));
       //here you must specify the column you are searching as you put it in the index file
        Query q = new QueryParser(Version.LUCENE_36, "name", analyzer).parse(query);
        int hitsPerPage = 10;
        IndexReader reader = IndexReader.open(directory);
        @SuppressWarnings("resource")
		IndexSearcher searcher = new IndexSearcher(reader);
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
        //display result
        System.out.println("Found " + hits.length + " hits.");
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println("Found a record id:[" + d.get("id") + "] \t code:[" + d.get("code")+"] \t name:["+ d.get("name") +"] \t description:["+ d.get("comp") +"]");
        }
    }
}