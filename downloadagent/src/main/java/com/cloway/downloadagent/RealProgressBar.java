package com.cloway.downloadagent;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         Conreate implementetion of Progress Bar interface.
 */
public class RealProgressBar implements ProgressBar {
    /**
     * Returns only number % 10 == 0.
     *
     * @param percentage calculated percentage.
     * @return number % 10 == 0
     */
    public int showProgress(int percentage) {
        if (percentage % 10 != 0) {

        }
        return percentage;
    }
}
