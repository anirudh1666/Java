<%@ page import = "java.util.List" %>
<%@ page contentType = "text/html;charset=UTF-8" language = "java" %>

<html>
<head>
    <jsp:include page = "/meta.jsp"/>
    <title> Patient Data App </title>
</head>
<body>
<jsp:include page = "/header.jsp"/>
<div class = "main">
    <h1>Patient Data</h1>
    <%
        List<String> data = (List<String>) request.getAttribute("Data");
        List<String> columns = (List<String>) request.getAttribute("columns");
        if (data.size() != 0) {
        %>
        <ul>
            <%
                for (int i = 0; i < columns.size(); i++) {
            %>
            <li><%=columns.get(i) + " = " + data.get(i)%></li>
            <%  }
            } else {%>
                <p> Nothing found </p>
            <%}%>
        </ul>
</div>
<jsp:include page = "/footer.jsp"/>
</body>
</html>