<%@ page import="java.util.ArrayList" %>
<%@ page import="ucl.ac.uk.main.Classes.*"
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Maze Game</title>
    <meta name="Maze Game" content="Maze game with levels">
    <meta name="Author" content="Anirudh Lakra">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles">
    <script src="maze.js"></script>
</head>
<body>
<div id="heading-box">
    <h1 id="header">Maze Game</h1>
</div>
<div id="maze">
    <canvas id="my-maze" width="800" height="800">Your browser does not support canvas.</canvas>
</div>
<%
    ArrayList<ArrayList<Cell>> maze = (ArrayList<ArrayList<Cell>>) request.getAttribute("maze");
    %>
    <script type="text/javascript" src="maze.js">
        var special_cells = new Array();
        var maze = new Array();
            for (var i = 0; i != row_num; ++i) {
                maze[i] = new Array();
            }

        <% for (int i = 0; i != maze.size(); ++i) {
            for (int j = 0; j != maze.get(0).size(); ++j) {
                Cell cell = maze.get(i).get(j);
                ArrayList<ArrayList<Integer>> edges = cell.get_edges();
                %>

                var row = <%= cell.get_row() %>;
                var col = <%= cell.get_col() %>;
                var start_or_end = false;
                var neighbours = new Array();

                <% for (int k = 0; k != edges.size(); ++k) {
                    %>
                    var neigh = [<%= edges.get(k).get(0) %>,<%= edges.get(k).get(1) %>];
                    neighbours[<%= k %>] = neigh;
                <% } %>

                var start_or_end = <%= cell.is_start_or_end %>;
                var cell = new Cell(row, col, neighbours, start_or_end);

                maze[<%= i %>][<%= j %>] = cell;

                if (start_or_end) {
                    special_cells.push([row, col]);
            <% }
        } %>

        draw_maze(maze, special_cells);
    </script>
</body>
</html>