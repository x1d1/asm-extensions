package me.lucas.asm.clazz;

import org.objectweb.asm.tree.ClassNode;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Lucas
 */
public interface IClassNodePool<T extends ClassNode> {
    /**
     * @return the {@link T} objects belonging to this pool.
     */
    Collection<T> getClassNodes();

    /**
     * @param classNode the {@link T} to be added to the pool.
     */
    void addClassNode(final T classNode);

    /**
     * @param classNode the {@link T} to be removed from the pool.
     */
    void removeClassNode(final T classNode);

    /**
     * @param name the name of the desired {@link T} that exists in the pool.
     * @return {@link Optional<T>}.
     */
    Optional<T> getClassNode(final String name);
}