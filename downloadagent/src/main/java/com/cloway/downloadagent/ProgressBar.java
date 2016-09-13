package com.cloway.downloadagent;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This progress bar interface is used for showing percentage of downloaded file.
 */
public interface ProgressBar {
    /**
     * Show percentage of downloaded file.
     *
     * @param percentage calculated percentage.
     * @return percentage.
     */
    int showProgress(int percentage);
}
