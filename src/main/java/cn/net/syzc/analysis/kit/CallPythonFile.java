package cn.net.syzc.analysis.kit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CallPythonFile {

    public static BaseResponse callPythonScripts(String[] args) {
        BaseResponse baseResponse = new BaseResponse();
        StringBuilder pyReInfo = new StringBuilder();
        int re = 1;
        try {
            Process process = Runtime.getRuntime().exec(args);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),
                    StandardCharsets.UTF_8));
            String line = null;
            while ((line = in.readLine()) != null) {
                pyReInfo.append(line);
            }
            in.close();
            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
            re = process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (re == 0) {
                baseResponse.setData(pyReInfo.toString());
                baseResponse.setResult(ResultCodeEnum.CALL_PYTHON_SCRIPTS_SUCCESS);
            } else {
                baseResponse.setResult(ResultCodeEnum.CALL_PYTHON_SCRIPTS_ERROR);
            }
        }
        return baseResponse;
    }
}
