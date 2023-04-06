import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.dsl.from
import me.liuwj.ktorm.dsl.select
import me.liuwj.ktorm.schema.*

object Department: Table<Nothing>("department"){
    val id = int("id").primaryKey()
    val name = varchar("name")
    val location = varchar("location")
}

object Employees: Table<Nothing>("employee"){
    val id = int("id").primaryKey()
    val name = varchar("name")
    val job = varchar("job")
    val managerId = int("manager_id")
    val hireDate = date("hire_date")
    val salary = long("salary")
    val departmentId = int("department_id")
}

fun main() {
    val database = Database.connect("jdbc:mysql://localhost:3306/db004_2", user = "root", password = "")
    for (row in database.from(Employees).select()){
        println(row[Employees.name])
    }
}