import com.cloway.downloadagent.*;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 12.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class DownloadFileFromResource {

    @Test
    public void happyPath() {
        String urlString = "http://www.spyderonlines.com/images/wallpapers/picture/picture-8.jpg";
        String downloadDir = "/home/clouway/Downloads";
        ProgressBar progressBar = new RealProgressBar();
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        downloadAgent.download(urlString, downloadDir);

        File file = new File("/home/clouway/Downloads/picture-8.jpg");
        assertThat(file.exists(), is(true));
    }

    @Test(expected = UnreachableOrBrokenResource.class)
    public void emptyResource() {
        String urlString = "";
        String downloadDir = "/home/clouway/Downloads";
        ProgressBar progressBar = new RealProgressBar();
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);
        downloadAgent.download(urlString, downloadDir);

    }

    @Test(expected = DownloadDirectoryException.class)
    public void noDirProvided() {
        String urlString = "http://www.spyderonlines.com/images/wallpapers/picture/picture-8.jpg";
        String downloadDir = "";
        ProgressBar progressBar = new RealProgressBar();
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);
        downloadAgent.download(urlString, downloadDir);
    }
}