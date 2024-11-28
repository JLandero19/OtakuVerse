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

object Datasource {

    val animeList : () -> MutableList<Anime> = {
        mutableListOf<Anime>(
            Anime(
                "Fullmetal Alchemist: Brotherhood",
                "During their decade-long quest to defeat the Demon King, the members of the hero's party—Himmel himself, the priest Heiter, the dwarf warrior Eisen, and the elven mage Frieren—forge bonds through adventures and battles, creating unforgettable precious memories for most of them. However, the time that Frieren spends with her comrades is equivalent to merely a fraction of her life, which has lasted over a thousand years. When the party disbands after their victory, Frieren casually returns to her \"usual\" routine of collecting spells across the continent. Due to her different sense of time, she seemingly holds no strong feelings toward the experiences she went through. As the years pass, Frieren gradually realizes how her days in the hero's party truly impacted her. Witnessing the deaths of two of her former companions, Frieren begins to regret having taken their presence for granted; she vows to better understand humans and create real personal connections. Although the story of that once memorable journey has long ended, a new tale is about to begin.",
                listOf("Aventura", "Drama", "Fantasía"),
                "sousou_no_frieren.webp",
                64,
                9.1F,
                1
            ),
            Anime(
                "One Piece",
                "During their decade-long quest to defeat the Demon King, the members of the hero's party—Himmel himself, the priest Heiter, the dwarf warrior Eisen, and the elven mage Frieren—forge bonds through adventures and battles, creating unforgettable precious memories for most of them. However, the time that Frieren spends with her comrades is equivalent to merely a fraction of her life, which has lasted over a thousand years. When the party disbands after their victory, Frieren casually returns to her \"usual\" routine of collecting spells across the continent. Due to her different sense of time, she seemingly holds no strong feelings toward the experiences she went through. As the years pass, Frieren gradually realizes how her days in the hero's party truly impacted her. Witnessing the deaths of two of her former companions, Frieren begins to regret having taken their presence for granted; she vows to better understand humans and create real personal connections. Although the story of that once memorable journey has long ended, a new tale is about to begin.",
                listOf("Aventura", "Drama", "Fantasía"),
                "sousou_no_frieren.webp",
                null,
                8.71F,
                2
            ),
            Anime(
                "Hunter x Hunter (2011)",
                "During their decade-long quest to defeat the Demon King, the members of the hero's party—Himmel himself, the priest Heiter, the dwarf warrior Eisen, and the elven mage Frieren—forge bonds through adventures and battles, creating unforgettable precious memories for most of them. However, the time that Frieren spends with her comrades is equivalent to merely a fraction of her life, which has lasted over a thousand years. When the party disbands after their victory, Frieren casually returns to her \"usual\" routine of collecting spells across the continent. Due to her different sense of time, she seemingly holds no strong feelings toward the experiences she went through. As the years pass, Frieren gradually realizes how her days in the hero's party truly impacted her. Witnessing the deaths of two of her former companions, Frieren begins to regret having taken their presence for granted; she vows to better understand humans and create real personal connections. Although the story of that once memorable journey has long ended, a new tale is about to begin.",
                listOf("Aventura", "Drama", "Fantasía"),
                "sousou_no_frieren.webp",
                148,
                9.04F,
                3
            ),
            Anime(
                "Steins;Gate",
                "During their decade-long quest to defeat the Demon King, the members of the hero's party—Himmel himself, the priest Heiter, the dwarf warrior Eisen, and the elven mage Frieren—forge bonds through adventures and battles, creating unforgettable precious memories for most of them. However, the time that Frieren spends with her comrades is equivalent to merely a fraction of her life, which has lasted over a thousand years. When the party disbands after their victory, Frieren casually returns to her \"usual\" routine of collecting spells across the continent. Due to her different sense of time, she seemingly holds no strong feelings toward the experiences she went through. As the years pass, Frieren gradually realizes how her days in the hero's party truly impacted her. Witnessing the deaths of two of her former companions, Frieren begins to regret having taken their presence for granted; she vows to better understand humans and create real personal connections. Although the story of that once memorable journey has long ended, a new tale is about to begin.",
                listOf("Aventura", "Drama", "Fantasía"),
                "sousou_no_frieren.webp",
                24,
                9.07F,
                4
            ),
            Anime(
                "Death Note",
                "During their decade-long quest to defeat the Demon King, the members of the hero's party—Himmel himself, the priest Heiter, the dwarf warrior Eisen, and the elven mage Frieren—forge bonds through adventures and battles, creating unforgettable precious memories for most of them. However, the time that Frieren spends with her comrades is equivalent to merely a fraction of her life, which has lasted over a thousand years. When the party disbands after their victory, Frieren casually returns to her \"usual\" routine of collecting spells across the continent. Due to her different sense of time, she seemingly holds no strong feelings toward the experiences she went through. As the years pass, Frieren gradually realizes how her days in the hero's party truly impacted her. Witnessing the deaths of two of her former companions, Frieren begins to regret having taken their presence for granted; she vows to better understand humans and create real personal connections. Although the story of that once memorable journey has long ended, a new tale is about to begin.",
                listOf("Aventura", "Drama", "Fantasía"),
                "sousou_no_frieren.webp",
                37,
                8.62F,
                5
            ),

        ).apply { shuffle() }
    }

    val getListXtimes : (Int) -> MutableList<Anime> = { times ->
        val list = mutableListOf<Anime>()
        for (i in 1..times) {
            list.addAll(animeList())
        }
        list.shuffle()
        list
    }

    val getAnimeByTitle : (String) -> Anime? = { title ->
        animeList().find { it.title == title } ?: null
    }

    val getSomeRandAnimes : (Int) -> MutableList<Anime> = { num ->
        val animes = animeList()
        if(num <= animes.size) animes.subList(0, num)
        animes
    }

//    fun getDrawableIdByName(name: String): Int {
//        return when (name) {
//            "iron_man" -> R.drawable.iron_man
//            "capitana_marvel" -> R.drawable.capitana_marvel
//            "viuda_negra" -> R.drawable.viuda_negra
//            "thor" -> R.drawable.thor
//            "spiderman" -> R.drawable.spiderman
//            "star_lord" -> R.drawable.star_lord
//            "gamora" -> R.drawable.gamora
//            "doctor_strange" -> R.drawable.doctor_strange
//            "capitan_america" -> R.drawable.capitan_america
//            "hulk" -> R.drawable.hulk
//            else -> R.drawable.mh_icono
//        }
//    }

}