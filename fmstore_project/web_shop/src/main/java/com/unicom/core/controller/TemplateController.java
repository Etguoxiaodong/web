package com.unicom.core.controller;

import com.unicom.core.pojo.template.TypeTemplate;
import com.unicom.core.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/temp")
public class TemplateController {
	@Autowired
	private TemplateService templateService;
	@RequestMapping("/findOne")
	public TypeTemplate findOne(Long id){

		return templateService.findOneTemplate(id);
	}
    @RequestMapping("/findBySpecList")
	public List<Map> findBySpecList(Long id){
		List<Map> list = templateService.findBySpecList(id);
		return list;
    }
}
