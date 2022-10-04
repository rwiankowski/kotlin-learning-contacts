package contacts

import kotlinx.datetime.*

abstract class Contact {

    val createdTimestamp: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+1"))
    var updatedTimestamp: LocalDateTime = createdTimestamp

    var phoneNumber = ""
        get() = field.ifBlank { "[no number]" }

        set(value) {
            //val regex = """[+]?((([(]+\w+[)]+[ |-]?\w*)|(\w+[ |-]+[(]\w{2,}[)]))([ |-]+\w{2,})*|([ |-]?\w{2,})*)""".toRegex()
            val regex = """[+]?(([(]+\w+[)]+[ |-]?\w*)|(\w+[ |-]+[(]\w{2,}[)])|\w)?((([ |-]?\w{2,})+)|\w+)?""".toRegex()
            field = if(value.matches(regex)) value
            else {
                println("Wrong number format!")
                ""
            }
        }

    abstract fun printInfo()
}
