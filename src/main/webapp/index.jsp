<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();
%>

<html>
<head>
    <link href="resources/js/themes/default/easyui.css" rel="stylesheet" />
    <link href="resources/js/themes/icon.css" rel="stylesheet" />
    <link href="resources/css/style.css" rel="stylesheet" />
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/jquery.easyui.min.js"></script>
    <script src="resources/js/locale/easyui-lang-zh_CN.js"></script>
    <script src="resources/js/index/index.js"></script>


    <script language="JavaScript">
        $(document)
            .ready(
                function() {
                    //加载左侧菜单的图标
                    $(".tree-file").addClass("icon-treeicon");

                    $('.easyui-accordion li a').click(
                        function() {
                            var tabTitle = $(this).text();
                            var url = $(this).attr("rel");
                            addTab(tabTitle, url);
                            $('.easyui-accordion li div').removeClass(
                                "selected");
                            $(this).parent().addClass("selected");
                        }).hover(function() {
                        $(this).parent().addClass("hover");
                    }, function() {
                        $(this).parent().removeClass("hover");
                    });

                    function addTab(subtitle, url) {
                        if (!$('#tabs').tabs('exists', subtitle)) {
                            $('#tabs').tabs('add', {
                                title : subtitle,
                                content : createFrame(url),
                                closable : true,
                                width : $('#mainPanle').width() - 10,
                                height : $('#mainPanle').height() - 26
                            });
                        } else {
                            $('#tabs').tabs('select', subtitle);
                        }
                        tabClose();
                    }

                    function createFrame(url) {
                        var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
                            + url
                            + '" style="width:100%;height:100%;"></iframe>';
                        return s;
                    }

                    function tabClose() {
                        /*双击关闭TAB选项卡*/
                        $(".tabs-inner").dblclick(function() {
                            var subtitle = $(this).children("span").text();
                            $('#tabs').tabs('close', subtitle);
                        })

                        $(".tabs-inner").bind('contextmenu', function(e) {
                            $('#mm').menu('show', {
                                left : e.pageX,
                                top : e.pageY
                            });

                            var subtitle = $(this).children("span").text();
                            $('#mm').data("currtab", subtitle);

                            return false;
                        });
                    }
                    //绑定右键菜单事件
                    function tabCloseEven() {
                        //关闭当前
                        $('#mm-tabclose').click(function() {
                            var currtab_title = $('#mm').data("currtab");
                            $('#tabs').tabs('close', currtab_title);
                        })
                        //全部关闭
                        $('#mm-tabcloseall').click(function() {
                            $('.tabs-inner span').each(function(i, n) {
                                var t = $(n).text();
                                $('#tabs').tabs('close', t);
                            });
                        });
                        //关闭除当前之外的TAB
                        $('#mm-tabcloseother').click(function() {
                            var currtab_title = $('#mm').data("currtab");
                            $('.tabs-inner span').each(function(i, n) {
                                var t = $(n).text();
                                if (t != currtab_title)
                                    $('#tabs').tabs('close', t);
                            });
                        });
                        //关闭当前右侧的TAB
                        $('#mm-tabcloseright').click(function() {
                            var nextall = $('.tabs-selected').nextAll();
                            if (nextall.length == 0) {
                                //msgShow('系统提示','后边没有啦~~','error');
                                alert('后边没有啦~~');
                                return false;
                            }
                            nextall.each(function(i, n) {
                                var t = $('a:eq(0) span', $(n)).text();
                                $('#tabs').tabs('close', t);
                            });
                            return false;
                        });
                        //关闭当前左侧的TAB
                        $('#mm-tabcloseleft').click(function() {
                            var prevall = $('.tabs-selected').prevAll();
                            if (prevall.length == 0) {
                                alert('到头了，前边没有啦~~');
                                return false;
                            }
                            prevall.each(function(i, n) {
                                var t = $('a:eq(0) span', $(n)).text();
                                $('#tabs').tabs('close', t);
                            });
                            return false;
                        });

                        //退出
                        $("#mm-exit").click(function() {
                            $('#mm').menu('hide');
                        })
                    }

                });
    </script>
    <style>
        .footer {
            width: 100%;
            text-align: center;
            line-height: 35px;
        }

        .top-bg {
            background-color: #d8e4fe;
            height: 80px;
        }
    </style>
</head>
<body class="easyui-layout">
<div region="north" border="true" split="true"
     style="overflow: hidden; height: 80px;">
    <div class="top-bg">
        <div id="header_div">
            <div id="lenovo_logo">
                <img src="resources/images/login-logo.jpg" style="height: 80px;">
            </div>

        </div>
    </div>
</div>
<div region="south" border="true" split="true"
     style="overflow: hidden; height: 40px;">
    <div class="footer">
        不会了搜搜：<a href="http://www.baidu.com" target="black">百度一下</a>
    </div>
</div>
<div region="west" split="true" title="菜单" style="width: 200px;">
    <div id="aa" class="easyui-accordion"
         style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">

        <div title="假期统计" style="padding: 10px;">
            <ul class="easyui-tree">
                <li><span><a ref="1" href="#" rel="duty/index">数据列表</a></span></li>
                <li><span><a ref="1" href="#" rel="duty/addOverTime">填加班</a></span></li>
                <li><span><a ref="1" href="#" rel="duty/addVacation">填调休</a></span></li>
            </ul>
        </div>

        <%--<div title="系统管理" style="padding: 10px; display: none">--%>
            <%--<ul class="easyui-tree">--%>
                <%--<li><span><a ref="1" href="#" rel="monitoring">性能监控</a></span></li>--%>
                <%--<li><span><a ref="1" href="#" rel="scheduleJob">作业管理</a></span></li>--%>
            <%--</ul>--%>
        <%--</div>--%>

    </div>
</div>
<div id="mainPanle" region="center" style="overflow: hidden;">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home">
            <fieldset>
                <legend>Welcome</legend>
                <table class="form">
                    <tr>
                        <td>浏览器的建议为火狐或者谷歌浏览器
                        </td>
                    </tr>
                    <tr>
                        <td style="height: 10px"></td>
                    </tr>
                    <tr>
                        <td style="height: 10px"></td>
                    </tr>

                    <tr>
                        <td>本系统只供技术部门内部使用，没有考虑并发等情况</td>
                    </tr>
                    <tr>
                        <td style="height: 10px"></td>
                    </tr>
                    <tr>
                        <td>发现系统问题可以直接联系我，邮箱:shixuekai@meituan.com</td>
                    </tr>
                    <tr>
                        <td style="height: 10px"></td>
                    </tr>
                    <tr>
                        <td>希望本系统能够给各位同事带来一点便利，O(∩_∩)O~</td>
                    </tr>
                </table>


            </fieldset>
        </div>
    </div>
</div>

<!--鼠标右键-->
<div id="mm" class="easyui-menu" style="width: 150px;">
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseall">全部关闭</div>
    <div id="mm-tabcloseother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">当前页右侧全部关闭</div>
    <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-exit">退出</div>
</div>
</body>
</html>

