import sqlite3 as db

connection = db.connect("test.db")
connection.row_factory = db.Row
cursor = connection.cursor()


cursor.execute("select * from films")

rows = cursor.fetchall()

for row in rows:
    print("%s %s %s" %(row["title"], row["year"], row["director"]))

connection.close()
