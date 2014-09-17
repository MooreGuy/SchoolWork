def openAndPrintFile(fileName):
    try:
        file = open("fileName")
        for i in file:
            print(i.readline())
    except IOError:
        print("Sorry, we can't open that file")
def main():
    openAndPrintFile(input("Please enter a file name\n"))
main()
