import com.cloway.downloadagent.*;
import org.junit.Test;

import java.io.File;

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

    @Test
    public void happyPath() {
        String urlString = "file:///home/clouway/workspace/networking-and-gui/downloadagent/src/test/expected/picture-11.jpg";
        String downloadDir = "/home/clouway/workspace/networking-and-gui/downloadagent/src/test/expected/";
        ProgressBar progressBar = new RealProgressBar();
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        downloadAgent.download(urlString, downloadDir);

        File file = new File("/home/clouway/workspace/networking-and-gui/downloadagent/src/test/actual/picture-11.jpg");
        assertThat(file.exists(), is(true));
    }

    @Test
    public void fileSize(){
        String urlString = "file:///home/clouway/workspace/networking-and-gui/downloadagent/src/test/expected/picture-11.jpg";
        String downloadDir = "/home/clouway/workspace/networking-and-gui/downloadagent/src/test/actual";
        ProgressBar progressBar = new RealProgressBar();
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        downloadAgent.download(urlString, downloadDir);
        File expected = new File("/home/clouway/workspace/networking-and-gui/downloadagent/src/test/expected/picture-11.jpg");
        File actual = new File("/home/clouway/workspace/networking-and-gui/downloadagent/src/test/actual/picture-11.jpg");
        assertThat(expected.length(), is(equalTo(actual.length())));
    }

    @Test(expected = UnreachableOrBrokenResource.class)
    public void emptyResource() {
        String urlString = "";
        String downloadDir = "/home/clouway/workspace/networking-and-gui/downloadagent/src/test/expected/picture-11.jpg";
        ProgressBar progressBar = new RealProgressBar();
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);
        downloadAgent.download(urlString, downloadDir);

    }

    @Test(expected = DownloadDirectoryException.class)
    public void noDirProvided() {
        String urlString = "file:///home/clouway/workspace/networking-and-gui/downloadagent/src/test/expected/picture-11.jpg";
        String downloadDir = "";
        ProgressBar progressBar = new RealProgressBar();
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);
        downloadAgent.download(urlString, downloadDir);
    }
}