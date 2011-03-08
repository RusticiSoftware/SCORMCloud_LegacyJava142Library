package com.rusticisoftware.hostedengine.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RegistrationData {

	public static class InstanceData {
		private int instanceId;
		private int courseVersion;
		
		public InstanceData() {}
		public int getInstanceId(){
			return this.instanceId;
		}
		public void setInstanceId(int instanceId){
			this.instanceId = instanceId;
		}
		public int getCourseVersion(){
			return this.courseVersion;
		}
		public void setCourseVersion(int courseVersion){
			this.courseVersion = courseVersion;
		}
	}
	
	
	private String appId;
	private String courseId;
	private String registrationId;
	private String resultsData;
	private ArrayList instances = new ArrayList();
	
	
	public RegistrationData() {}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public ArrayList getInstances(){
		return instances;
	}
	public void setResultsData(String resultsData){
		this.resultsData = resultsData;
	}
	public String getResultsData(){
		return this.resultsData;
	}
	
	public static RegistrationData parseFromXmlElement (Element elem) {
		String regId = elem.getAttribute("id");
		String courseId = elem.getAttribute("courseid");
		
		
		RegistrationData data = new RegistrationData();
		data.setRegistrationId(regId);
		data.setCourseId(courseId);
		
		ArrayList instances = data.getInstances();
		NodeList instancesList = elem.getElementsByTagName("instance");
		if(instancesList.getLength() > 0){
			for(int i = 0; i < instancesList.getLength(); i++){
				Element instance = (Element)instancesList.item(i);
				
				int instanceId, courseVersion;
				try { instanceId = Integer.parseInt( instance.getAttribute("id") ); }
				catch (Exception e) { instanceId = -1; }
				try { courseVersion = Integer.parseInt( instance.getAttribute("courseversion") ); }
				catch (Exception e) { courseVersion = -1; }

				RegistrationData.InstanceData instanceData = new RegistrationData.InstanceData();
				instanceData.setInstanceId(instanceId);
				instanceData.setCourseVersion(courseVersion);
				instances.add(instanceData);
			}
		}
		
		NodeList reportList = elem.getElementsByTagName("registrationreport");
		if(reportList.getLength() > 0){
			Element report = (Element)reportList.item(0);
			String regResults = null;
			try { regResults = XmlUtils.getXmlString(report); }
			catch (Exception e) {};
			if(regResults != null){
				data.setResultsData(regResults);
			}
		}		
		
		return data;
	}
	
	public static List ConvertToRegistrationDataList (Document regListXml){
		Element regListElem = (Element)regListXml.getElementsByTagName("registrationlist").item(0);
		ArrayList regList = new ArrayList();
		NodeList regElems = regListElem.getChildNodes();
		for(int i = 0; i < regElems.getLength(); i++){
			Element regElem = (Element)regElems.item(i);
			regList.add( parseFromXmlElement(regElem) );
		}
		return regList;
	}
	
	
	public static String getXmlString (List regList) {
		StringBuffer xml = new StringBuffer();
		xml.append("<registrationlist>");
		//for(RegistrationData regData : regList){
		Iterator it = regList.iterator();
		while(it.hasNext()){
			RegistrationData regData = (RegistrationData)it.next();
			xml.append("<registration id=\"" + XmlUtils.xmlEncode(regData.getRegistrationId()) + "\" ");
			xml.append("courseid=\"" + XmlUtils.xmlEncode(regData.getCourseId()) + "\" ");
			xml.append(">");
			
			ArrayList instances = regData.getInstances();
			xml.append("<instances>");
			Iterator it2 = instances.iterator();
			while(it2.hasNext()){
				RegistrationData.InstanceData instanceData = (RegistrationData.InstanceData)it2.next();
				xml.append("<instance id=\"" + instanceData.getInstanceId() + "\" courseversion=\"" + instanceData.getCourseVersion() + "\" />");
			}
			xml.append("</instances>");
			
			if(regData.getResultsData() != null){
				xml.append(regData.getResultsData());
			}	
			
			xml.append("</registration>");
		}
		xml.append("</registrationlist>");
		return xml.toString();
	}
}
