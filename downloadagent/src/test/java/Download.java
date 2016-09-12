import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 12.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class Download {

    @Test
    public void happyPath() {
        String resourceContent = "This is downloaded";
        int sizeOfContent = 18;
        DownloadAgent downloadAgent = new DownloadAgent();
        assertThat(downloadAgent.download(resourceContent, sizeOfContent), is(equalTo(resourceContent)));
    }

    @Test(expected = EmptyOrBrokenResourceException.class)
    public void emptyContent() {
        String resourceContent = "";
        int sizeOfContent = 18;
        DownloadAgent downloadAgent = new DownloadAgent();
        downloadAgent.download(resourceContent,sizeOfContent);
    }

    @Test(expected = EmptyOrBrokenResourceException.class)
    public void brokenContent(){
        String resourceContent = "This is";
        int sizeOfContent = 18;
        DownloadAgent downloadAgent = new DownloadAgent();
        assertThat(downloadAgent.download(resourceContent, sizeOfContent), is(equalTo(resourceContent)));
    }

    private class DownloadAgent {
        String download(String resourceContent, int sizeOfContent) {
            consistencyOfResource(resourceContent,sizeOfContent);

            StringBuffer stringBuffer = new StringBuffer(resourceContent);
            InputStream inputStream = new ByteArrayInputStream(stringBuffer.toString().getBytes());
            OutputStream outputStream = new ByteArrayOutputStream();
            StringBuilder stringBuilder = new StringBuilder();
            byte[] outputByte = new byte[sizeOfContent];

            writeData(sizeOfContent, inputStream, outputStream, stringBuilder, outputByte);
            closeStreams(inputStream,outputStream);
            return stringBuilder.toString();
        }

        private void writeData(int sizeOfContent, InputStream inputStream, OutputStream outputStream, StringBuilder stringBuilder, byte[] outputByte) {
            try {
                while (inputStream.read(outputByte, 0, sizeOfContent) != -1) {
                    outputStream.write(outputByte, 0, sizeOfContent);

                    stringBuilder.append(outputStream.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void consistencyOfResource(String resourceContent, int sizeOfContent) {
        if (resourceContent.length() == 0||resourceContent.length()<sizeOfContent) {
            throw new EmptyOrBrokenResourceException("Resource was not downloaded because it is empty or broken.");
        }
    }

    private void closeStreams(InputStream inputStream, OutputStream outputStream) {
        try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class EmptyOrBrokenResourceException extends RuntimeException {
        EmptyOrBrokenResourceException(String message) {
            super(message);
        }
    }
}