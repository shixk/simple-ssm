<%--
  Created by IntelliJ IDEA.
  User: shixuekai
  Date: 2017/12/28
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:import url="/WEB-INF/view/common.jsp" />
<body>
<div id="searchHistory" class="easyui-panel" title="填写加班信息"
     style="width: 100%; background: #fafafa;"
     data-options="closable:false,collapsible:true,minimizable:false,maximizable:false">
    <br />
    <form id="formQueryInvoiceNeededToTms" method="post">
        <table width="800px" class="searchTable" border="0">
            <tr>
                <td width="50px"  valign="top" rowspan="5">
                </td>
                <td align="right"><label for="name">姓名:</label> <input
                        class="easyui-textbox" id="name" name="name" />
                </td>
                <td width="400px">&nbsp;</td>
            </tr>
            <tr>

                <td align="right"><label for="misno">mis号:</label> <input
                        class="easyui-textbox" id="misno" name="misno" />
                </td>
                <td width="400px">&nbsp;</td>
            </tr>
            <tr>
                <td align="right"><label for="occurTime">加班日期:</label> <input
                        class="easyui-datetimebox"  id="occurTime" name="occurTime" />
                </td>
                <td width="400px">&nbsp;</td>
            </tr>
            <tr>
                <td align="right"><label for="remark">加班事由:</label> <input
                        class="easyui-textbox" id="remark" name="remark" data-options="multiline:true" style="height:60px" />
                </td>
                <td width="400px">&nbsp;</td>
            </tr>
            <tr>
                <td align="right">
                    <a href="#"
                       class="easyui-linkbutton"
                       data-options="iconCls:'icon-ok'"
                       onclick="ok()">确定</a>
                    <a href="#"
                       class="easyui-linkbutton"
                       data-options="iconCls:'icon-cancel'"
                       onclick="cancel()">取消</a>
                </td>
                <td width="400px">&nbsp;</td>
            </tr>

        </table>

    </form>
    <br />
</div>
</body>
<script type="text/javascript">

    function ok(){
        var name=$("#name").val();
        var misno=$("#misno").val();
        var occurtime=$("#occurTime").datetimebox('getValue');
        var remark=$("#remark").val();
        if(name==""){
            alert("姓名不能为空");
            return ;
        }
        if(misno==""){
            alert("mis号不能为空");
            return;
        }
        if(occurTime==""){
            alert("加班时间不能为空");
            return;
        }

        $.ajax({
            type: "POST",
            url: '<c:url value="/duty/insertOverRecord"/>',
            data: {name:name,misno:misno,occurtime:occurtime,remark:remark},
            success: function(msg){
                alert(msg);
                if(msg=="success"){
                    window.location.href="<%=path%>"+"/duty/index";
                }
            }
        });

    }

    function cancel() {
        window.location.href="<%=path%>"+"/duty/index";
    }
</script>