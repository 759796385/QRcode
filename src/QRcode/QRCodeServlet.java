package QRcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QRCodeServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8"); 
		 String qrtext =request.getParameter("qrtext");
		 ByteArrayOutputStream out = new MatrixToImageWriter(qrtext).getByteArrayOutputStream();
		 response.setContentType("image/png");
		 response.setContentLength(out.size());
		 
		 OutputStream outStream = response.getOutputStream();
		 outStream.write(out.toByteArray());
		 
	        outStream.flush();
	        outStream.close();
	}
	public void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
