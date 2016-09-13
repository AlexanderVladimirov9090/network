package com.cloway.downloadagent;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class RealProgressBar implements ProgressBar {

    public int showProgress(int percentage) {
        if(percentage%10!=0){

        }
        return percentage;
    }
}
