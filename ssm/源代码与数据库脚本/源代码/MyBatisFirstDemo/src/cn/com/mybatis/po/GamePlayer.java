package cn.com.mybatis.po;

import java.util.Map;

public class GamePlayer {
  private int id;
  private String username;
  private String uGender;
  private int uLevel;
  private Map professionalAttributes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getuGender() {
		return uGender;
	}
	public void setuGender(String uGender) {
		this.uGender = uGender;
	}
	public int getuLevel() {
		return uLevel;
	}
	public void setuLevel(int uLevel) {
		this.uLevel = uLevel;
	}
	public Map getProfessionalAttributes() {
		return professionalAttributes;
	}
	public void setProfessionalAttributes(Map professionalAttributes) {
		this.professionalAttributes = professionalAttributes;
	}
	
  
}
