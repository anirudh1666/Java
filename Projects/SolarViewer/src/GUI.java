import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

public class GUI extends JPanel implements ActionListener {

    private ArrayList<CelestialBody> planets = new ArrayList<>();
    Timer tm = new Timer(10, this);
    private final int centreX = 750;
    private final int centreY = 425;
    private double angle = 0;

    /* This function initialises all the planets in our solar system as
       CelestialBody objects. It then adds them to an ArrayList containing planets.
       Read the README.txt to see the quantities used for radius, distance from sun and
       orbital speed for each planet.
     */
    private void init_planets() {

        CelestialBody mercury = new CelestialBody("Mercury", 0.4, 47, "light gray", 2);
        planets.add(mercury);
        CelestialBody venus = new CelestialBody("Venus", 0.7, 35, "orange", 6);
        planets.add(venus);
        CelestialBody earth = new CelestialBody("Earth", 1, 30, "blue", 6);
        planets.add(earth);
        CelestialBody mars = new CelestialBody("Mars", 1.5, 24, "red", 3);
        planets.add(mars);
        CelestialBody jupiter = new CelestialBody("Jupiter", 5.2, 13, "orange", 70);
        planets.add(jupiter);
        CelestialBody saturn = new CelestialBody("Saturn", 9.5, 10, "yellow", 58);
        planets.add(saturn);
        CelestialBody uranus = new CelestialBody("Uranus", 19.8, 7, "cyan", 25);
        planets.add(uranus);
        CelestialBody neptune = new CelestialBody("Neptune", 30, 7, "blue", 26);
        planets.add(neptune);
        CelestialBody pluto = new CelestialBody("Pluto", 39.5, 5, "gray", 1);
        planets.add(pluto);
    }

    public void actionPerformed(ActionEvent e) {

        if (angle > 360) {
            angle = 0;
        }
        angle += 0.01;
        repaint();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(Color.yellow);
        g.fillOval(centreX, centreY, 12, 12);
        for (CelestialBody planet : planets) {
            String color = planet.get_color();
            double speed = planet.get_velocity();
            int radius_of_orbit = (int) (planet.get_dist());
            int radius_of_planet = (int) planet.get_radius();
            double planet_angle = angle * speed;
            int planetX = (int) ((centreX + 8) + (Math.cos(planet_angle) * radius_of_orbit));             // Shift centre by 6 down and right to give appearence of ellipitcal orbit
            int planetY = (int) ((centreY + 5) + (Math.sin(planet_angle) * radius_of_orbit));             // as well as to stop the sun from swallowing up mercury.
            if (color.equals("gray")) {
                g.setColor(Color.darkGray);
            }
            else if (color.equals("blue")) {
                g.setColor(Color.BLUE);
            }
            else if (color.equals("yellow")) {
                g.setColor(Color.yellow);
            }
            else if (color.equals("orange")) {
                g.setColor(Color.ORANGE);
            }
            else if (color.equals("red")) {
                g.setColor(Color.red);
            }
            else if (color.equals("cyan")) {
                g.setColor(Color.cyan);
            }
            else if (color.equals("light gray")) {
                g.setColor(Color.gray);
            }
            else {
                g.setColor(Color.orange);
            }
            g.fillOval(planetX, planetY, radius_of_planet, radius_of_planet);
        }
        tm.start();
    }

    /* Runs the animation of the planets moving in the solar system.
     */
    private void display() {

        this.init_planets();
        // Init the JFrame with dimensions width = 2000, height = 1000.
        JFrame jf = new JFrame();
        jf.setTitle("Solar System");
        jf.setSize(2000, 1000);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(this);
    }

    public static void main(String[] args) {
        GUI obj = new GUI();
        obj.display();
    }
}
