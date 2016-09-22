package com.log.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.log.spring.entity.Result;
import com.log.spring.entity.User;
import com.log.spring.service.ServiceLog;
import com.log.spring.serviceImpl.ServiceLogImpl;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Autowired
	private ServiceLogImpl serviceImpl;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest r) {

		try {
			String name=r.getParameter("name");
			if(name==null)
				name="";
			model.addAttribute("listLog", serviceImpl.selectAll(name));
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "home";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	
	public @ResponseBody
	Result  register(@RequestBody String json) {
Result r = new Result();
boolean result=false;
		try {
			JSONObject jsonObject = new JSONObject(json);
		
			result= serviceImpl.register(jsonObject.getString("mobile"),
					jsonObject.getString("password"),
					jsonObject.getString("name"),
					jsonObject.getString("address"));
		} catch (Exception ex) {
			ex.printStackTrace();
			result=false;
		}
		r.setSuccess(result);
		return r;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	User login(@RequestBody String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);
			User u = new User();
			u = serviceImpl.login(jsonObject.getString("mobile"),
					jsonObject.getString("password"));
			return u;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/sendData", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Result senDat(@RequestBody String json) {

		Result r = new Result();
		boolean result=false;

		try {
			JSONObject jsonObject = new JSONObject(json);
			// User u= new User();
			result= serviceImpl.sendData(jsonObject.getString("name"),
					jsonObject.getString("lon"), jsonObject.getString("lat"));
			// return u;
		} catch (Exception ex) {
			ex.printStackTrace();
			result=false;
		}
		r.setSuccess(result);
		return r;
	}
}
