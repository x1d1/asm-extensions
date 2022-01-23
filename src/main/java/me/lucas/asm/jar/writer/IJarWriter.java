package me.lucas.asm.jar.writer;

import me.lucas.asm.jar.IJarArchive;

import java.io.IOException;

/**
 * @author Lucas
 */
public interface IJarWriter {
    /**
     * Writes the {@code jarArchive} and its content to the disk.
     * @param jarArchive the {@link IJarArchive} to be written.
     * @param path the path where the archive will be written to.
     * @throws IOException
     */
    void writeToFile(final IJarArchive jarArchive, final String path) throws IOException;
}
