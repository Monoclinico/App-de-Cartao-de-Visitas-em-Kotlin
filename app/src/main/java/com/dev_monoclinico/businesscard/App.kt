package com.dev_monoclinico.businesscard

import android.app.Application
import com.dev_monoclinico.businesscard.data.AppDataBase
import com.dev_monoclinico.businesscard.data.BusinessCardRepository

class App: Application() {
    val database by lazy { AppDataBase.getDataBase(this) }
    val repository by lazy {BusinessCardRepository(database.businessDao())}

}