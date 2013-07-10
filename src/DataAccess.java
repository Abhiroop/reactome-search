import java.util.Collection;

import org.gk.model.GKInstance;
import org.gk.model.ReactomeJavaConstants;
import org.gk.persistence.MySQLAdaptor;


public class DataAccess 
{
	public static void main(String [] args)
	{
		try
		{
			MySQLAdaptor dba = new MySQLAdaptor("localhost","Reactome","root","passworld",3306);
			String query="%Apoptosis%";
			@SuppressWarnings("unchecked")
			Collection<GKInstance> instances=dba.fetchInstanceByAttribute(ReactomeJavaConstants.Pathway, ReactomeJavaConstants._displayName,"like", query);
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
	}
}
