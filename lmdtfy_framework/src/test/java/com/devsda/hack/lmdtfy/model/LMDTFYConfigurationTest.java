package com.devsda.hack.lmdtfy.model;

import com.devsda.utils.httputils.loader.JsonLoader;
import org.junit.Test;

import java.io.IOException;

public class LMDTFYConfigurationTest {

    @Test
    public void loadConfigurationTest() throws IOException {
        LMDTFYConfiguration lmdtfyConfiguration = JsonLoader.loadFile("configurations/dev-lmdtfy-configuration.json", LMDTFYConfiguration.class);

        System.out.println(lmdtfyConfiguration);

    }

}
