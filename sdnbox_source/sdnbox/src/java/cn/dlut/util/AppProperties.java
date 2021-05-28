package cn.dlut.util;

import cn.dlut.util.PropertiesFileLoader;

public class AppProperties {	
	
	private static PropertiesFileLoader propertiesFileLoader = new PropertiesFileLoader("constants.properties");

	
	public static String floodSwsIds()
	{
		return propertiesFileLoader.get("floodSwsIds").toString();
	}
	
	public static String floodSwsPorts()
	{
		return propertiesFileLoader.get("floodSwsPorts").toString();
	}
	
	public static String floodSwsDesc()
	{
		return propertiesFileLoader.get("floodSwsDesc").toString();
	}
	
	public static String floodSwsTable()
	{
		return propertiesFileLoader.get("floodSwsTable").toString();
	}
	
	public static String floodSwsFeatures()
	{
		return propertiesFileLoader.get("floodSwsFeatures").toString();
	}
	
	public static String floodCtrlMemUsage()
	{
		return propertiesFileLoader.get("floodCtrlMemUsage").toString();
	}
	
	public static String floodLinkInfo()
	{
		return propertiesFileLoader.get("floodLinkInfo").toString();
	}
	
	public static String floodSwsDevice()
	{
		return propertiesFileLoader.get("floodSwsDevice").toString();
	}
	
	public static String floodSwsCtrlRole()
	{
		return propertiesFileLoader.get("floodSwsCtrlRole").toString();
	}
	
	public static String floodSwsFlow()
	{
		return propertiesFileLoader.get("floodSwsFlow").toString();
	}
	
	public static String floodFlowPUT()
	{
		return propertiesFileLoader.get("floodFlowPUT").toString();
	}
	
	public static String flood_preurl()
	{
		return propertiesFileLoader.get("flood_preurl").toString();
	}
	
	public static String RyuSwsIds()
	{
		return propertiesFileLoader.get("RyuSwsIds").toString();
	}
	
	public static String RyuSwsPorts()
	{
		return propertiesFileLoader.get("RyuSwsPorts").toString();
	}
	
	public static String RyuSwsDesc()
	{
		return propertiesFileLoader.get("RyuSwsDesc").toString();
	}
	
	public static String RyuSwsFlow()
	{
		return propertiesFileLoader.get("RyuSwsFlow").toString();
	}
	
	public static String RyuPortDesc()
	{
		return propertiesFileLoader.get("RyuPortDesc").toString();
	}
	
	public static String RyuGroup()
	{
		return propertiesFileLoader.get("RyuGroup").toString();
	}
	
	public static String RyuGroupDesc()
	{
		return propertiesFileLoader.get("RyuGroupDesc").toString();
	}
	
	public static String RyuGroupFeatures()
	{
		return propertiesFileLoader.get("RyuGroupFeatures").toString();
	}
	
	public static String RyuMeter()
	{
		return propertiesFileLoader.get("RyuMeter").toString();
	}
	
	public static String RyuMeterConfig()
	{
		return propertiesFileLoader.get("RyuMeterConfig").toString();
	}
	
	public static String RyuMeterFeatures()
	{
		return propertiesFileLoader.get("RyuMeterFeatures").toString();
	}
	
	public static String Ryu_preurl()
	{
		return propertiesFileLoader.get("Ryu_preurl").toString();
	}
	
	public static String RyuAddFlow(){
		return propertiesFileLoader.get("RyuAddFlow").toString();
	}
	
	public static String RyuModifyFlow(){
		return propertiesFileLoader.get("RyuModifyFlow").toString();
	}
	
	public static String RyuModifyFlowStrictly(){
		return propertiesFileLoader.get("RyuModifyFlowStrictly").toString();
	}
	
	public static String RyuDeleteAllMatchFlow(){
		return propertiesFileLoader.get("RyuDeleteAllMatchFlow").toString();
	}
	
	public static String RyuDeleteFlowStrictly(){
		return propertiesFileLoader.get("RyuDeleteFlowStrictly").toString();
	}
	
	public static String RyuDeleteAllFlow(){
		return propertiesFileLoader.get("RyuDeleteAllFlow").toString();
	}
	
	public static String RyuAddGroup(){
		return propertiesFileLoader.get("RyuAddGroup").toString();
	}
	
	public static String RyuModifyGroup(){
		return propertiesFileLoader.get("RyuModifyGroup").toString();
	}
	
	public static String RyuDeleteGroup(){
		return propertiesFileLoader.get("RyuDeleteGroup").toString();
	}
	
	public static String RyuModifyPort(){
		return propertiesFileLoader.get("RyuModifyPort").toString();
	}
	
	public static String RyuAddMeter(){
		return propertiesFileLoader.get("RyuAddMeter").toString();
	}
	
	public static String RyuModifyMeter(){
		return propertiesFileLoader.get("RyuModifyMeter").toString();
	}
	
	public static String RyuDeleteMeter(){
		return propertiesFileLoader.get("RyuDeleteMeter").toString();
	}
	
	public static String RyuSendExperimenterMessage(){
		return propertiesFileLoader.get("RyuSendExperimenterMessage").toString();
	}
	
	public static String getRyu_preurl(){
		return propertiesFileLoader.get("Ryu_preurl").toString();
	}
	
	public static String getRyuRule(){
		return propertiesFileLoader.get("RyuRule").toString();
	}

	public static String getAclRule(){
		return propertiesFileLoader.get("AclRule").toString();
	}
	
	public static String RyuLink(){
		return propertiesFileLoader.get("RyuLink").toString();
	}

	public static String RyuFireWallEnable(){
		return propertiesFileLoader.get("RyuFireWallEnable").toString();
	}
	
}
