


// add controller class
package com.nymisha;
import package *;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddController 
{
@RequestMapping("/add")
public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
{
		int i= Integer.parseInt(request.getParameter("t1"));
		int j= Integer.parseInt(request.getParameter("t2"));
		int k=i+j;
		ModelAndView mv= new ModelAndView();
		mv.setViewName("display.jsp");
		mv.addObject("result",k);
	return mv;
	

}
}


// index.jp form page
<html>
<body>
<form action="add">
<input type="text" name= "t1"><br>
<input type="text" name= "t2"><br>
<input type="submit" >
</form>
</body>
</html>




// display.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  Result is : <%=request.getAttribute("result") %> // 

</body>
</html>