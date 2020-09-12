import java.lang.Math;

/* This class is used whenever you are making an object that you want to add
   to the solar system. This class is responsible for calculating the dimensions
   and location of the object on the screen and holds the important information about the
   planet such as name, size etc.
 */
public class CelestialBody {

    private String name;
    private double dist_from_sun;
    private double orbital_velocity;
    private String color;
    private double radius;

    /* Constructor for CelestialBody object.
       @params = name : Name of planet.
                 dist_from_sun : distance from sun in units AU.
                 orbital_velocity : how quickly it revolves around the sun in units km/s.
                 color : color of planet.
                 radius : radius of planet in 10000 km units.
     */
    public CelestialBody(String name, double dist_from_sun, double orbital_velocity, String color, double radius) {

        this.name = name;
        this.dist_from_sun = dist_from_sun;
        this.orbital_velocity = orbital_velocity;
        this.color = color;
        this.radius = radius;
    }

    public String get_name() { return this.name; }

    public double get_raw_dist() { return this.dist_from_sun; }

    public double get_raw_velocity() { return this.orbital_velocity; }

    public String get_color() { return this.color; }

    public double get_raw_radius() { return this.radius; }

    /* This function calculates the distance from the center of the screen to the center
       of the planet. The screen has width 2000. The longest distance is 39.5 AU to pluto.
       Therefore 1000 = 40 AU and we calculate distance of planets accordingly to that scale.
       @returns = distance from center in units of screen.
     */
    public double get_dist() {

        return (this.dist_from_sun / 40) * 1000;
    }

    /* We scale down the

     */
    public double get_velocity() { return this.orbital_velocity / 10;}

    /* This function calculates the radius of this. The scale factor is by square rooting
       the actual distance. This is ideal since it shrinks the larger planets the most while
       keeping the smaller planets roughly the same size. I could use a linear scale factor,
       however, then the planets would be quite big and the distance between them would look
       small. The downside of square rooting is that the sizes aren't very accurate.
       @returns = radius in units of screen.
     */
    public double get_radius() {

        return Math.sqrt(this.radius * 2);
    }
}
