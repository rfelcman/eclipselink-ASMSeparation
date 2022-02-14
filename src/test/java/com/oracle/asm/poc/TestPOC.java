package com.oracle.asm.poc;

import com.oracle.asm.poc.domain.TestEntity;

import org.eclipse.persistence.asm.ASMFactory;
import org.eclipse.persistence.asm.ClassWriter;
import org.eclipse.persistence.asm.MethodVisitor;
import org.eclipse.persistence.asm.Opcodes;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPOC {


    @Test
    public void testClassWriter() throws Throwable {
//        System.setProperty(ASMFactory.ASM_SERVICE, ASMFactory.ASM_SERVICE_ECLIPSELINK);
        System.setProperty(ASMFactory.ASM_SERVICE, ASMFactory.ASM_SERVICE_OW2);

        String className = TestEntity.class.getName();

        ClassWriter classWriter = ASMFactory.createClassWriter();
        classWriter.visit(33, className, null, className.replace('.', '/'), null);
        MethodVisitor mv = classWriter.visitMethod(Opcodes.getInt("ACC_PUBLIC"), "<init>", "()V", null, null);
        mv.visitVarInsn(Opcodes.getInt("ALOAD"), 0);
        mv.visitMethodInsn(Opcodes.getInt("INVOKESPECIAL"), className.replace('.', '/'), "<init>", "()V", false);
        mv.visitInsn(Opcodes.getInt("RETURN"));
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
        assertEquals(1, Opcodes.getInt("ACC_PUBLIC"));
        assertEquals(62, Opcodes.getInt("V18"));
        assertEquals(1, Opcodes.getInteger("INTEGER"));
    }
}
