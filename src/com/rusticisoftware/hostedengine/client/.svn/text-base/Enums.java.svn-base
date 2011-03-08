package com.rusticisoftware.hostedengine.client;

public class Enums
{
	public static class EnumBase {
		
	}
    public static class MetadataScope {
        public static MetadataScope COURSE = new MetadataScope("course");
        public static MetadataScope ACTIVITY = new MetadataScope("activity");
        private String type;
        private static MetadataScope[] values = { COURSE, ACTIVITY };
        
        private MetadataScope(String type){
        	this.type = type;
        }
        
        public static MetadataScope getValue(String name) {
            if (name == null)
                return null;
            
            for(int i = 0; i < values.length; i++){
            	String value = values[i].toString();
                if (value.equalsIgnoreCase(name)){
                    return values[i];
                }
            }
            return null;    
        }
        public String toString() {
            return this.type;
        }
    }
    
    public static class MetadataFormat { 
        public static MetadataFormat SUMMARY = new MetadataFormat("summary");
        public static MetadataFormat DETAIL = new MetadataFormat("detail");
        private String type;
        private static MetadataFormat[] values = { SUMMARY, DETAIL };
        
        private MetadataFormat(String type){
        	this.type = type;
        }
        
        public static MetadataFormat getValue(String name) {
            if (name == null)
                return null;
            
            for(int i = 0; i < values.length; i++){
            	String value = values[i].toString();
                if (value.equalsIgnoreCase(name)){
                    return values[i];
                }
            }
            return null;    
        }
        public String toString() {
            return this.type;
        }
        
    }
    
    public static class RegistrationResultsFormat { 
    	public static RegistrationResultsFormat COURSE_SUMMARY = new RegistrationResultsFormat("course");
        public static RegistrationResultsFormat ACTIVITY_SUMMARY = new RegistrationResultsFormat("activity");
        public static RegistrationResultsFormat FULL_DETAIL = new RegistrationResultsFormat("full");
        private String type;
        private static RegistrationResultsFormat[] values = { COURSE_SUMMARY, ACTIVITY_SUMMARY, FULL_DETAIL };
        
        private RegistrationResultsFormat(String type){
        	this.type = type;
        } 
        
        public static RegistrationResultsFormat getValue(String name) {
            if (name == null)
                return null;
            
            for(int i = 0; i < values.length; i++){
            	String value = values[i].toString();
                if (value.equalsIgnoreCase(name)){
                    return values[i];
                }
            }
            return null;    
        }
        public String toString() {
            return this.type;
        }   
    }
    
    public static class DataFormat {
        public static DataFormat XML = new DataFormat("xml");
        public static DataFormat JSON = new DataFormat("json");
        private String type;
        private static DataFormat[] values = { XML, JSON };
        
        private DataFormat(String type){
        	this.type = type;
        }
        
        public static DataFormat getValue(String name) {
            if (name == null)
                return null;
            
            for(int i = 0; i < values.length; i++){
            	String value = values[i].toString();
                if (value.equalsIgnoreCase(name)){
                    return values[i];
                }
            }
            return null;    
        }
        public String toString() {
            return this.type;
        }
    }
    
    public static class RegistrationResultsAuthType {
        public static RegistrationResultsAuthType FORM = new RegistrationResultsAuthType("form");
        public static RegistrationResultsAuthType HTTPBASIC = new RegistrationResultsAuthType("httpbasic");
        private String type;
        private static RegistrationResultsAuthType[] values = { FORM, HTTPBASIC };
        
        private RegistrationResultsAuthType(String type){
        	this.type = type;
        }
        
        public static RegistrationResultsAuthType getValue(String name) {
            if (name == null)
                return null;
            
            for(int i = 0; i < values.length; i++){
            	String value = values[i].toString();
                if (value.equalsIgnoreCase(name)){
                    return values[i];
                }
            }
            return null;    
        }
        public String toString() {
            return this.type;
        }
    }
    
    public static class ReportageNavPermission {
    	public static ReportageNavPermission NONAV = new ReportageNavPermission("nonav");
        public static ReportageNavPermission DOWNONLY = new ReportageNavPermission("downonly");
        public static ReportageNavPermission FREENAV = new ReportageNavPermission("freenav");
        private String type;
        private static ReportageNavPermission[] values = { NONAV, DOWNONLY, FREENAV };
        
        private ReportageNavPermission(String type){
        	this.type = type;
        } 
        
        public static ReportageNavPermission getValue(String name) {
            if (name == null)
                return null;
            
            for(int i = 0; i < values.length; i++){
            	String value = values[i].toString();
                if (value.equalsIgnoreCase(name)){
                    return values[i];
                }
            }
            return null;    
        }
        public String toString() {
            return this.type;
        }
    }
}
