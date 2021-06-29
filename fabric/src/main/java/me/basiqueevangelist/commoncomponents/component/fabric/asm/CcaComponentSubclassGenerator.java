package me.basiqueevangelist.commoncomponents.component.fabric.asm;

import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponent;
import org.intellij.lang.annotations.MagicConstant;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import user11681.reflect.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

import static org.objectweb.asm.Opcodes.*;

public final class CcaComponentSubclassGenerator {
    private CcaComponentSubclassGenerator() {

    }

    private static final ConcurrentMap<Integer, Class<?>> PREGENERATED_CLASSES = new ConcurrentHashMap<>();

    public static final int SYNCING = 1;
    public static final int CLIENT_TICKING = 2;
    public static final int SERVER_TICKING = 4;

    public static Class<?> getClassFor(@MagicConstant(flagsFromClass = CcaComponentSubclassGenerator.class) int flags) {
        if (flags == 0)
            return CcaComponent.class;

        return PREGENERATED_CLASSES.computeIfAbsent(flags, unused -> {
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

            String className = generateClassNameFor(flags);
            writer.visit(V1_8, ACC_PUBLIC | ACC_SUPER, className, null, "me/basiqueevangelist/commoncomponents/component/fabric/CcaComponent", gatherInterfacesFor(flags));
            writer.visitEnd();

            MethodVisitor mv = writer.visitMethod(ACC_PUBLIC, "<init>", "(Lme/basiqueevangelist/commoncomponents/component/Component;)V", null, new String[0]);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESPECIAL, "me/basiqueevangelist/commoncomponents/component/fabric/CcaComponent", "<init>", "(Lme/basiqueevangelist/commoncomponents/component/Component;)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();

            gatherWrappedGettingMethods(flags, interfaceName -> {
                MethodVisitor newMv = writer.visitMethod(ACC_PUBLIC, "getWrapped", "()L" + interfaceName + ";", null, new String[0]);
                newMv.visitCode();
                newMv.visitVarInsn(ALOAD, 0);
                newMv.visitFieldInsn(GETFIELD, className, "wrapped", "Lme/basiqueevangelist/commoncomponents/component/Component;");
                newMv.visitTypeInsn(CHECKCAST, interfaceName);
                newMv.visitInsn(ARETURN);
                newMv.visitMaxs(1, 1);
                newMv.visitEnd();
            });

            return Classes.defineClass(CcaComponentSubclassGenerator.class.getClassLoader(), className.replace("/", "."), writer.toByteArray());
        });
    }

    private static String[] gatherInterfacesFor(@MagicConstant(flagsFromClass = CcaComponentSubclassGenerator.class) int flags) {
        List<String> interfaces = new ArrayList<>();
        if ((flags & SYNCING) == SYNCING)
            interfaces.add("me/basiqueevangelist/commoncomponents/component/fabric/CcaSyncingImpl");
        if ((flags & CLIENT_TICKING) == CLIENT_TICKING)
            interfaces.add("me/basiqueevangelist/commoncomponents/component/fabric/CcaClientTickingImpl");
        if ((flags & SERVER_TICKING) == SERVER_TICKING)
            interfaces.add("me/basiqueevangelist/commoncomponents/component/fabric/CcaServerTickingImpl");
        return interfaces.toArray(new String[0]);
    }

    private static void gatherWrappedGettingMethods(@MagicConstant(flagsFromClass = CcaComponentSubclassGenerator.class) int flags, Consumer<String> consumer) {
        if ((flags & SYNCING) == SYNCING)
            consumer.accept("me/basiqueevangelist/commoncomponents/component/SyncingComponent");
        if ((flags & CLIENT_TICKING) == CLIENT_TICKING)
            consumer.accept("me/basiqueevangelist/commoncomponents/component/ClientTickedComponent");
        if ((flags & SERVER_TICKING) == SERVER_TICKING)
            consumer.accept("me/basiqueevangelist/commoncomponents/component/ServerTickedComponent");
    }

    private static String generateClassNameFor(@MagicConstant(flagsFromClass = CcaComponentSubclassGenerator.class) int flags) {
        StringBuilder sb = new StringBuilder();
        sb.append("me/basiqueevangelist/commoncomponents/component/fabric/asm/generated/Cca");
        if ((flags & SYNCING) == SYNCING)
            sb.append("Syncing");
        if ((flags & CLIENT_TICKING) == CLIENT_TICKING)
            sb.append("ClientTicking");
        if ((flags & SERVER_TICKING) == SERVER_TICKING)
            sb.append("ServerTicking");
        sb.append("Component");
        return sb.toString();
    }
}
