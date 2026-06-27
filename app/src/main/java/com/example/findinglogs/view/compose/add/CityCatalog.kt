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
        City("Recife", "-8.0476,-34.8770"),
        City("Porto Alegre", "-30.0346,-51.2177"),
        City("Belém", "-1.4558,-48.5044"),
        City("Goiânia", "-16.6869,-49.2648"),
        City("São Luís", "-2.5391,-44.2829"),
        City("Maceió", "-9.6498,-35.7089"),
        City("Natal", "-5.7945,-35.2110"),
        City("Teresina", "-5.0892,-42.8019"),
        City("João Pessoa", "-7.1195,-34.8450"),
        City("Campo Grande", "-20.4697,-54.6201"),
        City("Aracaju", "-10.9472,-37.0731"),
        City("Cuiabá", "-15.6014,-56.0979"),
        City("Florianópolis", "-27.5954,-48.5480"),
        City("Vitória", "-20.3155,-40.3128"),
        City("Porto Velho", "-8.7619,-63.9039"),
        City("Macapá", "0.0349,-51.0694"),
        City("Rio Branco", "-9.9754,-67.8249"),
        City("Boa Vista", "2.8235,-60.6758"),
        City("Palmas", "-10.1842,-48.3336")
    ).sortedBy { it.name }
}