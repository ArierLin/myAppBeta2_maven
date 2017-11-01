package com.jr.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	private static Logger log = LoggerFactory.getLogger(FileUtil.class);

	public static String uploadFile(HttpServletRequest request, MultipartFile file) {
		log.info("!!!!!!!!!!!!!!!!!!add");
		String newfilename = "";
		System.out.println("开始");
		// String path = Constants.SystemConstants.Local_upgradefile_path;
		String path = Constants.SysConstants.LOCAL_UPGRADEFILE_PATH;
		String contentRange = request.getHeader("Content-Range");

		File file3 = new File(path);
		// 如果文件夹不存在则创建
		if (!file3.exists() && !file3.isDirectory()) {
			file3.mkdir();
		}
		new File(path).mkdir();
		String fileName = file.getOriginalFilename();
		String str = ".tar.gz";
		log.info("!!!!!!!!!!!!!" + fileName);
		log.info("!!!!!!!!!!!!!" + path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存

		try {
			file.transferTo(targetFile);
			log.info("!!!!!!!!!!!!!" + path + fileName);
			String id = IdGenerator.generateRandomStr(8);
			File oldFile = new File(path + fileName);
			File newFile = new File(path + id + str);
			oldFile.renameTo(newFile);
			newfilename = id + str;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return newfilename;
	}

	public static String resumeUpload(MultipartFile file, String fileName) {
		String path = Constants.SysConstants.LOCAL_UPGRADEFILE_PATH;
		OutputStream os = null;
		InputStream inputStream = null;
		Boolean writeFlag = true; // 标志是否写入成功
		String newfilename = path + fileName;
		try {
			inputStream = file.getInputStream();
			// 2、保存到临时文件
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流保存到本地文件

			File tempFile = new File(path);
			if (!tempFile.exists()) {
				tempFile.mkdirs();
			}
			os = new FileOutputStream(path + fileName, true);

			// 开始读取
			while ((len = inputStream.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// String str=".tar.gz";
			// File oldFile = new File(path+fileName);
			// String id=IdGenerator.generateRandomStr(8);
			// File newFile = new File(path +id+str);
			//
			// oldFile.renameTo(newFile);
			// newfilename=id+str;
		} catch (IOException e) {
			e.printStackTrace();
			writeFlag = false;
		} catch (Exception e) {
			e.printStackTrace();
			writeFlag = false;
		} finally {
			// 完毕，关闭所有链接
			try {
				os.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return newfilename;
	}

	public static boolean deleteFile(String filename) {
		String path = Constants.SysConstants.LOCAL_UPGRADEFILE_PATH;
		log.info("---deleteFile--- filename=" + path + filename);
		if (filename != null && filename.length() > 0) {

			File file = new File(path + filename);
			if (file.exists()) {
				// logger.info("file.exists");
				file.delete();
			}

		}
		return true;

	}
}
