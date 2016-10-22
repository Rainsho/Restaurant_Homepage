package com.restaurant.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.restaurant.dao.PictureDAO;
import com.restaurant.entity.Picture;

public class PicUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		SmartUpload smt = new SmartUpload();
		smt.initialize(getServletConfig(), request, response);

		try {
			smt.setAllowedFilesList("gif,jpg,jpeg,png");
			smt.upload();
		} catch (Exception e) {
			request.getSession().setAttribute("pic_upload_msg", "格式不支持");
			response.sendRedirect("back-end/Picture/pic.jsp");
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

		request.getSession().setAttribute("pic_upload_msg", "上传成功");
		response.sendRedirect("PicAllServlet");

	}

}
