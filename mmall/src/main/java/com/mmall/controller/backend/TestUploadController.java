package com.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IFileService;
import com.mmall.service.IUserService;
import com.mmall.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/manage/test")
public class TestUploadController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IFileService iFileService;

    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpSession session, HttpServletRequest request,
                                 @RequestParam(value = "upload_file", required = false) MultipartFile file) {

        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("urii", targetFileName);
        fileMap.put("urll", url);
        return ServerResponse.createBySuccess(fileMap);
    }

    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "upload_file", required = false) MultipartFile file) {
        Map resultMap = Maps.newHashMap();

//        {"msg":"请登录管理员","success":false}
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if (user == null) {
//            resultMap.put("success", false);
//            resultMap.put("msg", "请登录管理员");
//            return resultMap;
//        }

        //富文本中对于返回值有自己的要求,我们使用是simditor所以按照simditor的要求进行返回，具体去网站里查
//        {
//            "success": true/false,
//                "msg": "error message", # optional
//            "file_path": "[real file path]"
//        }

        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        if (StringUtils.isBlank(targetFileName)) {
            resultMap.put("success", false);
            resultMap.put("msg", "上传失败");
            return resultMap;
        }
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        resultMap.put("success", true);
        resultMap.put("msg", "上传成功");
        resultMap.put("file_path", url);
//        {"msg":"上传成功","file_path":"http://img.happymmall.com/8646cd5d-1f7a-4702-86bf-15dd8aa6d457.jpg","success":true}
        response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
        return resultMap;
    }
}

