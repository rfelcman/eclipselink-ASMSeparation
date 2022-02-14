package org.eclipse.persistence.asm;

public interface MethodVisitor {

    void visitVarInsn(final int opcode, final int var);

    void visitMethodInsn(final int opcode, final String owner, final String name, final String descriptor, final boolean isInterface);

    void visitInsn(final int opcode);

    void visitMaxs(final int maxStack, final int maxLocals);

    void visitEnd();

    }
