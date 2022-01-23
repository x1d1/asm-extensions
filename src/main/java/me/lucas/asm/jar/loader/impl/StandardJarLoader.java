package me.lucas.asm.jar.loader.impl;

import me.lucas.asm.clazz.ClassNodeImpl;
import me.lucas.asm.clazz.factory.IClassNodeFactory;
import me.lucas.asm.jar.IJarArchive;
import me.lucas.asm.jar.JarResource;
import me.lucas.asm.jar.StandardJarArchive;
import me.lucas.asm.jar.loader.IJarLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Lucas
 */
public final class StandardJarLoader implements IJarLoader {
    private final IClassNodeFactory<ClassNodeImpl> classNodeFactory;

    /**
     * @param classNodeFactory the {@link #classNodeFactory} of this jar loader.
     */
    public StandardJarLoader(final IClassNodeFactory<ClassNodeImpl> classNodeFactory) {
        this.classNodeFactory = classNodeFactory;
    }

    /**
     * Creates a {@link IJarArchive} object based upon the archive referenced.
     * Classes are read relative to the flags provided by {@link #classNodeFactory}.
     * If ASM fails to read the class file, it is added as a resource.
     * @param path the path of Java jar archive.
     * @return {@link IJarArchive}
     * @throws IOException if the archive or its contents cannot be processed.
     */
    @Override
    public final IJarArchive loadFromFile(final String path) throws IOException {
        try (final JarFile jarFile = new JarFile(path)) {
            final IJarArchive jar = new StandardJarArchive();
            final Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                final JarEntry entry = entries.nextElement();
                try (final InputStream inputStream = jarFile.getInputStream(entry)) {
                    final byte[] bytes = toByteArray(inputStream);
                    if (bytes.length == 0 || bytes.length == 4)
                        continue;
                    // We want to include fake directories here: ".class/"
                    if (entry.getName().endsWith(".class") || entry.getName().endsWith(".class/") && isValidClass(bytes)) {
                        try {
                            final ClassNodeImpl clazz = classNodeFactory.create(new ClassNodeImpl(), bytes);
                            jar.getClassNodePool().addClassNode(clazz);
                        } catch (Throwable e) {
                            jar.addResource(new JarResource(entry.getName(), bytes));
                        }

                    } else {
                        jar.addResource(new JarResource(entry.getName(), bytes));
                    }
                }
            }
            return jar;
        }
    }

    /**
     * Writes the content of the input stream to the output stream and returns the byte array.
     */
    private static byte[] toByteArray(final InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read;
        byte[] data = new byte[1024];
        while ((read = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, read);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    /**
     * Checks the first four bytes of the Java class file which are supposed to be 0xCAFEBABE.
     * @param bytes the binary content of the possible Java class file.
     * @return whether the parameter {@code bytes} provided can be classified as a Java class file.
     */
    private static boolean isValidClass(final byte[] bytes) {
        return bytes[0] == (byte) 0xCA && bytes[1] == (byte) 0xFE && bytes[2] == (byte) 0xBA && bytes[3] == (byte) 0xBE;
    }
}
