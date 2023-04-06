import java.sql.Connection
import java.sql.DriverManager

fun main() {
    createDbTable()
}

fun createDbTable(){
    val sql = "CREATE TABLE IF NOT EXISTS `db004_1`.`test` (\n" +
            "    id int not null auto_increment primary key,\n" +
            "    name varchar(200) not null,\n" +
            "    price float not null default 0.00 check ( price >= 0.00 )\n" +
            ")"
    val conn: Connection? = DriverManager.getConnection("jdbc:mysql://localhost:3306/db004_1", "root", "")
    conn?.apply{
        createStatement()?.also {st->
            st.execute(sql)
            st.execute("DELETE FROM `test`")
            listOf(
                "Название 1" to 345.32,
                "Название 2" to 125.43,
                "Название 3" to 3464.99
            ).forEach {
                val iSql = "INSERT INTO `test` (\n" +
                        "name, price)\n" +
                        "VALUES (" +
                        "'${it.first}', ${it.second})"
                st.execute(iSql)
            }
            val sSql = "SELECT * FROM `test`"
            val res = st.executeQuery(sSql)
            while (res.next()){
                println("${res.getInt(1)}, ${res.getString(2)}, ${res.getFloat(3)}")
            }
        }
        close()
    }

}