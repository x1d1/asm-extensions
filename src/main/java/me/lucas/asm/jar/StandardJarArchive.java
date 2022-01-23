package me.lucas.asm.jar;

import me.lucas.asm.clazz.ClassNodeImpl;
import me.lucas.asm.clazz.IClassNodePool;
import me.lucas.asm.clazz.StandardClassNodePool;
import org.objectweb.asm.tree.ClassNode;

import java.util.*;

/**
 * @author Lucas
 */
public final class StandardJarArchive implements IJarArchive {
    private final IClassNodePool<ClassNodeImpl> classNodePool = new StandardClassNodePool<>();
    private final List<JarResource> resources = new ArrayList<>();

    /**
     * @return {@link #classNodePool}.
     */
    @Override
    public final IClassNodePool<ClassNodeImpl> getClassNodePool() {
        return classNodePool;
    }

    /**
     * @return a {@link Collection<JarResource>} from {@link #resources}.
     */
    @Override
    public final Collection<JarResource> getResources() {
        return Collections.unmodifiableCollection(resources);
    }

    /**
     * @param jarResource the {@link JarResource} to be added to this jar.
     */
    @Override
    public final void addResource(final JarResource jarResource) {
        resources.add(jarResource);
    }

    /**
     * @param name the name of the desired {@link JarResource}.
     * @return {@link Optional<JarResource>}.
     */
    @Override
    public final Optional<JarResource> getResource(final String name) {
        return resources.stream().filter(jarResource -> jarResource.getName().equals(name)).findFirst();
    }

    /**
     * @see IClassNodePool#getClassNodes().
     */
    @Override
    public final Collection<ClassNodeImpl> getClassNodes() {
        return classNodePool.getClassNodes();
    }

    /**
     * Updates the jar archive of {@code classNode}.
     * @see IClassNodePool#addClassNode(ClassNode).
     */
    @Override
    public final void addClassNode(final ClassNodeImpl classNode) {
        classNode.setJarArchive(this);
        classNodePool.addClassNode(classNode);
    }

    /**
     * Resets the jar archive of {@code classNode}.
     * @see IClassNodePool#removeClassNode(ClassNode).
     */
    @Override
    public final void removeClassNode(final ClassNodeImpl classNode) {
        classNode.setJarArchive(null);
        classNodePool.removeClassNode(classNode);
    }

    /**
     * @see IClassNodePool#getClassNode(String).
     */
    @Override
    public final Optional<ClassNodeImpl> getClassNode(final String name) {
        return classNodePool.getClassNode(name);
    }
}
