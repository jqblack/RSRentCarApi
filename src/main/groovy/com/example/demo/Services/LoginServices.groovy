package com.example.demo.Services

import com.example.demo.database.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LoginServices {

    @Autowired
    Sql sql

    List test(){
        String query = "  SELECT \n" +
                "  \"ID\",\n" +
                "  \"ID_municipio\",\n" +
                "  \"nomvreSector\"\n" +
                "FROM \n" +
                "  public.\"t_Sector\" ;"

        return sql.executeQueryAsList(query)
    }

    Boolean InsertUser(String nom, String ape, Date fecha, String user, String pass, int tipo){

        String query = "INSERT INTO \n" +
                "  public.\"t_Persona\"\n" +
                "(\n" +
                "  \"Nombre\",\n" +
                "  \"Apellido\",\n" +
                "  \"Fecha_nacimiento\"\n" +
                ")\n" +
                "VALUES (\n" +
                "  '${nom}',\n" +
                "  '${ape}',\n" +
                " to_date(to_char('${fecha}'::DATE,'dd/mm/yyyy'),'dd/mm/yyyy')\n" +
                ") RETURNING \"ID\";"
        Map MapPersona = [:];

        MapPersona = sql.executeQueryAsMap(query);

        if(MapPersona != [:]){
            query = " INSERT INTO \n" +
                    "  public.\"t_Usuario\"\n" +
                    "(\n" +
                    "  \"ID_persona\",\n" +
                    "  username,\n" +
                    "  password,\n" +
                    "  fecha,\n" +
                    "  \"ID_tipoUsuario\"\n" +
                    ")\n" +
                    "VALUES (\n" +
                    "  ${MapPersona.ID},\n" +
                    "  '${user}',\n" +
                    "  '${pass}',\n" +
                    "  now(),\n" +
                    "  ${tipo}\n" +
                    "); ";

            return sql.executeQueryInsertUpdate(query);
        }
        else{
            return false;
        }
    }
    Map VerifiedLogin(String user, String pass){
        String query = "SELECT \n" +
                "  \"ID\",\n" +
                "  \"ID_persona\",\n" +
                "  username,\n" +
                "  password,\n" +
                "  fecha,\n" +
                "  \"ID_tipoUsuario\",\n" +
                "  \"currentScore\"\n" +
                "FROM \n" +
                "  public.\"t_Usuario\" AS U WHERE \n" +
                "  U.username = '${user}' AND U.password = '${pass}' "

        Map mapa = sql.executeQueryAsMap(query)
        return mapa
    }
}
