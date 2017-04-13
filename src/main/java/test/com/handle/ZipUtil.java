package test.com.handle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


public class ZipUtil {  
    private static ZipUtil instance = new ZipUtil();  
    private static final int BUFFEREDSIZE = 1024;  
    private static Log log = LogFactory.getLog(ZipUtil.class);  
  
    private ZipUtil() {  
    }  
  
    public static ZipUtil getInstance() {  
        return instance;  
    }  
    
    public static void main(String[] args) {
    	try {
			zip("G:\\Download\\AdminEx\\AdminEx","G:\\a.zip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public static void zip(String inputFilename, String zipFilename)  
            throws IOException {  
        zip(new File(inputFilename), zipFilename);  
    }  
  
    public static void zip(File inputFile, String zipFilename)  
            throws IOException {  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(  
                zipFilename));  
        out.setEncoding("GBK");  
  
        try {  
            if (log.isDebugEnabled()) {  
                log.debug("压缩开始：待压缩文件(夹)为 " + inputFile);  
            }  
            zip(inputFile, out, "");  
        } catch (IOException e) {  
            log.error("压缩失败");  
        } finally {  
            out.close();  
            if (log.isDebugEnabled()) {  
                log.debug("压缩成功");  
            }  
        }  
    }  
  
    private static void zip(File inputFile, ZipOutputStream out, String base)  
            throws IOException {  
        if (inputFile.isDirectory()) {  
            if (inputFile.listFiles().length == 0) {  
                ZipEntry zipEntry = new ZipEntry(base + inputFile.getName()  
                        + "/");  
                out.putNextEntry(zipEntry);  
                out.closeEntry();  
            } else {  
                base += inputFile.getName() + inputFile.separator;  
                for (File f : inputFile.listFiles()) {  
                    zip(f, out, base);  
                }  
            }  
        } else {  
            ZipEntry zipEntry = new ZipEntry(base + inputFile.getName());  
            out.putNextEntry(zipEntry);  
            FileInputStream in = new FileInputStream(inputFile);  
            try {  
                int c;  
                byte[] by = new byte[BUFFEREDSIZE];  
                while ((c = in.read(by)) != -1) {  
                    out.write(by, 0, c);  
                }  
            } catch (IOException e) {  
                throw e;  
            } finally {  
                in.close();  
            }  
        }  
    }  
  
    public static void multiZip(String[] inputFilelist, String zipFilename)  
            throws IOException {  
        File[] files = new File[inputFilelist.length];  
        for (int i = 0; i < inputFilelist.length; i++) {  
            files[i] = new File(inputFilelist[i]);  
        }  
  
        multiZip(files, zipFilename);  
    }  
  
    public static void multiZip(File[] files, String zipFilename)  
            throws IOException {  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(  
                zipFilename));  
        out.setEncoding("GBK");  
  
        try {  
            if (log.isDebugEnabled()) {  
                log.debug("压缩开始：待压缩文件(夹)共 " + files.length + "个");  
            }  
  
            for (File f : files) {  
                zip(f, out, "");  
            }  
        } catch (Exception e) {  
            log.error("压缩失败");  
        } finally {  
            out.close();  
            if (log.isDebugEnabled()) {  
                log.debug("压缩成功");  
            }  
        }  
    }  
}  