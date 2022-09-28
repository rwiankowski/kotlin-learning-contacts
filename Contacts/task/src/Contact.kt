package contacts

data class Contact(var name : String, var surname : String) {

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

    constructor(_name: String, _surname:String, _phoneNumber: String) : this(_name, _surname) {
        phoneNumber = _phoneNumber
    }

    override fun toString(): String {
        return "$name $surname, $phoneNumber"
    }

    fun hasNumber() : Boolean = phoneNumber != ""

}
