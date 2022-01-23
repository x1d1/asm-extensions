package me.lucas.asm.jar.loader;

import me.lucas.asm.jar.IJarArchive;

import java.io.IOException;

/**
 * @author Lucas
 */
public interface IJarLoader {
    /**
     * Creates a {@link IJarArchive} object based upon the archive referenced.
     * @param path the path of Java jar archive.
     * @return {@link IJarArchive}
     * @throws IOException
     */
    IJarArchive loadFromFile(final String path) throws IOException;
}