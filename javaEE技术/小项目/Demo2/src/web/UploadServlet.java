package web;

import entity.FileBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.FileBeanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UploadServlet")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory(10*1024,new File("F/tmp"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            //设置文件名称编码
            upload.setHeaderEncoding("utf-8");
            //限制文件大小
            upload.setFileSizeMax(100*1024); //每个文件
            upload.setSizeMax(500*1024);//总文件


            //解析
            List<FileItem> fileItems = upload.parseRequest(request);
            FileBeanService service = new FileBeanService();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            ArrayList<FileBean> fileList = new ArrayList<>();
            if(fileItems != null){
                FileBean bean = null;
                for(FileItem item : fileItems){
                    if(item.isFormField()){
                        //普通元素表单
                        bean.setInfo(item.getString("utf-8"));
                        fileList.add(bean);
                        //把文件内容相关信息保存到数据库中
                        service.saveFile(bean);
                    }else {
                        bean = new FileBean();
                        //文件元素表单
                        //1)把文件保存到服务器硬盘中
                        String uuid = UUID.randomUUID().toString();
                        String fileName = item.getName();
                        //随机文件名称
                        fileName = uuid + fileName.substring(fileName.lastIndexOf("."));
                        //2)构件目录结构
                        //得到web应用中的某个目录的绝对路径
                        String baseDir = this.getServletContext().getRealPath("/upload");
                        String subDir = makeDirectory(fileName);
                        String finalDir = baseDir + "/" + subDir;
                        File dir = new File(finalDir);
                        if(!dir.exists()){
                            dir.mkdirs();
                        }
                        //把文件复制到tomcat的app目录下
                        item.write(new File(finalDir,fileName));
                        item.delete();

                        //计算文件大小
                        long size = item.getSize();
                        String sizeStr = "";
                        if (size >= 1024 && size < 1024 * 1024) {
                            sizeStr = (size / 1024.0) + "KB";
                        } else if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {
                            sizeStr = (size / (1024 * 1024.0)) + "MB";
                        } else if (size >= 1024 * 1024 * 1024) {
                            sizeStr = (size / (1024 * 1024.0 * 1024)) + "GB";
                        } else {
                            sizeStr = size + "B";
                        }
                        //2)把文件信息保存到数据库中
                        bean.setName(fileName);
                        bean.setSize(sizeStr);
                        bean.setType(item.getContentType());
                        bean.setAddTime(sdf.format(new Date()));
                        bean.setFile_path("/upload/" + subDir + fileName);
                    }
                }
                //把完成的文件信息转发到jsp页面显示
                request.setAttribute("fileList",fileList);
                request.getRequestDispatcher("/success.jsp").forward(request,response);
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e){//每个文件限制
            request.setAttribute("msg","每个文件不能超过100KB");
            request.getRequestDispatcher("/upload.jsp").forward(request,response);
        }catch (FileUploadBase.SizeLimitExceededException e){//总文件限制
            request.setAttribute("msg","总文件不能超过500KB");
            request.getRequestDispatcher("/upload.jsp").forward(request,response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String makeDirectory(String fileName){
        //1.根据文件名称返回hashCode值
        int code = fileName.hashCode();

        //2.算出第一层目录的名称
        //这个first值的范围在0~15之间
        int first = code&0xF;

        //3.算出第二层目录的名称
        //0xF>>1的结果是7,即second值的范围是0~7
        int second = code & (0xF>>1);
        return first + "/" + second + "/";
    }
}
