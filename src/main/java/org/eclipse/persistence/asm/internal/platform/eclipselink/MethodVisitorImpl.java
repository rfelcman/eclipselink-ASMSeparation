package org.eclipse.persistence.asm.internal.platform.eclipselink;

import org.eclipse.persistence.internal.libraries.asm.MethodVisitor;

public class MethodVisitorImpl implements org.eclipse.persistence.asm.MethodVisitor {

    MethodVisitor methodVisitor = null;

    public MethodVisitorImpl(MethodVisitor methodVisitor) {
        this.methodVisitor = methodVisitor;
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        methodVisitor.visitVarInsn(opcode, var);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        methodVisitor.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }

    @Override
    public void visitInsn(int opcode) {
        methodVisitor.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        methodVisitor.visitMaxs(maxStack, maxLocals);
    }

    @Override
    public void visitEnd() {
        methodVisitor.visitEnd();
    }
}
