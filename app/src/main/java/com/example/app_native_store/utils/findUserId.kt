fun  FindUserId(id: String):UsuarioType{
    val user = UserList.find { x->x.id == id }
    return user as UsuarioType
}