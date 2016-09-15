package com.cloway.downloadagent;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 * This interface is used by Donwload Agent to comunicate with Real Implementation of Progress bar.
 */
public interface ProgressBar{
    void update(long downloadedContent, long size);
}