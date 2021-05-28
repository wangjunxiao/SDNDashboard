// ActionScript file
import mx.controls.Image;
import mx.managers.PopUpManager;

import org.bytearray.gif.events.*;
import org.bytearray.gif.player.GIFPlayer; 

private var mygifplayer:GIFPlayer = new GIFPlayer();//new gif player
private var img:Image = new Image();	//new image container as GIF player container	

private function loading(url:String):void{
	var request:URLRequest = new URLRequest(url);//gif under same document   
	mygifplayer.load(request);  //GIF player download gif
	img.addChild(mygifplayer);//img add play function
	mygifplayer.addEventListener(GIFPlayerEvent.COMPLETE, onCompleteGIF);//callback function of finishing gif loading 			  	
	
	//To achieve the pop-up window, the following second lines represents middle pop-up, note that only relative to the parent container center
	PopUpManager.addPopUp(img,this,true);
	PopUpManager.centerPopUp(img); 				
}

public function onCompleteGIF(event:GIFPlayerEvent):void{
	mygifplayer.play(); //play gif
}
