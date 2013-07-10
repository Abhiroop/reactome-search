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
			System.out.println("Results:"+instances.size());
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
			System.out.println("Results:"+instances.size());
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
			System.out.println("Results:"+instances.size());
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
	public static void main(String [] args)
	{
			String query="%Apoptosis%";
			List<String> reaction=Reaction(query);
			List<String> pathway=Pathway(query);
			List<String> disease=Disease(query);
			System.out.println(reaction.size());
			System.out.println(pathway.size());
			System.out.println(disease.size());
	}
}
/*
public static void main(String [] args)
{
	try
	{
		MySQLAdaptor dba = new MySQLAdaptor("localhost","Reactome","root","passworld",3306);
		String query="%Diabetes%";
		@SuppressWarnings("unchecked")
		Collection<GKInstance> instances=dba.fetchInstanceByAttribute(ReactomeJavaConstants.Disease, ReactomeJavaConstants._displayName,"like", query);
		System.out.println("Results:"+instances.size());
		for(GKInstance instance:instances)
		{
			GKInstance species=(GKInstance)instance.getAttributeValue(ReactomeJavaConstants.species);
			System.out.println(instance + "[" +species.getDisplayName()+"]");
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
*/