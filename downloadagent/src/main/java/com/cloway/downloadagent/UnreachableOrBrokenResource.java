package com.cloway.downloadagent;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class UnreachableOrBrokenResource extends RuntimeException {

    UnreachableOrBrokenResource(String massage) {
        super(massage);
    }
}
