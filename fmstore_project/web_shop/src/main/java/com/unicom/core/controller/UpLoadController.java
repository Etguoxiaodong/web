package com.unicom.core.controller;

import com.unicom.core.pojo.entity.ResponseResult;
import com.unicom.core.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UpLoadController {
	@Value("${FILE_SERVER_URL}")
	private String FILE_SERVER;

	@RequestMapping("/uploadFile")
	public ResponseResult uploadFile(MultipartFile file) throws Exception {
		try {
			FastDFSClient fastDFS = new FastDFSClient("classpath:fastDFS/fdfs_client.conf");
			//上传文件返回文件保存的路径和文件名
			String path = fastDFS.uploadFile(file.getBytes(), file.getOriginalFilename(), file.getSize());
			//拼接上服务器的地址返回给前端
			return new ResponseResult(true, FILE_SERVER + path);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseResult(false, "上传失败!");
		}
	}
	@RequestMapping("/delImg")
	public ResponseResult deleteFile(String url) throws Exception {
		String path  = url.substring(FILE_SERVER.length());
		FastDFSClient fastDFS = new FastDFSClient("classpath:fastDFS/fdfs_client.conf");
		Integer integer = fastDFS.delete_file(path);
		if (integer == 0){
			return new ResponseResult(true, "删除成功");
		}else {
			return new ResponseResult(true, "删除失败");
		}
	}
	@RequestMapping("/uploadImage")
	public Map uploadImage(MultipartFile upfile) throws Exception {
		try {
			FastDFSClient fastDFS = new FastDFSClient("classpath:fastDFS/fdfs_client.conf");
			//上传文件返回文件保存的路径和文件名
			String path = fastDFS.uploadFile(upfile.getBytes(), upfile.getOriginalFilename(), upfile.getSize());
			//拼接上服务器的地址返回给前端
			String url  = FILE_SERVER + path;
			Map<String ,Object > result = new HashMap<>();
			result.put("state","SUCCESS");
			result.put("url",url);
			result.put("title",upfile.getOriginalFilename());
			result.put("original",upfile.getOriginalFilename());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
