package cn.net.syzc.analysis.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test_wzy {

    public static void main(String[] args) {
        String[] arguments = new String[]{"python", "D:\\Users\\pleasure\\Desktop\\DataAnalysisPlatform\\demo.py", "14", "7"};
        StringBuilder pyReInfo = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),
                    "UTF-8"));
            String line = null;
            while ((line = in.readLine()) != null) {
                pyReInfo.append(line);
            }
            in.close();
            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
            int re = process.waitFor();
            if (re != 0) {
                throw new Exception("[callPythonFile]:Error,return 1.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(pyReInfo.toString());
        }
    }
}

