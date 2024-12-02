/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.otakuverse.model

import com.example.otakuverse.R

object Datasource {
    // Top 10 animes
    val topAnimeList : () -> MutableList<Anime> = {
        mutableListOf<Anime>(
            Anime(
                "Fullmetal Alchemist: Brotherhood",
                "fullmetal_alchemist",
                1,
                9.1F,
                "TV (64 eps)",
                "Apr 2009 - Jul 2010"
            ),
            Anime(
                "One Piece",
                "one_piece",
                2,
                8.71F,
                null,
                "Oct 1999 -",
                true
            ),
            Anime(
                "Hunter x Hunter (2011)",
                "hunter_x_hunter",
                3,
                9.04F,
                "TV (14 eps)8",
                "Oct 2011 - Sep 2014",
                true
            ),
            Anime(
                "Steins;Gate",
                "steins_gate",
                4,
                9.07F,
                "TV (24 eps)",
                "Apr 2011 - Sep 2011"
            ),
            Anime(
                "Death Note",
                "death_note",
                5,
                8.62F,
                "TV (37 eps)",
                "Oct 2006 - Jun 2007",
                true
            ),
            Anime(
                "Shingeki no Kyojin",
                "shingeki_no_kyogin",
                6,
                8.54F,
                "TV (25 eps)",
                "Apr 2013 - Sep 2013"
            ),
            Anime(
                "Naruto: Shippuden",
                "naruto_shippuden",
                7,
                8.26F,
                "TV (50 eps)0",
                "Feb 2007 - Mar 2017",
                true
            ),
            Anime(
                "Code Geass: Hangyaku no Lelouch",
                "code_geas",
                8,
                8.7F,
                "TV (25 eps)",
                "Oct 2006 - Jul 2007"
            ),
            Anime(
                "Neon Genesis Evangelion",
                "neo_genesis_evangelion",
                9,
                8.35F,
                "TV (30 eps)",
                "Oct 1995 - Mar 1996"
            ),
            Anime(
                "Kimetsu no Yaiba",
                "kimetsu_no_yaiba",
                10,
                8.49F,
                "TV (26 eps)",
                "Apr 2019 - Sep 2019",
                true
            ),
        ).apply { shuffle() }
    }

    // Genera una lista larga de elementos
    val getListXtimes : (Int) -> MutableList<Anime> = { times ->
        val list = mutableListOf<Anime>()
        for (i in 1..times) {
            list.addAll(topAnimeList())
        }
        list.shuffle()
        list
    }

    // Devuelve un elemento de la lista buscado a través del título
    val getAnimeByTitle : (String) -> Anime? = { title ->
        topAnimeList().find { it.title == title } ?: null
    }

    // Devuelve algunos animes de la lista
    val getSomeRandAnimes : (Int) -> MutableList<Anime> = { num ->
        val animes = topAnimeList()
        if(num <= animes.size) animes.subList(0, num)
        animes
    }

    // Esta función es para transformar una URL
    // Convierte la URL https://cdn.myanimelist.net/r/50x70/images/anime/1079/138100.jpg
    // En está URL => https://cdn.myanimelist.net/images/anime/1079/138100.jpg
    // MT 225 x 327
    fun transformUrl(url: String): String {
        // Reemplazamos "/r/50x70/" por un espacio vacío
        return url.replace("/r/50x70", "")
    }


    // Devuelve las imágenes
    fun getDrawableIdByName(name: String): Int {
        return when (name) {
            "fullmetal_alchemist" -> R.drawable.fullmetal_alchemist
            "one_piece" -> R.drawable.one_piece
            "hunter_x_hunter" -> R.drawable.hunter_x_hunter
            "steins_gate" -> R.drawable.steins_gate
            "death_note" -> R.drawable.death_note
            "shingeki_no_kyogin" -> R.drawable.shingeki_no_kyogin
            "naruto_shippuden" -> R.drawable.naruto_shippuden
            "code_geas" -> R.drawable.code_geas
            "neo_genesis_evangelion" -> R.drawable.neo_genesis_evangelion
            "kimetsu_no_yaiba" -> R.drawable.kimetsu_no_yaiba
            else -> R.drawable.mh_icono
        }
    }

}