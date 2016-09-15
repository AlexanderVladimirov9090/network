package com.cloway.downloadagent;


import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 12.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class DownloadFileFromResource {

    private ProgressBar progressBar = (downloadedContent, size) -> System.out.println((downloadedContent * 100) / size);

    @Test
    public void happyPath(){
        String urlString = "../downloadagent/expected/picture-11.jpg";
        File file = new File("../downloadagent/actual/picture-11.jpg");
        String downloadDir = "../downloadagent/actual/";
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        downloadAgent.download(urlString, downloadDir);

        assertThat(file.isFile(), is(true));
    }


    @Test
    public void totalSpace(){
        String urlString = "../downloadagent/expected/picture-11.jpg";
        String downloadDir = "../downloadagent/actual/";
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        downloadAgent.download(urlString, downloadDir);
        File expected = new File("../downloadagent/expected/picture-11.jpg");
        File actual = new File("../downloadagent/actual/picture-11.jpg");
        assertThat(expected.getTotalSpace(), is(equalTo(actual.getTotalSpace())));
    }

    @Test(expected = UnreachableOrBrokenResource.class)
    public void emptyResource(){
        String urlString = "";
        String downloadDir = "../downloadagent/actual/picture-11.jpg";
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);
        downloadAgent.download(urlString, downloadDir);

    }

    @Test(expected = DownloadDirectoryException.class)
    public void noDirProvided(){
        String urlString = "../downloadagent/expected/picture-11.jpg";
        String downloadDir = "";
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);
        downloadAgent.download(urlString, downloadDir);
    }
}