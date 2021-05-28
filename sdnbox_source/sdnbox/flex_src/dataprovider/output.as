// ActionScript file

import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.core.FlexGlobals;
import mx.events.MenuEvent;

private var start_string1:String;
private var start_string2:String;

//get python script to add controller in mininet 
protected function control_string(c_ac:ArrayCollection):String{
	var c_string:String = "";
	start_string2 = "";
	for(var i:int = 0;i<c_ac.length;i++){
		if(c_ac[i].source){
			c_string +="    "+ c_ac[i].getName()+ " = net.addController( '"+c_ac[i].getName()+
				"', controller=RemoteController, ip='127.0.0.1', port=6633 )\r\n"
				//python script to start up controller in mininet
			start_string2 +="    "+ c_ac[i].getName()+ ".start()\r\n"
		}		
	}
	return c_string;
}

//get python script to add switch in mininet 
protected function switch_string(s_ac:ArrayCollection):String{
	var s_string:String = "";
	for(var i:int = 0;i<s_ac.length;i++){
		if(s_ac[i].source){
			s_string += "    "+ s_ac[i].getName()+ " = net.addSwitch( '"+s_ac[i].getName()+
				"', listenPort=" +s_ac[i].port +" )\r\n";
		}		
	}
	return s_string;
}

//get python script to add host in mininet
protected function host_string(h_ac:ArrayCollection):String{
	var h_string:String = "";
	for(var i:int = 0;i<h_ac.length;i++){
		if(h_ac[i].source){
			h_string +="    "+ h_ac[i].host_Name+ " = net.addHost( '"+h_ac[i].host_Name+
				"', mac='"+ h_ac[i].Mac_Add +"' )\r\n";
		}		
	}
	return h_string;
}

//get python script to add link in mininet
protected function link_string(h_ac:ArrayCollection,s_ac:ArrayCollection,c_ac:ArrayCollection):String{
	var l_string:String = "";	
	//python script to add of link between controller and switch in mininet
	for(var i:int = 0;i<h_ac.length;i++){
		for(var j:int = 0;j<h_ac[i].getChildrenLinks().length;j++){
			if(h_ac[i].getChildrenLinks()[j].fromObj){
				l_string += "    "+"net.addLink("+h_ac[i].getChildrenLinks()[j].toObj.getName()+","+
					h_ac[i].getChildrenLinks()[j].fromObj.getName() + "," +
						h_ac[i].getChildrenLinks()[j].t_port + "," + h_ac[i].getChildrenLinks()[j].f_port +
						")\r\n";		
			}
		}		
	}
	////python script to add link among hosts and switchs in mininet
	for(var k:int = 0;k<s_ac.length;k++){
		for(var t:int = 0;t<s_ac[k].getChildrenLinks().length;t++){
			if(s_ac[k].getChildrenLinks()[t].fromObj){
				l_string += "    "+"net.addLink("+s_ac[k].getChildrenLinks()[t].fromObj.getName()+","+
					s_ac[k].getChildrenLinks()[t].toObj.getName()+ "," +
					s_ac[k].getChildrenLinks()[t].f_port + "," + s_ac[k].getChildrenLinks()[t].t_port + 
						")\r\n";
			}
		}		
	}
	start_string1 = "";
	for(var m:int = 0;m<c_ac.length;m++){
		//start_string1="";
		for(var n:int = 0;n<c_ac[m].getChildrenLinks().length;n++){
			//python script to control switch in mininet
		start_string1 += "    "+c_ac[m].getChildrenLinks()[n].toObj.getName()+".start( [" +
				c_ac[m].getChildrenLinks()[n].fromObj.getName()+"] )\r\n";
		}
		for(var x:int = 0;x<c_ac[m].getParentLink().length;x++){
			//python script to control switch in mininet
			start_string1 += "    "+c_ac[m].getParentLink()[x].fromObj.getName()+".start( [" +
				c_ac[m].getParentLink()[x].toObj.getName()+"] )\r\n";
		}
	}
	return l_string;
}

protected function confirm_clickHandler(event:MenuEvent,C:ArrayCollection,S:ArrayCollection,H:ArrayCollection):void
{	
	var file:FileReference=new FileReference();
		//python script run in mininet
	  var string:String=
		"#!/usr/bin/python\r\n"+"\r\n"+
		"\"\"\"\r\n" +
		"Script created by NCC-SDN Group - Visual Network Description (SDN version)\r\n" +
		"\"\"\"\r\n" +
		"from mininet.net import Mininet\r\n" +
		"from mininet.node import Controller, RemoteController, OVSKernelSwitch, OVSLegacyKernelSwitch, UserSwitch\r\n" +
		"from mininet.cli import CLI\r\n" +
		"from mininet.log import setLogLevel\r\n" +
		"from mininet.link import Link, TCLink\r\n" +"\r\n"+
		"def topology():\r\n" +
		"    \"Create a network.\"\r\n" +
		"    net = Mininet( controller=RemoteController, link=TCLink, switch=OVSKernelSwitch )\r\n" +"\r\n"+
		"    print \"*** Creating nodes\"\r\n" +
		     host_string(H) +
		     switch_string(S) +
		     control_string(C) +"\r\n"+
		"    print \"*** Creating links\"\r\n" +
		     link_string(H,S,C)+"\r\n"+
		"    print \"*** Starting network\"\r\n" +
		"    net.start()\r\n" +
		     start_string1 +
			 start_string2 +"\r\n"+
		"    print \"*** Running CLI\"\r\n" +
		"    CLI( net )\r\n" +"\r\n"+
		"    print \"*** Stopping network\"\r\n" +
		"    net.stop()\r\n" +"\r\n"+
		"if __name__ == '__main__':\r\n" +
		"    setLogLevel( 'info' )\r\n" +
		"    topology()";
	  //Pop-up dialog box to select the location of the export file, file name: string, default file name: test.sh
	file.save(string,"test.sh");	
}