package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Switch")]
	public class Switch
	{
		public var id:int;
		public var ctrl_id:int;
		public var dp_id:String;
		public var n_tables:int;
		public var dp_desc:String;
		public var sw_desc:String;
		public var hw_desc:String;
		public var serial_num:String;
		public var mfr_desc:String;
		public var ctime:Date;
	}
}