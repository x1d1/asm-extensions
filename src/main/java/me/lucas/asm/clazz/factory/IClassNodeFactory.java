package me.lucas.asm.clazz.factory;

import org.objectweb.asm.tree.ClassNode;

/**
 * @author Lucas
 */
public interface IClassNodeFactory<T extends ClassNode> {
    /**
     * @param buffer the binary content of the Java class file.
     * @return {@link T}.
     */
    T create(final T classNode, final byte[] buffer);
}
