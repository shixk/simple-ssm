package com.imooc.vat.util;

import com.sankuai.mall.common.dx.DXPushUtil;

/**
 * @Author shixuekai
 * @CreateDate 2018/1/2
 * @Description
 **/
public class NotifyTool {

    public static void DXAlertMsg(String msg,String misno){
        DXPushUtil.push(msg,misno);
    }
}
