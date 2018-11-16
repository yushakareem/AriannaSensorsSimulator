/**
 *  NOTE TO SELF: Fix copy data delay. It's not what you want.
 */

fun main(args: Array<String>) {

    //Create Objects of mySQL connector for respective DBs
    val fromDB = MySqlConnector("realDB24","root","nepo")
    val toDB = MySqlConnector("AriannaDB", "root", "nepo")
    val myDB = MySqlConnector("openhabDB24","root","nepo")
    val ariannaSensorsSimulator = AriannaSensorsSimulator()

    //Initialize the DBs
    fromDB.connectToDBorCreateNewDB()
    toDB.connectToDBorCreateNewDB()
//    myDB.connectToDBorCreateNewDB()


    //Execute what needs to be done
    ariannaSensorsSimulator.copyData(fromDB,toDB,"PIR_Bathroom", "PIR_Bathroom", 1)
//    ariannaSensorsSimulator.simulateData(myDB, "Item0", 1, 2)

    //UnInitialize the DBs
    fromDB.disconnectFromDB()
    toDB.disconnectFromDB()
//    myDB.disconnectFromDB()
}