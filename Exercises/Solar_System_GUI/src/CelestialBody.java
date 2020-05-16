public class CelestialBody {
    private String name;
    private int distanceFromSun;
    private int orbitalSpeed;
    private String color;
    private int radiusOfPlanet;
    private double angle;

    public CelestialBody(String name, int distanceFromSun, int orbitalSpeed, String color, int radiusOfPlanet) {
        this.name = name;
        this.distanceFromSun = distanceFromSun;
        this.orbitalSpeed = orbitalSpeed;
        this.color = color;
        this.radiusOfPlanet = radiusOfPlanet;
    }

    public int rawDistance() {
        return this.distanceFromSun;
    }

    public int rawRadius() {
        return this.radiusOfPlanet;
    }

    public int rawOrbitalSpeed() {
        return this.orbitalSpeed;
    }

    public String getName() {
        return this.name;
    }

    public double getDistanceFromSun() {
        if (this.distanceFromSun < 6) {
            return 50;
        }
        else if (this.distanceFromSun >= 6 && this.distanceFromSun < 8) {
            return 56;
        }
        else if (this.distanceFromSun >= 8 && this.distanceFromSun < 11) {
            return 70;
        }
        else if (this.distanceFromSun <= 11 && this.distanceFromSun < 13) {
            return 80;
        }
        else if (this.distanceFromSun >= 13 && this.distanceFromSun < 15) {
            return 95;
        }
        else if (this.distanceFromSun >= 15 && this.distanceFromSun < 18) {
            return 115;
        }
        else if (this.distanceFromSun >= 18 && this.distanceFromSun < 23) {
            return 120;
        }
        else if (this.distanceFromSun >= 23 && this.distanceFromSun < 30) {
            return 150;
        }
        else if (this.distanceFromSun >= 30 && this.distanceFromSun < 40) {
            return 170;
        }
        else if (this.distanceFromSun >= 40 && this.distanceFromSun < 50) {
            return 185;
        }
        else if (this.distanceFromSun >= 50 && this.distanceFromSun < 70) {
            return 200;
        }
        else if (this.distanceFromSun >= 70 && this.distanceFromSun < 80) {
            return 235;
        }
        else if (this.distanceFromSun >= 80 && this.distanceFromSun < 100) {
            return 250;
        }
        else if (this.distanceFromSun >= 100 && this.distanceFromSun < 120) {
            return 270;
        }
        else if (this.distanceFromSun >= 120 && this.distanceFromSun < 143) {
            return 290;
        }
        else if (this.distanceFromSun >= 143 && this.distanceFromSun < 170) {
            return 320;
        }
        else if (this.distanceFromSun >= 170 && this.distanceFromSun < 190) {
            return 330;
        }
        else if (this.distanceFromSun >= 180 && this.distanceFromSun < 210) {
            return 350;
        }
        else if (this.distanceFromSun >= 210 && this.distanceFromSun < 240) {
            return 380;
        }
        else if (this.distanceFromSun >= 240 && this.distanceFromSun < 287) {
            return 400;
        }
        else if (this.distanceFromSun >= 287 && this.distanceFromSun < 310) {
            return 440;
        }
        else if (this.distanceFromSun >= 310 && this.distanceFromSun < 350) {
            return 475;
        }
        else if (this.distanceFromSun >= 350 && this.distanceFromSun < 390) {
            return 510;
        }
        else if (this.distanceFromSun >= 390 && this.distanceFromSun < 420) {
            return 540;
        }
        else if (this.distanceFromSun >= 420 && this.distanceFromSun < 450) {
            return 570;
        }
        else if (this.distanceFromSun >= 450 && this.distanceFromSun < 490) {
            return 620;
        }
        else if (this.distanceFromSun >= 490 && this.distanceFromSun < 530) {
            return 670;
        }
        else if (this.distanceFromSun >= 530 && this.distanceFromSun < 590) {
            return 720;
        }
        else if (this.distanceFromSun >= 590) {
            return 780;
        }
        return 800;
    }

    public double getOrbitalSpeed() {
        return (this.orbitalSpeed / 10.0);
    }

    public String getColor() {
        return this.color;
    }

    public int getRadiusOfPlanet() {
        if (this.radiusOfPlanet >= 1195 && this.radiusOfPlanet < 2440) {
            return 5;
        }
        else if (this.radiusOfPlanet >= 2440 && this.radiusOfPlanet < 3396) {
            return 8;
        }
        else if (this.radiusOfPlanet >= 3396 && this.radiusOfPlanet < 6052) {
            return 11;
        }
        else if (this.radiusOfPlanet >= 6052 && this.radiusOfPlanet < 6378) {
            return 13;
        }
        else if (this.radiusOfPlanet >= 6378 && this.radiusOfPlanet < 24764) {
            return 15;
        }
        else if (this.radiusOfPlanet >= 24764 && this.radiusOfPlanet < 25559) {
            return 24;
        }
        else if (this.radiusOfPlanet >= 25559 && this.radiusOfPlanet < 60268) {
            return 34;
        }
        else if (this.radiusOfPlanet >= 60268 && this.radiusOfPlanet < 71492) {
            return 38;
        }
        return 30;
    }
}

