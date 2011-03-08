package com.rusticisoftware.hostedengine.client;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AsyncImportResult
{
    public static class ImportStatus {
    	public static final ImportStatus CREATED = new ImportStatus("created");
    	public static final ImportStatus RUNNING = new ImportStatus("running");
    	public static final ImportStatus FINISHED = new ImportStatus("finished");
    	public static final ImportStatus ERROR = new ImportStatus("error");
    	private String type;
    	private ImportStatus(String type){
    		this.type = type;
    	}
    	public String toString() {
    		return this.type;
    	}
    }
    
    private ImportStatus status = ImportStatus.CREATED;
    private List importResults;
    private int errorCode = -1;
    private String errorMessage;
    
    public ImportStatus getImportStatus()
    {
        return status;
    }
    public List getImportResults()
    {
        return importResults;
    }
    public int getErrorCode()
    {
    	return errorCode;
    }
    public String getErrorMessage()
    {
        return errorMessage;
    }
    
    public AsyncImportResult(Document asyncImportResultXml)
    {
        Element elem = ((Element)(asyncImportResultXml
                                .getElementsByTagName("status")).item(0));
        
        String statusText = XmlUtils.getElemText(elem);
        
        if (statusText.equals("created")){
            this.status = ImportStatus.CREATED;
        }  else if (statusText.equals("running")) {
            this.status = ImportStatus.RUNNING;
        } else if (statusText.equals("finished")) {
            this.status = ImportStatus.FINISHED;
        } else if (statusText.equals("error")) {
            this.status = ImportStatus.ERROR;
        }

        if (this.status == ImportStatus.FINISHED) {
            this.importResults = ImportResult.ConvertToImportResults(asyncImportResultXml);
        }

        if (this.status == ImportStatus.ERROR) {
        	Element errElem = ((Element)(asyncImportResultXml
                    				.getElementsByTagName("error")).item(0));
        	
        	try { this.errorCode = Integer.parseInt(errElem.getAttribute("code")); }
        	catch (NumberFormatException nfe) {}
        	
            this.errorMessage = XmlUtils.getElemText(errElem);
        }
    }

    public boolean IsComplete()
    {
        return ((this.status == ImportStatus.FINISHED) || (this.status == ImportStatus.ERROR));
    }

    public boolean HasError()
    {
        return (this.status == ImportStatus.ERROR);
    }
}
