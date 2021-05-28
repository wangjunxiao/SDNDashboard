package utils
{
	public class createMAC	
	{
		/**
		 * @param  decimal from 0 to 1<<48-1= 2.8147497671066*10^14
		 * @return  12-bits hexadecimal String
		 */
		public static function getMAC(decimal:Number):String 
		{
			var hexadecimal:String;
			var prefix:String="";
			var fullString:String;
			var temp:String="";
			var macString:String;
			
			var i:int=0;
			
			hexadecimal=decimal.toString(16);
			
			while(i<12-hexadecimal.length){
				prefix=prefix+'0';
				i++;
			}
			
			i=0;
			fullString=prefix+hexadecimal;
			
			while(i<fullString.length){
				if(i<fullString.length-2)
					temp=temp+fullString.substr(i,2)+':';
				else
					temp=temp+fullString.substr(i,2)
				i+=2;
			}
			
			macString=temp;
			return macString;
		}
		
	}
}