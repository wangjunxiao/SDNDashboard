package Components
{
	[Bidable]
	[RemoteClass(alias="cn.dlut.entity.FlowEntry")]
	public class FlowEntry
	{
		public var name:String ="";
		public var dpid:String ="";
		public var in_port:String ="";
		public var priority:String ="";
		public var hard_timeout:String=""; 
		public var idle_timeout:String ="";
		public var cookie:String ="";
		
		// Layer 2
		public var dl_type:String ="";
		public var dl_vlan:String ="";
		public var dl_vlan_priority:String ="";
		public var dl_src:String ="";
		public var dl_dst:String ="";
		
		// Layer 3
		public var nw_src:String ="";
		public var nw_src_masklen:String ="";
		public var nw_dst:String ="";
		public var nw_dst_masklen:String ="";
		public var nw_tos:String ="";
		public var nw_proto:String ="";
		
		// Layer 4
		public var tp_src:String ="";
		public var tp_dst:String ="";
		
		public var action:String;
		
		public var output_port:String; // Add Output Ports
		public var new_vlan_id:String; // Set VLAN ID
		public var new_vlan_priority:String; // Set VLAN Priority
		public var new_dl_src:String; // Modify Datalayer Source Address
		public var new_dl_dst:String; // Modify Datalayer Destination Address
		public var new_nw_src:String; // Modify Network Source Address
		public var new_nw_dst:String; // Modify Network Destination Address
		public var new_nw_tos:String; // Modify ToS Bits
		public var new_tp_src:String; // Modify Transport Source Port
		public var new_tp_dst:String; // Modify Transport Destination Port
	}
}