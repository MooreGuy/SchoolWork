import sqlite3 as db

connection = db.connect("test.db")
cursor = connection.cursor()
cursor.execute("create table films(title text, year text, director text)")
print("Table created")

