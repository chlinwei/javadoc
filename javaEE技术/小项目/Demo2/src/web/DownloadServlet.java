package web;

import entity.FileBean;
import service.FileBeanService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(name = "DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受需要下载的文件id
        String idStr = request.getParameter("id");
        FileBeanService service = new FileBeanService();
        if(idStr!=null&&!idStr.equals("")) {
            int id = Integer.parseInt(idStr);
            FileBean bean = service.findById(id);
            //文件名称
            String fileName = bean.getName();
            //文件所在路径
            String path = bean.getFile_path();
            System.out.println(path);
            System.out.println(fileName);
            //根据web下的资源获取资源的InputStream
            InputStream in = this.getServletContext().getResourceAsStream(path);
            fileName = URLEncoder.encode(fileName, "utf-8");//注意这一行的位置
            String userAgent = request.getHeader("user-agent");
            String content = "";
            if (userAgent.contains("Firefox")) {
                //firefox
                content = "attachment;filename*=" + fileName;
            } else {
                //chrome
                content = "attachment;filename=" + fileName;
            }
            response.setHeader("content-Disposition", content);
            //2)把文件发送给浏览器
            ServletOutputStream out = response.getOutputStream();
            //边读边写
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
    }
}
