package me.basiqueevangelist.commoncomponents.fabric.asm;

public class CommonComponentsClassLoader extends ClassLoader {
    public static CommonComponentsClassLoader INSTANCE = new CommonComponentsClassLoader();

    public CommonComponentsClassLoader() {
        super(CommonComponentsClassLoader.class.getClassLoader());
    }

    public Class<?> defineClass(String name, byte[] bytes) throws ClassFormatError {
        return this.defineClass(name, bytes, 0, bytes.length);
    }
}
