//? if fabric && >=1.20.6 {
package dev.imb11.mru.event.fabric;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import java.nio.file.Path;

/**
 * Callback for when data generation has finished.
 */
public interface DatagenFinishedCallback {
    /**
     * Event instance for when data generation has finished.
     */
    Event<DatagenFinishedCallback> EVENT = EventFactory.createArrayBacked(DatagenFinishedCallback.class, (listeners) -> (Path outputDirectory) -> {
        for (DatagenFinishedCallback listener : listeners) {
            listener.onDatagenFinished(outputDirectory);
        }
    });

    /**
     * Called when data generation has finished.
     */
    void onDatagenFinished(Path outputDirectory);
}
//?}
