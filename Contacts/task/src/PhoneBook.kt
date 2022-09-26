package contacts

class PhoneBook {
    val contacts = mutableListOf<Contact>()

    fun addContact() {
        var name : String
        var surname : String
        var phoneNumber : String

        println("Enter the name of the person:")
        do {
            name = readln()
            if(name.isBlank()) println("Please enter a valid name: ")
        } while (name.isBlank())

        println("Enter the surname of the person:")
        do {
            surname = readln()
            if(surname.isBlank()) println("Please enter a valid surname: ")
        } while (surname.isBlank())

        println("Enter the number:")
        do {
            phoneNumber = readln()
            if(phoneNumber.isBlank()) println("Please enter a valid number: ")
        } while (phoneNumber.isBlank())

        contacts.add(Contact(name, surname, phoneNumber))
        println("A record created!")
    }
}