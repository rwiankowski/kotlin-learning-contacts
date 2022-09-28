package contacts

class PhoneBook {
    private val contacts = mutableListOf<Contact>()

    fun addContact() {
        val name = setName()
        val surname = setSurname()
        val phoneNumber = setPhoneNumber()

        contacts.add(Contact(name, surname, phoneNumber))
        println("The record added.")
    }

    fun removeContact() {
        if(contacts.size == 0) {
            println("No records to remove!")
            return
        }

        try {
            contacts.removeAt(selectContact())
            println("The record removed!")
        } catch (e : Exception) {
            println("Please select a valid record!")
        }
    }

    fun editContact() {
        if(contacts.size == 0) {
            println("No records to edit!")
            return
        }

        val index = selectContact()

        println("Select a field (name, surname, number):")

        when(readln().lowercase()) {
            "name" -> contacts[index].name = setName()
            "surname" -> contacts[index].surname = setSurname()
            "number" -> contacts[index].phoneNumber = setPhoneNumber()
            else -> println("Invalid command! Please select a valid option!")
        }
        println("The record updated!")
    }

    fun listAllContacts() {
        if(contacts.size == 0) return
        var counter = 1
        contacts.forEach { contact ->
            println("${counter++}. $contact")
        }
    }

    fun countContacts(): Int {
        return contacts.size
    }

    private fun setName() : String {
        var name : String
        do {
            println("Enter the name of the person:")
            name = readln()
            if(name.isBlank()) println("Please enter a valid name: ")
        } while (name.isBlank())
        return name
    }

    private fun setSurname() : String {
        var surname : String
        do {
            println("Enter the surname of the person:")
            surname = readln()
            if(surname.isBlank()) println("Please enter a valid surname: ")
        } while (surname.isBlank())
        return surname
    }

    private fun setPhoneNumber() : String {
        var phoneNumber : String
        do {
            println("Enter the number:")
            phoneNumber = readln()
            if(phoneNumber.isBlank()) println("Please enter a valid number: ")
        } while (phoneNumber.isBlank())
        return phoneNumber
    }

    private fun selectContact() : Int {
        var index = 0
        listAllContacts()
        print("Select a record:")
        try {
            index = readln().toInt() - 1
        } catch (e : Exception) {
            println("Please select a valid record!")
        }
        return index
    }
}
