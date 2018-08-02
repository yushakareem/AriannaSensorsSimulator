fun main(args: Array<String>) {

    //Create Objects of mySQL connector for respective DBs
    val fromDB = MySqlConnector()
    val toDB = MySqlConnector()
    val ariannaSensorsSimulator = AriannaSensorsSimulator()

    //Init the DBs
    fromDB.connectToDBorCreateNewDB("realDB24","root","nepo")
    toDB.connectToDBorCreateNewDB("openhabDB24", "root", "nepo")

    //Execute what needs to be done
    ariannaSensorsSimulator.moveData(fromDB,toDB,"Item2",1)

    //UnInit the DBs
    fromDB.disconnectFromDB()
    toDB.disconnectFromDB()
}