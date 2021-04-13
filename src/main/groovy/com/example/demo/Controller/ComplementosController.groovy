package com.example.demo.Controller

import com.example.demo.Services.ComplementosServices
import com.example.demo.Utilidades.CustomRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class ComplementosController {

    @Autowired
    ComplementosServices complementosServices

    CustomRequest MyCustomsRequests = new CustomRequest();

    @RequestMapping(value="/complementos/getrentados", method = RequestMethod.POST)
    def GetAllrent(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            return complementosServices.GetListRentados(MapData.idUser as int)
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/complementos/insertAveria", method = RequestMethod.POST)
    def Insert(@RequestBody Map  data) {

        Map MapData = data

        println(MapData)

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            if(complementosServices.InsertReporte(MapData.idCar as int, MapData.descri as String, MapData.idRent as int, MapData.idUser as int)){
                return MyCustomsRequests.MessageSuccess()
            }
            else{
                return MyCustomsRequests.MessageFailed()
            }
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/complementos/listadoaveria", method = RequestMethod.POST)
    def GetAverias(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            return complementosServices.GetAverias(MapData.idUser as int)
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }

    @RequestMapping(value="/complementos/completaraveria", method = RequestMethod.POST)
    def CompletarAveria(@RequestBody Map  data) {

        Map MapData = data

        if(MapData.key == "416063c3d13d79e6e99a702fcd9cea10"){
            MapData = MapData.data;

            if(complementosServices.CompletarAveria(MapData.idadv as int)){
                return MyCustomsRequests.MessageSuccess()
            }
            else{
                return MyCustomsRequests.MessageFailed()
            }
        }
        else{
            return MyCustomsRequests.TokenNoValido();
        }
    }
}
