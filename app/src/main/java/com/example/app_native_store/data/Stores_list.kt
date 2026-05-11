import androidx.compose.runtime.mutableStateListOf


val storeList = mutableStateListOf(
    StoreType(
        id = 1,
        name = "Glow Studio Chapinero",
        address = "Cra 13 #63-45, Chapinero, Bogotá",
        createdAt = "2024-01-15",
        photo = "https://picsum.photos/200",
        types = listOf("uñas", "pestañas"),
        phone = "3104567890",
        description = "Especialistas en uñas y pestañas con estilo moderno y delicado.",
        ownerId = "11"
    ),
    StoreType(
        id = 2,
        name = "Barber Kings Zona T",
        address = "Calle 82 #12-10, Zona T, Bogotá",
        createdAt = "2023-11-10",
        photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTU43bg9cq9PpEBrGLaqWVPr53XPHDU2hlURg&s",
        types = listOf("barberia", "cortes para dama"),
        phone = "3112345678",
        description = "Barbería premium con cortes modernos y ambiente urbano.",
        ownerId = "12"
    ),
    StoreType(
        id = 3,
        name = "Bella Look Suba",
        address = "Av Suba #100-22, Bogotá",
        createdAt = "2024-03-05",
        photo = "https://www.univision.com/api/image/x/us/estilo-de-vida/belleza/conoce-tu-ceja-ideal-segun-la-forma-de-tu-rostro",
        types = listOf("cejas", "pestañas"),
        phone = "3209876543",
        description = "Realzamos tu mirada con cejas y pestañas profesionales.",
        ownerId = "13"
    ),
    StoreType(
        id = 4,
        name = "Urban Style Barber Shop",
        address = "Cra 7 #45-18, Centro Internacional, Bogotá",
        createdAt = "2023-09-20",
        photo = "https://img.freepik.com/foto-gratis/hombre-salon-barberia-cortando-cabello-barba_1303-20953.jpg?semt=ais_hybrid&w=740&q=80",
        types = listOf("barberia"),
        phone = "3156781234",
        description = "Cortes clásicos y modernos para hombres con estilo.",
        ownerId = "14"
    ),
    StoreType(
        id = 5,
        name = "Divas Nails & Beauty",
        address = "Calle 140 #12-55, Cedritos, Bogotá",
        createdAt = "2024-02-28",
        photo = "https://img.freepik.com/psd-gratis/plantilla-redes-sociales-salon-belleza-cirugia-estetica_505751-7339.jpg?semt=ais_hybrid&w=740&q=80",
        types = listOf("uñas", "cejas"),
        phone = "3123456789",
        description = "Belleza integral con diseños de uñas únicos y cejas perfectas.",
        ownerId = "15"
    ),
    StoreType(
        id = 6,
        name = "Golden Touch Salón",
        address = "Cra 15 #93-30, Parque 93, Bogotá",
        createdAt = "2023-12-12",
        photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoBvPeDWQWQuPHcdE512GGGBx4Z22M6GTqfA&s",
        types = listOf("cortes para dama", "pestañas"),
        phone = "3187654321",
        description = "Salón elegante con servicios de alta calidad para mujeres.",
        ownerId = "16"
    ),
    StoreType(
        id = 7,
        name = "Black & White Barbería",
        address = "Calle 26 #68-20, Salitre, Bogotá",
        createdAt = "2024-04-01",
        photo = "https://static.vecteezy.com/system/resources/previews/007/619/658/non_2x/vintage-barbershop-logo-design-template-free-vector.jpg",
        types = listOf("barberia"),
        phone = "3174567890",
        description = "Barbería moderna con atención rápida y cortes precisos.",
        ownerId = "17"
    ),
    StoreType(
        id = 8,
        name = "Magic Brows Studio",
        address = "Cra 19 #120-15, Usaquén, Bogotá",
        createdAt = "2023-10-08",
        photo = "https://st2.depositphotos.com/3250997/7019/i/450/depositphotos_70190383-stock-photo-beautiful-girl-with-a-bright.jpg",
        types = listOf("cejas"),
        phone = "3145678901",
        description = "Expertos en diseño de cejas para un look natural.",
        ownerId = "18"
    ),
    StoreType(
        id = 9,
        name = "Pink Glow Beauty Bar",
        address = "Calle 109 #15-60, Bogotá",
        createdAt = "2024-01-30",
        photo = "https://st2.depositphotos.com/3250997/7019/i/450/depositphotos_70193699-stock-photo-beautiful-girl-with-a-bright.jpg",
        types = listOf("uñas", "pestañas", "cejas"),
        phone = "3198765432",
        description = "Espacio femenino con servicios completos de belleza.",
        ownerId = "19"
    ),
    StoreType(
        id = 10,
        name = "Elite Hair & Style",
        address = "Cra 11 #85-77, Bogotá",
        createdAt = "2023-08-18",
        photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6kX4sF1n-XJegVGkDaFd22Yj7a-8oRBRd7w&s",
        types = listOf("cortes para dama"),
        phone = "3162345678",
        description = "Cortes y estilos exclusivos para resaltar tu personalidad.",
        ownerId = "20"
    )
)