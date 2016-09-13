package com.cloway.downloadagent;

/**
 * Created by clouway on 13.09.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This exception is thrown when resoutce is unrechable or broken.
 */
public class UnreachableOrBrokenResource extends RuntimeException {

    UnreachableOrBrokenResource(String massage) {
        super(massage);
    }
}
