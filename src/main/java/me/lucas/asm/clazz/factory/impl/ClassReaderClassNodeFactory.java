package me.lucas.asm.clazz.factory.impl;

import me.lucas.asm.clazz.factory.IClassNodeFactory;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.tree.ClassNode;

/**
 * @author Lucas
 */
public final class ClassReaderClassNodeFactory<T extends ClassNode> implements IClassNodeFactory<T> {
    private final int parsingOptions;

    /**
     * @param parsingOptions the {@link #parsingOptions} of the factory.
     */
    private ClassReaderClassNodeFactory(final int parsingOptions) {
        this.parsingOptions = parsingOptions;
    }

    /**
     * Utilizes {@link ClassReader#accept(ClassVisitor, int)} to visit the {@link T} object, {@code classNode}.
     * @param classNode the {@link T} to be visited.
     * @param buffer the binary content of the Java class file.
     * @return {@code classNode}.
     */
    @Override
    public final T create(final T classNode, final byte[] buffer) {
        final ClassReader reader = new ClassReader(buffer);
        reader.accept(classNode, parsingOptions);
        return classNode;
    }

    public static class Builder<T extends ClassNode> {
        private int parsingOptions;

        /**
         * Performs the bitwise OR operation on {@link #parsingOptions} to apply the {@link ClassReader} SKIP_CODE flag.
         * @return the builder instance.
         */
        public final Builder<T> skipCode() {
            parsingOptions |= ClassReader.SKIP_CODE;
            return this;
        }

        /**
         * Performs the bitwise OR operation on {@link #parsingOptions} to apply the {@link ClassReader} SKIP_DEBUG flag.
         * @return the builder instance.
         */
        public final Builder<T> skipDebug() {
            parsingOptions |= ClassReader.SKIP_DEBUG;
            return this;
        }

        /**
         * Performs the bitwise OR operation on {@link #parsingOptions} to apply the {@link ClassReader} SKIP_FRAMES flag.
         * @return the builder instance.
         */
        public final Builder<T> skipFrames() {
            parsingOptions |= ClassReader.SKIP_FRAMES;
            return this;
        }

        /**
         * Performs the bitwise OR operation on {@link #parsingOptions} to apply the {@link ClassReader} EXPAND_FRAMES flag.
         * @return the builder instance.
         */
        public final Builder<T> expandFrames() {
            parsingOptions |= ClassReader.EXPAND_FRAMES;
            return this;
        }

        /**
         * @return {@link ClassReaderClassNodeFactory}
         */
        public final IClassNodeFactory<T> build() {
            return new ClassReaderClassNodeFactory<>(parsingOptions);
        }
    }
}
