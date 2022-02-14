package org.eclipse.persistence.asm;

public interface ClassWriter {

    void visit(int access, String name, String signature, String superName, String[] interfaces);

    MethodVisitor visitMethod(final int access, final String name, final String descriptor, final String signature, final String[] exceptions);

    void visitEnd();

    byte[] toByteArray();

}
