package cn.com.mvc.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadControllerTest {
	
	@RequestMapping("toUploadPage")
    public String toUploadPage(Model model)throws Exception{
		return "/ImgUploadTest";
	}
	
	@RequestMapping("uploadImg")
    public String uploadImg(Model model,MultipartFile file)throws Exception{
		//上传的图片的原始名称
		String originalFilename=file.getOriginalFilename();
		String newFileName = null;
		//上传图片
		if(file!=null&&originalFilename!=null&&originalFilename.length()>0){
			//存储图片的物理路径
			String pic_path="G:\\upload\\";
			//新的图片名称（UUID的随机名称）
			newFileName=UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile=new File(pic_path+newFileName);
			//将内存中的数据写入磁盘
			file.transferTo(newFile);
		}
		
		//回显刚才上传好的图片名称
		model.addAttribute("image", newFileName); 
		//重回到上传页面
		return "/ImgUploadTest";
	}
}
