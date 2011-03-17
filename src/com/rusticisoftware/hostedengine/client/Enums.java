/* Software License Agreement (BSD License)
 * 
 * Copyright (c) 2010-2011, Rustici Software, LLC
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Rustici Software, LLC BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
