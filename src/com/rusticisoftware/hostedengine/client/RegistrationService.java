package com.rusticisoftware.hostedengine.client;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.rusticisoftware.hostedengine.client.Enums.*;

public class RegistrationService
{
    private Configuration configuration = null;
    private ScormEngineService manager = null;

    /// <summary>
    /// Main constructor that provides necessary configuration information
    /// </summary>
    /// <param name="configuration">Application Configuration Data</param>
    public RegistrationService(Configuration configuration, ScormEngineService manager)
    {
        this.configuration = configuration;
        this.manager = manager;
    }

    /// <summary>
    /// Create a new Registration (Instance of a user taking a course)
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="courseId">Unique Identifier for the course</param>
    /// <param name="versionId">Optional versionID, if Int32.MinValue, latest course version is used.</param>
    /// <param name="learnerId">Unique Identifier for the learner</param>
    /// <param name="learnerFirstName">Learner's first name</param>
    /// <param name="learnerLastName">Learner's last name</param>
    /// <param name="resultsPostbackUrl">URL to which the server will post results back to</param>
    /// <param name="authType">Type of Authentication used at results postback time</param>
    /// <param name="postBackLoginName">If postback authentication is used, the logon name</param>
    /// <param name="postBackLoginPassword">If postback authentication is used, the password</param>
    /// <param name="resultsFormat">The Format of the results XML sent to the postback URL</param>
    public void CreateRegistration(String registrationId, String courseId, int versionId, String learnerId, 
        String learnerFirstName, String learnerLastName, String resultsPostbackUrl, 
        RegistrationResultsAuthType authType, String postBackLoginName, String postBackLoginPassword,
        RegistrationResultsFormat resultsFormat) throws Exception
    {
        CreateRegistration(registrationId, courseId, versionId, 
        		learnerId, learnerFirstName, learnerLastName, null, 
        		resultsPostbackUrl, authType, postBackLoginName, 
        		postBackLoginPassword, null, null, null, resultsFormat);
    }
    
    /// <summary>
    /// Create a new Registration (Instance of a user taking a course)
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="courseId">Unique Identifier for the course</param>
    /// <param name="versionId">Optional versionID, if Int32.MinValue, latest course version is used.</param>
    /// <param name="learnerId">Unique Identifier for the learner</param>
    /// <param name="learnerFirstName">Learner's first name</param>
    /// <param name="learnerLastName">Learner's last name</param>
    /// <param name="learnerLastName">Learner's email address</param>
    /// <param name="resultsPostbackUrl">URL to which the server will post results back to</param>
    /// <param name="authType">Type of Authentication used at results postback time</param>
    /// <param name="postBackLoginName">If postback authentication is used, the logon name</param>
    /// <param name="postBackLoginPassword">If postback authentication is used, the password</param>
    /// <param name="learnerTags">Comma separated list of learner tags</param>
    /// <param name="courseTags">Comma separated list of course tags</param>
    /// <param name="registrationTags">Comma separated list of registration tags</param>
    /// <param name="resultsFormat">The Format of the results XML sent to the postback URL</param>
    public void CreateRegistration(String registrationId, String courseId, int versionId, String learnerId, 
        String learnerFirstName, String learnerLastName, String email, String resultsPostbackUrl, 
        RegistrationResultsAuthType authType, String postBackLoginName, String postBackLoginPassword,
        String learnerTags, String courseTags, String registrationTags,
        RegistrationResultsFormat resultsFormat) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        request.getParameters().add("courseid", courseId);
        request.getParameters().add("fname", learnerFirstName);
        request.getParameters().add("lname", learnerLastName);
        request.getParameters().add("learnerid", learnerId);

        // Required on this signature but not by the actual service
        request.getParameters().add("authtype", authType.toString().toLowerCase());
        request.getParameters().add("resultsformat", resultsFormat.toString().toLowerCase());

        // Optional:
        if(!Utils.isNullOrEmpty(email))
        	request.getParameters().add("email", email);
        if (!Utils.isNullOrEmpty(resultsPostbackUrl))
            request.getParameters().add("postbackurl", resultsPostbackUrl);
        if (!Utils.isNullOrEmpty(postBackLoginName))
            request.getParameters().add("urlname", postBackLoginName);
        if (!Utils.isNullOrEmpty(postBackLoginPassword))
            request.getParameters().add("urlpass", postBackLoginPassword);
        if (versionId != Integer.MIN_VALUE)
            request.getParameters().add("versionid", new Integer(versionId));

        if(!Utils.isNullOrEmpty(learnerTags)){
        	request.getParameters().add("learnerTags", learnerTags);
        }
        if(!Utils.isNullOrEmpty(courseTags)){
        	request.getParameters().add("courseTags", courseTags);
        }
        if(!Utils.isNullOrEmpty(registrationTags)){
        	request.getParameters().add("registrationTags", registrationTags);
        }

        request.callService("rustici.registration.createRegistration");
    }

    
    /// <summary>
    /// Create a new Registration (Instance of a user taking a course). This method is only applicable
    /// to the SCORM Cloud App.
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="courseId">Unique Identifier for the course</param>
    /// <param name="versionId">Optional versionID, if Int32.MinValue, latest course version is used.</param>
    /// <param name="learnerId">Unique Identifier for the learner</param>
    /// <param name="learnerFirstName">Learner's first name</param>
    /// <param name="learnerLastName">Learner's last name</param>
    /// <param name="learnerLastName">Learner's email address</param>
    /// <param name="resultsPostbackUrl">URL to which the server will post results back to</param>
    /// <param name="authType">Type of Authentication used at results postback time</param>
    /// <param name="postBackLoginName">If postback authentication is used, the logon name</param>
    /// <param name="postBackLoginPassword">If postback authentication is used, the password</param>
    /// <param name="resultsFormat">The Format of the results XML sent to the postback URL</param>
    public void CreateSandboxRegistration(String registrationId, String courseId, String learnerId, 
        String learnerFirstName, String learnerLastName, String email) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("sandbox", new Boolean(true));
        request.getParameters().add("regid", registrationId);
        request.getParameters().add("courseid", courseId);
        request.getParameters().add("learnerid", learnerId);
        request.getParameters().add("fname", learnerFirstName);
        request.getParameters().add("lname", learnerLastName);
        if(!Utils.isNullOrEmpty(email))
        	request.getParameters().add("email", email);

        /*
        // Required on this signature but not by the actual service
        request.getParameters().add("authtype", authType.toString().toLowerCase());
        request.getParameters().add("resultsformat", resultsFormat.toString().toLowerCase());

        // Optional:
        	
        if (!Utils.isNullOrEmpty(resultsPostbackUrl))
            request.getParameters().add("postbackurl", resultsPostbackUrl);
        if (!Utils.isNullOrEmpty(postBackLoginName))
            request.getParameters().add("urlname", postBackLoginName);
        if (!Utils.isNullOrEmpty(postBackLoginPassword))
            request.getParameters().add("urlpass", postBackLoginPassword);
        if (versionId != Integer.MIN_VALUE)
            request.getParameters().add("versionid", new Integer(versionId));*/

        request.callService("rustici.registration.createRegistration");
    }
    

    //TODO: Other overrides of createRegistration....

    /// <summary>
    /// Create a new Registration (Instance of a user taking a course)
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="courseId">Unique Identifier for the course</param>
    /// <param name="learnerId">Unique Identifier for the learner</param>
    /// <param name="learnerFirstName">Learner's first name</param>
    /// <param name="learnerLastName">Learner's last name</param>
    public void CreateRegistration(String registrationId, String courseId, String learnerId,
        String learnerFirstName, String learnerLastName) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        request.getParameters().add("courseid", courseId);
        request.getParameters().add("fname", learnerFirstName);
        request.getParameters().add("lname", learnerLastName);
        request.getParameters().add("learnerid", learnerId);
        request.callService("rustici.registration.createRegistration");
    }
    
    /// <summary>
    /// Create a new Registration (Instance of a user taking a course)
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="courseId">Unique Identifier for the course</param>
    /// <param name="learnerId">Unique Identifier for the learner</param>
    /// <param name="learnerFirstName">Learner's first name</param>
    /// <param name="learnerLastName">Learner's last name</param>
    /// <param name="email">Learner's email address</param>
    /// <param name="learnerTags">Comma separated list of learner tags</param>
    /// <param name="courseTags">Comma separated list of course tags</param>
    /// <param name="registrationTags">Comma separated list of registration tags</param>
    public void CreateRegistration(String registrationId, String courseId, String learnerId,
        String learnerFirstName, String learnerLastName, String email,
        String learnerTags, String courseTags, String registrationTags) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        request.getParameters().add("courseid", courseId);
        request.getParameters().add("fname", learnerFirstName);
        request.getParameters().add("lname", learnerLastName);
        request.getParameters().add("learnerid", learnerId);
        if(!Utils.isNullOrEmpty(email))
        	request.getParameters().add("email", email);
        if(!Utils.isNullOrEmpty(learnerTags)){
        	request.getParameters().add("learnerTags", learnerTags);
        }
        if(!Utils.isNullOrEmpty(courseTags)){
        	request.getParameters().add("courseTags", courseTags);
        }
        if(!Utils.isNullOrEmpty(registrationTags)){
        	request.getParameters().add("registrationTags", registrationTags);
        }
        request.callService("rustici.registration.createRegistration");
    }
       
    /// <summary>
    /// Create a new Registration (Instance of a user taking a course)
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="courseId">Unique Identifier for the course</param>
    /// <param name="learnerId">Unique Identifier for the learner</param>
    /// <param name="learnerFirstName">Learner's first name</param>
    /// <param name="learnerLastName">Learner's last name</param>
    /// <param name="email">Learner's email address</param>
    public void CreateRegistration(String registrationId, String courseId, String learnerId,
        String learnerFirstName, String learnerLastName, String email) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        request.getParameters().add("courseid", courseId);
        request.getParameters().add("fname", learnerFirstName);
        request.getParameters().add("lname", learnerLastName);
        request.getParameters().add("learnerid", learnerId);
        if(!Utils.isNullOrEmpty(email))
        	request.getParameters().add("email", email);
        request.callService("rustici.registration.createRegistration");
    }
       
    /// <summary>
    /// Creates a new instance of an existing registration.  This essentially creates a
    /// fresh take of a course by the user. The latest version of the associated course
    /// will be used.
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <returns>Instance ID of the newly created instance</returns>
//    public int CreateNewInstance (String registrationId) throws Exception
//    {
//        ServiceRequest request = new ServiceRequest(configuration);
//        request.getParameters().add("regid", registrationId);
//        Document response = request.callService("rustici.registration.createNewInstance");
//
//        NodeList successNodes = response.getElementsByTagName("success");
//        return Integer.parse(((Element)successNodes.item(0)).getAttribute("instanceid"));
//    }

    /// <summary>
    /// Return a registration summary object for the given registration
    /// </summary>
    /// <param name="registrationId">The unique identifier of the registration</param>
    /// <returns></returns>
    public RegistrationSummary GetRegistrationSummary(String registrationId) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        request.getParameters().add("resultsformat", "course");
        request.getParameters().add("format", "xml");
        Document response = request.callService("rustici.registration.getRegistrationResult");
        Element reportElem = (Element)response.getElementsByTagName("registrationreport").item(0);
        return new RegistrationSummary(reportElem);
    }

    /// <summary>
    /// Returns the current state of the registration, including completion
    /// and satisfaction type data.  Amount of detail depends on format parameter.
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <returns>Registration data in XML Format</returns>
    public String GetRegistrationResult(String registrationId) throws Exception
    {
        return GetRegistrationResult(registrationId, RegistrationResultsFormat.COURSE_SUMMARY, DataFormat.XML);
    }
    
    /// <summary>
    /// Returns the current state of the registration, including completion
    /// and satisfaction type data.  Amount of detail depends on format parameter.
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="resultsFormat">Degree of detail to return</param>
    /// <returns>Registration data in XML Format</returns>
    public String GetRegistrationResult(String registrationId, RegistrationResultsFormat resultsFormat) throws Exception
    {
        return GetRegistrationResult(registrationId, resultsFormat, DataFormat.XML);
    }

    /// <summary>
    /// Returns the current state of the registration, including completion
    /// and satisfaction type data.  Amount of detail depends on format parameter.
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="resultsFormat">Degree of detail to return</param>
    /// <returns>Registration data in XML Format</returns>
    public String GetRegistrationResult(String registrationId, RegistrationResultsFormat resultsFormat, DataFormat dataFormat) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        request.getParameters().add("resultsformat", resultsFormat.toString().toLowerCase());
        if (dataFormat == DataFormat.JSON)
            request.getParameters().add("format", "json");

        if(dataFormat == DataFormat.XML){
        	Document response = request.callService("rustici.registration.getRegistrationResult");
            // Return the subset of the xml starting with the top <summary>
            Node reportElem = response.getElementsByTagName("registrationreport").item(0);
            return XmlUtils.getXmlString(reportElem);
        } else {
            return request.getStringFromService("rustici.registration.getRegistrationResult");
        }
    }

    /// <summary>
    /// Returns a list of registration id's along with their associated course
    /// </summary>
    /// <param name="regIdFilterRegex">Optional registration id filter</param>
    /// <param name="courseIdFilterRegex">Option course id filter</param>
    /// <returns></returns>
    public List GetRegistrationList(String regIdFilterRegex, String courseIdFilterRegex) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        if (!Utils.isNullOrEmpty(regIdFilterRegex))
            request.getParameters().add("filter", regIdFilterRegex);
        if (!Utils.isNullOrEmpty(courseIdFilterRegex))
            request.getParameters().add("coursefilter", courseIdFilterRegex);
        Document response = request.callService("rustici.registration.getRegistrationList");

        // Return the subset of the xml starting with the top <summary>
        return RegistrationData.ConvertToRegistrationDataList(response);
    }

    /// <summary>
    /// Returns a list of all registration id's along with their associated course
    /// </summary>
    public List GetRegistrationList() throws Exception
    {
        return GetRegistrationList(null, null);
    }

    /// <summary>
    /// Delete the specified registration
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="deleteLatestInstanceOnly">If false, all instances are deleted</param>
    public void DeleteRegistration(String registrationId) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        request.callService("rustici.registration.deleteRegistration");
    }


    /// <summary>
    /// Resets all status data regarding the specified registration -- essentially restarts the course
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    public void ResetRegistration(String registrationId) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        request.callService("rustici.registration.resetRegistration");
    }


    /// <summary>
    /// Clears global objective data for the given registration
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="deleteLatestInstanceOnly">If false, all instances are deleted</param>
    public void ResetGlobalObjectives(String registrationId, boolean deleteLatestInstanceOnly) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        if (deleteLatestInstanceOnly)
            request.getParameters().add("instanceid", "latest");
        request.callService("rustici.registration.resetGlobalObjectives");
    }

    /// <summary>
    /// Delete the specified instance of the registration
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="instanceId">Specific instance of the registration to delete</param>
    public void DeleteRegistrationInstance(String registrationId, int instanceId) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        request.getParameters().add("instanceid", "instanceId");
        request.callService("rustici.registration.deleteRegistration");
    }

    /// <summary>
    /// Gets the url to directly launch/view the course registration in a browser
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="redirectOnExitUrl">Upon exit, the url that the SCORM player will redirect to</param>
    /// <returns>URL to launch</returns>
    public String GetLaunchUrl(String registrationId, String redirectOnExitUrl) throws Exception
    {
        return GetLaunchUrl(registrationId, redirectOnExitUrl, null, null);

    }
    
    /// <summary>
    /// Gets the url to directly launch/view the course registration in a browser
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="redirectOnExitUrl">Upon exit, the url that the SCORM player will redirect to</param>
    /// <param name="cssUrl">Absolute url that points to a custom player style sheet</param>
    /// <returns>URL to launch</returns>
    public String GetLaunchUrl(String registrationId, String redirectOnExitUrl, String cssUrl) throws Exception
    {
    	return GetLaunchUrl(registrationId, redirectOnExitUrl, cssUrl, null);
    }

    /// <summary>
    /// Gets the url to directly launch/view the course registration in a browser
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="redirectOnExitUrl">Upon exit, the url that the SCORM player will redirect to</param>
    /// <param name="cssUrl">Absolute url that points to a custom player style sheet</param>
    /// <param name="debugLogPointerUrl">Url that the server will postback a "pointer" url regarding
    /// a saved debug log that resides on s3</param>
    /// <returns>URL to launch</returns>
    public String GetLaunchUrl(String registrationId, String redirectOnExitUrl, String cssUrl, String debugLogPointerUrl) throws Exception
    {
    	return GetLaunchUrl(registrationId, redirectOnExitUrl, cssUrl, debugLogPointerUrl, false);
	}

    /// <summary>
    /// Gets the url to directly launch/view the course registration in a browser
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="redirectOnExitUrl">Upon exit, the url that the SCORM player will redirect to</param>
    /// <param name="cssUrl">Absolute url that points to a custom player style sheet</param>
    /// <param name="debugLogPointerUrl">Url that the server will postback a "pointer" url regarding
    /// a saved debug log that resides on s3</param>
    /// <returns>URL to launch</returns>
    public String GetLaunchUrl(String registrationId, String redirectOnExitUrl, String cssUrl, String debugLogPointerUrl, boolean disableTracking) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        if (!Utils.isNullOrEmpty(redirectOnExitUrl))
            request.getParameters().add("redirecturl", redirectOnExitUrl);
        if(!Utils.isNullOrEmpty(cssUrl))
        	request.getParameters().add("cssurl", cssUrl);
        if (!Utils.isNullOrEmpty(debugLogPointerUrl))
            request.getParameters().add("saveDebugLogPointerUrl", debugLogPointerUrl);
        if(disableTracking){
        	request.getParameters().add("disableTracking", "true");
        }

        return request.constructUrl("rustici.registration.launch");
    }
    
  /// <summary>
    /// Gets the url to directly launch/view the course registration in a browser
    /// </summary>
    /// <param name="registrationId">Unique Identifier for the registration</param>
    /// <param name="redirectOnExitUrl">Upon exit, the url that the SCORM player will redirect to</param>
    /// <param name="cssUrl">Absolute url that points to a custom player style sheet</param>
    /// <param name="debugLogPointerUrl">Url that the server will postback a "pointer" url regarding
    /// a saved debug log that resides on s3</param>
    /// <returns>URL to launch</returns>
    public String GetLaunchUrlWithTags(String registrationId, String redirectOnExitUrl, String cssUrl, String debugLogPointerUrl, String learnerTags, String courseTags, String registrationTags) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        if (!Utils.isNullOrEmpty(redirectOnExitUrl))
            request.getParameters().add("redirecturl", redirectOnExitUrl);
        if(!Utils.isNullOrEmpty(cssUrl))
        	request.getParameters().add("cssurl", cssUrl);
        if (!Utils.isNullOrEmpty(debugLogPointerUrl))
            request.getParameters().add("saveDebugLogPointerUrl", debugLogPointerUrl);
        if(!Utils.isNullOrEmpty(learnerTags)){
        	request.getParameters().add("learnerTags", learnerTags);
        }
        if(!Utils.isNullOrEmpty(courseTags)){
        	request.getParameters().add("courseTags", courseTags);
        }
        if(!Utils.isNullOrEmpty(registrationTags)){
        	request.getParameters().add("registrationTags", registrationTags);
        }
        return request.constructUrl("rustici.registration.launch");
    }

    /// <summary>
    /// Returns list of launch info objects, each of which describe a particular launch,
    /// but note, does not include the actual history log for the launch. To get launch
    /// info including the log, use GetLaunchInfo
    /// </summary>
    /// <param name="registrationId"></param>
    /// <returns></returns>
    public List GetLaunchHistory(String registrationId) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("regid", registrationId);
        Document response = request.callService("rustici.registration.getLaunchHistory");
        Element launchHistory = ((Element)response.getElementsByTagName("launchhistory").item(0));
        return LaunchInfo.ConvertToLaunchInfoList(launchHistory);
    }

    /// <summary>
    /// Get the full launch information for the launch with the given launch id
    /// </summary>
    /// <param name="launchId"></param>
    /// <returns></returns>
    public LaunchInfo GetLaunchInfo(String launchId) throws Exception
    {
        ServiceRequest request = new ServiceRequest(configuration);
        request.getParameters().add("launchid", launchId);
        Document response = request.callService("rustici.registration.getLaunchInfo");
        Element launchInfoElem = ((Element)response.getElementsByTagName("launch").item(0));
        return new LaunchInfo(launchInfoElem);
    }
    
    public void UpdateLearnerInfo(String learnerId, String learnerFirstName, String learnerLastName) throws Exception {
    	UpdateLearnerInfo(learnerId, learnerFirstName, learnerLastName, null);
    }
    
    public void UpdateLearnerInfo(String learnerId, String learnerFirstName, String learnerLastName, String newLearnerId) throws Exception {
    	ServiceRequest request = new ServiceRequest(configuration);
    	request.getParameters().add("learnerid", learnerId);
    	request.getParameters().add("fname", learnerFirstName);
    	request.getParameters().add("lname", learnerLastName);
    	if(newLearnerId != null){
    		request.getParameters().add("newid", newLearnerId);
    	}
    	request.callService("rustici.registration.updateLearnerInfo");
    }
}
