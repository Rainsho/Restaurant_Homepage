package com.restaurant.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");

		int width = 100;
		int height = 40;

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		// bgcolor
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		// verify code
		char[] list = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		list = new char[] { '1', '2' }; // easy mode
		String verifycode = "";
		Random rnd = new Random();
		for (int i = 0; i < 4; i++) {
			// prepare verify code
			String t = list[rnd.nextInt(list.length)] + "";
			verifycode += t;
			// draw code
			g.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd
					.nextInt(255)));
			g.setFont(new Font("Bauhaus 93", Font.ITALIC, 30));
			g.drawString(t, 10 + 20 * i, 30);
		}

		// random line and point
		for (int i = 0; i < 50; i++) {
			g.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd
					.nextInt(255)));
			g.fillOval(rnd.nextInt(width), rnd.nextInt(height), 2, 2);
		}
		for (int i = 0; i < 5; i++) {
			g.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd
					.nextInt(255)));
			g.drawLine(rnd.nextInt(width), rnd.nextInt(height),
					rnd.nextInt(width), rnd.nextInt(height));
		}

		request.getSession().setAttribute("verifycode", verifycode);
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpeg", out);

	}

}
