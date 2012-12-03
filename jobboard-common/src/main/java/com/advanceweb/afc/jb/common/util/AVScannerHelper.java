	// after POM file modification we need to Uncomment  the below code
/*package com.advanceweb.afc.jb.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.symantec.scanengine.api.Policy;
import com.symantec.scanengine.api.Result;
import com.symantec.scanengine.api.ScanEngine;
import com.symantec.scanengine.api.ScanEngine.ScanEngineInfo;
import com.symantec.scanengine.api.ScanException;
import com.symantec.scanengine.api.StreamScanRequest;
*//**
 * Class to scan file for virus while uploading 
 * @author deviprasadm
 *
 *//*
public class AVScannerHelper {
	private static final Logger LOGGER = Logger
			.getLogger(AVScannerHelper.class);
	private @Value("${semantic.antivirus.ip}")
	String antivirusIp;
	private @Value("${semantic.antivirus.port}")
	int pord;
	*//**
	 * Virus Scanning poicy
	 *//*
	private Policy scPolicy = Policy.SCAN;
	
	*//**
	 * Method to chaeck the uploaded file for virus
	 * @param uploadedFilePath
	 * @param fileName
	 * @return
	 *//*
	public  boolean scanFile(String uploadedFilePath, String fileName) {
		Vector<ScanEngineInfo> scanEnginesForScanning = new Vector<ScanEngineInfo>();
		LOGGER.info("Uploaded file Path : "+ uploadedFilePath);
		
		//TEST ONLY --DELETE
		if (uploadedFilePath == null) {
			uploadedFilePath = "c:\\temp\\ResumeTemp.docx";
			fileName = "ResumeTemp.docx";
		}
		
		ScanEngine.ScanEngineInfo scanEngTobeUsed = new ScanEngine.ScanEngineInfo("192.168.31.230",1344);
        scanEnginesForScanning.add(scanEngTobeUsed);
        
        FileOutputStream output = null;
        String defaultResultFile="file.out";
        Result result = null;
        boolean virusFound = false;
        try
        {
        	output = new FileOutputStream(defaultResultFile);
           	ScanEngine scanEngine = ScanEngine.createScanEngine(scanEnginesForScanning);
            StreamScanRequest streamScanReq = scanEngine.createStreamScanRequest(uploadedFilePath, fileName, output, scPolicy);
            result = streamScanReq.scanFile();						
            
            if (output != null){
            	output.close();
            }
        }
        catch(ScanException ex)
        {
        	if (output != null){
            	try {
					output.close();
				} catch (IOException sce) {
					LOGGER.error("Exception while scaning the file" +sce);
				}
            }
        	LOGGER.error("Problem encountered! Scanning Failed!! "+ex.getExceptionCode());
            ex.printStackTrace();
        }
        catch(Exception ex)
        {
        	if (output != null){
            	try {
					output.close();
				} catch (IOException sce) {
					LOGGER.error("Exception while scaning the file" +sce);
				}
            }
        	LOGGER.error("Problem encountered! Scanning Failed!! ");
        }
        
        if (result != null) {
        	if(result.getStatus() != null && !result.getStatus().toString().equalsIgnoreCase("CLEAN")){
        		virusFound = true;
        	}
        }
        
        return virusFound;
	}
	
	
	private void printResult (Result result) {
		
		 LOGGER.info("----------------------------------------------------------------------");
	        LOGGER.info("Scanning file ........................................................");
	        LOGGER.info("----------------------------------------------------------------------");
	        LOGGER.info("Results ..............................................................");
	        LOGGER.info("----------------------------------------------------------------------");
	        LOGGER.info("File Scanned		: "+ "C:\\Users\\deviprasadm\\Desktop\\AResumeText.docx");
	        LOGGER.info("Scan Policy		: "+scPolicy);
	        LOGGER.info("File Status		: "+result.getStatus());
	        LOGGER.info("Total Infection		: "+result.getTotalInfection());
	        LOGGER.info("Virus Def Date		: "+result.getDefinitionDate());
	        LOGGER.info("Virus Def Revision No	: "+result.getDefinitionRevNumber());
	        
	        
	        
		LOGGER.info("Result = "+result.getThreatInfo().length);
		ThreatInfo[] virusIn = result.getThreatInfo();
        for(int i = 0; i< virusIn.length ; i++)
        {

            LOGGER.info("File Name               : "+virusIn[i].getFileName());
            LOGGER.info("Violation Name          : "+virusIn[i].getViolationName());
            if(virusIn[i].getThreatCategory().length() > 0)
            {
                LOGGER.info("Non Viral Threat Category : "+virusIn[i].getThreatCategory());
            }
            LOGGER.info("Violation Id            : "+virusIn[i].getViolationId());
            LOGGER.info("Disposition	        : "+virusIn[i].getDisposition());
            LOGGER.info("File Unscannable		: "+virusIn[i].getFileunscannable());
        }
        
        ConnectionAttempt[] conTry = result.getIPTries();
        for(int x=0; x< conTry.length ; x ++)
        {
            LOGGER.info("Symantec Protection Engine IP		: "+ conTry[x].getIPAddress());
            LOGGER.info("Symantec Protection Engine Port	: "+ conTry[x].getPortNumber());
            LOGGER.info("Symantec Protection Engine Port	: "+ conTry[x].getErrString());
        }
	
	}
	public static void main (String args[]) {
		
		new AVScannerHelper().scanFile("C:\\temp\\ResumeTemp.docx","ResumeTemp.docx");
	}
	
}
*/