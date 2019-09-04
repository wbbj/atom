package wbb.ssm.web;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wbb.ssm.Utils.Base64Util;
import wbb.ssm.Utils.FileUtil;
import wbb.ssm.Utils.HttpUtil;
import wbb.ssm.entity.Garbage;
import wbb.ssm.service.GarbageService;

@RestController
public class GarbageController extends HttpServlet {
    @Autowired
    private GarbageService garbageService;

    @RequestMapping({"/getGarbageByGname"})
    public void getGarbageByGname(HttpServletRequest request, HttpServletResponse response, Garbage garbage) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        StringBuilder gname = new StringBuilder();
        String json = AdvancedGeneral(garbage);
        String name = request.getParameter("gname");
        int num;
        int i;
        //图片
        if (json != null) {
            JSONObject object = new JSONObject(json);
            num = (Integer)object.get("result_num");
            if (num == 0) {
                response.getWriter().write("-1");
            } else {
                i = 0;
                JSONArray array = object.getJSONArray("result");
                StringBuilder imgname = new StringBuilder();
                StringBuilder sb = new StringBuilder();

                for(int l = 0; l < num; ++l) {
                    String iname = array.getJSONObject(l).getString("keyword");
                    for(i = 0; i < iname.split("").length; ++i) {
                        imgname.append("%").append(iname.split("")[i]);
                    }

                    imgname.append("%");
                    if (garbageService.getGarbageByGname(imgname.toString()) != null && !garbageService.getGarbageByGname(imgname.toString()).isEmpty()) {
                        for(i = 0; i < garbageService.getGarbageByGname(imgname.toString()).size(); ++i) {
                            sb.append(garbageService.getGarbageByGname(imgname.toString()).get(i).getGname()).append(" ");
                            sb.append(garbageService.getGarbageByGname(imgname.toString()).get(i).getVariety()).append(" ");
                            sb.append(garbageService.getGarbageByGname(imgname.toString()).get(i).getDescribe()).append(" ");
                            sb.append(garbageService.getGarbageByGname(imgname.toString()).get(i).getHandle()).append(" ");
                        }
                    } else {
                        ++i;
                    }
                }

                if (i != num) {
                    System.out.println(sb.toString());
                    response.getWriter().write(sb.toString());
                } else {
                    response.getWriter().write("-1");
                }
            }
        }

        //文本

        if (name != null) {
            for(num = 0; num < name.split("").length; num++) {
                gname.append("%").append(name.split("")[num]);
            }
            gname.append("%");
            if (garbageService.getGarbageByGname(gname.toString()) != null && !garbageService.getGarbageByGname(gname.toString()).isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (i = 0; i < garbageService.getGarbageByGname(gname.toString()).size(); i++) {

                    sb.append(garbageService.getGarbageByGname(gname.toString()).get(i).getGname()).append(" ");
                    sb.append(garbageService.getGarbageByGname(gname.toString()).get(i).getVariety()).append(" ");
                    sb.append(garbageService.getGarbageByGname(gname.toString()).get(i).getDescribe()).append(" ");
                    sb.append(garbageService.getGarbageByGname(gname.toString()).get(i).getHandle()).append(" ");

                }

                System.out.println(sb.toString());
                response.getWriter().write(sb.toString());
            } else {
                response.getWriter().write("-1");
            }
        }

        if (json == null && name == null) {
            response.getWriter().write("0");
        }

    }

    @RequestMapping({"/queryGarbage"})
    public void queryGarbage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("searchname");
        StringBuilder gname = new StringBuilder();

        for(int i = 0; i < name.split("").length; ++i) {
            gname.append("%").append(name.split("")[i]);
        }

        gname.append("%");
        System.out.println(garbageService.queryGarbage(gname.toString()));
        response.getWriter().write(garbageService.queryGarbage(gname.toString()));
    }

    @RequestMapping({"/createGarbage"})
    public void createGarbage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String gname = request.getParameter("gname");
        String variety = request.getParameter("variety");
        String describe = request.getParameter("describe");
        String handle = request.getParameter("handle");
        response.getWriter().write(garbageService.createGarbage(gname, variety, describe, handle));
    }

    private static String upload(Garbage garbage) throws Exception {
        String localPath = "/home/arthas/Downloads/";
        String filename = null;
        if (!garbage.getFile().isEmpty()) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String contentType = garbage.getFile().getContentType();
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            filename = uuid + "." + suffixName;
            System.out.println(filename);
            garbage.getFile().transferTo(new File(localPath + filename));
            return localPath + filename;
        } else {
            return null;
        }
    }

//    @RequestMapping({"/up"})
    private static String AdvancedGeneral(Garbage garbage) {
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";

        try {
            String filePath = upload(garbage);
            if (filePath != null) {
                byte[] imgData = FileUtil.readFileByBytes(filePath);
                String imgStr = Base64Util.encode(imgData);
                String imgParam = URLEncoder.encode(imgStr, "UTF-8");
                String param = "image=" + imgParam;
                String accessToken = "24.d2f0a3170ee153119b9ea58fd5469b86.2592000.1568000807.282335-16978389";
                String result = HttpUtil.post(url, accessToken, param);
                System.out.println(result);
                return result;
            } else {
                return null;
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }
}