package org.eclipse.persistence.asm;

public class ASMFactory {

    public final static String ASM_SERVICE = "eclipselink.asm.service";
    public final static String ASM_SERVICE_ECLIPSELINK = "eclipselink";
    public final static String ASM_SERVICE_OW2 = "OW2";
    public final static String ASM_SERVICE_DEFAULT = ASM_SERVICE_ECLIPSELINK;

    public static ClassWriter createClassWriter() {
        String asmService = System.getProperty(ASM_SERVICE, ASM_SERVICE_DEFAULT);
        if (ASM_SERVICE_ECLIPSELINK.equals(asmService)) {
/*
            ServiceLoader<ClassWriter> classWriterServiceLoader = ServiceLoader.load(ClassWriter.class, Thread.currentThread().getContextClassLoader());
            //Prints java.util.ServiceConfigurationError: org.eclipse.persistence.asm.ClassWriter: org.eclipse.persistence.internal.libraries.asm.EclipseLinkASMClassWriter not a subtype
            //Correct usage of ServiceLoader need put org.eclipse.persistence.asm.ClassWriter and other intefaces to org.eclipse.persistence.asm project.
        for (Object service: classWriterServiceLoader) {
            System.out.println(service);
        }

*/
            return new org.eclipse.persistence.asm.internal.platform.eclipselink.ClassWriterImpl();
        } else if (ASM_SERVICE_OW2.equals(asmService)) {
            return new org.eclipse.persistence.asm.internal.platform.ow2.ClassWriterImpl();
        } else {
            throw new RuntimeException("Incorrect ASM service name.");
        }
    }
}
