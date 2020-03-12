package cn.net.syzc.analysis.service;

import cn.net.syzc.analysis.kit.*;
import cn.net.syzc.analysis.model.Task;
import cn.net.syzc.analysis.model.User;
import cn.net.syzc.analysis.test.Test;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IndexService {
    private static User userDao = new User().dao();
    private static Task taskDao = new Task().dao();

    public User login(String username, String password) {
        return userDao.findFirst("select * from user where UserName = ? ", username);
    }

    public List<Task> getTaskList(Integer UserId) {
        return taskDao.find("select * from task where UserID = ? ", UserId);
    }

    /**
     *
     * @param taskId
     * @return
     */
    public BaseResponse deleteTask(String taskId) {
        BaseResponse baseResponse = new BaseResponse();
        Task task = taskDao.findFirst("select * from task where id = ?", taskId);
        if (task != null) {
            if (task.delete()) {
                baseResponse.setResult(ResultCodeEnum.TASK_DELETE_SUCCESS);
            } else {
                baseResponse.setResult(ResultCodeEnum.TASK_DELETE_FAILURE_DB_ERROR);
            }
        } else {
            baseResponse.setResult(ResultCodeEnum.TASK_NOT_EXIST);
        }
        return baseResponse;
    }

    /**
     *
     * @param taskId
     * @return
     */
    public BaseResponse getTask(String taskId) {
        Test.log("Get Task IndexService start");
        BaseResponse baseResponse = new BaseResponse();
        CharmData charmData = new CharmData();
        List<Integer> nodes = new ArrayList<>();
        Test.log("Get Task IndexService query record start");
        Task task = taskDao.findFirst("select * from task where id = ?", taskId);
        Test.log("Get Task IndexService query record end");
        if (task != null) {
            String attriPath = PathKit.getWebRootPath() + task.getAttriFile();
            String edgePath = PathKit.getWebRootPath() + task.getEdgeFile();
            Test.log("Get Task IndexService try-catch start");
            try {
                double[][] attriArray = FileUtil.readFile(new File(attriPath)).getValue();
                for (int i = 0; i < attriArray.length; i++) {
                    nodes.add((int)attriArray[i][0]);
                }

                int[][] edgeArray = FileUtil.readOtherFile(new File(edgePath)).getValue();

                charmData.setNodes(nodes);
                charmData.setSides(edgeArray);
                charmData.setTask(task);

                baseResponse.setData(charmData);
                baseResponse.setResult(ResultCodeEnum.TASK_QUERY_SUCCESS);
            } catch (Exception e) {
                baseResponse.setResult(ResultCodeEnum.File_NO_EXIST);
                e.printStackTrace();
            }
            Test.log("Get Task IndexService try-catch end");
        } else {
            baseResponse.setResult(ResultCodeEnum.TASK_NOT_EXIST);
        }
        Test.log("Get Task IndexService end");
        return baseResponse;
    }

    /**
     *
     * @param taskId
     * @return
     */
    public BaseResponse getFeatureGroup(String taskId) {
        Test.log("Get Feature and Group IndexService start");
        BaseResponse baseResponse = new BaseResponse();
        FeatureGroupData featureGroupData = new FeatureGroupData();
        Test.log("Get Feature and Group IndexService query record start");
        Task task = taskDao.findFirst("select * from task where id = ?", taskId);
        Test.log("Get Feature and Group IndexService query record end");
        if (task != null) {
            String attriPath = PathKit.getWebRootPath() + task.getAttriFile();
            String classificationPath = PathKit.getWebRootPath() + task.getClassFile();
            Test.log("Get Feature and Group IndexService try-catch start");
            try {
                double[][] attriArray = FileUtil.readFile(new File(attriPath)).getValue();
                featureGroupData.setAttri(attriArray);
                if (task.getClassFile() != null) {
                    int[][] classificationArray = FileUtil.readOtherFile(new File(classificationPath)).getValue();
                    featureGroupData.setClassification(classificationArray);
                }

                baseResponse.setData(featureGroupData);
                baseResponse.setResult(ResultCodeEnum.FEATURES_GROUP_QUERY_SUCCESS);
            } catch (Exception e) {
                baseResponse.setResult(ResultCodeEnum.File_NO_EXIST);
                e.printStackTrace();
            }
            Test.log("Get Feature and Group IndexService try-catch end");
        } else {
            baseResponse.setResult(ResultCodeEnum.TASK_NOT_EXIST);
        }
        Test.log("Get Feature and Group IndexService end");
        return baseResponse;
    }
    /**
     *
     * @param taskId
     * @param nodeId
     * @return
     */
    public BaseResponse getClassification(String taskId, String nodeId) {
        BaseResponse baseResponse = new BaseResponse();
        String pythonFilePath = "D:\\Users\\pleasure\\Desktop\\DataAnalysisPlatform\\demo.py";
        // 调用python脚本获取节点的分类号
        String[] args = new String[]{"python", pythonFilePath, taskId, nodeId};
//        baseResponse = CallPythonFile.callPythonScripts(args);
        baseResponse.setResult(ResultCodeEnum.CLASSIFICATION_GET_SUCCESS);
        return baseResponse;
    }

    /**
     *
     * @param taskId
     * @param nodeId1
     * @param nodeId2
     * @return
     */
    public BaseResponse getSimilarity(String taskId, String nodeId1, String nodeId2) {
        BaseResponse baseResponse = new BaseResponse();
        String pythonFilePath = "";
        // 调用python脚本获取进行链接预测的两个节点的相似度
        String[] args = new String[]{"python", pythonFilePath, taskId, nodeId1, nodeId2};
//        baseResponse = CallPythonFile.callPythonScripts(args);
//        baseResponse.setData("1");
        baseResponse.setResult(ResultCodeEnum.SIMILARITY_GET_SUCCESS);
        return baseResponse;
    }

    /**
     *
     * @param u_id
     * @param task_name
     * @param algorithm_type
     * @param uploadFiles
     * @return
     */
    public BaseResponse addTask(String u_id, String task_name, String algorithm_type, List<UploadFile> uploadFiles) throws Exception {
        Test.log("Add Task Index Service start");
        BaseResponse baseResponse = new BaseResponse();
        Task task = new Task();
        task.setUserID(Integer.parseInt(u_id));
        task.setTaskName(task_name);
        task.setAlgorithmType(algorithm_type);

        String attriPath = null;
        String edgePath = null;
        String classificationPath = null;
        System.out.println("上传文件数：" + uploadFiles.size());
        Test.log("Add Task Index Service switch start");
        switch (uploadFiles.size()) {
            case 2:
                ResultCodeEnum result1 = FileUtil.checkFiles(uploadFiles.get(0).getFile(), uploadFiles.get(1).getFile(), null);
                if(!result1.getCode().equals("6021")){
                    baseResponse.setResult(result1);
                    return baseResponse;
                }
                attriPath = FileUtil.rename(uploadFiles.get(0));
                task.setAttriFile(attriPath);
                edgePath = FileUtil.rename(uploadFiles.get(1));
                task.setEdgeFile(edgePath);
                break;
            case 3:
                ResultCodeEnum result2 = FileUtil.checkFiles(uploadFiles.get(0).getFile(), uploadFiles.get(1).getFile(), uploadFiles.get(2).getFile());
                if(!result2.getCode().equals("6021")){
                    baseResponse.setResult(result2);
                    return baseResponse;
                }
                attriPath = FileUtil.rename(uploadFiles.get(0));
                task.setAttriFile(attriPath);
                edgePath = FileUtil.rename(uploadFiles.get(1));
                task.setEdgeFile(edgePath);
                classificationPath = FileUtil.rename(uploadFiles.get(2));
                task.setClassFile(classificationPath);
                break;
        }
        Test.log("Add Task Index Service switch end");
        Test.log("Add Task Index Service save start");
        if (task.save()) {
            baseResponse.setResult(ResultCodeEnum.TASK_ADD_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.TASK_ADD_FAILURE_DB_ERROR);
        }
        Test.log("Add Task Index Service save end");
        Test.log("Add Task Index Service end");
        return baseResponse;
    }
}
