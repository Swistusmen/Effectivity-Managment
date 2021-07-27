# Effectivity-Managment
First serious Kotlin app

Developed and design in purpose of learning basics of android development

<h3>App contains:</h3>

-SQLite database

-Basic connection to server (so simple, that server code will be written below this readme)

-Some basic android operations

-Reading and writing to files and managing them

<h3> Modules </h3>

Welcome page:

![alt text](https://github.com/Swistusmen/Effectivity-Managment/blob/master/screenshots/welcome_page.png)

Eisenhower Matrix (2 possible views):

![alt text](https://github.com/Swistusmen/Effectivity-Managment/blob/master/screenshots/eisenhower.png)

![alt text](https://github.com/Swistusmen/Effectivity-Managment/blob/master/screenshots/eisenhower_full.png)

Goals and Goals Insights (only goal insights screenshot):

![alt text](https://github.com/Swistusmen/Effectivity-Managment/blob/master/screenshots/goal_insights.png)

Todo List:

![alt text](https://github.com/Swistusmen/Effectivity-Managment/blob/master/screenshots/to_do_list.png)

<h3> Server code written in FastAPI- working in LAN </h3>

from fastapi import FastAPI

from fastapi.middleware.cors import CORSMiddleware

app=FastAPI()

origins=[

]

app.add_middleware(

CORSMiddleware,
    
 allow_origins=origins,
    
allow_credentials=True,

allow_methods=["*"],
    
allow_headers=["*"],
    
)

@app.get("/")

def hello_world():

print("Hello world")
    
return {"message":"hello world"}
    

@app.get("/welcome/")

def mobile_test():

print("Hello world")
    
return {"welcomeMessage":"hello world"}
 
    
   ```
   hypercorn --bind "127.0.0.1:8080" main:app --reload
   ```


