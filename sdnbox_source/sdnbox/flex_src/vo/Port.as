package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Port")]
	public class Port
	{
		public id:int;
		public switch_id:int;
		public port_no:int;
		public mac_addr:String;
		public port_name:String;
		public ctime:Date;
	}
}