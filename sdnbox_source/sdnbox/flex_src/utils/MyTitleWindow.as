package utils
{
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.MouseEvent;
	import flash.geom.Point;
	import flash.geom.Rectangle;
	import mx.core.FlexGlobals;
	import mx.containers.TitleWindow;
	import mx.controls.Button;
	import mx.controls.LinkButton;
	import mx.managers.CursorManager;
	import mx.core.mx_internal;
	import flash.text.TextFieldAutoSize;
	use namespace mx_internal;
	
	public class MyTitleWindow extends TitleWindow
	{
		public function MyTitleWindow()
		{
			super();
			this.showCloseButton=true;
		}
		
		protected var maxSizeButton:Button; 
		[Embed("assets/icon/titleWinIcon/WindowMaxButton.gif")]private var IconMaxSize:Class;
		[Embed("assets/icon/titleWinIcon/Buttonmaximize.png")]private var mouseOnIconMaxSize:Class;
		[Embed("assets/icon/titleWinIcon/WindowMaxButton2.gif")]private var mouseupIconMaxSize:Class;
		protected var minSizeButton:Button;
		[Embed("assets/icon/titleWinIcon/WindowMinButton.gif")]private var IconMinSize:Class;
		[Embed("assets/icon/titleWinIcon/Buttonminimize.png")]private var mouseOnIconMinSize:Class;
		[Embed("assets/icon/titleWinIcon/WindowMinButton2.gif")]private var mouseupIconMinSize:Class;
		[Embed("assets/icon/titleWinIcon/Buttonrestore.png")]private var Iconhuany:Class;
		
		//adjust TitleWindow size:0 top:1 right:2 left:7 bottom:4
		private var mouseMargin:Number=4;// response range
		//set mouse location right-up:3 right-down:6  left-down:11 left-up:8
		private var theSide:Number=0;
		private var SIDE_OTHER:Number=0;
		private var SIDE_TOP:Number=1;
		private var SIDE_RIGHT:Number=2;
		private var SIDE_LEFT:Number=7;
		private var SIDE_BOTTOM:Number=4;
		private var isReSize:Boolean;
		private var theOldPoint:Point;
		private var theMinWidth:Number=200;
		private var theMinHeight:Number=200;
		private var theOldWidth:Number;
		private var theOldHeight:Number;
		private var theStatus:int=0;
		private var titleNormal:String;
		private var titleMin:String;
		
		
		public var currentType:Class=null;
		
		
		[Embed("assets/icon/titleWinIcon/resizeCursorH.gif")]
		private var CursorH:Class;
		[Embed("assets/icon/titleWinIcon/resizeCursorTLBR.gif")]
		private var CursorR:Class;
		[Embed("assets/icon/titleWinIcon/resizeCursorTRBL.gif")]
		private var CursorL:Class;
		[Embed("assets/icon/titleWinIcon/resizeCursorV.gif")]
		private var CursorV:Class;
		private var CursorNull:Class=null;
		
		override protected function createChildren():void
		{
			super.createChildren();
			
			
			this.maxSizeButton=new LinkButton();
			this.maxSizeButton.width=this.maxSizeButton.height=16;
			this.maxSizeButton.y=6;
			this.maxSizeButton.setStyle("upIcon", IconMaxSize);
			this.maxSizeButton.setStyle("overIcon", mouseOnIconMaxSize);
			this.maxSizeButton.setStyle("downIcon", mouseupIconMaxSize);
			
			this.maxSizeButton.addEventListener(MouseEvent.CLICK,OnMaxSize);
			this.titleBar.addChild(this.maxSizeButton);
			
			
			this.minSizeButton=new LinkButton();
			this.minSizeButton.width=this.minSizeButton.height=16;
			this.minSizeButton.y=6;
			this.minSizeButton.setStyle("upIcon", IconMinSize);
			this.minSizeButton.setStyle("overIcon", mouseOnIconMinSize);
			this.minSizeButton.setStyle("downIcon", mouseupIconMinSize);
			
			this.minSizeButton.addEventListener(MouseEvent.CLICK,OnMinSize);
			this.titleBar.addChild(this.minSizeButton);
			
			
			this.addEventListener(MouseEvent.MOUSE_MOVE,onMouseMove);
			this.addEventListener(MouseEvent.MOUSE_OUT,onMouseOut);
			this.addEventListener(MouseEvent.MOUSE_DOWN,onMouseDown);
			
			titleNormal = this.title;
			titleMin = this.title.substring(0, 10) + "...";

		}
		
		protected function OnMaxSize(e:MouseEvent):void
		{
			if(theStatus == 0)
			{
				onSaveRestore();
				winMaxSize();
			}
			else if(theStatus == 2){
				winMaxSize();
			}
			else if(theStatus == 1)
			{
				onGetRestore();
			}
		}
		protected function OnMinSize(e:MouseEvent):void
		{
			if(theStatus != 2){
				if(theStatus == 0){
					onSaveRestore();
				}
				winMinSize();
			}
			else{
				onGetRestore();
			}
		}
		
		override protected function startDragging(event:MouseEvent):void{
			
			var rt:Rectangle = new Rectangle(0, 0, this.parent.width-this.width, this.parent.height-this.height);
			this.startDrag(false, rt);
			this.addEventListener(MouseEvent.MOUSE_UP, mouseUpEvent);
		}
		
		protected function mouseUpEvent(event:MouseEvent):void{
			this.stopDrag();
			this.removeEventListener(MouseEvent.MOUSE_UP, mouseUpEvent);
		}
		
		
		override mx_internal function createTitleTextField(childIndex:int):void
		{
			super.createTitleTextField(childIndex);
			titleTextField.percentWidth = 90;
			titleTextField.autoSize = TextFieldAutoSize.CENTER;
		}
		
		override protected function layoutChrome(unscaledWidth:Number,
												 unscaledHeight:Number):void
		{
			super.layoutChrome(unscaledWidth,unscaledHeight);
			
			this.maxSizeButton.x=this.titleBar.width-43;
			this.minSizeButton.x=this.titleBar.width-60;
			
			
			this.statusTextField.x-=32;
		}
		
		
		
		private function onMouseUp(event:MouseEvent):void
		{
			if(isReSize)
			{
				FlexGlobals.topLevelApplication.parent.removeEventListener(MouseEvent.MOUSE_UP,onMouseUp);
				FlexGlobals.topLevelApplication.parent.removeEventListener(MouseEvent.MOUSE_MOVE,onResize);
				isReSize=false;
//				dispatchEvent(new Event(TITLEWINDOW_RESIZE));
			}
			onChangeCursor(CursorNull);
		}
		private function onMouseDown(event:MouseEvent):void
		{
			if(theSide!=0 && theSide != 5)
			{
				isReSize=true;
				FlexGlobals.topLevelApplication.parent.addEventListener(MouseEvent.MOUSE_UP,onMouseUp);
				FlexGlobals.topLevelApplication.parent.addEventListener(MouseEvent.MOUSE_MOVE,onResize);
				var point:Point=new Point();
				point=this.localToContent(point);
				theOldPoint=point;
			}
		}
		private function onResize(event:MouseEvent):void
		{
			if(isReSize)
			{
				var xPlus:Number=FlexGlobals.topLevelApplication.parent.mouseX-this.x;
				var yPlus:Number=FlexGlobals.topLevelApplication.parent.mouseY-this.y;
				
				switch(theSide)
				{
					case SIDE_RIGHT+SIDE_BOTTOM:
						this.width=xPlus>theMinWidth?xPlus:theMinWidth;
						this.height=yPlus>theMinHeight?yPlus:theMinHeight;
						break;
					case SIDE_LEFT+SIDE_TOP:
						this.width=this.width-xPlus>theMinWidth?this.width-xPlus:theMinWidth;
						this.height=this.height-yPlus>theMinHeight?this.height-yPlus:theMinHeight;
						this.x=this.width>theMinWidth?FlexGlobals.topLevelApplication.parent.mouseX:this.x;
						this.y=this.height>theMinHeight?FlexGlobals.topLevelApplication.parent.mouseY:this.y;
						break;
					case SIDE_LEFT+SIDE_BOTTOM:
						this.width=this.width-xPlus>theMinWidth?this.width-xPlus:theMinWidth;
						this.height=yPlus>theMinHeight?yPlus:theMinHeight;
						this.x=this.width>theMinWidth?FlexGlobals.topLevelApplication.parent.mouseX:this.x;
						break;
					case SIDE_RIGHT+SIDE_TOP:
						this.width=xPlus>theMinWidth?xPlus:theMinWidth;
						this.height=this.height-yPlus>theMinHeight?this.height-yPlus:theMinHeight;
						this.y=this.height>theMinHeight?FlexGlobals.topLevelApplication.parent.mouseY:this.y;
						break;
					case SIDE_RIGHT:
						this.width=xPlus>theMinWidth?xPlus:theMinWidth;
						break;
					case SIDE_LEFT:
						this.width=this.width-xPlus>theMinWidth?this.width-xPlus:theMinWidth;
						this.x=this.width>theMinWidth?FlexGlobals.topLevelApplication.parent.mouseX:this.x;
						break;
					case SIDE_BOTTOM:
						this.height=yPlus>theMinHeight?yPlus:theMinHeight;
						break;
					case SIDE_TOP:
						this.height=this.height-yPlus>theMinHeight?this.height-yPlus:theMinHeight;
						this.y=this.height>theMinHeight?FlexGlobals.topLevelApplication.parent.mouseY:this.y;
						break;
				}
			}
			
		}
		private function onMouseOut(event:MouseEvent):void
		{
			if(!isReSize&&this.theStatus==0)
			{
				theSide=0;
				onChangeCursor(CursorNull);
				this.isPopUp=true;
			}
		}
		private function onMouseMove(event:MouseEvent):void
		{
			if(!theStatus)
			{
				var point:Point=new Point();
				point=this.localToGlobal(point);
				var xPosition:Number=FlexGlobals.topLevelApplication.parent.mouseX;
				var yPosition:Number=FlexGlobals.topLevelApplication.parent.mouseY;
				if(xPosition>=(point.x+this.width-mouseMargin)&&yPosition>=(point.y+this.height-mouseMargin))
				{//right-down
					onChangeCursor(CursorR,-9,-9);
					theSide=SIDE_RIGHT+SIDE_BOTTOM;
					this.isPopUp=false;
				}else if(xPosition<=(point.x+mouseMargin)&&yPosition<=(point.y+mouseMargin))
				{//left-up
					onChangeCursor(CursorR,-9,-9);
					theSide=SIDE_LEFT+SIDE_TOP;
					this.isPopUp=false;
				}else if(xPosition<=(point.x+mouseMargin)&&yPosition>=(point.y+this.height-mouseMargin))
				{//left-down
					onChangeCursor(CursorL,-9,-9);
					theSide=SIDE_BOTTOM+SIDE_LEFT;
					this.isPopUp=false;
				}else if(xPosition>=(point.x+this.width-mouseMargin)&&yPosition<=(point.y+mouseMargin))
				{//right-up
					onChangeCursor(CursorL,-9,-9);
					theSide=SIDE_RIGHT+SIDE_TOP;
					this.isPopUp=false;
				}else if(xPosition>(point.x+this.width-mouseMargin))
				{//right
					onChangeCursor(CursorH,-9,-9);
					theSide=SIDE_RIGHT;
					this.isPopUp=false;
				}else if(xPosition<(point.x+mouseMargin))
				{//left
					onChangeCursor(CursorH,-9,-9);
					theSide=SIDE_LEFT;
					this.isPopUp=false;
				}else if(yPosition<(point.y+mouseMargin))
				{//up
					onChangeCursor(CursorV,-9,-9);
					theSide=SIDE_TOP;
					this.isPopUp=false;
				}
				else if(yPosition>(point.y+this.height-mouseMargin))
				{//down
					onChangeCursor(CursorV,-9,-9);
					theSide=SIDE_BOTTOM;
					this.isPopUp=false;
				}
				else
				{
					onChangeCursor(CursorNull);
					if(!isReSize&&theStatus==0)
					{
						theSide=0;
						this.isPopUp=true;
					}
				}
				event.updateAfterEvent();
			}
		}

		private function onChangeCursor(type:Class,xOffset:Number=0,yOffset:Number=0):void
		{
			if(currentType!=type)
			{
				currentType=type;
				CursorManager.removeCursor(CursorManager.currentCursorID);
				if(type!=null)
				{
					CursorManager.setCursor(type,2,xOffset,yOffset);
				}
			}
		}
		private function onSaveRestore():void
		{
			var point:Point=new Point();
			theOldPoint=this.localToGlobal(point);
			theOldWidth=this.width;
			theOldHeight=this.height;
		}
		private function onGetRestore():void
		{
			this.x=theOldPoint.x;
			this.y=theOldPoint.y
			this.width=theOldWidth;
			this.height=theOldHeight;			
			this.theStatus = 0;
			setWinIcon(theStatus);
			this.title = titleNormal;
			titleTextField.autoSize = TextFieldAutoSize.CENTER;
		}
		
		private function winMaxSize():void{
			
			this.x=0;
			this.y=0;
			this.height = this.parent.height;
			this.width = this.parent.width;
			this.theStatus = 1;
			setWinIcon(theStatus);
			this.title = titleNormal;
			
			titleTextField.autoSize = TextFieldAutoSize.CENTER;
		}
		
		
		
		private function winMinSize():void{
			this.width = 200;
			//height < 31, titleBar can not display
			this.height = 31;  
			this.x  = 0;
			this.y = this.parent.height-31;	
			this.theStatus = 2;
			setWinIcon(theStatus);
			this.title = titleMin;
			
			titleTextField.autoSize = TextFieldAutoSize.LEFT;	
		}
		
		private function setWinIcon(status:int):void{
			switch(status){
				case 0:
					this.minSizeButton.setStyle("upIcon", IconMinSize);
					this.minSizeButton.setStyle("overIcon", mouseOnIconMinSize);
					this.minSizeButton.setStyle("downIcon", mouseupIconMinSize);
					
					this.maxSizeButton.setStyle("upIcon", IconMaxSize);
					this.maxSizeButton.setStyle("overIcon", mouseOnIconMaxSize);
					this.maxSizeButton.setStyle("downIcon", mouseupIconMaxSize);
					break;
				case 1:
					this.maxSizeButton.setStyle("upIcon", Iconhuany);
					this.maxSizeButton.setStyle("overIcon", Iconhuany);
					this.maxSizeButton.setStyle("downIcon", Iconhuany);
					
					this.minSizeButton.setStyle("upIcon", IconMinSize);
					this.minSizeButton.setStyle("overIcon", mouseOnIconMinSize);
					this.minSizeButton.setStyle("downIcon", mouseupIconMinSize);
					break;
				case 2:
					this.minSizeButton.setStyle("upIcon", Iconhuany);
					this.minSizeButton.setStyle("overIcon", Iconhuany);
					this.minSizeButton.setStyle("downIcon", Iconhuany);
					
					this.maxSizeButton.setStyle("upIcon", IconMaxSize);
					this.maxSizeButton.setStyle("overIcon", mouseOnIconMaxSize);
					this.maxSizeButton.setStyle("downIcon", mouseupIconMaxSize);
					break;
			}

		}
	}
}