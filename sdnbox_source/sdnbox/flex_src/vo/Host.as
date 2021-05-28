package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Host")]
	public class Host
	{
		public id:int;
		public switch_id:int;
		public port_id:int;
		public mac_addr:String;
		public ip_addr:String;
		public ctime:Date;
		public mtime:Date;
	}
}