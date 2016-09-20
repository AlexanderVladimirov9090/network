package com.cloway.downloadagent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

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
        File downloadDir = new File(downloadPath);
        if (!downloadDir.isDirectory()) {
            throw new DownloadDirectoryException("See if directory exists.");
        }
        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            long sizeOfFile = urlConnection.getContentLength();
            long progress = 0;
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            String fileName = url.getFile().substring(url.getFile().lastIndexOf("/"));
            FileOutputStream fis = new FileOutputStream(downloadDir.getCanonicalPath() + fileName);
            byte[] buffer = new byte[1024];
            write(sizeOfFile, progress, bis, fis, buffer);
            fis.close();
            bis.close();
        } catch (IOException e) {
            throw new UnreachableOrBrokenResource("Unreachable or broken resource see if url is valid.");
        }
    }

    private void write(long sizeOfFile, long progress, BufferedInputStream bis, FileOutputStream fis, byte[] buffer) throws IOException {
        int count;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fis.write(buffer, 0, count);
            progress += buffer.length;
            progressBar.update(progress, sizeOfFile);
        }
    }
}