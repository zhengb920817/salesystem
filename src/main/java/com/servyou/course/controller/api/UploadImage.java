package com.servyou.course.controller.api;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.servyou.course.meta.Product;
import com.servyou.course.utils.ResponseCode;

@Controller
@RequestMapping("/api")
public class UploadImage {
	
	public String getImageSaveFileName(final String originalName) {
		FastDateFormat dateFormat = FastDateFormat.getInstance("yyyyMMddHHmmss");
		String filePrefix = "image_" + dateFormat.format(new Date());
		String fileSuffix = originalName.substring(originalName.lastIndexOf('.'));
		String fileName = filePrefix + fileSuffix;
		return fileName;
	}

	private String getFileName(final String filePath) {
		return filePath.substring(filePath.lastIndexOf('/') + 1);
	}
	
	private boolean saveFile(MultipartFile file, final String saveDirectory,final String saveFileName) {
		try {
			file.transferTo(new File(saveDirectory, saveFileName));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping(value = "/upload", method = { RequestMethod.POST })
	@ResponseBody
	public ModelMap uploadImage(@RequestParam("file") MultipartFile file, ModelMap modelMap, HttpSession httpSession,
			HttpServletRequest httpRequest) {
		String contextRealPath = httpSession.getServletContext().getRealPath("image");
		String fileSaveName = "";
		if (httpSession.getAttribute("editingProduct") != null) {
			System.out.println("session中存在编辑商品");
			Product product = (Product) httpSession.getAttribute("editingProduct");
			fileSaveName = getFileName(product.getImage());
		} else {
			fileSaveName = getImageSaveFileName(file.getOriginalFilename());
			System.out.println("session中不存在编辑商品");
		}
		System.out.println("保存文件名：" + fileSaveName);
		if (saveFile(file, contextRealPath, fileSaveName)) {
			modelMap.addAttribute("code", ResponseCode.REQUST_SUCCESS);
			modelMap.addAttribute("message", "上传成功");
			modelMap.addAttribute("result", "/image/" + fileSaveName);
		}
		else
		{
			modelMap.addAttribute("code", ResponseCode.REQUEST_FAIL);
			modelMap.addAttribute("message", "上传失败");
		}
		httpSession.removeAttribute("editingProduct");
		return modelMap;
	}
}
