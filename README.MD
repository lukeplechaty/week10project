# week-10-assignment

API based home automation server

We have decided to create an small automation server suitable as the core for a home automation system, to keep things simple we will only be using the system to record temperature measurements, the Automation server (from here on referred to as just the server) will accept new temperature readings via the API and record them in a database.

The server needs to be lightweight so it can be run on a single board computer running a headless debian based Linux distribution.

We will be researching two programming languages to use on the server, Python and Java.

The server will provide an API to post new and fetch past temperature readings from a local database running on the same SBC allowing for a simple self contained solution. the SBC is expected to be running on a private network.

This repository covers the Java solution, you can find the Python solution <a href="https://github.com/Ivovis/week-10-assignment">Here</a>

---

#### How to use.

Make sure you have Java V20 or greater you can find it <a href="https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html">Here</a>.

you can open a console and type vvv to find out what version you have

        java -version

Then download the release found <a href="https://github.com/lukeplechaty/week10project/releases">Here</a>.

And it is as ease as double clicking the app to run it.

If that dont work open a console and type in

        java -jar week10server.jar

it should run