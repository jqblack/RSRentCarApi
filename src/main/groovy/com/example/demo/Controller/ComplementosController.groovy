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
}
