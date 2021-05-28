package Components
{
	import flash.events.MouseEvent;
	import flash.geom.Point;
	
	import mx.controls.Menu;
	import mx.core.FlexGlobals;
	import mx.events.MenuEvent;
	import mx.managers.PopUpManager;
	
	import skins.Item_images_skin;
	
	import view.H_SimpleTitleWindowExample;

	public class Host extends Item_image
	{
		[Bindable]
		public var host_ID:int;
		
		[Bindable]
		public var host_Name:String;
		
		[Bindable]
		public var host_IP:String;
		
		[Bindable]
		public var switch_ID:int;
		
		[Bindable]
		public var Mac_Add:String;
		
		private var point:Point = new Point();
		
		/****************impl: mouse click and menu pop up****************/
		/*************************menu set********************************/	
		private var menuData:Array=
			[{label:'properties'},{label:'remove'}];
		private var myMenu:Menu;
		
		
	   public function showDialogue():void{
		   /*  the third parameter 'true' represents: if have access to parent elements afer myPanel window popping up
		   in the case of 'true', background blur effect appears, can not have access to parent elements */	  
		   FlexGlobals.topLevelApplication.activeItem = this;
		   
		 //  this.parent = this.host_IP;
				var myPanel:H_SimpleTitleWindowExample = 
					H_SimpleTitleWindowExample(PopUpManager.createPopUp(this, H_SimpleTitleWindowExample, true)); 
				
				myPanel.x = 420;
				
				myPanel.y = 170;			
		}
	   
	   /**********************define function to handle menu click event***************************************/	  
	   private function menuHandler(event:MenuEvent):void {	

		   
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
	   public function Host()
	   {		
		   super();
		   this.source = "img/host.jpg";
		   setStyle("skinClass", skins.Item_images_skin);
		   //os default disallow double-click evnet, set to allow double-click
		   this.doubleClickEnabled = true;
		   this.addEventListener(MouseEvent.DOUBLE_CLICK,showMenu);
	   }
		
/************************get and set host_id******************************/
		public function getHost_ID():int{
			return this.host_ID;
		}
		public override function setHost_ID(_id:int):void{
			this.host_ID=_id;
		}
		
/************************get and set host_name*****************************/
		public function getName():String{
			return this.host_Name;
		}
		public override function setHost_Name(_name:String):void{
			this.host_Name=_name;
		}
		
/***********************get and set host_IP********************************/
		public function getHost_IP():String{
			return this.host_IP;
		}
		public override function setHost_IP(_ip:String):void{
			this.host_IP=_ip;
		}
		
/***********************get and set Switch_ID*******************************/
		public function getSwitch_ID():int{
			return this.switch_ID;
		}
		public override function setSwitch_ID(s_id:int):void{
			this.switch_ID=s_id;
		}
		
/***********************get and set Mac_Add*********************************/
		public function getMac_Add():String{
			return this.Mac_Add;
		}
		public override function setMac_Add(_add:String):void{
			this.Mac_Add=_add;
		}
/**************************end***********************************************/
	}
}