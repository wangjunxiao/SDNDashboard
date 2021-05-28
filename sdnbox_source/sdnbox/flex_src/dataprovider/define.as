// ActionScript file
import Components.Component;
import Components.Controller;
import Components.Host;
import Components.Item_image;
import Components.Link;
import Components.Switch;

import flash.display.DisplayObject;
import flash.events.*;
import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.controls.*;
import mx.controls.Alert;
import mx.controls.Menu;
import mx.core.Container;
import mx.core.DragSource;
import mx.core.MXMLObjectAdapter;
import mx.core.UIComponent;
import mx.events.*;
import mx.events.DragEvent;
import mx.events.FlexEvent;
import mx.events.MenuEvent;
import mx.managers.CursorManager;
import mx.managers.DragManager;

import skins.Item_images_skin;

import spark.components.Button;

import utils.createMAC;

[Embed(source="assets/pen.png")]
private static const imageCursor:Class;

private var previewURL:String;
private var drag_start_obj:String;

private var isMouseDown:Boolean = false;
private var isDraw:Boolean = false;
private var item:Item_image = null;
private var fromObj:Item_image = null;
private var toObj:Item_image = null;
private var currentTarget:Item_image = null;
private var currentX:Number = 0;
private var currentY:Number = 0;
private var controller:String="img/controller.jpg";
private var switchs:String="img/switch.jpg";
private var host:String="img/host.jpg";
private var id_num:int = 0;
private var link_num:int = 0;

private var C_obj:ArrayCollection = new ArrayCollection();
private var H_obj:ArrayCollection = new ArrayCollection();
public  var S_obj:ArrayCollection = new ArrayCollection();

public var ovxAddControllerCollection:ArrayCollection = new ArrayCollection();

[Bindable]
public var startY:int;
[Bindable]
public var endY: int;
[Bindable]
public var startX:int;
[Bindable]
public var endX:int;




public var ac_controller:ArrayCollection=new ArrayCollection();
public var ac_switch:ArrayCollection=new ArrayCollection();
public var ac_flow:ArrayCollection=new ArrayCollection();
public var ac_port:ArrayCollection=new ArrayCollection();
public var ac_host:ArrayCollection=new ArrayCollection();
public var ac_link:ArrayCollection=new ArrayCollection();
public var ac_package:ArrayCollection = new ArrayCollection();

public var ac_firewall_enable:String ;

//AclRule
public var ac_aclrule:ArrayCollection =new ArrayCollection();
public var rule_answer:String;



public  var activeItem:Item_image;
public  var selectedObj:Component;
public  var activeLink:Link;

public var parentDisplayObject:DisplayObject;

public function getlink_num():int{
	return link_num;
}

public function getS_obj():ArrayCollection{
	return S_obj;
}