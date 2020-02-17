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

    public static int[][] readFile(File file) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str;
        List<int[]> list = new ArrayList<>();
        while ((str = bufferedReader.readLine()) != null) {
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
        for (int[] ints : list) {
            if (max < ints.length)
                max = ints.length;
        }
        int[][] array = new int[list.size()][max];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(list.get(i), 0, array[i], 0, list.get(i).length);
        }
        return array;
    }

    /**
     * 将上传的文件重命名为“时间_文件名” ,防止文件名重复
     *
     * @param uploadFile 文件
     * @return 文件路径
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
     * @return pass: 0; Edge file exception: 1; Classification file exception: 2.
     */
    public static int checkFiles(File attrFile, File edgeFile, File classFile) throws Exception {
        int[][] attribute = readFile(attrFile);
        int[][] edge = readFile(edgeFile);

        // save the points to the list
        List<Integer> points = new ArrayList<>();
        for (int[] attrLine : attribute) {
            points.add(attrLine[0]);
        }

        // verify the edge file
        for (int[] edgeLine : edge) {
            for (int value : edgeLine) {
                // a nonexistent point appears in the file
                if (!points.contains(value)) {
                    return 1;
                }
            }
        }

        // If the user uploads the classification file, verify the classification file
        if (classFile != null) {
            int[][] classification = readFile(classFile);
            for (int[] classificationLine : classification) {
                if (!points.contains(classificationLine[0])) {
                    return 2;
                }
            }
        }

        // pass
        return 0;
    }

    public static void main(String[] args) throws Exception {
        File attrFile = new File("src/main/java/cn/net/syzc/analysis/test/attri.txt");
        File edgeFile = new File("src/main/java/cn/net/syzc/analysis/test/edge.txt");
        File classFile = new File("src/main/java/cn/net/syzc/analysis/test/classification.txt");
        int result = checkFiles(attrFile, edgeFile, classFile);
        System.out.println("result = " + result);
    }
}
