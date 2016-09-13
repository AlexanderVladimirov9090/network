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
     * @param url         given resource's url for download.
     * @param downloadDir given directory to be downloaded
     */
    public void download(String url, String downloadDir) {

        try {
            URL uRL = new URL(url);
            URLConnection urlConnection = uRL.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            ReadableByteChannel channel = Channels.newChannel(inputStream);
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            int size = urlConnection.getContentLength();

            String fileName = url.substring(url.lastIndexOf('/'));
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

    /**
     * Writes to file.
     *
     * @param channel    readable byte channel for reading byte from recourse.
     * @param byteBuffer
     * @param size       size of file.
     * @param fos        File output stream for writing in to file.
     * @throws IOException when something is wrong with writing.
     */
    private void write(ReadableByteChannel channel, ByteBuffer byteBuffer, int size, FileOutputStream fos) throws IOException {
        int sum = 0;
        while (channel.read(byteBuffer) > 0) {
            fos.write(byteBuffer.array());
            sum += byteBuffer.array().length;
            percentage(sum, size);
            byteBuffer.clear();
        }
    }

    /**
     * Calculates percentage
     *
     * @param sum  sum of delivered bytes
     * @param size size of file.
     */
    private void percentage(int sum, int size) {
        int percentage = (sum * 100) / size;
        if (percentage % 10 != 0) {

        }
        progressBar.showProgress(percentage);
    }
}