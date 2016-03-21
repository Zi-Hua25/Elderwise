<%-- 
    Document   : index
    Created on : Mar 4, 2016, 4:29:31 PM
    Author     : Terence
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Hashtable"%>
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

            if (session.getAttribute("loggedin") == null) {
                AppController.bootstrap();
                session.setAttribute("loggedin", true);
            }

        %> 
        <form method="POST" action="SensorReadingServlet">

            <table>
                <tr>
                    <td>
                        Elderly:
                        <select name="elderlyDropDown">
                            <%  Hashtable<String, Hashtable<String, ArrayList<SensorReading>>> allReadings = AppController.getAllSensorReadings();
                                ArrayList<String> keys = new ArrayList<String>();
                                for (String elderly : allReadings.keySet()) {
                                    keys.add(elderly);
                                }
                                String elderlySelected = "";
                                if (request.getParameter("selectElderly") == null) {
                                    //request.set("selectElderly", keys.get(0));
                                    elderlySelected = keys.get(0);
                                } else {
                                    elderlySelected = "" + request.getParameter("selectElderly");
                                }
                                for (String key : keys) {
                                    if (key.equals(elderlySelected)) {%>
                            <option selected value="<%= key%>"><%= key%></option>
                            <%} else {%>
                            <option value="<%= key%>"><%= key%></option>
                            <%}
                                }
                            %>      
                        </select>
                        <%
                            Hashtable<String, ArrayList<SensorReading>> elderlyReadings = allReadings.get(elderlySelected);
                        %>
                        Date (yyMMdd):
                        <select name="dateDropDown">
                            <%
                                SimpleDateFormat formatDate = new SimpleDateFormat("yyMMdd");
                                ArrayList<Date> keyDates = new ArrayList<Date>();
                                for (String dates : elderlyReadings.keySet()) {
                                    keyDates.add(formatDate.parse(dates));
                                }

                                Collections.sort(keyDates, new Comparator<Date>() {
                                    @Override
                                    public int compare(Date o1, Date o2) {
                                        return o1.compareTo(o2);
                                    }
                                });
                                String dateSelected = "";
                                if (request.getParameter("selectDate") == null) {
                                    dateSelected = formatDate.format(keyDates.get(0));
                                } else {
                                    dateSelected = "" + request.getParameter("selectDate");
                                }
                                for (Date key : keyDates) {
                                    String strDate = formatDate.format(key);
                                    if (strDate.equals(dateSelected)) {%>
                            <option selected value="<%= strDate%>"><%= strDate%></option>
                            <%} else {%>
                            <option value="<%= strDate%>"><%= strDate%></option>
                            <%}
                                }
                            %>      
                        </select>
                    </td>
                    <td>
                        <input type="Submit" value="Go!"></input>
                    </td>
                </tr>
            </table>
        </form>
        <font color="red" size="4"><b>Displaying elderly <%= elderlySelected%> data on <%= dateSelected%></b></font><br>


        <%
            Calendar date = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            date.setTime(dateFormat.parse(dateSelected));

            readings = AppController.getOneDayReadingForElderly(date, elderlySelected);
            Hashtable<String, ArrayList<Calendar[]>> testing = AppController.interpretReadings(date, elderlySelected);
            String testOut = "";
            for (String s : testing.keySet()) {
                if (s.contains("_")) {
                    testOut = s;
                }
            }%>
        <table width = 100%>        
            <tr>
                <th>Row</th>

                <th>Date</th>
                <th>Door</th>
                <th>Living Room</th>
                <th>BedRoom</th>
                <th>Bed</th>
                <th>Bathroom</th>
                <th>Kitchen</th>
                <td style="padding: 40px;color: blue;" width=60% valign="top" rowspan="<%= readings.size() + 1%>">
                    <%=testOut%></td>

            </tr>    
            <%  int count = 0;
                for (SensorReading sr : readings) {
                    DateFormat format = new SimpleDateFormat("yyMMdd HH:mm:ss");%>
            <tr>
                <td align="center"><%= count%></td>

                <td align="center"><%= format.format(sr.getDate().getTime())%></td>
                <td align="center"><%= sr.getDoorContact()%></td>
                <td align="center"><%= sr.getLivingRoomPIR()%></td>
                <td align="center"><%= sr.getBedRoomPIR()%></td>
                <td align="center"><%= sr.getBed()%></td>
                <td align="center"><%= sr.getBathroomPIR()%></td>
                <td align="center"><%= sr.getKitchenPIR()%></td>

            </tr>
            <% count++;
                }
            %>
        </table>


    </body>
</html>
