package cn.net.syzc.analysis.service;

import cn.net.syzc.analysis.kit.BaseResponse;
import cn.net.syzc.analysis.kit.ResultCodeEnum;
import cn.net.syzc.analysis.model.Task;
import cn.net.syzc.analysis.model.User;

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
        Task task = taskDao.findFirst("select * from task where id = ?", taskId);
        if (task != null) {
            baseResponse.setData(task);
            baseResponse.setResult(ResultCodeEnum.TASK_QUERY_SUCCESS);
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
        // 调用python脚本获取节点的分类号
        return baseResponse;
    }

    public BaseResponse getSimilarity(String taskId, String nodeId1, String nodeId2) {
        BaseResponse baseResponse = new BaseResponse();
        // 调用python脚本获取进行链接预测的两个节点的相似度
        return baseResponse;
    }
}
