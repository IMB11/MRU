package com.mineblock11.mru.test.entry;

import com.mineblock11.mru.entry.CompatabilityEntrypoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MRUCompatEntrypoint implements CompatabilityEntrypoint {
    
    private static final Logger LOGGER = LoggerFactory.getLogger("MRU-TEST-COMPAT-ENTRYPOINT");
    
    @Override
    public void initialize() {
        LOGGER.info("Successfully triggered 'mru-compat-mru' entrypoint.");
    }
}
