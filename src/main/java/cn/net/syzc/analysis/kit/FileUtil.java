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

    public static FileResult readFile(File file) throws Exception {
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str;
        List<int[]> list = new ArrayList<>();
        while ((str = bufferedReader.readLine()) != null) {
            int s = 0;
            String[] arr = str.split(" ");
            if (arr.length <= 1){
                continue;
            }
            int[] dArr = new int[arr.length];
            for (String ss : arr) {
                if (ss != null) {
                    dArr[s++] = Integer.parseInt(ss);
                }
            }
            list.add(dArr);
        }
        int max = 0, min = list.get(0).length;
        for (int[] ints : list) {
            if (max < ints.length)
                max = ints.length;
            if (min > ints.length) {
                min = ints.length;
            }
        }
        int[][] array = new int[list.size()][max];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(list.get(i), 0, array[i], 0, list.get(i).length);
        }
        bufferedReader.close();
        return new FileResult(array, max, min);
    }

    /**
     * Rename the upload file to "datetime_filename"
     *
     * @param uploadFile file
     * @return file path
     */
    public static String rename(UploadFile uploadFile) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = sdf.format(new Date()) + "_" + uploadFile.getFileName();
        System.out.println("newFileName: " + newFileName);
        String path = "/upload/" + newFileName;
        String absoluteString = PathKit.getWebRootPath() + path;
        System.out.println("absoluteString: " + absoluteString);
        File newFile = new File(absoluteString);
        boolean renameSuccess = uploadFile.getFile().renameTo(newFile);
        System.out.println("renameSuccess = " + renameSuccess);
        return path;
    }

    /**
     * Verify that the file is correct
     *
     * @param attrFile  attribute file
     * @param edgeFile  edge file
     * @param classFile classification file
     */
    public static ResultCodeEnum checkFiles(File attrFile, File edgeFile, File classFile) throws Exception {
        FileResult attribute = readFile(attrFile);
        FileResult edge = readFile(edgeFile);

        // save the points to the list
        List<Integer> points = new ArrayList<>();
        for (int[] attrLine : attribute.getValue()) {
            if (attribute.getMax() != attribute.getMin()) {
                return ResultCodeEnum.ATTR_FILE_EXCEPTION;
            }
            if (points.contains(attrLine[0])) {
                return ResultCodeEnum.ATTR_FILE_EXCEPTION;
            }
            for (int i = 1; i < attrLine.length; i++) {
                if (attrLine[i] != 0 && attrLine[i] != 1) {
                    return ResultCodeEnum.ATTR_FILE_EXCEPTION;
                }
            }
            points.add(attrLine[0]);
        }

        // verify the edge file
        for (int[] edgeLine : edge.getValue()) {
            if (edge.getMax() != 2 || edge.getMin() != 2) {
                return ResultCodeEnum.EDGE_FILE_EXCEPTION;
            }
            for (int value : edgeLine) {
                // a nonexistent point appears in the file
                if (!points.contains(value)) {
                    return ResultCodeEnum.EDGE_FILE_EXCEPTION;
                }
            }
        }

        // If the user uploads the classification file, verify the classification file
        if (classFile != null) {
            FileResult classification = readFile(classFile);
            if (classification.getMax() != 2 || classification.getMin() != 2) {
                return ResultCodeEnum.CLASS_FILE_EXCEPTION;
            }
            for (int[] classificationLine : classification.getValue()) {
                if (!points.contains(classificationLine[0])) {
                    return ResultCodeEnum.CLASS_FILE_EXCEPTION;
                }
            }
        }

        // pass
        return ResultCodeEnum.FILE_CHECK_PASSED;
    }

    public static void main(String[] args) throws Exception {
        File attrFile = new File("src/main/java/cn/net/syzc/analysis/test/attri.txt");
        File edgeFile = new File("src/main/java/cn/net/syzc/analysis/test/edge.txt");
        File classFile = new File("src/main/java/cn/net/syzc/analysis/test/classification.txt");
        // int result = checkFiles(attrFile, edgeFile, classFile);
        // System.out.println("result = " + result);
    }
}
