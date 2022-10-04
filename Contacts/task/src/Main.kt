package contacts

fun main() {

    val phoneBook = PhoneBook()

    do {

        println("\nEnter action (add, remove, edit, count, info, exit):")

        when(readln().lowercase()) {
            "add" -> phoneBook.addContact()
            "remove" -> phoneBook.removeContact()
            "edit" -> phoneBook.editContact()
            "count" -> println("The Phone Book has ${phoneBook.countContacts()} records.")
            "info" -> phoneBook.getContactInfo()
            "exit" -> return
            else -> println("Unknown command! Please made a valid selection!")
        }

    } while (true)
}