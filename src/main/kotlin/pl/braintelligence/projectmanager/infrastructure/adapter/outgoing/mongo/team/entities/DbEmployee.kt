import pl.braintelligence.projectmanager.core.team.domain.values.Employee
import pl.braintelligence.projectmanager.core.team.domain.values.JobPosition

data class DbEmployee(
        private val firstName: String,
        private val lastName: String,
        private val jobPosition: String
) {
    companion object {
        fun toDbEmployee(employee: List<Employee>): List<DbEmployee> =
                employee.map { toDbEmployee(it) }

        private fun toDbEmployee(employee: Employee): DbEmployee =
                DbEmployee(
                        employee.firstName,
                        employee.lastName,
                        employee.jobPosition.toString()
                )

        fun toEmployee(dbEmployee: DbEmployee): Employee =

                Employee(
                        dbEmployee.firstName,
                        dbEmployee.lastName,
                        JobPosition.valueOf(dbEmployee.jobPosition)
                )
    }
}
