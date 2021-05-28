package cn.dlut.service.impl;

import java.io.IOException;
import org.springframework.stereotype.Service;
import cn.dlut.entity.Rule;
import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.ModifyFloodlightAclService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.HttpRequest;

@Service("ModifyFloodlightAclService")
public class ModifyFloodlightAclServiceImpl extends AbstractBaseService implements ModifyFloodlightAclService {

	private String strUrl = null;
	private String strContent;//normal json formulation
	
	//add ACL rule
	@Override
	public String writeRule(Rule rule, int controller_id) {
	
		String[] origin_url = AppProperties.flood_preurl().split(";");
		strUrl = origin_url[controller_id-50] + AppProperties.getAclRule();
		
		System.out.println(strUrl);
			
		if(rule.getSrc_ip()!=null)
			System.out.println(rule.getSrc_ip());
		if(rule.getDst_ip()!=null)
			System.out.println(rule.getDst_ip());
		String result ="";
		try 
		{  
			//dst or src is empty
			if(rule.getDst_ip().equals("")||rule.getSrc_ip().equals(""))
			
			{
				//System.out.println("hello");
				if(rule.getNw_proto().equals("ANY"))
				{
					//System.out.println("hello");
					if(rule.getDst_ip().equals(""))
					{
						 //dst_ip is empty and proto is any valued
						strContent="{\"src-ip\":\""+rule.getSrc_ip()+"\",\"action\":\""+rule.getAction()+"\"}";
						result = HttpRequest.readContentFromPost(strUrl, strContent);
					}
					else
					{
						//src_ip is empty and proto is any valued
						//System.out.println(strContent+"hello");
						strContent="{\"dst-ip\":\""+rule.getDst_ip()+"\",\"action\":\""+rule.getAction()+"\"}";
						result = HttpRequest.readContentFromPost(strUrl, strContent);
					}
						
				}
				else if(rule.getNw_proto().equals("ICMP"))
				{
					if(rule.getDst_ip().equals(""))
					{
						//dst_ip is empty and proto is ICMP typed
						strContent="{\"src-ip\":\""+rule.getSrc_ip()+"\",\"nw-proto\":\""+
						rule.getNw_proto()+"\",\"action\":\""+rule.getAction()+"\"}";
						result = HttpRequest.readContentFromPost(strUrl, strContent);
					}
					else
					{
						//src_ip is empty, proto is ICMP typed
						strContent="{\"dst-ip\":\""+rule.getDst_ip()+"\",\"nw-proto\":\""+
						rule.getNw_proto()+"\",\"action\":\""+rule.getAction()+"\"}";
						result = HttpRequest.readContentFromPost(strUrl, strContent);
					}
				}
				else if(rule.getNw_proto().equals("TCP")||rule.getNw_proto().equals("UDP"))
				{
					if(rule.getDst_ip().equals(""))
					{
						//dst_ip is empty, proto is TCP UDP typed
						strContent = "{\"src-ip\":\""+rule.getSrc_ip()+"\",\"action\":\""+rule.getAction()+"\",\"nw-proto\":\""+
						rule.getNw_proto()+"\",\"tp-dst\":"+rule.getTp_dst()+"}";
						result = HttpRequest.readContentFromPost(strUrl, strContent);
					}
					else
					{
						//src_ip is empty, proto is TCP UDP typed
						strContent = "{\"dst-ip\":\""+rule.getDst_ip()+"\",\"action\":\""+rule.getAction()+"\",\"nw-proto\":\""+
						rule.getNw_proto()+"\",\"tp-dst\":"+rule.getTp_dst()+"}";
						result = HttpRequest.readContentFromPost(strUrl, strContent);
					}
				}	
			}
			//src and dst both are not empty
			else
			{
				if(rule.getNw_proto().equals("ANY"))
				{
					//src and dst both are not empty, proto is any valued
			        strContent= "{\"src-ip\":\""+rule.getSrc_ip()+"\",\"dst-ip\":\""+
					rule.getDst_ip()+"\",\"action\":\""+rule.getAction()+"\"}";
			        result = HttpRequest.readContentFromPost(strUrl, strContent);
				}
				else if(rule.getNw_proto().equals("ICMP"))
				{
					//src and dst both are not empty, proto is ICMP typed
					strContent = "{\"src-ip\":\""+rule.getSrc_ip()+"\",\"dst-ip\":\""+
					rule.getDst_ip()+"\",\"action\":\""+rule.getAction()+"\",\"nw-proto\":\""+
					rule.getNw_proto()+"\"}";
					result = HttpRequest.readContentFromPost(strUrl, strContent);
				}
				else 
				{
					//normally set rule
					strContent = "{\"src-ip\":\""+rule.getSrc_ip()+"\",\"dst-ip\":\""+
					rule.getDst_ip()+"\",\"action\":\""+rule.getAction()+"\",\"nw-proto\":\""+
					rule.getNw_proto()+"\",\"tp-dst\":"+rule.getTp_dst()+"}";
					result = HttpRequest.readContentFromPost(strUrl, strContent);
				}
			}
		   
			   System.out.println(result);
			
			if(result.equals("{\"status\" : \"Success! New rule added.\"}"))
			{
				return "Success!";
			}
			else
			{
				return ("Failed!"+result);
			}
		} catch (IOException e) {
			e.printStackTrace();
			result = "Connect failed!";
			return (result);
		}
		
	
	}
	//delete acl rule
	@Override
	public String delRule(int Rule_id, int controller_id) {
		
		String[] origin_url = AppProperties.flood_preurl().split(";");
		strUrl = origin_url[controller_id-50] + AppProperties.getAclRule();
		
		String strResult=new String();
		System.out.println(strUrl);
		/*Map<String,Object> map=new HashMap();
		map.put("rule_id","14");
		try {
			HttpRequest.doDelete(strUrl, map);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		System.out.println("{\"ruleid\":\"" + Rule_id + "\"}");
		
		strResult=HttpRequest.MyDeleteMethod(strUrl, "{\"ruleid\":\"" + Rule_id + "\"}");
		if(("{\"status\" : \"Success! Rule deleted\"}").equals(strResult))
		{
			return ("Success!");
		}
	    
		return ("Failed!");
	
	}
	//query ACL rule
	@Override
	public String queryRule(int controller_id) {
		String strResult;
		
		String[] origin_url = AppProperties.flood_preurl().split(";");
		strUrl = origin_url[controller_id-50] + AppProperties.getAclRule();
		System.out.println(strUrl);
		
		try{
			strResult = HttpRequest.readContentFromGet(strUrl);
			return strResult;
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return "";
	}

}
