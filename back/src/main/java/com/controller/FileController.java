package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.entity.EIException;
import com.service.ConfigService;
import com.utils.R;

/**
 * 该类作为上传文件映射表相关的控制器，主要负责处理文件的上传和下载操作，
 * 通过调用ConfigService等相关服务来实现部分业务逻辑，比如根据上传文件类型进行配置保存等操作。
 */
@RestController
@RequestMapping("file")
@SuppressWarnings({"unchecked", "rawtypes"})
public class FileController {

	@Autowired
	private ConfigService configService;

	/**
	 * 提取获取文件上传目录的公共逻辑到一个私有方法中，
	 * 如果classpath:static下的/upload目录不存在则创建它，并返回该目录对象。
	 *
	 * @return 文件上传目录对应的File对象
	 * @throws FileNotFoundException 如果获取classpath:static路径出现问题则抛出此异常
	 */
	private File getUploadDir() throws FileNotFoundException {
		File path = new File(ResourceUtils.getURL("classpath:static").getPath());
		if (!path.exists()) {
			path = new File("");
		}
		File upload = new File(path.getAbsolutePath(), "/upload/");
		if (!upload.exists()) {
			upload.mkdirs();
		}
		return upload;
	}

	/**
	 * 文件上传功能
	 * 接收上传的文件和文件类型参数，先验证文件是否为空，然后将文件保存到指定的上传目录下，
	 * 如果文件类型为指定值（这里是 "1"），还会根据上传的文件名将对应配置信息保存或更新到数据库中，
	 * 最后返回包含文件名的成功响应结果。
	 *
	 * @param file 要上传的文件，通过请求参数传入，使用MultipartFile类型接收
	 * @param type 文件类型参数，通过请求参数传入，用于判断是否进行相关配置保存操作，字符串类型
	 * @return 返回操作成功的响应结果，包含上传后的文件名信息，包装在R对象中
	 * @throws Exception 如果文件保存过程或者配置保存过程出现问题则抛出异常，比如文件传输失败、数据库操作异常等
	 */
	@RequestMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file, String type) throws Exception {
		if (file.isEmpty()) {
			throw new EIException("上传文件不能为空");
		}

		// 获取文件扩展名
		String fileExt = getFileExtension(file.getOriginalFilename());

		// 获取文件上传目录
		File uploadDir = getUploadDir();

		// 生成新的文件名，使用时间戳 + 文件扩展名的方式
		String fileName = new Date().getTime() + "." + fileExt;

		// 构建目标文件对象，即要保存上传文件的最终位置
		File dest = new File(uploadDir.getAbsolutePath() + "/" + fileName);

		// 将上传的文件传输到目标文件位置
		file.transferTo(dest);

		if (StringUtils.isNotBlank(type) && type.equals("1")) {
			ConfigEntity configEntity = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
			if (configEntity == null) {
				configEntity = new ConfigEntity();
				configEntity.setName("faceFile");
				configEntity.setValue(fileName);
			} else {
				configEntity.setValue(fileName);
			}
			configService.insertOrUpdate(configEntity);
		}

		return R.ok().put("file", fileName);
	}

	/**
	 * 从文件名中获取文件扩展名的私有辅助方法
	 *
	 * @param originalFilename 原始文件名，包含扩展名
	 * @return 文件的扩展名，如 "jpg"、"txt" 等
	 */
	private String getFileExtension(String originalFilename) {
		return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
	}

	/**
	 * 文件下载功能
	 * 根据传入的文件名尝试从文件上传目录中查找对应的文件，如果找到且满足一定条件（原代码中有权限相关注释逻辑，这里暂未完善），
	 * 则设置响应头信息，将文件内容以字节数组形式返回给客户端，若文件不存在或者出现其他IO异常则返回相应的错误响应。
	 *
	 * @param fileName 要下载的文件名，通过请求参数传入
	 * @return 返回包含文件内容字节数组的ResponseEntity对象，若成功则附带正确的响应头信息和状态码，若失败则返回相应的错误状态码
	 */
	@IgnoreAuth
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam String fileName) {
		try {
			File uploadDir = getUploadDir();
			File file = new File(uploadDir.getAbsolutePath() + "/" + fileName);
			if (file.exists()) {
				// 原代码此处有关于权限判断的逻辑，暂时未完善，可根据实际业务需求补充
				// if(!fileService.canRead(file, SessionManager.getSessionUser())){
				//     getResponse().sendError(403);
				// }

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("attachment", fileName);
				return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}