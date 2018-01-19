<%--
  Created by IntelliJ IDEA.
  User: shixuekai
  Date: 2017/12/29
  Time: 13:56
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
<head>
    <title>加班明细</title>
</head>
<body>
<input type="hidden" value="${misno}" id="hidMisno">
<table id="queryTb" style="width: 100%; height: 100%"></table>
<div id="toolbar" align="left">
    <table border="0" style="width:100%">
        <tr>
            <td align="left">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-back" plain="true" onclick="back()">返回列表</a>
            </td>
            <td align="left">
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    /* 初始化脚本 */
    $(document).ready(function () {
        searchData();
    });

    /* 处理主页面datagrid */
    $('#queryTb').datagrid({
        title : '数据列表',
        //fit : true,
        fixed : true,
        striped : true,
        loadMsg : "使劲加载中 ...",
        autoRowHeight : true,
        align : 'center',
        showFooter : true,
        remoteSort : false,
        singleSelect : false,
        fitColumns : false,
        pagination : true,
        pageNumber : 1,
        pageSize : 10,
        collapsible : true,
        pageList : [ 10, 15, 20, 25, 30, 40, 50, 100, 200, 300,
            500, 1000 ],
        toolbar: '#toolbar',
        columns: [[
            {field: 'name', title: '姓名', width: 150},
            {field: 'misno', title: 'mis号', width: 150},
            {field : 'occurtime',title : '加班日期',width: 150},
            {field : 'createtime',title : '创建时间',width: 150,formatter:formatDatebox},
            {field: 'remark', title: '加班事由', width: 200},
            {field : 'id',title : '操作',
                formatter : function(value, row, index) {
                    return '<a href="#" onclick="deleteInfo(\''
                        + value
                        + '\')"><span style="color:blue">删除</span></a>';
                }
            },
        ]]
    });

    /* 主页面 搜索 */
    function searchData() {
        $('#queryTb').datagrid({
            url: '<c:url value="/duty/getOverTimeList"/>',
            queryParams:{misno:$('#hidMisno').val()},
            method : 'post'
        });
    }

    /*删除*/
    function deleteInfo(id){
        if(confirm("您确定要删除该数据吗")){
            $.ajax({
                type: "POST",
                url: '<c:url value="/duty/deleteOverTime"/>',
                data: {id:id},
                success: function(msg){
                    alert(msg);
                    searchData();
                }
            });
        }

    }

    function back() {
        window.location.href="<%=path%>"+"/duty/index";
    }
</script>
</body>