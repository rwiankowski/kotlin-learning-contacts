package contacts

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.io.File
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.adapters.*

enum class ContactType {
    PersonType,
    BusinessType
}


class PhoneBook {
    private var contacts = mutableListOf<Contact>()

    fun diskData(action: String, filePath: String) {



        val jsonFile = File(filePath)
        val moshi = Moshi.Builder()
            .add(PolymorphicJsonAdapterFactory.of(Contact::class.java, "type")
                .withSubtype(Person::class.java, "PersonType")
                .withSubtype(Business::class.java, "BusinessType"))
            .add(KotlinJsonAdapterFactory())
            .build()
        val type = Types.newParameterizedType(MutableList::class.java, Contact::class.java)
        val taskListAdapter : JsonAdapter<MutableList<Contact>> = moshi.adapter(type)

        if (action.lowercase() == "save") {
            val jsonData = taskListAdapter.toJson(contacts)
            jsonFile.writeText(jsonData)
        }

        if (action.lowercase() == "load") {
            if (!jsonFile.exists()) return

            val jsonData = jsonFile.readText()
            contacts = taskListAdapter.fromJson(jsonData)!!
        }


    }
    fun addContact() {

        print("Enter the type (person, organization): ")
        when (readln()) {
            "person" -> addPerson()
            "organization" -> addOrganisation()
            else -> println("Please make a valid selection")
        }
    }

    fun listAllContacts() {
        if (contacts.size == 0) println("No records!")

        var counter = 1
        contacts.forEach { contact ->
            println("${counter++}. $contact")
        }

        println("\n[list] Enter action ([number], back, again): ")
        when (val listAction = readln()) {
            "back" -> return
            "again" -> listAllContacts()
            else -> contactActions(listAction)
        }
    }

    fun searchForContact() {

        print("Enter search query: ")
        val searchQuery = readln()
        val searchResults = contacts.filter { it.searchString().contains(searchQuery, ignoreCase = true) }

        println("Found ${searchResults.size} results:")
        searchResults.forEach { println(it.toString()) }

        println("\n[search] Enter action ([number], back, again): ")
        when (val searchAction = readln()) {
            "back" -> return
            "again" -> searchForContact()
            else -> contactActions(searchAction)
        }

    }

    fun countContacts(): Int {
        return contacts.size
    }

    private fun contactActions(input: String) {
        val choice: Int

        try {
            choice = input.toInt() - 1
            contacts[choice].printInfo()
        } catch (e: Exception) {
            println("Incorrect input!")
            return
        }

        print("\n[record] Enter action (edit, delete, menu): ")

        when (readln()) {
            "edit" -> editContact(choice)
            "delete" -> removeContact(choice)
            else -> return
        }
    }

    private fun removeContact(index: Int) {

        if (index == -1) {
            println("No records to remove")
            return
        }
        try {
            contacts.removeAt(index)
            println("The record removed!")
        } catch (e: Exception) {
            println("Please select a valid record!")
        }
    }

    private fun editContact(index: Int) {

        if (contacts[index] is Person) {
            val contact = contacts[index] as Person

            print("Select a field (name, surname, birth, gender, number): ")
            when (readln().lowercase()) {
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
            when (readln().lowercase()) {
                "name" -> contact.name = setValue("name", true)
                "address" -> contact.address = setValue("address", true)
                "number" -> contact.phoneNumber = setValue("number", false)
                else -> println("Invalid command! Please select a valid option!")
            }
        }

        contacts[index].updatedTimestamp = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+1")).toString()
        println("The record updated!")
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

    private fun setValue(valueName: String, mandatory: Boolean): String {
        var value: String
        do {
            println("Enter the $valueName:")
            value = readln().trimEnd { it.isWhitespace() }
            if (value.isBlank()) {
                if (mandatory) println("Please enter a valid $valueName: ")
                else break
            }
        } while (value.isBlank())
        return value
    }


}
