package com.rusticisoftware.hostedengine.client;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CourseMetadata {

	private String id;
	private String objectTitle;
	private String title;
	private String description;
	private long duration;
	private long typicaltime;
	private double masteryScore;
	private ArrayList keyWords = new ArrayList();
	private ArrayList children = new ArrayList();
	
	public CourseMetadata() {};
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObjectTitle(){
		return objectTitle;
	}
	public void setObjectTitle(String objectTitle){
		this.objectTitle = objectTitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public long getTypicaltime() {
		return typicaltime;
	}
	public void setTypicaltime(long typicaltime) {
		this.typicaltime = typicaltime;
	}
	public double getMasteryScore() {
		return masteryScore;
	}
	public void setMasteryScore(double masteryScore){
		this.masteryScore = masteryScore;
	}
	public ArrayList getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(ArrayList keyWords) {
		this.keyWords = keyWords;
	}
	public ArrayList getChildren() {
		return children;
	}
	public void setChildren(ArrayList children) {
		this.children = children;
	}
	
	public static CourseMetadata parseFromXmlElement (Element objectElem) throws Exception {
		CourseMetadata data = new CourseMetadata();
		data.setId(objectElem.getAttribute("id"));
		data.setObjectTitle(objectElem.getAttribute("title")); 
		
		Element elem = (Element)objectElem.getElementsByTagName("metadata").item(0);
		NodeList attrs = elem.getChildNodes();
		for(int i = 0; i < attrs.getLength(); i++){
			Element attr = (Element)attrs.item(i);
			String tagName = attr.getTagName();
			if(tagName.equals("title")){
				data.setTitle( XmlUtils.getElemText(attr) );
			}
			else if (tagName.equals("description")){
				data.setDescription( XmlUtils.getElemText(attr) );
			}
			else if (tagName.equals("duration")){
				long duration = 0L;
				try { duration = Long.parseLong( XmlUtils.getElemText(attr) ); }
				catch (Exception e) {};
				data.setDuration(duration);
			}
			else if (tagName.equals("typicaltime")){
				long typicalTime = 0L;
				try { typicalTime = Long.parseLong( XmlUtils.getElemText(attr) ); }
				catch (Exception e) {};
				data.setTypicaltime(typicalTime);
			}
			else if (tagName.equals("keywords")){
				ArrayList kwords = data.getKeyWords();
				NodeList keyWordElems = attr.getElementsByTagName("keyword");
				for(int wordIndex = 0; wordIndex < keyWordElems.getLength(); wordIndex++){
					Element keyWordElem = (Element)keyWordElems.item(wordIndex);
					kwords.add( XmlUtils.getElemText(keyWordElem) );
				}
			}
			else if (tagName.equals("children")){
				ArrayList children = data.getChildren();
				NodeList childElems = attr.getChildNodes();
				for(int childIndex = 0; childIndex < childElems.getLength(); childIndex++){
					Element childElem = (Element)childElems.item(childIndex); 
					children.add( XmlUtils.getElemText(childElem) );
				}
			}
		}
		return data;
	}
}
