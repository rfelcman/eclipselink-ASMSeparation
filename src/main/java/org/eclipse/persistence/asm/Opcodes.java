package org.eclipse.persistence.asm;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

public class Opcodes {

    public final static String ASM_OPCCODES_ECLIPSELINK = "org.eclipse.persistence.internal.libraries.asm.Opcodes";
    public final static String ASM_OPCCODES_OW2 = "org.objectweb.asm.Opcodes";

    public static int valueInt(String fieldName) {
        return ((int)getFieldValueInvokeAPI(fieldName, Integer.TYPE));
//        return ((int)getFieldValueReflection(fieldName));
    }

    public static Integer valueInteger(String fieldName) {
        return ((Integer)getFieldValueInvokeAPI(fieldName, Integer.class));
//        return ((Integer)getFieldValueReflection(fieldName));
    }

    //reflection <= JDK 8
    private static Object getFieldValueReflection(String fieldName) {
        String asmService = System.getProperty(ASMFactory.ASM_SERVICE, ASMFactory.ASM_SERVICE_DEFAULT);
        Object result = null;
        try {
            Class<?> clazz = null;
            if (ASMFactory.ASM_SERVICE_ECLIPSELINK.equals(asmService)) {
                clazz = Class.forName(ASM_OPCCODES_ECLIPSELINK);
            } else if (ASMFactory.ASM_SERVICE_OW2.equals(asmService)) {
                clazz = Class.forName(ASM_OPCCODES_OW2);
            } else {
                throw new RuntimeException("Incorrect ASM service name.");
            }
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            result = field.get(clazz);
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    //java.lang.invoke.VarHandle >= JDK 9
    private static Object getFieldValueInvokeAPI(String name, Class<?> type) {
        String asmService = System.getProperty(ASMFactory.ASM_SERVICE, ASMFactory.ASM_SERVICE_DEFAULT);
        Object result = null;
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            Class<?> clazz = null;
            if (ASMFactory.ASM_SERVICE_ECLIPSELINK.equals(asmService)) {
                clazz = lookup.findClass(ASM_OPCCODES_ECLIPSELINK);
            } else if (ASMFactory.ASM_SERVICE_OW2.equals(asmService)) {
                clazz = lookup.findClass(ASM_OPCCODES_OW2);
            } else {
                throw new RuntimeException("Incorrect ASM service name.");
            }
            VarHandle field = MethodHandles.lookup().findStaticVarHandle(clazz, name, type);
            result = field.get();

        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return result;
    }
}
