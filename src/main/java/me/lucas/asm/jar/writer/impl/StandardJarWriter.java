package me.lucas.asm.jar.writer.impl;

import me.lucas.asm.clazz.ClassNodeImpl;
import me.lucas.asm.jar.IJarArchive;
import me.lucas.asm.jar.JarResource;
import me.lucas.asm.jar.writer.IJarWriter;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

/**
 * @author Lucas
 */
public final class StandardJarWriter implements IJarWriter {
    private final int classWriterFlags;

    /**
     * @param classWriterFlags the flags passed through to a {@link ClassWriter} object when writing the classes.
     */
    private StandardJarWriter(final int classWriterFlags) {
        this.classWriterFlags = classWriterFlags;
    }

    /**
     * Writes the {@code jarArchive} and its content to the disk.
     * @param jarArchive the {@link IJarArchive} to be written.
     * @param path the path where the archive will be written to.
     * @throws IOException if the content cannot be written to the disk.
     */
    @Override
    public final void writeToFile(final IJarArchive jarArchive, final String path) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path); JarOutputStream jarOutputStream = new JarOutputStream(fileOutputStream)) {
            for (final ClassNodeImpl clazz : jarArchive.getClassNodePool().getClassNodes()) {
                try {
                    final ClassWriter writer = new ClassWriter(classWriterFlags);
                    clazz.accept(writer);
                    jarOutputStream.putNextEntry(new JarEntry(clazz.name + ".class"));
                    jarOutputStream.write(writer.toByteArray());
                } catch (Throwable e) {
                    final ClassWriter writer = new ClassWriter(0);
                    clazz.accept(writer);
                    jarOutputStream.putNextEntry(new JarEntry(clazz.name + ".class"));
                    jarOutputStream.write(writer.toByteArray());
                }
            }

            for (final JarResource resource : jarArchive.getResources()) {
                jarOutputStream.putNextEntry(new JarEntry(resource.getName()));
                jarOutputStream.write(resource.getContent());
            }
        }
    }

    public static class Builder {
        private int classWriterFlags;

        /**
         * Performs the bitwise OR operation on {@link #classWriterFlags} to apply the {@link ClassWriter} COMPUTE_FRAMES flag.
         * @return the builder instance.
         */
        public final Builder computeFrames() {
            classWriterFlags |= ClassWriter.COMPUTE_FRAMES;
            return this;
        }

        /**
         * Performs the bitwise OR operation on {@link #classWriterFlags} to apply the {@link ClassWriter} COMPUTE_MAXS flag.
         * @return the builder instance.
         */
        public final Builder computeMaxs() {
            classWriterFlags |= ClassWriter.COMPUTE_MAXS;
            return this;
        }

        /**
         * @return {@link StandardJarWriter}
         */
        public final IJarWriter build() {
            return new StandardJarWriter(classWriterFlags);
        }
    }
}
