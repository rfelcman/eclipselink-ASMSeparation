package com.oracle.asm.poc;

import com.oracle.asm.poc.domain.TestEntity;

import org.eclipse.persistence.asm.ASMFactory;
import org.eclipse.persistence.asm.ClassWriter;
import org.eclipse.persistence.asm.MethodVisitor;
import org.eclipse.persistence.asm.Opcodes;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPOC {


    @Test
    public void testClassWriter() throws Throwable {
        System.setProperty(ASMFactory.ASM_SERVICE, ASMFactory.ASM_SERVICE_ECLIPSELINK);
//        System.setProperty(ASMFactory.ASM_SERVICE, ASMFactory.ASM_SERVICE_OW2);

        String className = TestEntity.class.getName();

        ClassWriter classWriter = ASMFactory.createClassWriter();
        classWriter.visit(33, className, null, className.replace('.', '/'), null);
        MethodVisitor mv = classWriter.visitMethod(Opcodes.valueInt("ACC_PUBLIC"), "<init>", "()V", null, null);
        mv.visitVarInsn(Opcodes.valueInt("ALOAD"), 0);
        mv.visitMethodInsn(Opcodes.valueInt("INVOKESPECIAL"), className.replace('.', '/'), "<init>", "()V", false);
        mv.visitInsn(Opcodes.valueInt("RETURN"));
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] classContent = classWriter.toByteArray();

        FileOutputStream fos = new FileOutputStream("target/TestEntityASM.class");
        fos.write(classContent);
        fos.close();
        System.out.println(classContent);
    }

    @Test
    public void testOpcodes() {
        System.setProperty(ASMFactory.ASM_SERVICE, ASMFactory.ASM_SERVICE_ECLIPSELINK);
//        System.setProperty(ASMFactory.ASM_SERVICE, ASMFactory.ASM_SERVICE_OW2);
        assertEquals(1, Opcodes.valueInt("ACC_PUBLIC"));
        assertEquals(62, Opcodes.valueInt("V18"));
        assertEquals(1, Opcodes.valueInteger("INTEGER"));
    }

    @Test
    public void testMethodHandle() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Class<?> clazz = lookup.findClass(Opcodes.ASM_OPCCODES_OW2);
//        VarHandle field = MethodHandles.lookup().in(clazz).findStaticVarHandle(clazz, "ACC_PUBLIC", Integer.TYPE);
        VarHandle field = MethodHandles.lookup().findStaticVarHandle(clazz, "ACC_PUBLIC", Integer.TYPE);
        int result = 0;
        result = (Integer)field.get();
        assertEquals(1, result);
    }
}