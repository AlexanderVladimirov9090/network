package com.cloway.downloadagent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class DownloadAgent {
    private final ProgressBar progressBar;

    public DownloadAgent(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    /**
     * Downloads file from given resource.
     *
     * @param givenURL         given resource's url for download.
     * @param downloadDir given directory to be downloaded
     */
    public void download(String givenURL, String downloadDir) {

        try {
            URL uRL = new URL(givenURL);
            URLConnection urlConnection = uRL.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            ReadableByteChannel channel = Channels.newChannel(inputStream);
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            int size = urlConnection.getContentLength();

            String fileName = givenURL.substring(givenURL.lastIndexOf('/'));
            FileOutputStream fos = new FileOutputStream(downloadDir + fileName);
            fos.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
            percentage(size);
            channel.close();
        } catch (MalformedURLException e) {
            throw new UnreachableOrBrokenResource("Not valid resource.");
        } catch (FileNotFoundException e) {
            throw new DownloadDirectoryException("DownloadFileFromResource directory not given.");
        } catch (IOException e) {
            throw new UnreachableOrBrokenResource("Interrupted download.");
        }

    }

    /**
     * Calculates percentage
     * @param size size of file.
     */
    private void percentage(int size) {
       int sum=0;
        while (sum<=size){
            sum++;
        }
        int percentage = (sum * 100) / size;

        if (percentage % 10 == 0) {
            progressBar.showProgress(percentage);
        }
    }
}