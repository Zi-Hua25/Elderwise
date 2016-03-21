<%-- 
    Document   : index
    Created on : Mar 4, 2016, 4:29:31 PM
    Author     : Terence
--%>

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
                
            if (session.getAttribute("loggedin") == null){
                AppController.bootstrap();
                Calendar date = Calendar.getInstance();
                date.set(2015, 9, 2, 0, 0, 0);
                
                readings = AppController.getOneDayReadingForElderly(date, "E001");
                out.println(readings.size() + "<br>");
                session.setAttribute("loggedin", true);
            } else {
                Calendar date = Calendar.getInstance();
                date.set(2015, 9, 2, 0, 0, 0);
                readings = AppController.getOneDayReadingForElderly(date, "E001");
                out.println(readings.size() + "<br>");
            }
        
        %> 
        <table width = 60%>
            <tr>
            <th>Row</th>
            <th>Sensor Id</th>
            <th>Date</th>
            <th>Door</th>
            <th>Living Room</th>
            <th>BedRoom</th>
            <th>Bed</th>
            <th>Bathroom</th>
            <th>Kitchen</th>
            </tr>
            <%
            int count = 0;
            for (SensorReading sr: readings){ 
                DateFormat format = new SimpleDateFormat("yyMMdd HH:mm:ss");%>
                <tr>
                    <td align="center"><%= count %></td>
                    <td align="center"><%= sr.getSensorId() %></td>
                    <td align="center"><%= format.format(sr.getDate().getTime()) %></td>
                    <td align="center"><%= sr.getDoorContact()%></td>
                    <td align="center"><%= sr.getLivingRoomPIR()%></td>
                    <td align="center"><%= sr.getBedRoomPIR()%></td>
                    <td align="center"><%= sr.getBed()%></td>
                    <td align="center"><%= sr.getBathroomPIR()%></td>
                    <td align="center"><%= sr.getKitchenPIR()%></td>
                </tr>
             <% count++;}
            %>
        </table>
        
        
    </body>
</html>
