package test.com.handle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test10 {

	public static void main(String[] args) throws Exception {
		
		String txt="<a href=\"http://i.stack.imgur.com/AeswW.png\">的武器大师<img src=\"http://localhost:8080/c2mmanage/static/ueditor/jsp/upload/image/20161103/1478165735524001454.jpg\" title=\"1478165735524001454.jpg\" alt=\"9c16fdfaaf51f3de433d153e95eef01f3b2979d9.jpg\"/>";

	    String re1=".*?";	// Non-greedy match on filler
	    String re2="(img)";	// Variable Name 1
	    String re3=".*?";	// Non-greedy match on filler
	    String re4="((?:http|https)(?::\\/{2}[\\w]+)(?:[\\/|\\.]?)(?:[^\\s\"]*))";	// HTTP URL 1

	    Pattern p = Pattern.compile(re1+re2+re3+re4,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(txt);
	    if (m.find())
	    {
	        String var1=m.group(1);
	        String httpurl1=m.group(2);
	        System.out.print("("+var1.toString()+")"+"("+httpurl1.toString()+")"+"\n");
	    }
	}
}
