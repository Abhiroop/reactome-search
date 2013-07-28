/*
 * Below is the EBI search client for Reactome which given a user input
 * for a Reaction or pathway or disease returns the corresponding reactome id
 * as per the EBI search engine.
 */
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.rpc.ServiceException;
/**
 * EBI Search web service client.
 *
 * @author Abhiroop Sarkar
 * @version 1.0
 * GSOC 2013 Project
 */
public class SearchClient 
{
	// "Domain" in this sense means the name of the index in the EBI search engine
	String domain_name="reactome";
	EBeyeClient obj;
	public SearchClient()
	{
		obj=new EBeyeClient();
	}
	public static void main(String[] args) throws Exception
	{
        String query="";
        for(int z=0;z<args.length;z++)
        {
        	query=query+args[z]+" ";
        }
        String trimmed = query.trim();
        int words = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
        String[] desc=trimmed.split("\\s+");
		if (words == 0) {
            throw new IllegalArgumentException("Please pass in a search term or sequence");
        }

        boolean includeDescription = false;
        if (words > 1) {
            includeDescription = Boolean.valueOf(desc[1]);
        }

       SearchClient search = new SearchClient();

    
        Page page = search.search(query, 1, 10, includeDescription);
        List<Record> records = page.getRecords();

        if (page.count > 0) {
            System.out.println("Found " + page.count + " results for '" + query + "'.");
            if (records.size() > 9) {
                System.out.println("Showing results 1 to " + records.size() + ":");
            }
            System.out.println();
            for (Record r : records) {
                System.out.println(r.name + " (" + r.id + ")");
                if (r.description != null) 
                {
                    System.out.println(r.description);
                    System.out.println("-------------------------------------------------------------------------------");
                }
            }
        }
        else {
            System.out.println("No results for '" + query + "'.");
        }

    }
	   public Page search(String query, int pageNumber, int resultsPerPage, boolean includeDescription) 
	   {
	        try {
	            //return client.listDomains();
	            List<Record> records = new ArrayList<Record>();
	            int count = obj.getNumberOfResults(domain_name, query);
	            if (count > 0) {
	                List<String> f = new ArrayList<String>();
	                f.add("id");
	                f.add("name");
	                if (includeDescription) {
	                    f.add("description");
	                }
	                String[] fields = f.toArray(new String[f.size()]);
	                String[][] results = obj.getResults(domain_name, query, fields, pageNumber - 1, resultsPerPage);
	                for (String[] a : results) {
	                    String id = null, name = null, description = null;
	                    int len = a.length;
	                    if (len > 0) {
	                        id = a[0];
	                        if (len > 1) {
	                            name = a[1];
	                            if (len > 2) {
	                                description = a[2];
	                            }
	                        }
	                    }
	                    else {
	                        throw new IllegalStateException("No results, yet result count reported as " + count);
	                    }
	                    records.add(new Record(id, name, description));
	                }
	            }
	            return new Page(count, records);
	        }
	        catch (RemoteException e) {
	            throw new RuntimeException(e);
	            
	        }
	        catch (ServiceException e) {
	            throw new RuntimeException(e);
	        }
	    }
}
