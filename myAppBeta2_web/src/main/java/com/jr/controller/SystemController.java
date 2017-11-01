package com.jr.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jr.utils.Constants;
import com.jr.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@SuppressWarnings(value = "all")
@Controller
@RequestMapping(value = "/system")
public class SystemController {

	private static Logger log = LoggerFactory.getLogger(SystemController.class);

	/**
	 * 跳转到系统升级页面
	 * 
	 * @author jql
	 * @return
	 */
	@RequestMapping(value = "toSystemUpgradeManagementPage")
	public String toSystemUpgradeManagementPage() {
		log.debug("跳转到系统升级管理页面toSystemUpgradeManagementPage");

		return "systemUpgradeManagement";
	}

	/**
	 * 查询文件的断点
	 * 
	 * @author jql
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value = "getFileResumePoint", method = RequestMethod.POST)
	@ResponseBody
	public long getFileResumePoint(String fileName) {
		String path = Constants.SysConstants.LOCAL_UPGRADEFILE_PATH;
		String filePath = path + fileName;
		log.info("/查询文件的断点 " + filePath + "           *****************************");
		File file = new File(filePath);
		if (file.exists()) {
			return file.length(); // 返回文件长度，以字节为单位
		}
		return 0; // 不存在返回0
	}

	/**
	 * 上传文件
	 * 
	 * @author jql
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "progress")
	@ResponseBody
	public boolean uploadFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file) throws IOException {
		log.info("----------------上传文件-----------------MultipartFileName=" + file.getName());
		log.info("----------------文件大小-----------------size=" + file.getSize());
		String fileName = request.getParameter("fileName");
		response.setContentType("text/html");
		String newfilename = "";
		boolean flag = false;
		Boolean writeFlag = false;
		if (file.getSize() > 0) {
			// 文件上传的位置可以自定义
			// newfiename = FileUploadUtil.uploadFile(request,file);
			newfilename = FileUtil.resumeUpload(file, fileName);
			flag = true;
		}
		String result = "{\"flag\": \"" + flag + "\"}";
		if (flag == false) {
			// 删除文件
			FileUtil.deleteFile(fileName);
		}
		return flag;
	}

	
}
