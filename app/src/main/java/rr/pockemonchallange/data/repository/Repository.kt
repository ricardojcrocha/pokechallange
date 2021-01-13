package rr.pockemonchallange.data.repository

import rr.pockemonchallange.dataimp.PokeAPI

interface Repository
{
    fun getPokeApi(): PokeAPI
}