import mx.collections.XMLListCollection;

// ActionScript file
private var menubarXML:XMLList =
	<>
          <menu label="file">
					<item label = "new"/>
					<item label = "open"/>
					<item label = "save"/>
					<menu label = "export">
						<menu label = "export Mininet script ...">
	                       <item label = "export topolopy in Topo"/>
						   <item label = "export topolopy in CreateTopo"/>
                        </menu>
						<item label = "export NS3 script (new) ..."/>
						<item label = "export NSDL file ..."/>
						<item label = "export openflow controller script ..."/>
						<item label = "export Qos script ..."/>
					</menu>
					<item label = "print network scheme"/>
			</menu>
				    <menu label="device">
					   <item label="add controller"/>
					   <item label="remove controller"/>
					   <item label = "controller"/>
					   <item label = "switch"/>
					   <item label = "port"/>
					   <item label = "link"/>
					   <item label = "flow table"/>
					   <item label = "host"/>
				    </menu>
				<menu label="statistics">
					<item label = "TOP10 switch"/>
					<item label = "TOP10 flow table"/>
					<item label = "TOP10 host"/>
				</menu>
				<menu label="help">
					<item label = "Guide"/>
					<item label = "About"/>
				</menu>				
	</>;   //menu content

private var typicalTopoXML:XMLList =
	<>		
				<items label= "typical topology">
					<item label = "FatTree"/>
					<item label = "BCube"/>
					<item label = "clear"/>
				</items>
	</>;   //menu content

private var treeXML:XMLList =
	<>
				<nodes label="nodes">
					<node label="computer" preview_url="img/host.jpg"/>
					<node label="controllerOpenflow" preview_url="img/controller.jpg"/>
					<node label="switchOpenflow" preview_url="img/switch.jpg"/>
					<node label="wirelessRouter" preview_url="img/wirelessRouter.jpg"/>
					<node label="smartPhone" preview_url="img/smartPhone.jpg"/>
				</nodes>
				<links label="links">
					<link label="ethernet" preview_url="img/ethernet.jpg"/>
					<link label="wireless" preview_url="img/wireless.jpg"/>
				</links>
	</>; 


[Bindable]
public var menuBarCollection:XMLListCollection;  //data object；

[Bindable]
public var library:XMLListCollection;  

[Bindable]
public var typicalTopo:XMLListCollection;

public function create():void{
	menuBarCollection = new XMLListCollection(menubarXML);
	library = new XMLListCollection(treeXML);
	typicalTopo = new XMLListCollection(typicalTopoXML);
}

	
	
 //after loading flash, call initializing function XMLList, get XMLListCollection.