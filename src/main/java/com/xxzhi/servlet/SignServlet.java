package com.xxzhi.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxzhi.pojo.Reader;
import com.xxzhi.service.ReaderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author temuulen
 */
@WebServlet("/Sign")
public class SignServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int status=Integer.parseInt(req.getParameter("status"));
        if (status==1){
            Reader reader=new ReaderService().signIn(req.getParameter("readerId"),req.getParameter("password"));
            resp.getWriter().print(JSONObject.toJSONString(reader));
        }else if (status==0){
            resp.getWriter().print(new ReaderService().signUp(JSON.parseObject(java.net.URLDecoder.decode(req.getParameter("reader"),"UTF-8"),Reader.class)));
        }
    }
}