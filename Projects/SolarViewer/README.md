##SOLAR VIEWER##

This program displays the planets in our solar system and shows them moving around. 

I tried to make it as accurate as possible. The distance from the sun was in units an AU and to scale it down I said that half the width of the screen is 40 AU. The longest distance is 39.5 AU for Pluto so all the planets would fit. I then took the ratio of planet AU/40 * half width of screen. 

For the size of the planets I took the radius in 1000 km units and performed Sqrt(radius * 2). Sqrt(radius) gave too small sizes for planets so I multiplied by 2 to make them slightly bigger.

However, the Sun was so big that it would swallow up the first couple of planets so I decided to compromise and shrink the Sun down so that the planets would be visible. 

The orbital speed is very accurate since all I did was divide their speed in units km/s by 10. 

Overall, the distance from centre of sun, radius of planets and orbital speed are accurate but the size of the sun is not. If I had a larger computer screen to work with, it would be possible to make the Sun's size accurate.

##Dimensions for planets##

Name          Radius (1000km)       Distance (AU)       Orbital Speed (km/s)

Mercury : 2, 0.4, 47

Venus: 6, 0.7, 35

Earth: 6, 1, 30

Mars: 3, 1.5, 24

Jupiter: 70, 5.2, 13

Saturn: 58, 9.5, 10

Uranus: 25, 19.8, 7, 

Neptune: 26, 30, 5

Pluto: 1, 39.5, 5

