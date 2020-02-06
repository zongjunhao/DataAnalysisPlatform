package cn.net.syzc.analysis.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public void setUserID(java.lang.Integer UserID) {
		set("UserID", UserID);
	}
	
	public java.lang.Integer getUserID() {
		return getInt("UserID");
	}

	public void setUserName(java.lang.String UserName) {
		set("UserName", UserName);
	}
	
	public java.lang.String getUserName() {
		return getStr("UserName");
	}

	public void setUserPassword(java.lang.String UserPassword) {
		set("UserPassword", UserPassword);
	}
	
	public java.lang.String getUserPassword() {
		return getStr("UserPassword");
	}

}
