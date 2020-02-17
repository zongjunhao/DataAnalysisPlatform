package cn.net.syzc.analysis.service;

import cn.net.syzc.analysis.kit.*;
import cn.net.syzc.analysis.model.Task;
import cn.net.syzc.analysis.model.User;
import com.jfinal.upload.UploadFile;

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
        BaseResponse baseResponse = new BaseResponse();
        CharmDataSource charmDataSource = new CharmDataSource();
        List<Node> nodes = new ArrayList<>();
        List<Side> sides = new ArrayList<>();

        Task task = taskDao.findFirst("select * from task where id = ?", taskId);
        if (task != null) {
            String attriPath = task.getAttriFile();
            String edgePath = task.getEdgeFile();
            String classificationPath = task.getClassFile();

            try {
                int[][] attriArray = FileUtil.getFile(attriPath);
                for (int i = 0; i < attriArray.length; i++) {
                    Node node = new Node();
                    node.setNodeId(attriArray[i][0]);
                    node.setName("节点" + attriArray[i][0]);
                    nodes.add(node);
                }

                int[][] edgeArray = FileUtil.getFile(edgePath);
                for (int i = 0; i < edgeArray.length; i++) {
                   Side side = new Side();
                   side.setSource(edgeArray[i][0]);
                   side.setTarget(edgeArray[i][1]);
                   sides.add(side);
                }

                if (!classificationPath.equals("")) {
                    int[][] classificationArray = FileUtil.getFile(classificationPath);
                    charmDataSource.setClassification(classificationArray);
                }

                charmDataSource.setNodes(nodes);
                charmDataSource.setSides(sides);
                charmDataSource.setAttri(attriArray);

                baseResponse.setData(charmDataSource);
                baseResponse.setResult(ResultCodeEnum.TASK_QUERY_SUCCESS);
            } catch (Exception e) {
                baseResponse.setResult(ResultCodeEnum.File_NO_EXIST);
                e.printStackTrace();
            }
        } else {
            baseResponse.setResult(ResultCodeEnum.TASK_NOT_EXIST);
        }
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
        baseResponse = CallPythonFile.callPythonScripts(args);
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
        baseResponse = CallPythonFile.callPythonScripts(args);
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
    public BaseResponse addTask(String u_id, String task_name, String algorithm_type, List<UploadFile> uploadFiles) {
        BaseResponse baseResponse = new BaseResponse();
        Task task = new Task();
        task.setUserID(Integer.parseInt(u_id));
        task.setTaskName(task_name);
        task.setAlgorithmType(algorithm_type);

        String attriPath = null;
        String edgePath = null;
        String classificationPath = null;
        System.out.println("上传文件数：" + uploadFiles.size());
        switch (uploadFiles.size()) {
            case 2:
                attriPath = FileUtil.rename(uploadFiles.get(0));
                task.setAttriFile(attriPath);
                edgePath = FileUtil.rename(uploadFiles.get(1));
                task.setEdgeFile(edgePath);
                break;
            case 3:
                attriPath = FileUtil.rename(uploadFiles.get(0));
                task.setAttriFile(attriPath);
                edgePath = FileUtil.rename(uploadFiles.get(1));
                task.setEdgeFile(edgePath);
                classificationPath = FileUtil.rename(uploadFiles.get(2));
                task.setClassFile(classificationPath);
                break;
        }
        if (task.save()) {
            baseResponse.setResult(ResultCodeEnum.TASK_ADD_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.TASK_ADD_FAILURE_DB_ERROR);
        }
        return baseResponse;
    }
}
