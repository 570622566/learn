package test.com.iteratorfile;

import java.io.File;

public class Test1 {
	public static void main(String[] args) throws Exception {
		
		File currentDir = new File("E:\\ykj");
        displayDirectoryContents(currentDir);

		
	}

	private static void displayDirectoryContents(File currentDir) throws Exception {
        StringBuilder sb1 = new StringBuilder("[");

        File[] files = currentDir.listFiles();
        
        for (File file : files) {
			if(file.isDirectory()){
              //  sb1 = sb1.append("{\"path\":\"" + file.getCanonicalPath() + "\",type:\"folder\"},");
                String str = file.getCanonicalPath();
                System.out.println(str);
                displayDirectoryContents(file);
			}else if(file.isFile()){
				sb1 = sb1.append("{\"path\":\"" + file.getCanonicalPath() + "\",type:\"file\"},");
			}
		}
        sb1.deleteCharAt(sb1.length() - 1);
        sb1 = sb1.append("]");
        System.out.println("s2==>" + sb1);
	}
}
