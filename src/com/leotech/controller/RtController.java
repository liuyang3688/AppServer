package com.leotech.controller;

import com.alibaba.fastjson.JSONObject;
import com.leotech.service.RtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("rt")
public class RtController {
	@RequestMapping("get_rt_data")
	public void getRtData(HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject datas = new JSONObject();

        String strBjlx = request.getParameter("bjlx");
        String strBjid = request.getParameter("bjid");

        // type 获取类型，type=0:取所有类型 type=1:取某一类型 type=2:取某一类型的某一ID
        int type = 0, bjlx=0, bjid=0;
        if (strBjlx == null || strBjlx.isEmpty()) {
            // 未指定不见类型，则取所有类型
            type = 0;
        } else if(strBjid == null || strBjid.isEmpty()) {
            // 指定类型，未指定ID
            bjlx = Integer.parseInt(strBjlx);
            type = 1;
        } else {
            // 指定类型，指定ID
            bjlx = Integer.parseInt(strBjlx);
            bjid = Integer.parseInt(strBjid);
            type = 2;
        }

        switch (type) {
            case (int)0:
                datas = RtService.getRtData();
                break;
            case (int)1:
                datas = RtService.getRtData(bjlx);
                break;
            case (int)2:
                datas = RtService.getRtData(bjlx, bjid);
                break;
            default:
                System.out.println("rt/get_rt_data: type out of range.");
                break;
        }

        try {
            response.getWriter().print(datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
