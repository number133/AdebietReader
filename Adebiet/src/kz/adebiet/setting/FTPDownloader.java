package kz.adebiet.setting;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import android.os.AsyncTask;

@SuppressWarnings("rawtypes")
public class FTPDownloader extends AsyncTask{
	private String server = "adebiet.hut2.ru";
	private int port = 21;
	private String user = "adebiet8";
	private String pass = "qncgXrkQ";
    
	@Override
	protected Object doInBackground(Object... params) {
		 FTPClient ftpClient = new FTPClient();
	        try {
	 
	            ftpClient.connect(server, port);
	            ftpClient.login(user, pass);
	            ftpClient.enterLocalPassiveMode();
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	 
	            // APPROACH #1: using retrieveFile(String, OutputStream)
	            String remoteFile1 = (String)params[0];
	            File downloadFile1 = new File((String)params[1]);
	            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
	            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
	            outputStream1.close();
	 
//	            if (success) {
//	                System.out.println("File #1 has been downloaded successfully.");
//	            }
	 
	        } catch (IOException ex) {
	            System.out.println("Error: " + ex.getMessage());
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
		return null;
	}
}
