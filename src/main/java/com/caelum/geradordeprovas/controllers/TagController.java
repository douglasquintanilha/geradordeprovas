package com.caelum.geradordeprovas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.caelum.geradordeprovas.DAO.TagDao;
import com.caelum.geradordeprovas.models.Tag;

@RestController
public class TagController {
	
	private TagDao tagDao;
	
	@Autowired
	public TagController(TagDao tagDao) {
		this.tagDao = tagDao;
	}


	@RequestMapping("json")
	@ResponseBody
	public List<Tag> listaTags(){
		return tagDao.list();
	}
}
