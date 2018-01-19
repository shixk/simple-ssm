<%--
  Created by IntelliJ IDEA.
  User: shixuekai
  Date: 2017/12/28
  Time: 14:40
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
    <title>查询列表</title>
</head>
<body>
<div id="divInvoiceSearcher" id="searchHistory" class="easyui-panel" title="查询面板"
     style="width: 100%; background: #fafafa;"
     data-options="closable:false,collapsible:true,minimizable:false,maximizable:false">
    <br />
    <form id="formSeachInvoices" method="post">
        <table class="searchTableVAT" width="80%">
            <tr>
                <td class="compact" align="right"><label for="name">姓名</label>
                    <input class="easyui-textbox" id="name" name="name" /></td>
                <td><label for="misno">mis号</label> <input
                        class="easyui-textbox" id="misno" name="misno" /></td>
            </tr>
            <tr>
                <td class="compact" align="right"><label for="from">更新日期</label>
                    <input id="from" name="from" class="easyui-datetimebox" /></td>
                <td><label for="to">至</label> <input id="to" name="to"
                                                     class="easyui-datetimebox" /></td>
                <td><input type="hidden" id="hInvoiceNos" name="hInvoiceNos" />
                </td>
            </tr>
        </table>
        <div class="toolbar">
            <a href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-search'" onclick="searchData()">搜索</a>
            <a href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-reload'" onclick="searchReset()">重置</a>
        </div>
    </form>

</div>
<table id="invoiceGrid" style="width: 100%; height: 460px"></table>
<div id="toolbar" align="left">
    <table border="0" style="width:100%">
        <tr>
            <td align="left">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addOverDay()">填加班</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="addVacationDay()">填调休</a>
            </td>
            <td align="left">
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    /* 初始化脚本 */
    $(document).ready(function () {
        initializeDatebox();
        searchData();
    });

    /* 初始化datebox 中的时间 */
    function initializeDatebox() {
        var date = new Date();
        var endDate = date.format("yyyy-MM-dd hh:mm:ss");
        date.setDate(date.getDate() - 90);
        var startDate = date.format("yyyy-MM-dd hh:mm:ss");
        $('#from').datetimebox('setValue', startDate);
        $('#to').datetimebox('setValue', endDate);
    }


    /* 发票处理主页面datagrid */
    $('#invoiceGrid').datagrid({
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
            {field : 'overdays',title : '加班天数',width: 150,
                formatter : function(value, row, index) {
                    if(value==0){
                        return value+'天';
                    }
                    return '<a href="#" onclick="gotoOverTime(\''
                        + row.misno
                        + '\')"><span style="color:blue">'+value+'天'+'</span></a>';
                }
            },
            {field : 'vacationdays',title : '调休天数',width: 150,
                formatter : function(value, row, index) {
                    if(value==0){
                        return value+'天';
                    }
                    return '<a href="#" onclick="gotoVacationDetail(\''
                        + row.misno
                        + '\')"><span style="color:blue">'+value+'天'+'</span></a>';
                }
            },
            {field: 'balancedays', title: '剩余调休', width: 150,
                formatter : function(value, row, index) {
                    return value+'天';
                }
            },
            {field : 'id',title : '操作',
                formatter : function(value, row, index) {
                    return '<a href="#" onclick="deleteInfo(\''
                        + row.misno
                        + '\')"><span style="color:blue">删除</span></a>';
                }
            },
        ]]
    });

    /* 主页面 搜索 */
    function searchData() {
        $('#invoiceGrid').datagrid('unselectAll');
        var name=$('#name').textbox('getValue');
        var misno=$('#misno').textbox('getValue');
        var startDate = $('#from').datetimebox('getValue'); //ftdateStartDate 订单起始时间
        var endDate = $('#to').datetimebox('getValue'); //ftdateStartDate 订单截止时间
        $('#invoiceGrid').datagrid({
            url: '<c:url value="/duty/getDutyList"/>',
            queryParams:{name:name,misno:misno,startDate:startDate,endDate:endDate},
            method : 'post'
        });
    }

    /* 主页面 重置 */
    function searchReset() {
        //$('#formSeachInvoices').form('clear');
        //$('#invoiceGrid').datagrid('load', {}).datagrid('unselectAll');
        initializeDatebox();

        $("#name").textbox("setValue","");
        $("#misno").textbox("setValue","");
    }

    /*删除*/
    function deleteInfo(misno){
        if(confirm("您确定要删除该数据吗")){
            $.ajax({
                type: "POST",
                url: '<c:url value="/duty/deleteDuty"/>',
                data: {misno:misno},
                success: function(msg){
                    alert(msg);
                    searchData();
                }
            });
        }

    }

    function addOverDay(){
        window.location.href="<%=path%>"+"/duty/addOverTime";
    }
    function addVacationDay(){
        window.location.href="<%=path%>"+"/duty/addVacation";
    }

    function gotoOverTime(misno) {
        window.location.href="<%=path%>"+"/duty/OverTimeDetail?misno="+misno;
    }

    function gotoVacationDetail(misno) {
        window.location.href="<%=path%>"+"/duty/VacationDetail?misno="+misno;
    }
</script>
</body>
