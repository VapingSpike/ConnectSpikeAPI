/**
 * Created by Spike
 * Created 25-01-2022 at 22:17
 */

package net.spike.api;

public class SpikeAPIException extends UnsupportedOperationException {

    public SpikeAPIException() {
        super("SpikeClient operation was executed while a API was not loaded.");
    }

}
