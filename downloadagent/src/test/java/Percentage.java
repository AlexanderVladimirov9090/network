import com.cloway.downloadagent.DownloadAgent;
import com.cloway.downloadagent.ProgressBar;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Percentage {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    ProgressBar progressBar = context.mock(ProgressBar.class);

    @Test
    public void happyPath() {
        String urlString = "http://www.spyderonlines.com/images/wallpapers/picture/picture-8.jpg";
        String downloadDir = "/home/clouway/Downloads";
        DownloadAgent downloadAgent = new DownloadAgent(progressBar);

        context.checking(new Expectations() {{
            allowing(progressBar).showProgress(0);
            will(returnValue(0));
            allowing(progressBar).showProgress(10);
            will(returnValue(10));
            allowing(progressBar).showProgress(20);
            will(returnValue(20));
            allowing(progressBar).showProgress(30);
            will(returnValue(30));
            allowing(progressBar).showProgress(40);
            will(returnValue(40));
            allowing(progressBar).showProgress(50);
            will(returnValue(50));
            allowing(progressBar).showProgress(60);
            will(returnValue(60));
            allowing(progressBar).showProgress(70);
            will(returnValue(70));
            allowing(progressBar).showProgress(80);
            will(returnValue(80));
            allowing(progressBar).showProgress(90);
            will(returnValue(90));
            allowing(progressBar).showProgress(100);
            will(returnValue(100));
        }});

        downloadAgent.download(urlString, downloadDir);
    }
}