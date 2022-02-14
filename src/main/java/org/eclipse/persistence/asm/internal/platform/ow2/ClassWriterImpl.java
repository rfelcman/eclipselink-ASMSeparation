package org.eclipse.persistence.asm.internal.platform.ow2;


import org.eclipse.persistence.asm.MethodVisitor;
import org.objectweb.asm.ClassReader;

public class ClassWriterImpl implements org.eclipse.persistence.asm.ClassWriter {

    private org.objectweb.asm.ClassWriter classWriter = null;

    public ClassWriterImpl() {
        this(org.objectweb.asm.ClassWriter.COMPUTE_FRAMES);
    }

    public ClassWriterImpl(final int flags) {
        this(null, flags);
    }

    public ClassWriterImpl(final ClassReader classReader, final int flags) {
        classWriter = new org.objectweb.asm.ClassWriter(classReader, flags);
    }

    @Override
    public void visit(int access, String name, String signature, String superName, String[] interfaces) {
        classWriter.visit(52, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String descriptor, final String signature, final String[] exceptions) {
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
