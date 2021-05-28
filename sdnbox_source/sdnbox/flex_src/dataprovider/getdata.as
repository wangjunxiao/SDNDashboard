// ActionScript file
import Components.Switch;

import flash.events.Event;
import flash.events.TimerEvent;
import flash.utils.Timer;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

			
			private var c_iscomplete:Boolean = false;
			private var s_iscomplete:Boolean = false;
			private var h_iscomplete:Boolean = false;
			private var l_iscomplete:Boolean = false;
			private var f_iscomplete:Boolean = false;
			private var p_iscomplete:Boolean = false;
			private var switch_number:int = 0 ;

			private var counter:int = 0;
			private var counter2:int = 0;
		    private var app_iscomplete:Boolean = false;
			private var topo_iscomplete:Boolean = false;
            private var istopo_groupcomplete:Boolean = false;
			private var timer:Timer;
			
			private function init():void{
					timer=new Timer(1000*8,2);
					timer.addEventListener(TimerEvent.TIMER,onTime);
					loading("img/loading.gif");
					timer.start();	
					
					dbInfo_getAll_Service.controller_getAll();
					dbInfo_getAll_Service.host_getAll();
					//dbInfo_getAll_Service.flow_getAll();
					dbInfo_getAll_Service.port_getAll();
					dbInfo_getAll_Service.link_getAll();
				    //dbInfo_getAll_Service.portStats_getAll();
					dbInfo_getAll_Service.switch_getAll();
					//dbInfo_getAll_Service.portStats_getAll();
					
					//CoordinatorService.InitController();
					
					//}
					create(); //call function to get menu data set			
			}

			/*private function getDataBaseData():void{
				//dbInfo_getAll_Service.flow_getAll();
				dbInfo_getAll_Service.portStats_getAll();
			}*/
			
			private function onTime(event:TimerEvent):void{
				if(timer.currentCount==1){
					if(!(c_iscomplete && h_iscomplete &&s_iscomplete)){
						Alert.show("connect overtime, please check out network status","failed to access topology");
						PopUpManager.removePopUp(img);
					}
				}
				if(timer.currentCount==2){
					if(!(l_iscomplete &&p_iscomplete)){
						PopUpManager.removePopUp(img);
						Alert.show("connect overtime, please check out network status","failed to access network info");
					}						
				}
				
			}
			
			private function faultHandler(event:FaultEvent):void{        
				Alert.show("Fault:"+event.fault.toString());
			}
			private function controller_resultHandler(event:ResultEvent):void{  
				ac_controller = event.result as ArrayCollection;
				c_iscomplete = true;
				counter++;
				if(s_iscomplete  && h_iscomplete && l_iscomplete && counter==4 ){
					counter--;
					topo_iscomplete=true;
					counter2++;
					if(app_iscomplete && counter2==2){
						counter2--;
						networkPanel_creationCompleteHandler();
						PopUpManager.removePopUp(img);
						//CoordinatorTopoCompleteHandler();
					}					
				}				
			}
			private function switch_resultHandler(event:ResultEvent):void{  
				switch_number++;
				if(switch_number==1){
					ac_switch = event.result as ArrayCollection;
					s_iscomplete = true;
					counter++;					
					if(c_iscomplete && h_iscomplete && l_iscomplete && counter==4){
						counter--;
						topo_iscomplete=true;
						counter2++;
						if(app_iscomplete && counter2==2 ){
							counter2--;
							networkPanel_creationCompleteHandler();
							PopUpManager.removePopUp(img);
							//CoordinatorTopoCompleteHandler();
						}					
					}
				}else{
					ac_switch = event.result as ArrayCollection;
				}		
			}
			private function flow_resultHandler(event:ResultEvent):void{    
				ac_flow.removeAll();
				ac_flow = event.result as ArrayCollection;
				f_iscomplete=true;
				
			}
			private function port_resultHandler(event:ResultEvent):void{        
				ac_port = event.result as ArrayCollection;
				p_iscomplete=true;
			}
			private function host_resultHandler(event:ResultEvent):void{
				ac_host = event.result as ArrayCollection;
				h_iscomplete = true;
				counter++;
				if(c_iscomplete && s_iscomplete && l_iscomplete && counter==4){	
					counter--;
					topo_iscomplete=true;
					counter2++;
					if(app_iscomplete && counter2==2){
						counter2--;
						PopUpManager.removePopUp(img);
						networkPanel_creationCompleteHandler();
						//CoordinatorTopoCompleteHandler();
					}					
				}				
			}
			private function link_resultHandler(event:ResultEvent):void{        
				ac_link = event.result as ArrayCollection;
				l_iscomplete=true;
				counter++;
				if(c_iscomplete && s_iscomplete && h_iscomplete && counter==4){	
					counter--;
					topo_iscomplete=true;
					counter2++;
					if(app_iscomplete && counter2==2)
					{
						counter2--;
						networkPanel_creationCompleteHandler();
						PopUpManager.removePopUp(img);
						//CoordinatorTopoCompleteHandler();
					}					
				}				
			}		
			
			private function portStats_resultHandler(event:ResultEvent):void{
				ac_package.removeAll();
				ac_package = event.result as ArrayCollection;	
			}	




			private function firewall_resultHandler(event:ResultEvent):void{
				ac_firewall_enable = event.result as String;
				if(ac_firewall_enable == "[Ryu]Switch firewall enable successd.")
					trace(ac_firewall_enable);
				else
					Alert.show(ac_firewall_enable);
			}

		