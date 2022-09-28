package contacts

fun main() {

    val phoneBook = PhoneBook()

    do {

        println("Enter action (add, remove, edit, count, list, exit):")

        when(readln().lowercase()) {
            "add" -> phoneBook.addContact()
            "remove" -> phoneBook.removeContact()
            "edit" -> phoneBook.editContact()
            "count" -> println("The Phone Book has ${phoneBook.countContacts()} records.")
            "list" -> phoneBook.listAllContacts()
            "exit" -> return
            else -> println("Unknown command! Please made a valid selection!")
        }

    } while (true)
}