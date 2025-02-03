package com.tbank.learn.carsharing.mapper

import com.tbank.learn.carsharing.dto.client.ClientLightDto
import com.tbank.learn.carsharing.dto.client.ClientUpsertDto
import com.tbank.learn.carsharing.model.user.Client

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ClientMapper {
    fun fromClientUpsertDto(userCreateDto: ClientUpsertDto): Client

    fun toClientLight(user: Client): ClientLightDto

    fun toClientLightList(users: List<Client>): List<ClientLightDto>
}