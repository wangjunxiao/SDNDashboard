	//mode 1:controller  2:switch   3:port 4:link 5:flow table 6:host
	import view.ControllerListTitleWindow;
	import view.SwitchListTitleWindow;
	import view.PortListTitleWindow;
	import view.LinkListTitleWindow;
	import view.FlowListTitleWindow;
	import view.HostListTitleWindow;
	import view.addControllerWindow;
	import view.deleteControllerWindow;


	protected function listshowHandler(mode:int):void{
		switch(mode){
			case 1: 
				var myPanel1:ControllerListTitleWindow = 
				ControllerListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, ControllerListTitleWindow, false));
				myPanel1.x = 100;
				myPanel1.y = 150;	
				break;
			case 2: 
				var myPanel2:SwitchListTitleWindow = 
				SwitchListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, SwitchListTitleWindow, false));
				myPanel2.x = 100;
				myPanel2.y = 150;
				break;
			case 3: 
				var myPanel3:PortListTitleWindow = 
				PortListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, PortListTitleWindow, false));
				myPanel3.x = 100;
				myPanel3.y = 150;	
				break;
			case 4:
				var myPanel4:LinkListTitleWindow = 
				LinkListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, LinkListTitleWindow, false));
				myPanel4.x = 100;
				myPanel4.y = 150;	
				break;
			case 5:
				var myPanel5:FlowListTitleWindow = 
				FlowListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, FlowListTitleWindow, false));
				myPanel5.x = 100;
				myPanel5.y = 150;	
				break;
			case 6: 
				var myPanel6:HostListTitleWindow = 
				HostListTitleWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, HostListTitleWindow, false));
				myPanel6.x = 100;
				myPanel6.y = 150;	
				break;
			case 7:
				var myPanel7:addControllerWindow = 
				addControllerWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, addControllerWindow, false)); 
				myPanel7.x = 420;
				myPanel7.y = 170;
				break;
			case 8:
				var myPanel8:deleteControllerWindow=
				deleteControllerWindow(PopUpManager.createPopUp(FlexGlobals.topLevelApplication.parentDisplayObject, deleteControllerWindow, false)); 
				myPanel8.x = 420;
				myPanel8.y = 170;
				break;
		}
	
		
	}
