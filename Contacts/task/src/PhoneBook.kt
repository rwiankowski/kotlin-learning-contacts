package contacts

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class PhoneBook {
    private val contacts = mutableListOf<Contact>()

    fun addContact() {

        print("Enter the type (person, organization): ")
        when(readln()) {
            "person" -> addPerson()
            "organization" -> addOrganisation()
            else -> println("Please make a valid selection")
        }
    }

    fun removeContact() {
        val index = selectContact()
        if (index == -1) {
            println("No records to remove")
            return
        }
        try {
            contacts.removeAt(index)
            println("The record removed!")
        } catch (e : Exception) {
            println("Please select a valid record!")
        }
    }

    fun editContact() {
        val index = selectContact()
        if (index == -1) {
            println("No records to edit")
            return
        }

        if (contacts[index] is Person ) {
            val contact = contacts[index] as Person

            print("Select a field (name, surname, birth, gender, number): ")
            when(readln().lowercase()) {
                "name" -> contact.name = setValue("name", true)
                "surname" -> contact.surname = setValue("surname", true)
                "birth" -> contact.dateOfBirth = setValue("birth date", false)
                "gender" -> contact.gender = setValue("gender (M, F)", false)
                "number" -> contact.phoneNumber = setValue("number", false)
                else -> println("Invalid command! Please select a valid option!")
            }

        }

        if (contacts[index] is Business) {
            print("Select a field (name, address, number): ")
            val contact = contacts[index] as Business
            when(readln().lowercase()) {
                "name" -> contact.name = setValue("name", true)
                "address" -> contact.address = setValue("address", true)
                "number" -> contact.phoneNumber = setValue("number", false)
                else -> println("Invalid command! Please select a valid option!")
            }
        }

        contacts[index].updatedTimestamp = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+1"))
        println("The record updated!")
    }

    fun getContactInfo() = contacts[selectContact()].printInfo()

    fun countContacts(): Int {
        return contacts.size
    }

    private fun addPerson() {
        val name = setValue("name", true)
        val surname = setValue("surname", true)
        val birthDate = setValue("birth date", false)
        val gender = setValue("gender (M, F)", false)
        val phoneNumber = setValue("number", false)
        contacts.add(Person(name, surname, birthDate, gender, phoneNumber))
        println("The record added.")
    }

    private fun addOrganisation() {
        val name = setValue("name", true)
        val address = setValue("address", true)
        val phoneNumber = setValue("number", false)

        contacts.add(Business(name, address, phoneNumber))
        println("The record added.")

    }

    private fun setValue(valueName: String, mandatory: Boolean) : String {
        var value : String
        do {
            println("Enter the $valueName:")
            value = readln().trimEnd { it.isWhitespace() }
            if(value.isBlank()) {
                if (mandatory) println("Please enter a valid $valueName: ")
                else break
            }
        } while (value.isBlank())
        return value
    }

    private fun listAllContacts() : Boolean {
        if(contacts.size == 0) return false

        var counter = 1
        contacts.forEach { contact ->
            println("${counter++}. $contact")
        }
        return true
    }

    private fun selectContact() : Int {
        var index = -1
        if (!listAllContacts()) return -1
        print("Select a record:")
        try {
            index = readln().toInt() - 1
        } catch (e : Exception) {
            println("Please select a valid record!")
        }
        return index
    }
}
