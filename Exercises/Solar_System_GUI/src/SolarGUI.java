import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SolarGUI extends JPanel implements ActionListener {
    private ArrayList<CelestialBody> planets = new ArrayList<>();
    Timer tm = new Timer(10, this);
    private final int centreX = 2000 / 3;
    private final int centreY = (900 / 2) - 75;
    private double angle = 0;


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(Color.yellow);
        g.fillOval(centreX, centreY, 50, 50);
        for (CelestialBody planet : planets) {
            String color = planet.getColor();
            double speed = planet.getOrbitalSpeed();
            int radiusOfOrbit = (int) (planet.getDistanceFromSun());
            int radiusOfPlanet = planet.getRadiusOfPlanet();
            double planetAngle = angle * speed;
            int planetX = (int) ((centreX + 20) + (Math.cos(planetAngle) * radiusOfOrbit));
            int planetY = (int) (centreY + (Math.sin(planetAngle) * radiusOfOrbit));
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
            g.fillOval(planetX, planetY, radiusOfPlanet, radiusOfPlanet);
        }
        tm.start();
    }

    private void initialisePlanets() {
        CelestialBody mercury = new CelestialBody("mercury", 6, 48, "light gray", 2440);
        planets.add(mercury);
        CelestialBody venus = new CelestialBody("venus", 11, 35, "orange", 6052);
        planets.add(venus);
        CelestialBody earth = new CelestialBody("earth", 15, 30,"blue", 6378);
        planets.add(earth);
        CelestialBody mars = new CelestialBody("mars", 23, 30, "red", 3396);
        planets.add(mars);
        CelestialBody jupiter = new CelestialBody("jupiter", 78, 13, "orange", 71492);
        planets.add(jupiter);
        CelestialBody saturn = new CelestialBody("saturn", 143, 10, "yellow", 60268);
        planets.add(saturn);
        CelestialBody uranus = new CelestialBody("uranus", 287, 7, "cyan", 25559);
        planets.add(uranus);
        CelestialBody neptune = new CelestialBody("neptune", 450, 5, "blue", 24764);
        planets.add(neptune);
        CelestialBody pluto = new CelestialBody("pluto", 590, 4, "gray", 1195);
        planets.add(pluto);
    }

    public void actionPerformed(ActionEvent e) {
        if (angle > 360) {
            angle = 0;
        }
        angle += 0.01;
        repaint();
    }

    private void displayMenu() {
        System.out.println("\n1) Add a new planet.");
        System.out.println("2) Remove a planet.");
        System.out.println("3) View solar system.");
        System.out.println("4) Quit");
    }

    private int intInput() {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        return input;
    }

    private String stringInput() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }

    private void run() {
        SolarGUI object = new SolarGUI();
        object.initialisePlanets();
        int option = -1;
        while (option != 4) {
            object.displayMenu();
            System.out.println(planets);
            System.out.print("Enter your choice here: ");
            option = object.intInput();
            switch (option) {
                case 1 : addCelestialBody(); continue;
                case 2 : removeCelestialBody(); continue;
                case 3 : viewSolarSystem(); continue;
            }
        }
    }

    private void viewSolarSystem() {
        SolarGUI object = new SolarGUI();
        object.initialisePlanets();
        JFrame jf = new JFrame();
        jf.setTitle("Solar System");
        jf.setSize(2000,1000);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(object);
    }

    private void addCelestialBody() {
        SolarGUI object = new SolarGUI();
        System.out.print("Enter the name of the planet you want to add: ");
        String name = stringInput();
        System.out.println("Choose a color from the following for your planet: Blue, Cyan, Red, Orange, Yellow, Gray, Dark gray. ");
        System.out.print("Enter your choice here: ");
        String color = stringInput().toLowerCase();
        System.out.print("Enter the distance of the planet from the sun in the form (<distance> x 10^7) km: ");
        int distance = intInput();
        System.out.print("Enter the radius of the planet in kilometers:  ");
        int radius = intInput();
        System.out.print("Enter the orbital speed of the planet in kilometers per second: ");
        int orbitalSpeed = intInput();
        CelestialBody planet = new CelestialBody(name, distance, orbitalSpeed, color, radius);
        this.planets.add(planet);
    }

    private void paintPlanets() {
        for (CelestialBody planet : planets) {
            CelestialBody planetIn = new CelestialBody(planet.getName(), planet.rawDistance(),planet.rawOrbitalSpeed(), planet.getColor(), planet.rawRadius());
            planets.add(planetIn);
        }
    }

    private void removeCelestialBody() {
        System.out.print("Enter the name of the planet you wish to remove: ");
        int found = 0;
        String name = stringInput();
        for (int i = 0; i < planets.size(); i++) {
            CelestialBody planet = planets.get(i);
            String planetName = planet.getName();
            System.out.println(planetName);
            if (name.equals(planetName)) {
                planets.remove(i);
                found = 1;
                break;
            }
        }
        if (found == 0) {
            System.out.println("This planet was not found in this solar system.");
        }
    }

    public static void main(String[] args) {
        SolarGUI object = new SolarGUI();
        object.initialisePlanets();
        object.run();
    }
}
