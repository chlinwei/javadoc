<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>文件上传</title>
</head>

<body>
<font color="red">${msg }</font>
<form action="${pageContext.request.contextPath }/upload" method="post" enctype="multipart/form-data" onsubmit="return checkFiles()">
    <table border="1">
        <tbody>
        <tr>
            <th>请选择文件</th>
            <td><input type="file" name="attachment"/><input type="text" name="info1"/><input type="button" value="删除" onclick="delItem(this)"/></td>
        </tr>
        </tbody>
        <tr>
            <td colspan="2"><input type="button" value="添加" onclick="addItem()"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="上传"/></td>
        </tr>
    </table>

</form>
</body>
<script type="text/javascript">
    var count = 1;
    function addItem(){
        /*
        <tr>
              <th>请选择文件</th>
              <td><input type="file" name="attachment"/><input type="button" value="删除"/></td>
          </tr>
        */
        //生成一个tr节点
        var tr = document.createElement("tr");
        //生成th
        var th = document.createElement("th");
        th.innerHTML = "请选择文件";
        //生成td
        var td = document.createElement("td");
        var fInput = document.createElement("input");
        fInput.setAttribute("type","file");
        fInput.setAttribute("name","attachment");
        var tInput = document.createElement("input");
        tInput.setAttribute("type","text");
        tInput.setAttribute("name","info");
        var dInput = document.createElement("input");
        dInput.setAttribute("type","button");
        dInput.setAttribute("value","删除");
        dInput.setAttribute("onclick","delItem(this)");

        td.appendChild(fInput);
        td.appendChild(tInput);
        td.appendChild(dInput);

        tr.appendChild(th);
        tr.appendChild(td);

        //把tr放入table的tbody中
        var tbody = document.getElementsByTagName("tbody")[0];
        tbody.appendChild(tr);

        count++;
    }

    function delItem(btn){
        if(count>1){
            //拿到tr节点,是button的祖父节点
            var tr = btn.parentNode.parentNode;
            var tbody = document.getElementsByTagName("tbody")[0];
            tbody.removeChild(tr);
            count--;
        }
    }


    //判断所有file是否已经选中，如果任何一个没有选择，则该函数返回false，这时表单不能提交
    function checkFiles(){
        var fileList = document.getElementsByName("attachment");
        for(var i=0;i<fileList.length;i++){
            //该file组件没有选文件
            if(fileList[i].value==null || fileList[i].value==""){
                alert("你的第"+(i+1)+"个文件没有选择！！！");
                return false;
            }
        }
        return true;
    }

</script>
</html>

