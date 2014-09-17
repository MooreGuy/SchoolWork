import sqlite3 as db

connection = db.connect("test.db")
cursor = connection.cursor()
cursor.execute("insert into films values('Annie Hall', '1977', 'Woody Allen')")
cursor.execute("insert into films values('Test1', 'Test2', 'Test3')")
print("Done inserting 2 values into films table")
connection.close()
