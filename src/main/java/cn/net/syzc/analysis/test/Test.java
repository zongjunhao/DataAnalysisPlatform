package cn.net.syzc.analysis.test;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        // Properties props = new Properties();
        // props.put("python.home", "path to the Lib folder");
        // props.put("python.console.encoding", "UTF-8");
        // props.put("python.security.respectJavaAccessibility", "false");
        // props.put("python.import.site", "false");
        // Properties properties = System.getProperties();
        // PythonInterpreter.initialize(properties, props, new String[0]);
        //
        // PythonInterpreter interpreter = new PythonInterpreter();
        // interpreter.execfile("D:\\Users\\pleasure\\Desktop\\DataAnalysisPlatform\\demo.py");
        // PyFunction function = interpreter.get("add", PyFunction.class);
        // int a = 10;
        // int b = 10;
        // PyObject pyObject = function.__call__(new PyInteger(a), new PyInteger(b));
        // System.out.println("answer = " + pyObject.toString());
        log("testmessage");
    }

    public static void log(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(message + "\t-------------------\t" + simpleDateFormat.format(new Date()) + "\t-------------------\t");
    }
}
