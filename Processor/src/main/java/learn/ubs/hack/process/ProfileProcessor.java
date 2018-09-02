package learn.ubs.hack.process;

import learn.ubs.hack.base.Profile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;

public abstract class ProfileProcessor {
    public abstract Profile processProfile(File profile) throws InvalidFormatException, IOException;
}
