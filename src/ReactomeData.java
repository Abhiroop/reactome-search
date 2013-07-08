package com.lucene.Reactome;

import java.util.Collection;

import org.gk.model.GKInstance;
import org.gk.model.ReactomeJavaConstants;
import org.gk.persistence.MySQLAdaptor;

public class ReactomeData 
{
	public static void main(String[]args)throws Exception
	{
		try{
		
			MySQLAdaptor x=new MySQLAdaptor("localhost", "Reactome", "root", "passworld", 3306);
			
			Long dbid=2775822L;
			GKInstance egfr=x.fetchInstance(dbid);
			System.out.println(egfr);
			@SuppressWarnings("unchecked")
			Collection<GKInstance> referrers=egfr.getReferers(ReactomeJavaConstants.hasEvent);
			//System.out.println(referrers.size());
			/*
			for(GKInstance i: referrers)
			{
				System.out.println(i);
			}
			*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
