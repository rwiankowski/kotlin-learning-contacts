package contacts

import kotlinx.datetime.*


class Person(var name : String, var surname : String) : Contact() {

    override val type = "PersonType"

    var gender = ""
        get() = field.ifBlank { "[no data]" }

        set(value) {
            val regex = """[M|F]?""".toRegex()
            field = if (value.matches(regex)) value
            else if(value == "[no data]") ""
            else {
                println("Bad Gender!")
                ""
            }
        }

    var dateOfBirth = ""
        get() = field.ifBlank { "[no data]" }

        set(value) {
            if (value.isBlank()) field = ""
            else if(value == "[no data]") field = ""
            else {
                try {
                    value.toLocalDate()
                    field = value
                } catch (e: Exception) {
                    println("Bad Birth Date!")
                }
            }
        }

    override fun toString(): String = "$name $surname"

    override fun searchString() = "$name + $surname + $dateOfBirth + $gender + $phoneNumber"

    constructor(_name: String, _surname: String, _dateOfBirth: String, _gender: String, _phoneNumber: String) : this(_name, _surname) {
        dateOfBirth = _dateOfBirth
        gender = _gender
        phoneNumber = _phoneNumber
    }

    override fun printInfo() {
        println("Name: $name")
        println("Surname: $surname")
        println("Birth date: $dateOfBirth")
        println("Gender: $gender")
        println("Number: $phoneNumber")
        println("Time created: $createdTimestamp")
        println("Time last edit: $updatedTimestamp")
    }

}