import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.gk.model.GKInstance;
import org.gk.model.ReactomeJavaConstants;
import org.gk.persistence.MySQLAdaptor;


public class DataAccess 
{
	public static List<String> Reaction(String query)
	{
		List<String> result = new ArrayList<String>();
		try
		{
			MySQLAdaptor dba = new MySQLAdaptor("localhost","Reactome","root","passworld",3306);
			@SuppressWarnings("unchecked")
			Collection<GKInstance> instances=dba.fetchInstanceByAttribute(ReactomeJavaConstants.Reaction, ReactomeJavaConstants._displayName,"like", query);
			for(GKInstance instance:instances)
			{
				GKInstance species=(GKInstance)instance.getAttributeValue(ReactomeJavaConstants.species);
				result.add((instance + "[" +species.getDisplayName()+"]"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public static List<String> Pathway(String query)
	{
		List<String> result = new ArrayList<String>();
		try
		{
			MySQLAdaptor dba = new MySQLAdaptor("localhost","Reactome","root","passworld",3306);
			@SuppressWarnings("unchecked")
			Collection<GKInstance> instances=dba.fetchInstanceByAttribute(ReactomeJavaConstants.Pathway, ReactomeJavaConstants._displayName,"like", query);
			for(GKInstance instance:instances)
			{
				GKInstance species=(GKInstance)instance.getAttributeValue(ReactomeJavaConstants.species);
				result.add((instance + "[" +species.getDisplayName()+"]"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public static List<String> Disease(String query)
	{
		List<String> result = new ArrayList<String>();
		try
		{
			MySQLAdaptor dba = new MySQLAdaptor("localhost","Reactome","root","passworld",3306);
			@SuppressWarnings("unchecked")
			Collection<GKInstance> instances=dba.fetchInstanceByAttribute(ReactomeJavaConstants.Disease, ReactomeJavaConstants._displayName,"like", query);
			for(GKInstance instance:instances)
			{
				GKInstance species=(GKInstance)instance.getAttributeValue(ReactomeJavaConstants.species);
				result.add((instance + "[" +species.getDisplayName()+"]"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String [] args)throws Exception
	{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String j=br.readLine();
			String query=("%"+j+"%");
			List<String> reaction=Reaction(query);
			List<String> pathway=Pathway(query);
			List<String> disease=Disease(query);
			for (String s : reaction) { System.out.println(s); }
			for (String s : pathway) { System.out.println(s); }
			for (String s : disease) { System.out.println(s); }
	}
}