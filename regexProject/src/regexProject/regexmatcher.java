package regexProject;

import java.util.regex.*;

public class regexmatcher {
	
	
	
	public static void main(String[] arg)
	{
		//Pattern p=Pattern.compile("(.*\\.tar[\\\\/](?:\\.[\\\\/])?(\\w{6})[\\\\/](vol_\\d{1,3}[\\\\/]iss_\\w?\\d{1,2}[\\\\/])(.*)\\.(pdf))$");
		//Matcher m =p.matcher("JCPSA6-vol_137-iss_10.tar/./JCPSA6/vol_137/iss_10/02_Cover1.pdf");
		
		//System.out.println(m.matches());
		//System.out.println(p.CASE_INSENSITIVE);
		//System.out.println(p.flags());
		//System.out.println(p.pattern());
		//System.out.println(p.quote("Amit Kumar"));
		
		/*
		Pattern p = Pattern.compile("cat");
		 Matcher m = p.matcher("one cat two cats in the yard");
		 StringBuffer sb = new StringBuffer();
		 while (m.find()) {
		     m.appendReplacement(sb, "dog");
		 }
		 m.appendTail(sb);
		 System.out.println(sb.toString());
		*/
		
		Pattern p = Pattern.compile(".*\\.zip[\\\\/](([a-z]+).*)[\\\\/](?:images|media)[\\\\/]");
		Matcher m = p.matcher("/pcontent/ongoing/journal_content/INGEST_MASTER/ACP/Fetched/ATY/NEW/aim/10.7326/aim.2020.172.issue-10/acp_aim_10.7326/aim.2020.172.issue-10_20200518135726.zip/acpj202005190-058/images/rating_5.jpg");
		
		//System.out.println(m.groupCount());
		int counter=m.groupCount();
		
		int c=0;
		int x=0;
		
		long starttime=System.nanoTime();
		
		while(m.matches())
		{	++c;
			System.out.println("Group " + x + " " +m.group(c));
			x++;
			if(c==m.groupCount())
				break;
		}
		System.out.println(m.hitEnd());
		/*
		while(m.find(c))
		{
			System.out.println("Find Group " + x + " " +m.group(c));
			c++;
		}*/
		if(m.find(0))
			System.out.println("Find Group " + x + " " +m.group(c));
		//System.out.println(m.hitEnd());
		//System.out.println(m.lookingAt());
		//System.out.println(m.start());
		//System.out.println(m.group(m.start()));
		//System.out.println(m.groupCount());
		//for(int i=0;i<m.groupCount();i++)
			//System.out.println(m.group(i));
		
		long endtime=System.nanoTime();
		
		long elapsedtime = endtime-starttime;
		System.out.println("execution time: "+ elapsedtime/1000000 + " milisecond");
		
		
	}
	
	
	

}
