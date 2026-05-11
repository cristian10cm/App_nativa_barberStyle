fun EditStore(
    data:StoreType
){
    OwnerStore.id = data.id.toString()
    OwnerStore.name = data.name
    OwnerStore.address = data.address
    OwnerStore.createdAt = data.createdAt
    OwnerStore.photo = data.photo
    OwnerStore.types = data.types
    OwnerStore.phone = data.phone
    OwnerStore.description = data.description
    OwnerStore.ownerId = data.ownerId
}