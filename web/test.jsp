<%-- 
    Document   : index
    Created on : Mar 4, 2016, 4:29:31 PM
    Author     : Terence
--%>

<%@page import="java.util.Hashtable"%>
<%@page import="elderwise.SensorInterpreter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="elderwise.AppController"%>
<%@page import="elderwise.SensorReading"%>
<%@page import="java.util.ArrayList"%>
<%@page import="elderwise.Elderwise"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<SensorReading> readings = new ArrayList<SensorReading>();
            Hashtable<String, ArrayList<Calendar[]>> testing = new Hashtable<String, ArrayList<Calendar[]>>();
            if (session.getAttribute("loggedin") == null){
                AppController.bootstrap();
                session.setAttribute("loggedin", true);
            }
            
            testing = AppController.interpretReadings();
            for (String s: testing.keySet()){
                if (s.contains("_")){
                    out.println(s);
                }
            }
        %>
        
        
        
    </body>
</html>
