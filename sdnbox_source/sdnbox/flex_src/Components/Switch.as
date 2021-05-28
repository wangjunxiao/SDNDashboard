package Components
{
	
	import flash.events.MouseEvent;
	import flash.geom.Point;
	
	import mx.controls.Menu;
	import mx.core.FlexGlobals;
	import mx.core.UIComponent;
	import mx.events.MenuEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	
	import skins.Item_images_skin;
	
	import view.AddFlowEntry;
	import view.PortStats;
	import view.RealTimeFlowWindow;
	import view.S_DestinationLinkListTitleWindow;
	import view.S_FlowListTitleWindow;
	import view.S_PortListTitleWindow;
	import view.S_SimpleTitleWindowExample;
	import view.S_SourceLinkListTitleWindow;
	import view.addFirewallRuler;
	import view.deleteFirewallRuler;
	import view.queryFirewallRuler;
	
	public class Switch extends Item_image
	{
		[Bindable]
		public var Switch_id:int;
		
		[Bindable]
		public var Switch_name:String;
		
		[Bindable]
		public var port:int;
		
		public var Crl_id:int;
		public var Dp_id:String;
		private var N_table:int;
		private var Dp_desc:String;
		private var Sw_desc:String;
		private var Hw_desc:String;
		private var Serial_num:String;
		private var Mfr_desc:String;
		private var point:Point = new Point();
		
		/****************impl: mouse click and menu pop up****************/
		/*************************menu set********************************/		
		private var menuData:Array=
			[{label:'port list'},{label:'flow table'},{label:'link_src switch list'},{label:'link_dst switch list'},{label:'properties'},
			 {label:'remove'},{label:'update flow table'},{label:'add ACL rule'},{label:'query ACL rule'},{label:'remove ACL rule'},
			 {label:'port statistics'}
			];
		private var myMenu:Menu;
		
		
		/**********************define function to handle menu click event***************************************/	  
		private function menuHandler(event:MenuEvent):void {
			
			if(event.item.label=="port list")
			{ 			
				showPortDialogue();
			} 	
			
			if(event.item.label=="flow table")
			{ 			
				showFlowDialogue();
			} 	
			
			if(event.item.label=="link_src switch list")
			{ 			
				showSourceLinkDialogue();
			} 	
			
			if(event.item.label=="link_dst switch list")
			{ 			
				showDestinationLinkDialogue();
			} 
			
			if(event.item.label=="update flow table") 
			{ 			
				AddFlowEntryDialogue();
			} 	
			//click remove entry
			// trace(event.item["label"]);
			if(event.item.label=="remove") 
			{ 			
				this.Delete();
			}	
			if(event.item.label=="properties") 
			{ 			
				showDialogue();
			}
			if (event.item.label=="add ACL rule")
			{
				showAddFirewallRulerDialogue();
			}
			if(event.item.label=="query ACL rule")
			{
				showQueryFirewallRulerDialogue();
			}
			if(event.item.label=="remove ACL rule")
			{
				showDeleteFirewallRulerDialogue();
			}
			if(event.item.label=="port statistics")
			{
				mouseOverHandler();
			}
		}
		/*****************impl menu pop up, define function to handle entry select event*********************/	
		public function showMenu(event:MouseEvent):void{
			myMenu= Menu.createMenu(this,menuData, false); 			
			//declare function handle entry select event
			myMenu.addEventListener("itemClick", menuHandler); 
			//get mouse axis and and pop up menu	 
			point.x=event.stageX; 
			point.y=event.stageY;  
			myMenu.show(point.x , point.y); 
		}
		public function AddFlowEntryDialogue():void{
			FlexGlobals.topLevelApplication.activeItem = this;			
			var FlowEntryDialogue:AddFlowEntry = 
				AddFlowEntry(PopUpManager.createPopUp(this, AddFlowEntry, true)); 
			
			FlowEntryDialogue.x = 420;
			
			FlowEntryDialogue.y = 170;	
		}
	
		public function showDialogue():void{
			/*  the third parameter 'true' represents: if have access to parent elements afer myPanel window popping up
			in the case of 'true', background blur effect appears, can not have access to parent elements */			  
			FlexGlobals.topLevelApplication.activeItem = this;
			
			//  this.parent = this.host_IP;
			var myPanel:S_SimpleTitleWindowExample = 
				S_SimpleTitleWindowExample(PopUpManager.createPopUp(this, S_SimpleTitleWindowExample, true)); 
			
			myPanel.x = 420;
			
			myPanel.y = 170;			
		}
		
		public function showPortDialogue():void{
			FlexGlobals.topLevelApplication.activeItem = this;
			var myPanel:S_PortListTitleWindow = 
				S_PortListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, S_PortListTitleWindow, false)); 
			
			myPanel.x = 100;
			
			myPanel.y = 150;			
		}
		
		public function showFlowDialogue():void{
			FlexGlobals.topLevelApplication.activeItem = this;
			//var myPanel:S_FlowListTitleWindow = 
				//S_FlowListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, S_FlowListTitleWindow, false)); 
			
			var myPanel:RealTimeFlowWindow = 
				RealTimeFlowWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, RealTimeFlowWindow, false)); 
			
			myPanel.x = 100;
			
			myPanel.y = 150;				
		}
		
		
		public function showSourceLinkDialogue():void{
			FlexGlobals.topLevelApplication.activeItem = this;
			var myPanel:S_SourceLinkListTitleWindow = 
				S_SourceLinkListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, S_SourceLinkListTitleWindow, false)); 
			
			myPanel.x = 100;
			
			myPanel.y = 150;				
		}
		
		public function showDestinationLinkDialogue():void{
			FlexGlobals.topLevelApplication.activeItem = this;
			var myPanel:S_DestinationLinkListTitleWindow = 
				S_DestinationLinkListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, S_DestinationLinkListTitleWindow, false)); 
			
			myPanel.x = 100;
			
			myPanel.y = 150;			
		}
		
		public function showAddFirewallRulerDialogue():void{
			FlexGlobals.topLevelApplication.activeItem=this;
			var myPanel:addFirewallRuler = 
				addFirewallRuler(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, addFirewallRuler, false)); 
			myPanel.x = 420;
			
			myPanel.y = 170;		
		}
		
		public function showDeleteFirewallRulerDialogue():void
		{
			FlexGlobals.topLevelApplication.activeItem=this;
			var myPanel:deleteFirewallRuler = deleteFirewallRuler(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, deleteFirewallRuler, false)); 
			myPanel.x = 420;
			
			myPanel.y = 170;	
			
		}
		
		public function showQueryFirewallRulerDialogue():void
		{
			FlexGlobals.topLevelApplication.activeItem=this;
			
			var myPanel:queryFirewallRuler = queryFirewallRuler(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, queryFirewallRuler, false)); 
				
			myPanel.x = 420;
				
			myPanel.y = 170;
		}
		
		/*****************menu pop up over*****************************/
		
		/***************** define the function handle the mouse in event *****************************/
		private function mouseOverHandler():void{
			var dialog:PortStats = new PortStats();
			dialog.dp_id = this.Dp_id;
			dialog.title = "switch " + this.Dp_id + " all port flow info";
			PopUpManager.addPopUp(dialog,this,false);
			PopUpManager.centerPopUp(dialog);
		}
		
		/***************** mouse in over *****************************/
		
		
		public function Switch()
		{
			super();
			this.source = "img/switch.jpg";
			setStyle("skinClass", skins.Item_images_skin);
			//os default disallow double-click evnet, set to allow double-click	
			this.doubleClickEnabled = true;
			mouseChildren = false;
			this.addEventListener(MouseEvent.DOUBLE_CLICK,showMenu);
			//this.addEventListener(MouseEvent.MOUSE_OVER,mouseOverHandler);
			//this.addEventListener(MouseEvent.MOUSE_OUT,mouseOutHnadler);
			setPort();
		}
		
		public function setPort():void{
			this.port = 6634 +FlexGlobals.topLevelApplication.S_obj.length;
		}
		/***************************get and set SwitchID**************************/	
		public function getSwitch_id():int{
			return this.Switch_id;
		}
		public override function setSwitch_id(_id:int):void{
			this.Switch_id=_id;
		}
		/***************************get and set SwitchName**************************/	
		public function getName():String{
			return this.Switch_name;
		}
		public override function setSwitch_name(_name:String):void{
			this.Switch_name=_name;
		}
		/****************************get and set Crl_id***************************/			
		public function getCrl_id():int{
			return this.Crl_id;
		}
		public override function setCrl_id(c_id:int):void{
			this.Crl_id=c_id;
		}
		/**************************get and set Dp_id*****************************/		
		public function getDp_id():String{
			return this.Dp_id;
		}
		public override function setDp_id(d_id:String):void{
			this.Dp_id=d_id;
		}
		/************************get and set N_table*****************************/
		public function getN_table():int{
			return this.N_table;
		}
		public override function setN_table(_n:int):void{
			this.N_table=_n;
		}
 		/************************get and set Dp_desc*****************************/
		public function getDp_desc():String{
			return this.Dp_desc;
		}
		public override function setDp_desc(d_d:String):void{
			this.Dp_desc=d_d;
		}
 		/************************get and set Sw_desc*****************************/
		public function getSw_desc():String{
			return this.Sw_desc;
		}
		public override function setSw_desc(s_d:String):void{
			this.Sw_desc=s_d;
		}
		/************************get and set Hw_desc*****************************/
		public function getHw_desc():String{
			return this.Hw_desc;
		}
		public override function setHw_desc(h_d:String):void{
			this.Hw_desc=h_d;
		}
		/************************get and set Mfr_desc*****************************/
		public function getMfr_desc():String{
			return this.Mfr_desc;
		}
		public override function setMfr_desc(m_d:String):void{
			this.Mfr_desc=m_d;
		}
		/************************get and set Serial_num*****************************/
		public function getSerial_num():String{
			return this.Serial_num;
		}
		public override function setSerial_num(s_n:String):void{
			this.Serial_num=s_n;
		}
		/*************************end***********************************************/
	}
}