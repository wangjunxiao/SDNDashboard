import mx.collections.ArrayCollection;
import mx.events.MenuEvent;

private var Node_obj:ArrayCollection = new ArrayCollection();
private var T_H_obj:ArrayCollection = new ArrayCollection();
private var T_S_obj:ArrayCollection = new ArrayCollection();
private var T_C_obj:ArrayCollection = new ArrayCollection();

	
	private function DrawFatTree():void{
		if(network_group != null){
			network_group.removeAllElements();	
		}
		H_obj.removeAll();
		S_obj.removeAll();
		C_obj.removeAll();
		for(var num_node:int=0;num_node<nodeData.length;num_node++){
			if(nodeData.getItemAt(num_node)["type"]=="controller")
				fatTreeAddController(int (nodeData.getItemAt(num_node)["id"]),nodeData.getItemAt(num_node)["name"]);
			if(nodeData.getItemAt(num_node)["type"]=="switch")
				fatTreeAddSwitch(int (nodeData.getItemAt(num_node)["id"]),nodeData.getItemAt(num_node)["name"]);			
			if(nodeData.getItemAt(num_node)["type"]=="host")
				fatTreeAddHost(int (nodeData.getItemAt(num_node)["id"]),nodeData.getItemAt(num_node)["name"]);				
		}
		for(var num_link:int=0;num_link<linkData.length;num_link++){
			var from_id:int = int (linkData.getItemAt(num_link)["fromNode"]);
			var to_id:int = int (linkData.getItemAt(num_link)["toNode"])				
			fatTreeAddLink(Node_obj[from_id-1],Node_obj[to_id-1]);
		}
		
	}
	
	private function fatTreeAddController(id:int,name:String):void{
		var fatTree_c_item:Controller = new Controller();				
		config(fatTree_c_item,1150-(id-8)*70,250,name+"~"+id);				
		fatTree_c_item.setCon_id(id);
		fatTree_c_item.setCon_type("floodnight");
		fatTree_c_item.setCon_name(name);
		C_obj.addItem(fatTree_c_item);
		Node_obj.addItemAt(fatTree_c_item,id-1);		
		fatTree_c_item.addEventListener(MouseEvent.MOUSE_DOWN, imageMouseDownHandler);
		fatTree_c_item.addEventListener(MouseEvent.MOUSE_UP, imageMouseUpHandler);
		fatTree_c_item.addEventListener(MouseEvent.MOUSE_OVER, imageMouseOverHandler);  
		network_group.addElement(fatTree_c_item);
	}
	
	private function fatTreeAddSwitch(id:int,name:String):void{
		var fatTree_s_item:Switch = new Switch();
		if(isFatTreeSel==1){
		if(id-16<=4)
			config(fatTree_s_item,900-((id-16)*150),60,name+"~"+id);
		else
			config(fatTree_s_item,900-(((id-21)%8)*90),220+(int((id-21)/8)*90),name+"~"+id);
		}
		else if(isBCubeSel==1){
			config(fatTree_s_item,800-(((id-1)%4)*150),60+(int((id-1)/4)*90),name+"~"+id);
		}
		fatTree_s_item.setSwitch_id(id);	
		fatTree_s_item.setSwitch_name(name);
		Node_obj.addItemAt(fatTree_s_item,id-1);
		S_obj.addItem(fatTree_s_item);
		fatTree_s_item.addEventListener(MouseEvent.MOUSE_DOWN, imageMouseDownHandler);
		fatTree_s_item.addEventListener(MouseEvent.MOUSE_UP, imageMouseUpHandler);
		fatTree_s_item.addEventListener(MouseEvent.MOUSE_OVER, imageMouseOverHandler);  
		network_group.addElement(fatTree_s_item);	
	}
	
	private function fatTreeAddHost(id:int,name:String):void{
		var fatTree_h_item:Host = new Host();				
		config(fatTree_h_item,1150-id*70,400,name+"~"+id);
		fatTree_h_item.setHost_ID(id);
		fatTree_h_item.setHost_Name(name); 		
		Node_obj.addItemAt(fatTree_h_item,id-1);
		H_obj.addItem(fatTree_h_item);
		fatTree_h_item.addEventListener(MouseEvent.MOUSE_DOWN, imageMouseDownHandler);
		fatTree_h_item.addEventListener(MouseEvent.MOUSE_UP, imageMouseUpHandler);
		fatTree_h_item.addEventListener(MouseEvent.MOUSE_OVER, imageMouseOverHandler);  
		network_group.addElement(fatTree_h_item);
	}

	private function fatTreeAddLink(F_obj:Item_image,T_obj:Item_image):void{
		var link:Link = new Link(F_obj, T_obj);
		var childrenLinks:ArrayCollection =F_obj.getChildrenLinks();
		childrenLinks.addItem(link);					
		var parentLink:ArrayCollection = T_obj.getParentLink();
		parentLink.addItem(link); 
		var uiObj:UIComponent = new UIComponent(); 
		uiObj.addChild(link);
		network_group.addElement(uiObj);
		//link_num++;
	}