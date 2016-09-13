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

    public void download(String urlString, String downloadDir) {

        try {
            URL uRL = new URL(urlString);
            URLConnection urlConnection = uRL.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            ReadableByteChannel channel = Channels.newChannel(inputStream);
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            int size = urlConnection.getContentLength();

            String fileName = urlString.substring(urlString.lastIndexOf('/'));
            FileOutputStream fos = new FileOutputStream(downloadDir + fileName);

            write(channel, byteBuffer, size, fos);
            channel.close();
        } catch (MalformedURLException e) {
            throw new UnreachableOrBrokenResource("Not valid resource.");
        } catch (FileNotFoundException e) {
            throw new DownloadDirectoryException("DownloadFileFromResource directory not given.");
        } catch (IOException e) {
            throw new UnreachableOrBrokenResource("Interrupted download.");
        }

    }

    private void write(ReadableByteChannel channel, ByteBuffer byteBuffer, int size, FileOutputStream fos) throws IOException {
        int sum = 0;
        while (channel.read(byteBuffer) > 0) {
            fos.write(byteBuffer.array());
            sum += byteBuffer.array().length;
            percentage(sum, size);
            byteBuffer.clear();
        }
    }

    private void percentage(int sum, int size) {
        int percentage = (sum * 100) / size;
        if (percentage % 10 != 0) {

        }
        progressBar.showProgress(percentage);
    }
}