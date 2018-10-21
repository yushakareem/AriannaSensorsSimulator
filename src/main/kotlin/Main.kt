/**
 *  NOTE TO SELF: Fix copy data delay. It's not what you want.
 */

fun main(args: Array<String>) {

    //Create Objects of mySQL connector for respective DBs
    val fromDB = MySqlConnector()
    val toDB = MySqlConnector()
    val myDB = MySqlConnector()
    val ariannaSensorsSimulator = AriannaSensorsSimulator()

    //Init the DBs
    fromDB.connectToDBorCreateNewDB("realDB24","root","nepo")
    toDB.connectToDBorCreateNewDB("AriannaDB", "root", "nepo")
//    myDB.connectToDBorCreateNewDB("openhabDB24","root","nepo")


    //Execute what needs to be done
    ariannaSensorsSimulator.copyData(fromDB,toDB,"PIR_Bathroom", "PIR_Bathroom", 1)
//    ariannaSensorsSimulator.simulateData(myDB, "Item0", 1, 2)

    //UnInit the DBs
    fromDB.disconnectFromDB()
    toDB.disconnectFromDB()
//    myDB.disconnectFromDB()
}