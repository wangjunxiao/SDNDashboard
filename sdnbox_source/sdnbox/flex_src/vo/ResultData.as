package vo {
	
	[Bindable]
	[RemoteClass(alias="cn.dlut.entity.ResultData")]
	public class ResultData {
		public var result:*;
		public var success:Boolean;
		public var faultMessage:String;
	}
}