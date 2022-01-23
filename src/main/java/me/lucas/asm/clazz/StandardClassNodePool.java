package me.lucas.asm.clazz;

import org.objectweb.asm.tree.ClassNode;

import java.util.*;

/**
 * @author Lucas
 */
public final class StandardClassNodePool<T extends ClassNode> implements IClassNodePool<T> {
    private final List<T> classNodes = new ArrayList<>();

    /**
     * @return the {@link T} objects belonging to this pool, {@link #classNodes}.
     */
    @Override
    public final Collection<T> getClassNodes() {
        return Collections.unmodifiableCollection(classNodes);
    }

    /**
     * @param classNode the {@link T} to be added to the pool, {@link #classNodes}.
     */
    @Override
    public final void addClassNode(final T classNode) {
        classNodes.add(classNode);
    }

    /**
     * @param classNode the {@link T} to be removed from the pool, {@link #classNodes}.
     */
    @Override
    public final void removeClassNode(final T classNode) {
        classNodes.remove(classNode);
    }

    /**
     * @param name the name of the desired {@link T} that exists in the pool, {@link #classNodes}.
     * @return {@link Optional<T>}.
     */
    @Override
    public final Optional<T> getClassNode(final String name) {
        return classNodes.stream().filter(classNode -> classNode.name.equals(name)).findFirst();
    }
}
