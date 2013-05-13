package kz.adebiet.setting;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import nl.siegmann.epublib.util.IOUtil;

import org.apache.commons.net.ftp.FTPClient;

import android.os.AsyncTask;

@SuppressWarnings("rawtypes")
public class FTPUploader extends AsyncTask {
	private String server = "adebiet.hut2.ru";
	private int port = 21;
	private String user = "adebiet8";
	private String pass = "qncgXrkQ";

	@Override
	protected Object doInBackground(Object... params) {
		FTPClient client = new FTPClient();
        FileInputStream fis = (FileInputStream)params[0];
        String path = (String)params[1];
        try {
            client.connect(server);
            client.login(user, pass);
            //
            // Create an InputStream of the file to be uploaded
            //
//            String filename = "Touch.dat";
//            fis = new FileInputStream(filename);
            //
            // Store file to server
            //

            client.storeFile(path, fis);
            client.logout();
            
//            File secondLocalFile = new File("E:/Test/Report.doc");
//            String secondRemoteFile = "test/Report.doc";
//            inputStream = new FileInputStream(secondLocalFile);
//            
//            System.out.println("Start uploading second file");
            OutputStream outputStream = client.storeFileStream(path);
            IOUtil.copy(fis, outputStream);
            fis.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return null;
	}
}
