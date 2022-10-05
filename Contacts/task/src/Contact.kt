package contacts

import kotlinx.datetime.*

abstract class Contact {
    abstract val type: String

    val createdTimestamp: String = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+1")).toString()
    var updatedTimestamp: String = createdTimestamp

    var phoneNumber = ""
        get() = field.ifBlank { "[no number]" }

        set(value) {
            //val regex = """[+]?((([(]+\w+[)]+[ |-]?\w*)|(\w+[ |-]+[(]\w{2,}[)]))([ |-]+\w{2,})*|([ |-]?\w{2,})*)""".toRegex()
            val regex = """[+]?(([(]+\w+[)]+[ |-]?\w*)|(\w+[ |-]+[(]\w{2,}[)])|\w)?((([ |-]?\w{2,})+)|\w+)?""".toRegex()
            field = if(value.matches(regex)) value
            else if(value == "[no number]") ""
            else {
                println("Wrong number format!")
                ""
            }
        }

    abstract fun printInfo()
    abstract fun searchString() : String
}
