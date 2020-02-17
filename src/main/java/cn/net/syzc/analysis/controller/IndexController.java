package cn.net.syzc.analysis.controller;

import cn.net.syzc.analysis.kit.BaseResponse;
import cn.net.syzc.analysis.kit.ResultCodeEnum;
import cn.net.syzc.analysis.model.Task;
import cn.net.syzc.analysis.model.User;
import cn.net.syzc.analysis.service.IndexService;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.upload.UploadFile;

import java.util.List;

@SuppressWarnings("unused")
public class IndexController extends Controller {
    private static IndexService indexService = new IndexService();

    public void index() {
        getSessionAttr("user_id");
    }

    public void login() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            String username = getPara("username");
            String password = getPara("password");
            if (!StrKit.isBlank(username) && !StrKit.isBlank(password)) {
                User user = indexService.login(username, password);
                if (user != null) {
                    if (user.getUserPassword().equals(password)) {
                        baseResponse.setResult(ResultCodeEnum.LOGIN_SUCCESS);
                        setSessionAttr("user_id", user.getUserID());
                    }
                } else {
                    baseResponse.setResult(ResultCodeEnum.NO_EXIST_USER);
                }
            } else {
                baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }

    public void logout() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            // clear the session
            removeSessionAttr("user_id");
            getSession().invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }

    /**
     *
     */
    public void getTaskList() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            Integer UserId = getSessionAttr("user_id");
            if (!StrKit.isBlank(UserId.toString())) {
                List<Task> taskList = indexService.getTaskList(UserId);
                baseResponse.setData(taskList);
                baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
            } else {
                baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }

    /**
     *
     */
    public void deleteTask() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            String taskId = getPara("taskId");
            if (!StrKit.isBlank(taskId)) {
                baseResponse = indexService.deleteTask(taskId);
            } else {
                baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }

    /**
     *
     */
    public void getTask() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            String taskId = getPara("taskId");
            if (!StrKit.isBlank(taskId)) {
                baseResponse = indexService.getTask(taskId);
            } else {
                baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }

    /**
     *
     */
    public void getClassification() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            String taskId = getPara("taskId");
            String nodeId = getPara("nodeId");

            if (!StrKit.isBlank(taskId) && !StrKit.isBlank(nodeId)) {
                baseResponse = indexService.getClassification(taskId, nodeId);
            } else {
                baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }

    /**
     *
     */
    public void getSimilarity() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            String taskId = getPara("taskId");
            String nodeId1 = getPara("nodeId1");
            String nodeId2 = getPara("nodeId2");

            if (!StrKit.isBlank(taskId) && !StrKit.isBlank(nodeId1) && !StrKit.isBlank(nodeId2)) {
                baseResponse = indexService.getSimilarity(taskId, nodeId1, nodeId2);
            } else {
                baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }

    /**
     *
     */
    public void addTask() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            List<UploadFile> uploadFiles = getFiles();
//            String u_id = getPara("u_id");
            String u_id = "1";
            String task_name = getPara("task-name");
            String algorithm_type = getPara("algorithm-type");

            if (!StrKit.isBlank(u_id) && !StrKit.isBlank(task_name) && !StrKit.isBlank(algorithm_type)) {
                baseResponse = indexService.addTask(u_id, task_name, algorithm_type, uploadFiles);
            } else {
                baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }
}
