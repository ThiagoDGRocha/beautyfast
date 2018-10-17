package com.web.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "hello")
public class HelloWorldMB {

	private String message;
	
	@PostConstruct
	public void init() {
		System.out.println("Método init() executado na inicialização devido a anotação @PostConstruct");
	}

	public String getMessage() {
		return "Hello wolrd JSF! V5 2\n"+this.message;
	}

	public void setMessage(String message) {
		System.out.println(message);
		this.message = message;
	}
}
