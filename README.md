**Storing data in the database**

**Description**

This is a project that show two approaches of storing data.

1.In filesystem - db stores only paths to the file
2.Inside databse directly - db stores file in datatype BLOB

Both have their prons and cons but main are:
* Storing in database may produce scalability problem but provides security and search capabilities
* Storing in filesystem offers faster access but has no structure and provides security concerns

**Endpoints:**
* POST /image (with body of key "image" and value)
* GET /image/{name of image}
* POST /file (with body of key "image" and value)
* GET /file/{name of image}



**Thechnologies used:
**
* Spring (Boot, Data)
* Maven
* MySQL docker container
