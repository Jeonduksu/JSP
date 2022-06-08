package com.bike.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/bike.do")
public class BikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//command 값 꺼내오기
		String command = request.getParameter("command");
		System.out.println("[command:"+command+"]");
		
		if(command.equals("first")) {
			//화면전환
			response.sendRedirect("bike01.jsp");
		}else if(command.equals("first_db")) {
			String[] bikeList = request.getParameterValues("bike");
			
			List<BikeDto> dtos = new ArrayList<BikeDto>();
			for(int i=0; i<bikeList.length; i++) {
//				System.out.println(bikeList[i]);
				//강남구/2301/ 현대고등학교 건너편/서울특별시 강남구 압구정로 134/10/127.02179/37.524071
				//배열을 만들어서 /단위로 잘라서 배열에 담는다
				String[] tmp = bikeList[i].split("/");
				BikeDto dto = new BikeDto(tmp[0],
										Integer.parseInt(tmp[1]),
										tmp[2],
										tmp[3],
										Integer.parseInt(tmp[4]),
										Double.parseDouble(tmp[5]),
										Double.parseDouble(tmp[6]));
				dtos.add(dto);
			}
			int res = new BikeDao().insert(dtos);
			
			if(res == dtos.size()) {
				System.out.println("\ninsert 성공!\n");
				response.sendRedirect("index.html");
			}else {
				System.out.println("\ninsert 실패 \n");
				response.sendRedirect("bike01.jsp");
			}
			
			
		}else if(command.equals("second")) {
			response.sendRedirect("bike02.jsp");
			
		}else if(command.equals("second_db")) {
			
			String obj = request.getParameter("obj");
//			System.out.println(obj);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(obj);
			
			List<BikeDto> dtos = new ArrayList<BikeDto>();
			
			//for문을 통해 리스트에 담은거
			for(int i=0; i<element.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
//				System.out.println(element.getAsJsonObject().get("DATA").getAsJsonArray().get(i));
			JsonObject tmp = element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
//			{"new_addr":"서울특별시 강남구 압구정로 134","content_id":"2301","cradle_count":10,"longitude":127.02179,"content_nm":" 현대고등학교 건너편","latitude":37.524071,"addr_gu":"강남구"},
			
			//JsonElement 형식으로 담은거
			JsonElement addr_gu_je = tmp.get("addr_gu");
			JsonElement content_id_je = tmp.get("content_id");
			JsonElement content_nm_je = tmp.get("content_nm");
			JsonElement new_addr_je = tmp.get("new_addr");
			JsonElement cradle_count_je = tmp.get("cradle_count");
			JsonElement longitude_je = tmp.get("longitude");
			JsonElement latitube_je = tmp.get("latitude");
			
			//java 형식으로 바군거
			String addr_gu = addr_gu_je.getAsString();
			int content_id = content_id_je.getAsInt();
			String content_nm = content_nm_je.getAsString();
			String new_addr = new_addr_je.getAsString();
			int cradle_count = cradle_count_je.getAsInt();
			double longitude = longitude_je.getAsDouble();
			double latitube = latitube_je.getAsDouble();
			
			BikeDto dto = new BikeDto();
			dto.setAddr_gu(addr_gu);
			dto.setContent_id(content_id);
			dto.setContent_nm(content_nm);
			dto.setNew_addr(new_addr);
			dto.setCradle_count(cradle_count);
			dto.setLongitude(longitude);
			dto.setLatitude(latitube);
			
			dtos.add(dto);
			
			}
			
			int res = new BikeDao().insert(dtos);
			if(res>0) {
				System.out.println("성공!!!");
			}else {
				System.out.println("실패 ㅠ");
			}
			
			PrintWriter out = response.getWriter();
			out.print(res);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
