package com.example.demo.Services

import com.example.demo.database.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ComplementosServices {

    @Autowired
    Sql sql

    Map GetListRentados(int idUser){
        String query = "SELECT \n" +
                "  count(CC.*)AS cantidad\n" +
                "FROM \n" +
                "  public.\"t_CurrentCarrosRentados\" as CC\n" +
                "  WHERE CC.\"ID_Usuario\" = ${idUser}"

        Map Mapa = sql.executeQueryAsMap(query)


        query = "SELECT \n" +
                "  CC.\"ID_Carro\" AS idCar,\n" +
                "  CC.\"ID_Usuario\" AS idUser,\n" +
                "  CC.fecha,\n" +
                "  C.\"nombreCar\" AS nombre\n" +
                "FROM \n" +
                "  public.\"t_CurrentCarrosRentados\" AS CC\n" +
                "  INNER JOIN PUBLIC.\"t_Carro\" as C\n" +
                "  ON CC.\"ID_Carro\" = C.\"ID\" \n" +
                "  WHERE CC.\"ID_Usuario\" = ${idUser}"

        Mapa.get("listaCarros", sql.executeQueryAsList(query))

        return Mapa
    }
}
