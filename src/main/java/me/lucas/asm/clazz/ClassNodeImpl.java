package me.lucas.asm.clazz;

import me.lucas.asm.jar.IJarArchive;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

/**
 * @author Lucas
 */
public final class ClassNodeImpl extends ClassNode {
    private static final int ASM_VERSION = Opcodes.ASM9;
    private IJarArchive jarArchive;

    public ClassNodeImpl() {
        super(ASM_VERSION);
    }

    /**
     * @return the {@link IJarArchive} that the class file belongs to, {@link #jarArchive}.
     */
    public final IJarArchive getJarArchive() {
        return jarArchive;
    }

    /**
     * @param jarArchive the {@link IJarArchive} that the class file belongs to, {@link #jarArchive}.
     */
    public final void setJarArchive(final IJarArchive jarArchive) {
        this.jarArchive = jarArchive;
    }
}
