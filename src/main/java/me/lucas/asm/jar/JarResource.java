package me.lucas.asm.jar;

/**
 * @author Lucas
 */
public final class JarResource {
    private String name;
    private byte[] content;

    /**
     * @param name the {@link #name} of the resource.
     * @param content the {@link #content} of the resource.
     */
    public JarResource(final String name, final byte[] content) {
        this.name = name;
        this.content = content;
    }

    /**
     * @return {@link #name}.
     */
    public final String getName() {
        return name;
    }

    /**
     * @return {@link #content}.
     */
    public final byte[] getContent() {
        return content;
    }

    /**
     * @param name {@link #name}.
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * @param content {@link #content}.
     */
    public final void setContent(final byte[] content) {
        this.content = content;
    }
}