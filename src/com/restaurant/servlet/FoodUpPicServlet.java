package com.restaurant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.restaurant.dao.PictureDAO;
import com.restaurant.entity.BackMsg;
import com.restaurant.entity.Picture;

public class FoodUpPicServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		SmartUpload smt = new SmartUpload();
		smt.initialize(getServletConfig(), request, response);

		BackMsg msg = new BackMsg(-1, "", null);

		try {
			smt.setAllowedFilesList("gif,jpg,jpeg,png");
			smt.upload();
		} catch (Exception e) {
			msg.setMsg("格式不支持");
			JSONObject jsobj = JSONObject.fromObject(msg);
			out.println(jsobj.toString());
			out.close();
			return;
		}

		Files fs = smt.getFiles();
		File f = fs.getFile(0);

		String path = getServletContext().getRealPath("front-end/upload");
		String fn = f.getFileName();
		String ext = f.getFileExt();

		fn = fn.replaceAll("." + ext, new Date().getTime() + "");
		fn += "." + ext;

		try {
			f.saveAs(path + "/" + fn);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Picture pic = new Picture();
		pic.setPname(fn);
		pic.setPpath("upload/" + fn);

		PictureDAO dao = new PictureDAO();
		dao.addPic(pic);
		Picture obj = dao.getPicByPname(fn);

		msg.setState(200);
		msg.setMsg("上传成功");
		msg.setObj(obj);
		JSONObject jsobj = JSONObject.fromObject(msg);
		out.println(jsobj.toString());

		out.close();

	}

}
