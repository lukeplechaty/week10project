---
marp: true
---

## Comparing two languages to make an API Server

Luke and Chris | Date: July 4, 2025

---

# Target Use

- Home Automation Server
- Data logging for a small business
- utilizing cheap small single board computers
- Raspberry Pi, ASUS Tinker Board S,Rock 64 Media Board ect.

---

# Limited Test

- Build an API to store sensor readings
- Test case single temperature reading with time
- Provide routes to retrieve all and the last reading

---

# Java

- Easy install and run
- More work to put in to make it
- 2 Libries for JSON and SQLite

---

# Python

- Packages Fast API and SQLite3
- Turns out FastAPI is Self Documenting
- Testing - sensor.js - fallback to Javascript

---

# Conclusion

- We got both languages to provide a practical API Server
- We found out way too late we were using too different table schemas
- Example projects can be found in the repositories:
  -- https://github.com/Ivovis/week-10-assignment
  -- https://github.com/lukeplechaty/week10project
  <img src="qr_link.png" width=200 float="right">
- Any Questions?
