package org.eclipse.persistence.asm;

import java.lang.reflect.Field;

public class Opcodes {

    public final static String ASM_OPCCODES_ECLIPSELINK = "org.eclipse.persistence.internal.libraries.asm.Opcodes";
    public final static String ASM_OPCCODES_OW2 = "org.objectweb.asm.Opcodes";

    public static int getInt(String fieldName) {
        return ((Integer)getFieldValue(fieldName)).intValue();
    }

    public static int getInteger(String fieldName) {
        return (Integer)getFieldValue(fieldName);
    }

    private static Object getFieldValue(String fieldName) {
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
}
