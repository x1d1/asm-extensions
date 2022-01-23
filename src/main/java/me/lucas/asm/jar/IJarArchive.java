package me.lucas.asm.jar;

import me.lucas.asm.clazz.ClassNodeImpl;
import me.lucas.asm.clazz.IClassNodePool;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Lucas
 */
public interface IJarArchive extends ClassNodePoolAdapter {
    /**
     * @return the {@link IClassNodePool} object belonging to this jar context.
     */
    IClassNodePool<ClassNodeImpl> getClassNodePool();

    /**
     * @return a collection of the {@link IJarArchive}'s resources.
     * @see JarResource
     */
    Collection<JarResource> getResources();

    /**
     * @param jarResource the {@link JarResource} to be added to this jar.
     */
    void addResource(final JarResource jarResource);

    /**
     * @param name the name of the desired {@link JarResource}.
     * @return {@link Optional<JarResource>}.
     */
    Optional<JarResource> getResource(final String name);
}
