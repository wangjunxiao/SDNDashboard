package Components
{
	import flash.events.MouseEvent;
	import flash.geom.Point;
	
	import mx.controls.Menu;
	import mx.core.FlexGlobals;
	import mx.events.MenuEvent;
	import mx.managers.PopUpManager;
	
	import skins.Item_images_skin;
	
	import view.C_SimpleTitleWindowExample;
	import view.C_SwitchListTitleWindow;
	
	public class Controller extends Item_image
	{
		private var Con_id:int;
		private var Con_type:String;
		private var Rest_url:String;
		private var Con_name:String;
		private var point:Point = new Point();
				
		/****************impl: mouse click and menu pop up****************/
		/*************************menu set********************************/	
		private var menuData:Array=
			[{label:'switch list'},{label:'properties'},{label:'remove'}];
		private var myMenu:Menu;
	
	
		/**************************dialogue pop up**************************************/
		public function showDialogue():void{
			/*  the third parameter 'true' represents: if have access to parent elements afer myPanel window popping up
			    in the case of 'true', background blur effect appears, can not have access to parent elements */				  
			FlexGlobals.topLevelApplication.activeItem = this;
			
			//  this.parent = this.host_IP;
			var myPanel:C_SimpleTitleWindowExample = 
				C_SimpleTitleWindowExample(PopUpManager.createPopUp(this, C_SimpleTitleWindowExample, true)); 
			
			myPanel.x = 420;
			
			myPanel.y = 170;			
		}
		
		public function showSwitchDialogue():void{
			FlexGlobals.topLevelApplication.activeItem = this;
			var myPanel:C_SwitchListTitleWindow = 
				C_SwitchListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, 
					C_SwitchListTitleWindow, false)); 
			
			myPanel.x = 100;
			
			myPanel.y = 150;				
		}
		
		/**********************define function to handle menu click event***************************************/	  
		private function menuHandler(event:MenuEvent):void {
			
			if(event.item.label=="switch list")
			{ 			
				showSwitchDialogue();
			} 	
			
			
			if(event.item.label=="properties") 
			{ 			
				showDialogue();
			} 	
			//click remove entry
			// trace(event.item["label"]);
			if(event.item.label=="remove") 
			{ 			
				 this.Delete();
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
		
		/**************************constructed function*****************************************/
		public function Controller()
		{		
			super();
			//os default disallow double-click evnet, set to allow double-click
			this.source="img/controller.jpg";
			setStyle("skinClass", skins.Item_images_skin);
			this.doubleClickEnabled = true;
			this.addEventListener(MouseEvent.DOUBLE_CLICK,showMenu);
		}
		/**************************get and set Con_id*********************************/		
		public function getCon_id():int{
			return this.Con_id;
		}
		public override function setCon_id(_id:int):void{
			this.Con_id=_id;
		}
		/**************************get and set Con_name*********************************/		
		public function getName():String{
			return this.Con_name;
		}
		public override function setCon_name(_name:String):void{
			this.Con_name = _name;
		}
		/**************************get and set Con_type*******************************/
		public function getCon_type():String{
			return this.Con_type;
		}
		public override function setCon_type(_type:String):void{
			this.Con_type=_type;
		}
		/*************************get and set Rest_url********************************/
		public function getRset_url():String{
			return this.Rest_url;
		}
		public override function setRest_url(_url:String):void{
			this.Rest_url=_url;
		}
		/**************************end*********************************************/
	}
}