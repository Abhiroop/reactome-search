import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;
public class SearchClient 
{
	String domain_name="reactome";
	EBeyeClient obj;
	public SearchClient()
	{
		obj=new EBeyeClient();
	}
	public static void main(String[] args) 
	{
        if (args.length == 0) {
            throw new IllegalArgumentException("Please pass in a search term or sequence");
        }

        String query = args[0];
        boolean includeDescription = false;
        if (args.length > 1) {
            includeDescription = Boolean.valueOf(args[1]);
        }

       SearchClient search = new SearchClient();

        // TODO: Add paging and get input from keyboard to show next page
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
