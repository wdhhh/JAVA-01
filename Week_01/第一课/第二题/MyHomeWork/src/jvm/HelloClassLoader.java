package jvm;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author weidh
 */
public class HelloClassLoader extends ClassLoader {
    String classPath;

    public HelloClassLoader(String classPath){
        this.classPath = classPath;
    }

    @Override
    public Class<?> findClass(String name) {
        try {
            byte[] bytes = loadByte(name);
            //把字节码转化为Class
            return defineClass(name,bytes,0,bytes.length);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加载文件
     * @param name
     * @return
     * @throws Exception
     */
    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.","/");
        InputStream fis = new FileInputStream(classPath+"/"+name+".xlass");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int r=0;
        while ((r=fis.read(buf))!=-1){
            byte[] decodeBuf = decode(buf);
            bos.write(decodeBuf,0,r);
        }
        return bos.toByteArray();
    }

    /**
     * 解码
     * @param buf
     * @return
     */
    public byte[] decode(byte[] buf ){
        byte[] decodeBuf = new byte[1024];
        for (int i=0;i<buf.length;i++){
            byte b = buf[i];
            decodeBuf[i] = (byte) (255-b);
        }
        return decodeBuf;
    }
}
