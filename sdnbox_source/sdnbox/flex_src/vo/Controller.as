package vo
{
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.Controller")]
	public class Controller
	{
		public var ip:String;
		public var port:String;
		public var id:int;
		public var type:String;
		public var rest_url:String;
		public var ctime:Date;
	}
}