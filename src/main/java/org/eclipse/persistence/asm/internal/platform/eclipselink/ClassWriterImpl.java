package org.eclipse.persistence.asm.internal.platform.eclipselink;

import org.eclipse.persistence.asm.MethodVisitor;
import org.eclipse.persistence.internal.libraries.asm.EclipseLinkASMClassWriter;

public class ClassWriterImpl implements org.eclipse.persistence.asm.ClassWriter {

    EclipseLinkASMClassWriter classWriter = null;

    public ClassWriterImpl() {
        classWriter = new EclipseLinkASMClassWriter(org.eclipse.persistence.internal.libraries.asm.ClassWriter.COMPUTE_FRAMES);
    }

    public ClassWriterImpl(final int flags) {
        classWriter = new EclipseLinkASMClassWriter(flags);
    }

    @Override
    public void visit(int access, String name, String signature, String superName, String[] interfaces) {
        classWriter.visit(52, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return new MethodVisitorImpl(classWriter.visitMethod(access, name, descriptor, signature, exceptions));
    }

    @Override
    public void visitEnd() {
        classWriter.visitEnd();
    }

    @Override
    public byte[] toByteArray() {
        return classWriter.toByteArray();
    }
}
