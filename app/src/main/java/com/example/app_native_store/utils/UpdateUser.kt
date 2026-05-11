fun updateUser(updated: UsuarioType) {
    val index = UserList.indexOfFirst { it.id == updated.id }
    if (index != -1) {
        UserList[index] = updated
    }
}