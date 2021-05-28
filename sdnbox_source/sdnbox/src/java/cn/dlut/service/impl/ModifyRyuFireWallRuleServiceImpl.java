package cn.dlut.service.impl;

import java.io.IOException;
import java.lang.Integer;

import org.springframework.stereotype.Service;

import cn.dlut.entity.Rule;
import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.ModifyRyuFireWallRuleService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.HttpRequest;


@Service("ModifyRyuFireWallRuleService")
public class ModifyRyuFireWallRuleServiceImpl extends AbstractBaseService implements ModifyRyuFireWallRuleService {
	
	private String strUrl = null;
	private String strContent;
	private String speicialstrContent;
	private String strContent2;
	
	
	//Add firewall rule
	@Override
	public String writeRule(Rule rule,String switch_id,int controller_id) {
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
		              + AppProperties.getRyuRule() + switch_id;
		
		//System.out.println(strUrl);
		//set rule priority=65535  and Dl_type="IPv4" 
		
		//normally set rule
		strContent = "{\"priority\":" + rule.getPriority() + ",\"dl_type\":\"" + rule.getDl_type()
		+ "\",\"nw_src\":\"" + rule.getSrc_ip() + "\",\"nw_dst\":\"" + rule.getDst_ip()
		+ "\",\"nw_proto\":\"" + rule.getNw_proto() + "\",\"tp_dst\":" + rule.getTp_dst()
		+ ",\"actions\":\"" + rule.getAction() + "\"}";
		
		//specially set enable rule
		speicialstrContent="{\"priority\":" + rule.getPriority() + ",\"dl_type\":\"" + rule.getDl_type()
		+ "\",\"actions\":\"" + rule.getAction() + "\"}";
		
		strContent2="{\"priority\":" + rule.getPriority() + ",\"dl_type\":\"" + rule.getDl_type()
		+ "\",\"nw_src\":\"" + rule.getSrc_ip() + "\",\"nw_dst\":\"" + rule.getDst_ip()
		+ "\",\"nw_proto\":\"" + rule.getNw_proto() +"\",\"actions\":\"" + rule.getAction() + "\"}";
		

		//System.out.println("JSON IS:"+strContent);
		String result ="";
        System.out.println(strContent);
		try 
		{   
			if(rule.getPriority()==1)
			{
				//System.out.println("test during  starting  ");
				//System.out.println("strurl: "+strUrl+"speicialstrContent: "+speicialstrContent+"test during    ");
				result = HttpRequest.readContentFromPost(strUrl, speicialstrContent);
			}
			//post http request of add rule
			else
			{
				if(rule.getTp_dst()==0)
				{
					result =HttpRequest.readContentFromPost(strUrl, strContent2);
				}
				else
				{
					result = HttpRequest.readContentFromPost(strUrl, strContent);
				}
			}
			//get rule_id to identify result 
	        String[] sub1 = result.split("\\=");
	        //System.out.println(sub1[0]);
	        //System.out.println(sub1[1]);
	        String[] sub2 = sub1[1].split("\\\"");
	        //System.out.println(sub2[0]);
           
	        
	        int x=Integer.parseInt(sub2[0]);
	        
			if(result.equals("[{\"switch_id\": \""+switch_id+"\", \"command_result\": " +
					"[{\"result\": \"success\", \"details\": \"Rule added. : rule_id="+x+"\"}]}]"))
				
			{
				return "Success!";
			}
			else
			{
				return ("Failed!");
			}
		} catch (IOException e) {
			e.printStackTrace();
			result = "Connect faild!";
			return (result);
		}
		
	}
    //delete firewall rule(ryu no support)
	@Override
	public String delRule(int Rule_id,String switch_id,int controller_id){
		
//		strUrl = AppProperties.getRyu_preurl() + AppProperties.getRyuRule()+switch_id+"/all";
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500] + AppProperties.getRyuRule()+switch_id;
		
		System.out.println(strUrl);
		System.out.println("{\"rule_id\":\"" + Rule_id + "\"}");
		System.out.println("[{\"switch_id\" :  " + switch_id + 
				" ,\"command_result\": [{\"result\": \"success\", \"details\": \"Rule deleted." +
				" : ruleID="+Rule_id+"}]}]");
		String strResult=new String();

//		strResult = HttpRequest.MyDeleteMethod(strUrl, "{\"rule_id\" : \"" + Rule_id + "\"}");
		strResult = HttpRequest.MyDeleteMethod(strUrl,"{\"rule_id\" : \"" + Rule_id + "\"}");
		
//		try {
//			strResult = HttpRequest.readContentFromDel(strUrl);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	    
		if(("[{\"switch_id\":\"" + switch_id + "\",\"command_result\":[\"result\": \"success\", \"details\": \"Rule added. : " 
				+"rule_id="+Rule_id+"}]}]").equals(strResult))
		{
			return ("Success");
		}
	    return ("Failed!");
		
	}
	

	//query firewall rule
	@Override
	public String queryRule(String switch_id,int controller_id) {
		String strResult;
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500] + AppProperties.getRyuRule()+switch_id;
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
