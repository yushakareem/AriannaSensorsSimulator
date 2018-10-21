import java.sql.SQLException
import java.sql.Timestamp
import java.util.concurrent.TimeUnit

class AriannaSensorsSimulator {

    /**
     * Copies data within a table from one DB (fromDatabase) to another DB (toDatabase) with a delay
     *
     * @param fromDatabase  The <code>MySqlConnector</code> object that represents DB, from where data is to be taken.
     * @param toDatabase  The <code>MySqlConnector</code> object that represents DB, to where data is to be put.
     * @param fromTable  The <code>String</code> name of the table.
     * @param timeDelayInSeconds  The <code>Int</code> value for the number of seconds of delay.
     */

    fun copyData(fromDatabase: MySqlConnector, toDatabase: MySqlConnector, fromTable: String, toTable: String, afterDelayOf_Seconds: Int) {

        // Delaying time for _ seconds
        try {
            TimeUnit.SECONDS.sleep(afterDelayOf_Seconds.toLong())
        } catch (e: InterruptedException) {
            error("Problem in delaying")
        }

        // read
        //Integer t = fromDatabase.get_table_row_number(Table_name);
        for (i in 0..4) {
            println("Begin of Read-Write")
            val sd = fromDatabase.readSpecificRow(fromTable, i)
            while (sd.next()) {
                try {
                    println(fromDatabase.getTimestamp(sd))
                    println(fromDatabase.getBooleanValue(sd))
                    val localTimeVal = Timestamp(System.currentTimeMillis()) //latest time
                    val localBoolVal = fromDatabase.getBooleanValue(sd) //Bool value
                    toDatabase.setBooleanValue(toTable, localTimeVal, localBoolVal)
                } catch (e: SQLException) {
                    error("MySQL getting Item TimeStamp/Value problem")
                }
            }
            println("end of Read-Write")
        }
    }

    /**
     * Simulate Boolean data into a table in a DB
     *
     * @param databaseName  The <code>MySqlConnector</code> object that represents DB.
     * @param tableName  The <code>String</code> name of the table.
     * @param booleanData The <code>Boolean</code> value.
     * @param timeDelayInSeconds  The <code>Int</code> value for the number of seconds of delay.
     */
    fun simulateData(databaseName: MySqlConnector, tableName: String, booleanData: Boolean, afterDelayOf_Seconds: Int) {

        // Delaying time for _ seconds
        try {
            TimeUnit.SECONDS.sleep(afterDelayOf_Seconds.toLong())
        } catch (e: InterruptedException) {
            error("Problem in delaying")
        }

        // Set bool value into the table with latest timeStamp
        databaseName.setBooleanValue(tableName, Timestamp(System.currentTimeMillis()), booleanData)
    }

    /**
     * Simulate Integer data into a table in a DB
     *
     * @param databaseName  The <code>MySqlConnector</code> object that represents DB.
     * @param tableName  The <code>String</code> name of the table.
     * @param integerData The <code>Integer</code> value.
     * @param timeDelayInSeconds  The <code>Int</code> value for the number of seconds of delay.
     */
    fun simulateData(databaseName: MySqlConnector, tableName: String, integerData: Int, afterDelayOf_Seconds: Int) {

        // Delaying time for _ seconds
        try {
            TimeUnit.SECONDS.sleep(afterDelayOf_Seconds.toLong())
        } catch (e: InterruptedException) {
            error("Problem in delaying")
        }

        // Set bool value into the table with latest timeStamp
        databaseName.setIntegerValue(tableName, Timestamp(System.currentTimeMillis()), integerData)
    }
}