package com.pinakin.www.plantixtest.repository

import com.pinakin.www.plantixtest.model.Profile

/**
 * MOCK repository
 * In real app this repository will take datasource as input parameter in constructor.
 * I will leverage dependency injection framework like Dagger Hilt.
 */
class ProfileRepository {

    /**
     * Fake data
     * In real scenario this data will come from REST API
     * I will use retrofit for REST API consumption.
     */
    private val listOfProfile = listOf<Profile>(
        Profile(
            "1",
            "jhon",
            "doe",
            "https://picsum.photos/seed/picsum/200/300"
        ),
        Profile(
            "2",
            "mia",
            "wong",
            "https://picsum.photos/seed/picsum/200/300"
        ),
        Profile(
            "3",
            "li",
            "ang",
            "https://picsum.photos/seed/picsum/200/300"
        ),
        Profile(
            "4",
            "harry",
            "daly",
            "https://picsum.photos/seed/picsum/200/300"
        )
    )

    /**
     * Act as mock DB
     * On first call this list is empty we will update this list on first call and return.
     */
    private var profilesFromDB: List<Profile> = emptyList()

    /**
     * This function will check if data exist in to DB
     * If data is not present in to DB then it will call REST API and dump data in to DB
     * @return list of [Profile]
     */
    suspend fun getProfiles(): List<Profile> {
        if(profilesFromDB.isEmpty()){
            profilesFromDB = listOfProfile
        }
        return profilesFromDB
    }

    /**
     * This function will return [Profile] details from the profile stored on DB.
     */
    suspend fun getProfileDetail(id: String): Profile? {
        return listOfProfile.find { it.id == id }
    }
}