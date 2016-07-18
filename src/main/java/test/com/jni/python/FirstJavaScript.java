package test.com.jni.python;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class FirstJavaScript {

	public static void main(String[] args) {
		
        PythonInterpreter interpreter = new PythonInterpreter();
        
        interpreter.execfile("e:/generate.py");
        PyFunction func = (PyFunction) interpreter.get("listdir",
                PyFunction.class);
        PyObject pyobj =  func.__call__(new PyString("E:/V1.7"));
		
        System.out.println("anwser = " + pyobj.toString());
        
        interpreter.close();
	}
}
