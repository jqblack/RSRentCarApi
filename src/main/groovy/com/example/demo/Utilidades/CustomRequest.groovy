package com.example.demo.Utilidades

import groovy.json.JsonSlurper


class CustomRequest{
//    Map f_data
//    String f_key


   Map TokenNoValido(){
       Map mapa = [:]
       Map MapData = [:]
       mapa.put("key","-1");

       MapData.put("mensaje","El token que ha utilizado no es valido")

       mapa.put("data",MapData);
       return mapa;
   }

    Map MessageSuccess(){
        Map mapa = [:];
        Map MapData = [:]

        mapa.put("key","1");
        MapData.put("mensaje","Accion completada correctamente")

        mapa.put("data",MapData);
        return mapa;
    }

    Map MessageFailed(){
        Map mapa = [:];
        Map MapData = [:];

        mapa.put("key","-2");
        MapData.put("mensaje","La accion no se ha podido completar")

        mapa.put("data",MapData);
        return mapa;
    }

}
