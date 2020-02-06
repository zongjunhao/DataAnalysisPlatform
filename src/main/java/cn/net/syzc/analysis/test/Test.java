package cn.net.syzc.analysis.test;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("python.home", "path to the Lib folder");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties properties = System.getProperties();
        PythonInterpreter.initialize(properties, props, new String[0]);

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("C:\\Users\\zongjunhao\\Desktop\\demo.py");
        PyFunction function = interpreter.get("wdd", PyFunction.class);
        int a = 10;
        int b = 10;
        PyObject pyObject = function.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("answer = " + pyObject.toString());
    }
}
