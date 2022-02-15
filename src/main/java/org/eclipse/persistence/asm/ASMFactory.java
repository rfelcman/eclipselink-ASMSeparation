package org.eclipse.persistence.asm;

public class ASMFactory {

    public final static String ASM_SERVICE = "eclipselink.asm.service";
    public final static String ASM_SERVICE_ECLIPSELINK = "eclipselink";
    public final static String ASM_SERVICE_OW2 = "OW2";
    public final static String ASM_SERVICE_DEFAULT = ASM_SERVICE_ECLIPSELINK;

    public static ClassWriter createClassWriter() {
        String asmService = System.getProperty(ASM_SERVICE, ASM_SERVICE_DEFAULT);
        if (ASM_SERVICE_ECLIPSELINK.equals(asmService)) {
            return new org.eclipse.persistence.asm.internal.platform.eclipselink.ClassWriterImpl();
        } else if (ASM_SERVICE_OW2.equals(asmService)) {
            return new org.eclipse.persistence.asm.internal.platform.ow2.ClassWriterImpl();
        } else {
            throw new RuntimeException("Incorrect ASM service name.");
        }
    }
}
