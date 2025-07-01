# week-10-assignment

API based home automation server

We have decided to create an small automation server suitable as the core for a home automation system, to keep things simple we will only be using the system to record temperature measurements, the Automation server (from here on referred to as just the server) will accept new temperature readings via the API and record them in a database.

The server needs to be lightweight so it can be run on a single board computer running a headless debian based Linux distribution.

We will be researching two programming languages to use on the server, Python and Java.

The server will provide an API to post new and fetch past temperature readings from a local database running on the same SBC allowing for a simple self contained solution. the SBC is expected to be running on a private network.

This repository covers the Java solution, you can find the Python solution <a href="https://github.com/Ivovis/week-10-assignment">Here</a>

---

https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html
https://www.tutorialspoint.com/sqlite/sqlite_java.htm