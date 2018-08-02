import java.sql.SQLException
import java.sql.Timestamp
import java.util.concurrent.TimeUnit

class AriannaSensorsSimulator {

    fun moveData(fromDatabase: MySqlConnector, toDatabase: MySqlConnector, tableName: String, timeDelayInSeconds: Int) {

        // read
        //Integer t = fromDatabase.get_table_row_number(Table_name);
        for (i in 0..4) {

            println("Begin of Read-Write")

            val sd = fromDatabase.readSpecificRow(tableName, i)
            while (sd.next()) {
                try {
                    println(fromDatabase.getTimestamp(sd))
                    println(fromDatabase.getBooleanValue(sd))

                    val localTimeVal = Timestamp(System.currentTimeMillis()) //latest time
                    val localBoolVal = fromDatabase.getBooleanValue(sd) //Bool value
                    toDatabase.setBooleanValue(tableName, localTimeVal, localBoolVal!!)
                } catch (e: SQLException) {
                    println("MySQL getting Item TimeStamp/Value problem")
                }
            }

            // wait
            try {
                TimeUnit.SECONDS.sleep(timeDelayInSeconds.toLong())
            } catch (e: InterruptedException) {
                println("Problem in delaying")
            }

            println("end of Read-Write")
        }
    }
}