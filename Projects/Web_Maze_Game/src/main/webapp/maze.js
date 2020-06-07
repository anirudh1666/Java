// TODO : MAKE IT SO THE NUMBER OF CELLS INCREASE EACH LEVEL.
// TODO : MAKE IT SO USER CAN CONTROL A SPRITE ON THE MAZE GRID. DO THIS FIRST.

var CANVAS_HEIGHT = 800;
var CANVAS_WIDTH = 800;

/* Constructor for Cell. Maze will be 2D array of cells.
   @params = row : row number
             col : column number
             edges : list of edges. Each edge has coordinates
                     of node the edge is to. E.g. [row, col].
             start_or_end : true if it is start or end node.
*/
function Cell(row, col, edges, start_or_end) {

    this.row = row;
    this.col = col;
    this.edges = edges;
    this.start_or_end = start_or_end;
}

/* This function draws the maze on the canvas. The first step
   is to calculate the cell width and height in pixels then draw
   it onto canvas and lastly color the start or end cells blue.
*/
function draw_maze(maze, special_cells) {

    var cell_dimensions = calc_dimensions(maze);
    render_maze(maze, cell_dimensions);
    color_special(special_cells, cell_dimensions);
}

/* This function calculates the dimensions of each individual cell.
*/
function calc_dimensions(maze) {

    var cell_height = Math.floor(CANVAS_HEIGHT / maze.length);
    var cell_width = Math.floor(CANVAS_WIDTH / maze[0].length);
    var cell_padding = cell_width / 10;

    return [cell_height, cell_width, cell_padding];
}

function render_maze(maze, cell_dimensions) {

    var canvas = document.getElementById("my-maze");
    var ctx = canvas.getContext("2d");
    var start = [50, 50];
    var line_width = cell_dimensions[1] / 4;

    ctx.beginPath();
    ctx.lineWidth = line_width;
    ctx.strokeStyle = "Red";

    for (var i = 0; i != maze.length; ++i) {
        // i = current row.
        for (var j = 0; j != maze[0].length; ++j) {
            // j = current column.

            var curr_cell = maze[i][j];

            if (!(curr_cell.edges === null)) {
                // Cell has a path to neighbouring cells.
                var x_coord = start[1] + (j * cell_dimensions[1]);
                var y_coord = start[0] + (i * cell_dimensions[0]);

                for (var k = 0; k != curr_cell.edges.length; ++k) {
                    // Loop through all edges.
                    var neigh_cell = curr_cell.edges[k];
                    var neigh_x = start[1] + (neigh_cell[1] * cell_dimensions[1]);
                    var neigh_y = start[0] + (neigh_cell[0] * cell_dimensions[0]);

                    // Move ctx to curr_cell coordinates.
                    ctx.moveTo(x_coord, y_coord);

                    // We move it like this so that the path is smooth and has no gaps.
                    if (neigh_x === x_coord && neigh_y > y_coord) {
                        // Moving vertically down.
                        ctx.lineTo(neigh_x, neigh_y + line_width/2);
                    }
                    else if (neigh_x === x_coord && neigh_y < y_coord) {
                        // Moving vertically up.
                        ctx.lineTo(neigh_x, neigh_y - line_width/2);
                    }
                    else if (neigh_y === y_coord && neigh_x > x_coord) {
                        // Moving horizontally to the right.
                        ctx.lineTo(neigh_x + line_width/2, neigh_y);
                    }
                    else {
                        // Moving horizontally to the left.
                        ctx.lineTo(neigh_x - line_width/2, neigh_y);
                    }

                    ctx.stroke();
                }
            }
        }
    }
}

function color_special(special_cells, cell_dimensions) {

    var canvas = document.getElementById("my-maze");
    var ctx = canvas.getContext("2d");
    var line_width = cell_dimensions[1] / 4;
    var start = special_cells[0];
    var end = special_cells[1];
    var start_point = [50, 50];

    var start_x = start_point[1] + (start[1] * cell_dimensions[1]);
    var start_y = start_point[0] + (start[0] * cell_dimensions[0]) - line_width/2;
    var end_x = start_point[1] + (end[1] * cell_dimensions[1]);
    var end_y = start_point[0] + (end[0] * cell_dimensions[0]) - line_width/2;

    alert("End_x: " + end_x);
    alert("End_y: " + end_y);

    ctx.beginPath();
    ctx.lineWidth = line_width;
    ctx.strokeStyle = "Blue";
    ctx.moveTo(start_x, start_y);
    ctx.lineTo(start_x, start_y + line_width);
    ctx.stroke();

    ctx.moveTo(end_x, end_y);
    ctx.lineTo(end_x, end_y + line_width);
    ctx.stroke();
} 
