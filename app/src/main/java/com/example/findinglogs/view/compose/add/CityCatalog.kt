package com.example.findinglogs.view.compose.add

data class City(val name: String, val latLon: String)

object CityCatalog {
    val cities = listOf(
        City("São Paulo", "-23.5505,-46.6333"),
        City("Rio de Janeiro", "-22.9068,-43.1729"),
        City("Brasília", "-15.7939,-47.8828"),
        City("Salvador", "-12.9777,-38.5016"),
        City("Fortaleza", "-3.7319,-38.5267"),
        City("Belo Horizonte", "-19.9167,-43.9345"),
        City("Manaus", "-3.1190,-60.0217"),
        City("Curitiba", "-25.4284,-49.2733"),
        City("Porto Alegre", "-30.0346,-51.2177"),
        City("Belém", "-1.4558,-48.5044"),
        City("Goiânia", "-16.6869,-49.2648"),
        City("Natal", "-5.7945,-35.2110"),
        City("João Pessoa", "-7.1195,-34.8450"),
        City("Maceió", "-9.6498,-35.7089"),
        City("Vitória", "-20.3155,-40.3128")
    )
}