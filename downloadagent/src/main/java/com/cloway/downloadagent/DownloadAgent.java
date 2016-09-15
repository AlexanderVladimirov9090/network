package com.cloway.downloadagent;

import java.io.*;
import java.net.*;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class DownloadAgent {
    private ProgressBar progressBar;

    public DownloadAgent(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    /**
     * Downloads file from url.
     *
     * @param urlString    given url string.
     * @param downloadPath download path.
     */
    public void download(String urlString, String downloadPath) {
        File downloadContent = new File(urlString);
        if (!downloadContent.isFile()) {
            throw new UnreachableOrBrokenResource("Unreachable or broken resource");
        }

        File downloadDir = new File(downloadPath);
        if (!downloadDir.isDirectory()) {
            throw new DownloadDirectoryException("Directory not found.");
        }
        try {
            String constructedUrlPath = downloadContent.toURI().getScheme() + "://" + downloadContent.getCanonicalPath();
            URL url = new URL(constructedUrlPath);
            URLConnection urlConnection = url.openConnection();
            long sizeOfFile= urlConnection.getContentLength();
            long progress = 0;
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(downloadDir.getCanonicalPath() + "/" + downloadContent.getName());
            byte[] buffer = new byte[1024];
            int count = 0;

            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
                progress+=buffer.length;
                progressBar.update(progress,sizeOfFile);
            }
            fis.close();
            bis.close();
        } catch (IOException e) {
            throw new UnreachableOrBrokenResource("Can't write content to file.");
        }
    }


}