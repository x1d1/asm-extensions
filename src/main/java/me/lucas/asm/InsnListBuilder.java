package me.lucas.asm;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;

/**
 * @author Lucas
 */
public final class InsnListBuilder {
    private final InsnList instructionList = new InsnList();

    /**
     * @param instruction the {@link AbstractInsnNode} to be added.
     * @return the builder instance.
     */
    public final InsnListBuilder add(final AbstractInsnNode instruction) {
        instructionList.add(instruction);
        return this;
    }

    /**
     * @param instructionList the {@link InsnList} to be appended.
     * @return the builder instance.
     */
    public final InsnListBuilder add(final InsnList instructionList) {
        this.instructionList.add(instructionList);
        return this;
    }

    /**
     * @return {@link InsnList}.
     */
    public final InsnList build() {
        return instructionList;
    }
}