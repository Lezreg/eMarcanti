package com.marcanti.ecommerce.view.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "messageBean")
@SessionScoped
public class MessageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(MessageBean.class);
	
	
	private boolean error=false;
	
	private boolean passed=false;
	
	private String text="";
	
	
	public MessageBean() {
	}

	@PostConstruct
	public void init() {

	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
	
	public void info(String text) {
		this.error=false;
		this.passed=true;
		this.text = text;
	}	
	
	public void error(String text) {
		this.error=true;
		this.passed=false;		
		this.text = text;
	}	
	
	
	public void reset(){
		this.error=false;
		this.passed=false;
		this.text="";
	}

}
