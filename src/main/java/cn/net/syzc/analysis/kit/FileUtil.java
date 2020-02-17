package cn.net.syzc.analysis.kit;

import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtil {

    public static int[][] getFile(String pathName) throws Exception {
        File file = new File(pathName);
        if (!file.exists())
            throw new RuntimeException("Not File!");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        List<int[]> list = new ArrayList<int[]>();
        while ((str = br.readLine()) != null) {
            int s = 0;
            String[] arr = str.split(" ");
            int[] dArr = new int[arr.length];
            for (String ss : arr) {
                if (ss != null) {
                    dArr[s++] = Integer.parseInt(ss);
                }

            }
            list.add(dArr);
        }
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i).length)
                max = list.get(i).length;
        }
        int[][] array = new int[list.size()][max];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                array[i][j] = list.get(i)[j];
            }
        }
        return array;
    }

    /**
     *
     * @param uploadFile
     * @return
     */
    public static String rename(UploadFile uploadFile) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = sdf.format(new Date()) + "_" + uploadFile.getFileName();
        String path = "upload/" + newFileName;
        String absoluteString = PathKit.getWebRootPath() + path;
        File newFile = new File(absoluteString);
        uploadFile.getFile().renameTo(newFile);
        return path;
    }
}
