package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.util.VeDate;

@Controller
public class UploadAction {

	public static final String path = "C:\\temp\\house_keep\\";

	@RequestMapping(value = "/upload_image.action")
	public String upload(@RequestParam(value = "image", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) throws IOException {

		String fileName = file.getOriginalFilename();
		int i = fileName.lastIndexOf(".");
		String type = fileName.substring(i + 1);
		fileName = new Date().getTime() + "." + type;
		File targetFile = new File(path+"upfiles\\"+fileName);
		if (!targetFile.exists()) {
			targetFile.createNewFile();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("imageFileName", fileName);
		return "saveimage";
	}

	@RequestMapping(value = "/upload_files.action")
	public String files(@RequestParam(value = "image", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
		String path = request.getSession().getServletContext().getRealPath("/") + "upfiles/";
		String fileName = file.getOriginalFilename();
		int i = fileName.lastIndexOf(".");
		String name = String.valueOf(VeDate.getStringDatex());
		String type = fileName.substring(i + 1);
		fileName = name + "." + type;
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("imageFileName", fileName);
		return "savefile";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/showImg.action")
	public void update(String imgurl,HttpServletRequest request,
					   HttpServletResponse response) throws Exception {
		String filePath = path + imgurl;
		File file = new File(filePath);
		// 获取输出流
		OutputStream outputStream = response.getOutputStream();
		FileInputStream fileInputStream = new FileInputStream(file);
		// 读数据
		byte[] data = new byte[fileInputStream.available()];
		fileInputStream.read(data);
		fileInputStream.close();
		// 回写
		response.setContentType("image/jpeg");
		outputStream.write(data);
		outputStream.flush();
		outputStream.close();
	}
}
